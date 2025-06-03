package myAdapter;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.NoSuchElementException;

/**
 * Classe che rappresenta la vista dei valori di una mappa. <br />
 * Implementa l'interfaccia {@link HCollection}. 
 */
public class ValueCollection implements HCollection
{
    //dati 
    protected MapAdapter parentMap = null;

    //costruttori
    
    /**
     * Costruttore che crea una nuova collezione a partire dalla sua mappa madre
     * 
     * @param parent mappa da cui si ricavano i valori
     */
    protected ValueCollection(MapAdapter parent)
    {
        parentMap = parent;
    }
   
    //-----------------------METODI------------------
    
    /**
     * Metodo che restituisce il numero di elementi della collezione
     * 
     * @return numero di valori della collezione
     */
    @Override
    public int size()
    {
        return parentMap.size();
    }

    /**
     * Metodo che indica se la collezione e' vuota o meno
     * 
     * @return true se la collezione e' priva di elementi
     */
    @Override
    public boolean isEmpty()
    {
        return parentMap.isEmpty();
    }

    /**
     * Metodo che restituisce true se l'oggetto specificato e' contenuto nella collezione. <br />
     * La mappa di cui la collezione e' la vista puo' accettare qualsiasi tipo di oggetto, quindi non va gestita ClassCastException perche' non ci sono elementi incompatibili non gia' gestiti
     * 
     * @param o oggetto specifico da controllare
     * @return true se l'oggetto specifico e' contenuto nella collezione
     * @throws NullPointerException se o e' null
     */
    @Override
    public boolean contains(Object o)
    {
        return parentMap.containsValue(o);
    }

    /**
     * Metodo che restituisce un iteratore agli elementi di questa collezione. <br />
     * Non esiste un ordine preciso per gli elementi letti dall'iteratore.
     * 
     * @return un iteratore agli elementi della collezione
     */
    @Override
    public HIterator iterator()
    {
        return new CollectionIterator(parentMap.hash);
    }

    /**
     * Metodo che restituisce un array contenente tutti i valori della collezione. <br />
     * La collezione nasce da una mappa che usa hashtable quindi non c'e' un ordine degli elementi. <br />
     * L'array restituito non contiene reference ad elementi della collezione, il chiamante puo' modificarlo liberamente.
     * 
     * @return un array contenente tutti gli elementi della collezione
     */
    @Override
    public Object[] toArray()
    {
        HIterator it = iterator();
        Object[] obj = new Object[parentMap.size()];
        int i = 0;
        while( it.hasNext())
        {
            obj[i] = it.next();
            i++;
        }
        return obj;
    }

    /**
     * Metodo che restituisce un array contenente tutti gli elementi della colleazione. <br />
     * Se la collezione puo' essere contenuta nell'array a allora e' ritornata li' dentro. <br />
     * Se a non riesce a contenere la collezione, viene allocato un nuovo array della dimensione della collezione.
     * 
     * @param a array in cui gli elementi della collezione vanno inseriti, se e' abbastanza grande
     * @return un array contenente gli elementi della collezione
     * @throws NullPointerException se l'array specificato e' nullo.
     * @throws ArrayStoreException se il tipo di a non e' un supertipo di ogni elemento del set
     */
    @Override
    public Object[] toArray(Object[] a)
    {
        if(a == null) throw new NullPointerException();
        Object[] temp = toArray();
        for(int i = 0; i < temp.length; i++)
        {
            if(a.getClass().isInstance(temp[i]))
            {
                throw new ArrayStoreException();
            }
        }
        if(a.length >= parentMap.size())
        {
            for(int i = 0; i < a.length; i++)
            {
                a[i] = null;
            }
            for(int i = 0; i < temp.length; i++)
            {
                a[i] = temp[i];
            }
            return a;
        }
        return temp;
    }

    /**
     * Metodo che si assicura che questa collezione contenga l'elemento specificato. <br />
     * Metodo non supportato sulle viste di una mappa. <br />
     * Ritornera' sempre UnsupportedOperatrionException.
     * 
     * @param o oggetto di cui ci si vuole assicurare la presenza nella collezione
     * @return true se la collezione cambia come risultato della chiamata
     * @throws UnsupportedOperationException visto che Map non supporta viste con questa operazione
     */
    @Override
    public boolean add(Object o)
    {
        throw new UnsupportedOperationException();
    }

    /**
     * Metodo che rimuove una singola istanza di un elemento specifico dalla collezione. <br />
     * Se la collezione non contiene l'elemento restituisce false e non modifica la collezione. <br />
     * La mappa di cui la collezione e' la vista puo' accettare qualsiasi tipo di oggetto, quindi non va gestita ClassCastException perche' non ci sono elementi incompatibili non gia' gestiti
     * 
     * @param o oggetto da rimuovere, se presente
     * @return true se la collezione e' cambiata come risultato della chiamata di questo metodo
     * @throws NullPointerException se l'elemento da eliminare e' null
     */
    @Override
    public boolean remove(Object o)
    {
        if(o == null) throw new NullPointerException();
        if(!parentMap.containsValue(o))
        {
            return false;
        }
        boolean temp = false;
        HSet s = parentMap.keySet();
        HIterator it = s.iterator();
        while(it.hasNext() && !temp)
        {
            if(parentMap.get(it.next()) == o)
            {
                it.remove();
                temp = true;
            }
        }
        return temp;
    }

    /**
     * Metodo che ritorna true se la collezione contiene tutti gli elementi della collezione specificata. <br />
     * La mappa di cui la collezione e' la vista puo' accettare qualsiasi tipo di oggetto, quindi non va gestita ClassCastException perche' non ci sono elementi incompatibili non gia' gestiti
     * 
     * @param c Collezione che si controlla sia contenuta in questa collezione
     * @return true se la collezione contiene tutti gli elementi di c
     * @throws NullPointerException se la collezione specificata e' null o contiene elementi null
     */
    @Override
    public boolean containsAll(HCollection c)
    {
        if(c == null) throw new NullPointerException();
        boolean temp = true;
        HIterator it = c.iterator();
        while( it.hasNext() && temp)
        {
            temp = parentMap.containsValue(it.next());      //lancia NullPointerException se it.next() e' un elemento null
        }
        return temp;
    }

    /**
     * Metodo che aggiunge tutti gli elementi di una specifica collezione a questa collezione. <br />
     * Metodo non supportato sulle viste dei valori. <br />
     * Ritornera' sempre UnsupportedOperationException.
     * 
     * @param c Collezione i cui elementi si vogliono aggiungere a questa collezione
     * @return true se la collezione e' cambiata come risultato della chiamata
     * @throws UnsupportedOperationException visto che Map non supporta viste con questa operazione
     */
    @Override
    public boolean addAll(HCollection c)
    {
        throw new UnsupportedOperationException();
    }

    /**
     * Metodo che rimuove tutti gli elementi da questa collezione che sono anche contenuti dalla collezione specifica c. <br />
     * Dopo una chiamata di questo metodo la collezione non conterra' nessun elemento della collezione specifica c. <br />
     * La mappa di cui la collezione e' la vista puo' accettare qualsiasi tipo di oggetto, quindi non va gestita ClassCastException perche' non ci sono elementi incompatibili non gia' gestiti
     * 
     * @param c collezione che contiene gli elementi che si vogliono rimuovere
     * @return true se la collezione cambia come risultato della chiamata di questo metodo
     * @throws NullPointerException se la collezione da confrontare e' null o contiene elementi null
     */
    @Override
    public boolean removeAll(HCollection c)
    {
        if(c == null) throw new NullPointerException();
        if(c.size() == 0) return false;

        HIterator it = c.iterator();
        boolean temp = false;
        while(it.hasNext())
        {
            Object t = it.next();
            while(parentMap.containsValue(t))
            {
                remove(t);
                temp = true;
            }
        }
        return temp;
    }

    /**
     * Metodo che mantiene in questa collezione solo gli elementi che sono anche contenuti nella collezione specificata. <br />
     * Rimuove dalla collezione tutti gli elementi non presenti nella collezione specificata
     * 
     * @param c Collezione che contiene gli elementi da mantenere in questa collezione
     * @return true se questa collezione e' cambiata come risultato della chiamata di questo metodo
     * @throws NullPointerException se la collezione specificata c e' null o contiene elementi null
     * @throws ClassCastException se uno o piu' oggetti di questa collezione non sono compatibili con la collezione specificata
     */
    @Override
    public boolean retainAll(HCollection c)
    {
        if(c == null) throw new NullPointerException();
        if(c.size() == 0) 
        {
            clear();
            return true;
        }

        boolean temp = false;
        HIterator it = iterator();
        while(it.hasNext())
        {
            if(!c.contains(it.next())) //puo' lanciare ClassCastException
            {
                it.remove();
                temp = true;
            }
        }
        return temp;

    }

    /**
     * Metodo che rimuove tutti gli elementi dalla collezione. <br />
     * La collezione sara' vuota dopo la chiamata di questo metodo.
     * 
     */
    @Override
    public void clear()
    {
        parentMap.clear();;
    }
    
    /**
     * Metodo che compara l'oggetto specificato con la collezione per uguaglianza. <br />
     * Due collezioni sono uguali se sono entrambe collezioni, hanno le stesse dimensioni e contengono gli stessi elementi.
     * 
     * @param o oggetto da comparare per uguaglianza con la collezione
     * @return true se l'oggetto o e' uguale alla collezione
     */
    @Override
    public boolean equals(Object o)
    {
        if(!(o instanceof HCollection) || (o instanceof HSet))      //una collezione e' uguale ad un altra solo se sono entrambe collezioni e non si tratti di un set
        {
            return false;
        }
        HCollection c = (HCollection) o;
        return size() == c.size() && containsAll(c) && c.containsAll(this);     
    }

    /**
     * Metodo che ritorna l'hash code della collezione. <br />
     * L'hash code di una collezione e' la somma degli hash code dei suoi membri.
     * 
     * @return l'hash code della collezione
     */
    @Override
    public int hashCode()
    {
        int hashCode = 0;
        HIterator it = iterator();
        while(it.hasNext())
        {
            hashCode += it.next().hashCode();
        }
        return hashCode;
    }

    //--------------------CLASSI PRIVATE----------------

    /**
     * Classe che funge da iteratore per la vista dei valori di MapAdapter. 
     */
    private class CollectionIterator implements HIterator
    {
        //dati privati
        protected Hashtable parent;
        private Enumeration en;
        private int state; //stato dell'iteratore: remove non puo' essere chiamata se state == 0
        private Object lastReturned;

        /**
         * Costruttore che inizializza l'iteratore sul primo elemento della hashtable
         * 
         * @param c hashtable su cui operera' l'iteratore
         */
        public CollectionIterator(Hashtable c)
        {
            
            parent = c;
            en = parent.elements();
            state = 0;
        }

        /**
         * Metodo che determina se ci sono ancora elementi da percorrere dall'iteratore
         * 
         * @return true se l'iteratore ha ulteriori elementi
         */
        @Override
        public boolean hasNext()
        {
            return en.hasMoreElements();
        }

        /**
         * Metodo che restituisce il prossimo elemento nell'iterazione
         * 
         * @return il prossimo elemento della collezione d'interesse
         * @throws java.util.NoSuchElementException se l'iteratore non ha ulteriori elementi
         */
        @Override
        public Object next()
        {
            if(!hasNext()) throw new NoSuchElementException();

            state = 1;
            lastReturned = en.nextElement();
            return lastReturned;
        }

        /**
         * Metodo che rimuove dalla collezione l'ultimo elemento visto dall'iteratore
         * 
         * @throws IllegalStateException se il metodo next() non e' stato ancora chiamato o il metodo remove() e' stato gia' chiamato dall'ultima chiamata
         */
        @Override
        public void remove()
        {
            if(state == 0) throw new IllegalStateException();

            state = 0; //questo metodo puo' essere chiamato solo una volta per ogni next()
            boolean temp = false;
            Enumeration keys = parent.keys();       //devo cercare la chiave a cui il valore da rimuovere appartiene
            while(keys.hasMoreElements() && !temp)
            {
                Object keyOfValue = keys.nextElement();
                if( parent.get(keyOfValue) == lastReturned) //se il valore a cui la chiave punta 
                {                                           //e' lo stesso dell'elemento da rimuovere, allora rimuovo
                    parent.remove(keyOfValue);
                    temp = true;
                }
            }
        }
    }
}
