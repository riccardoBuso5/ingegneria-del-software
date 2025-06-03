package myAdapter;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.NoSuchElementException;

/**
 * Classe che rappresenta la view delle chiavi di una mappa. <br />
 * Come condizione particolare un set non puo' contenere piu' di un elemento con lo stesso valore. <br />
 * Implementa l'interfaccia {@link HSet} ed e' figlia della classe {@link ValueCollection}.
 */
public class KeySet extends ValueCollection implements HSet 
{
    //dati gia' contenuti in ValueCollection

    //costruttori
    
    /**
     * Costruttore crea un nuovo Set a partire da una mappa madre, 
     * il set sara' di chiavi.
     * 
     * @param parent la mappa da cui si ricavano le chiavi
     */
    protected KeySet(MapAdapter parent)
    {
        super(parent);
    }
    
    //----------------------METODI---------------------

    //Metodo size() lo stesso di ValueCollection

    //Metodo isEmpty() lo stesso di ValueCollection

    /**
     * Metodo che restituisce true se l'oggetto specificato e' contenuto nel set. <br />
     * La mappa di cui il set e' la vista puo' accettare qualsiasi tipo di oggetto, quindi non va gestita ClassCastException perche' non ci sono elementi incompatibili non gia' gestiti
     * 
     * @param o oggetto specifico da controllare
     * @return true se l'oggetto specifico e' contenuto nel set
     * @throws NullPointerException se o e' null
     */
    @Override
    public boolean contains(Object o)
    {
        return parentMap.containsKey(o);    //parentMap lancera' NullPointerException se o e' null
    }

    /**
     * Metodo che restituisce un iteratore agli elementi del set
     * 
     * @return un iteratore agli elementi del set
     */
    @Override
    public HIterator iterator()
    {
        return new SetIterator(parentMap.hash);
    }

    //Metodo toArray() lo stesso di ValueCollection ma chiavi invece che valori

    //Metodo toArray(Object[] a) lo stesso di ValueCollection ma chiavi invece che valori

    //Metodo add(Object o) lo stesso di ValueCollection 

    /**
     * Metodo che rimuove un elemento specifico dal set. <br />
     * Se il set non contiene l'elemento non fa niente. <br />
     * La mappa di cui il set e' la vista puo' accettare qualsiasi tipo di oggetto, quindi non va gestita ClassCastException perche' non ci sono elementi incompatibili non gia' gestiti
     * 
     * @param o oggetto da rimuovere, se presente
     * @return true se il set cambiata come risultato della chiamata di questo metodo
     * @throws NullPointerException se l'elemento da eliminare e' null
     */
    @Override
    public boolean remove(Object o)
    {
        if(o == null) throw new NullPointerException();
        if(!parentMap.containsKey(o))
        {
            return false;       //le la chiave o non e' contenuta nella mappa, non fa nulla
        }
        parentMap.remove(o);
        return true;
    }

    /**
     * Metodo che ritorna true se il set contiene tutti gli elementi della collezione specificata. <br />
     * La mappa di cui il set e' la vista puo' accettare qualsiasi tipo di oggetto, quindi non va gestita ClassCastException perche' non ci sono elementi incompatibili non gia' gestiti
     * 
     * 
     * @param c Collezione che si controlla sia contenuta in questo set
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
            temp = parentMap.containsKey(it.next());      //lancia NullPointerException se it.next() e' un elemento null
        }
        return temp;
    }

    //Metodo addAll(HCollection c) lo stesso di ValueCollection

    /**
     * Metodo che rimuove tutti gli elementi da questo set che sono anche contenuti dalla collezione specifica c. <br />
     * Dopo una chiamata di questo metodo il set non conterra' nessun elemento della collezione specifica c. <br />
     * La mappa di cui il set e' la vista puo' accettare qualsiasi tipo di oggetto, quindi non va gestita ClassCastException perche' non ci sono elementi incompatibili non gia' gestiti
     * 
     * @param c collezione che contiene gli elementi che si vogliono rimuovere
     * @return true se il set cambia come risultato della chiamata di questo metodo
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
            if(parentMap.containsKey(t))
            {
                remove(t);
                temp = true;
            }
        }
        return temp;
    }

    //Metodo retainAll(HCollection c) lo stesso di ValueCollection

    //Metodo clear() lo stesso di ValueCollection

    /**
     * Metodo che compara l'oggetto specificato con il set per uguaglianza. <br />
     * Due set sono uguali se sono entrambi set e contengono gli stessi elementi.
     * 
     * @param o oggetto da comparare per uguaglianza con il set
     * @return true se l'oggetto o e' uguale al set
     */
    @Override
    public boolean equals(Object o)
    {
        if(!(o instanceof HSet))
        {
            return false;
        }
        HSet temp = (HSet) o;
        return size() == temp.size() && containsAll(temp);
    }

    //Metodo hashCode() lo stesso di ValueCollection

    //----------------------CLASSI PRIVATE -------------    

    /**
     * Classe che funge da iteratore per il set delle chiavi che fa parte delle view di Map. 
     */
    private class SetIterator implements HIterator
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
        public SetIterator(Hashtable c)
        {
            
            parent = c;
            en = parent.keys();
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
         * @return il prossimo elemento del set d'interesse
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
            parent.remove(lastReturned); //ho gia' la chiave da rimuovere
        }
    }

}
