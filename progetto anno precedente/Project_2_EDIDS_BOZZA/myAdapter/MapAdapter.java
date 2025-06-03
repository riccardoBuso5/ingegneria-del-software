package myAdapter;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.NoSuchElementException;

/**
 * Classe creata per adattare una Hashtable ad una mappa, contiene i metodi di una mappa aggiornati alla versione 1.4.2 di jdk. <br />
 * Questa mappa, implementata come adapter per una hashtable, puo' contenere oggetti di qualsiasi tipo Object. <br />
 * Implementa l'interfaccia {@link HMap}.
 */
public class MapAdapter implements HMap
{
    //dati 
    protected Hashtable hash;

    /**
     * Costruttore che crea una nuova mappa vuota
     */
    public MapAdapter()
    {
        hash = new Hashtable();
    }

    /**
     * Costruttore che crea una mappa con le stessa mappatura di un'altra mappa data come parametro. <br />
     * Costruttore copia della mappa.
     * 
     * @param mapAd la mappa da copiare.
     */
    public MapAdapter(HMap mapAd)
    {
        hash = new Hashtable();
        putAll(mapAd);
    }

    /**
     * Metodo che ritorna il numero delle mappature, coppie chiave-valore
     * 
     * @return numero di entry nella mappa
     */
    @Override
    public int size()
    {
        return hash.size();
    }

    /**
     * Metodo che determina se la mappa e' vuota o no.
     * 
     * @return true se la mappa e' vuota
     */
    @Override
    public boolean isEmpty()
    {
        return hash.isEmpty();
    }

    /**
     * Metodo che stabilisce se la mappa contiene una coppia chiave-valore con la chiave specificata. <br />
     * Questa mappa puo' contenere oggetti di qualsiasi tipo, quindi non lancera' mai l'eccezione ClassCastException.
     * 
     * @param key la chiave specificata
     * @return true se la chiave specificata e' una chiave della mappa
     * @throws NullPointerException se la chiave e' nulla
     */
    @Override
    public boolean containsKey(Object key)
    {
        if(key == null) throw new NullPointerException();

        return hash.containsKey(key);
    }

    /**
     * Metodo che stabilisce se la mappa contiene una coppia chiave-valore con il valore specificato. <br />
     * Questa mappa puo' contenere oggetti di qualsiasi tipo, quindi non lancera' mai l'eccezione ClassCastException.
     * 
     * @param value il valore specificato
     * @return true se il valore specificato e' un valore della mappa
     * @throws NullPointerException se il valore e' nullo
     */
    @Override
    public boolean containsValue(Object value)
    {
        if(value == null) throw new NullPointerException();

        return hash.contains(value);
    }

    /**
     * Metodo che data una chiave restituisce il valore ad essa associata. <br />
     * Questa mappa puo' contenere oggetti di qualsiasi tipo, quindi non lancera' mai l'eccezione ClassCastException
     * 
     * @param key la chiave il cui valore si sta cercando
     * @return il valore associato alla chiave, null se la chiave non e' mappata nella mappa
     * @throws NullPointerException se la chiave e' nulla
     */
    @Override
    public Object get(Object key)
    {
        if(key == null) throw new NullPointerException();

        return hash.get(key);
    }
    
    /**
     * Metodo che mappa la specifica chiave al valore specifico. <br />
     * Se la mappa precedentemente conteneva un valore associato a quella chiave allora il vecchio valore viene restituito. <br />
     * Questa mappa puo' contenere oggetti di qualsiasi tipo, quindi non lancera' mai l'eccezione ClassCastException.
     * 
     * @param key la chiave a cui il valore deve essere associato
     * @param value il valore da associare alla chiave
     * @return il precedente valore associato alla chiave specifica, null se non era associato nessun valore
     * @throws NullPointerException se la chiave o il valore sono null.
     */
    @Override
    public Object put(Object key, Object value)
    {
        if(key == null || value == null) throw new NullPointerException();

        return hash.put(key, value);
    }

    /**
     * Metodo che rimuove la coppia chiave-valore data la chiave specifica dalla mappa. <br />
     * Questa mappa puo' contenere oggetti di qualsiasi tipo, quindi non lancera' mai l'eccezione ClassCastException.
     * 
     * @param key la chiave che si vuole rimuovere
     * @return il valore precedentemente associato alla chiave rimossa o null se la chiave non era presente
     * @throws NullPointerException se la chiave e' null
     */
    @Override
    public Object remove(Object key)
    {
        if(key == null) throw new NullPointerException();

        return hash.remove(key);
    }

    /**
     * Metodo che copia tutti gli elementi di una mappa in questa mappa. <br />
     * Effetto equivalente a chiamare un put(k, v) in questa mappa per ogni entry della mappa specifica. <br />
     * Questa mappa puo' contenere oggetti di qualsiasi tipo, quindi non lancera' mai l'eccezione ClassCastException.
     * 
     * @param t mappa che si vuole copiare
     * @throws NullPointerException se la mappa da copiare e' null o contiene elementi null
     */
    @Override
    public void putAll(HMap t)
    {
        if(t == null) throw new NullPointerException();     //se la mappa e' un oggetto null allora non posso fare questa operazione
        if(t.equals(this))
        {
            return;
        }
        
        HIterator it = t.keySet().iterator();
        while(it.hasNext())
        {
            Object k = it.next();
            if(k == null || t.get(k) == null)
            {
                throw new NullPointerException();
            } 
            hash.put(k, t.get(k));
        }
    }

    /**
     * Metodo che svuota la mappa da tutte le coppie chiave-valore
     * 
     */
    @Override
    public void clear()
    {
        hash.clear();
    }

    /**
     * Metodo che restituisce una vista delle chiavi contenute in questa mappa. <br />
     * Cambiamenti nella mappa sono riflessi nel set e vice-versa. <br />
     * Il set supporta la rimozione degli elementi, che rimuove le mappatura dalla mappa, ma non le operazioni di aggiunta di elementi.
     * 
     * @return un set vista delle chiavi contenute in questa mappa
     */
    @Override
    public HSet keySet()
    {
        return new KeySet(this);
    }

    /**
     * Metodo che restituisce una vista dei valori contenuti in questa mappa. <br />
     * Cambiamenti nella mappa sono riflessi nella collezione e vice-versa. <br />
     * La collezione supporta la rimozione degli elementi, che rimuove le mappatura dalla mappa, ma non le operazioni di aggiunta di elementi.
     * 
     * @return una collezione vista dei valori contenuti in questa mappa
     */
    @Override
    public HCollection values()
    {
        return new ValueCollection(this);
    }

    /**
     * Metodo che ritorna un set vista delle coppie contenute in questa mappa. <br />
     * Cambiamenti nella mappa sono riflessi nel set delle Entry e vice-versa. <br />
     * Il set supporta la rimozione degli elementi, che rimuove le mappatura dalla mappa, ma non le operazioni di aggiunta di elementi.
     * 
     * @return un set vista delle coppie di questa mappa
     */
    @Override
    public HSet entrySet()
    {
        return new EntrySet(this);
    }
    
    /**
     * Metodo per comparare due mappe. Ritorna true se l'oggetto da comparare e' 
     * anch'esso una mappa e le due mappe hanno lo stesso mapping.
     * 
     * @param o l'ogetto da comparare
     * @return true se le due mappe sono uguali
     */
    @Override
    public boolean equals(Object o)
    {
        if(!( o instanceof HMap))
        {
            return false;
        }
        HMap temp = (HMap) o;
        return entrySet().equals(temp.entrySet());
    }

    /**
     * Metodo che restituisce l'hash code della mappa. <br />
     * L'hash code della mappa e' la somma degli hashCodes di ogni coppia nella vista entrySet.
     * 
     * @return l'hash code della mappa
     */
    @Override
    public int hashCode()
    {
        return entrySet().hashCode();
    }

    //----CLASSI PRIVATE -----

    /**
     * Classe privata Entry che rappresenta la coppia key-value di una mappa. <br />
     * Le Entry possono essere formate da qualsiasi tipo di oggetto perche' nascono come coppie chiave-valore di una hashtable. <br />
     * Implementa l'interfaccia {@link HEntry}
    */
     private class Entry implements HEntry
    {
        //Dati privati
        private Object key;
        private MapAdapter parent;

        /**
         * Costruttore che crea una coppia chiave-valore per la view della mappa
         * 
         * @param p mappa che contiene la coppia
         * @param k chiave della coppia
         */
        public Entry(MapAdapter p, Object k)
        {
            key = k;
            parent = p;
        }

        //metodi di Map.Entry di jdk 1.4.2
        
        /**
         * Metodo per ottenere la chiave di una coppia
         * 
         * @return la chiave della coppia
         */
        @Override
        public Object getKey()
        {
            return key;
        }
        
        /**
         * Metodo per ottenere il valore di una coppia. <br />
         * Se la mappatura e' stata rimossa dalla mappa madre allora restituisce null
         * 
         * @return il valore della coppia
         */
        @Override
        public Object getValue()
        {
            return parent.get(key);
        }
        
        /**
         * Metodo per sostituire il valore associato ad una chiave. <br />
         * Le Entry possono essere formate da qualsiasi tipo di oggetto perche' nascono come coppie chiave-valore di una hashtable, quindi non serve gestire l'eccezione ClassCastException
         * 
         * @param v nuovo valore da inserire nella coppia
         * @return la vecchia variabile contenuta nella coppia
         * @throws NullPointerException se il valore nuovo e' null, non accettabile nella mappa
         */
        @Override
        public Object setValue(Object v)
        {
            if(v == null) throw new NullPointerException();
            
            return parent.put(key, v);
        }

        /**
         * Comparo gli oggetti per uguaglianza. <br />
         * Ritorna vero se l'oggetto comparato e' esso stesso una coppia chiave-valore e le due rappresentano la stessa mappatura.
         * 
         * @param o l'oggetto da comparare con questa coppia chiave-valore
         * @return true se l'oggetto e' uguale a questo ingresso, usando l'hashcode
         */
        @Override
        public boolean equals(Object o)
        {

            if(!(this.getClass() == o.getClass()))
            {
                return false;
            }
            HEntry e = (HEntry) o;
            return getKey().equals(e.getKey()) && getValue().equals(e.getValue());
        }

        /**
         * Metodo che ritorna l'hash code per questa coppia
         * 
         * @return l'hash code per questa coppia chiave-valore di mappa
         */
        @Override
        public int hashCode()
        {
            return (getKey() == null? 0 : getKey().hashCode()) ^ 
                (getValue() == null? 0 : getValue().hashCode());
        }
    }

        
    /**
     * Classe che rappresenta la view delle coppie chiave-valore Entry di una mappa. <br />
     * Come set non puo' contentere due elementi con uguale valore. <br />
     * Implementa l'interfaccia {@link HSet} ed e' figlia della classe {@link ValueCollection}.
     */
    private class EntrySet extends ValueCollection implements HSet
    {
        
        //dati gia' contenuti in ValueCollection

        //costruttori

        /**
         * Costruttore crea un nuovo Set a partire da una mappa madre,
         *  il set sara' di coppie chiave-valore.
         * 
         * @param parent la mappa da cui si ricavano i valori
         */
        public EntrySet(MapAdapter parent)
        {
            super(parent);
        }
        
        //----------------------METODI---------------------
        
        //Metodo size() lo stesso di ValueCollection

        //Metodo isEmpty() lo stesso di ValueCollection

        /**
         * Metodo che restituisce true se l'oggetto specificato e' contenuto nel set. <br />
         * Questo set e' la vista delle coppie chiave-valore di una mappa e quindi puo' accettare solo elementi Entry
         * 
         * @param o oggetto specifico da controllare
         * @return true se l'oggetto specifico e' contenuto nel set
         * @throws NullPointerException se o e' null
         * @throws ClassCastException se l'elemento specificato non e' di tipo Entry
         */
        @Override
        public boolean contains(Object o)
        {
            if(o == null) throw new NullPointerException();
            if(o.getClass().getName() != (MapAdapter.Entry.class.getName()))
            {
                throw new ClassCastException(); 
            }
            HEntry temp = (HEntry)o;
            Object key = temp.getKey();
            Object value = temp.getValue();
            return parentMap.containsKey(key) && parentMap.get(key).equals(value);
        }

        /**
         * Metodo che restituisce un iteratore agli elementi del set
         * 
         * @return un iteratore agli elementi del set
         */
        @Override
        public HIterator iterator()
        {
            return new EntrySetIterator(parentMap);
        }

        //Metodo toArray() lo stesso di ValueCollection ma Entry invece che valori

        //Metodo toArray(Object[] a) lo stesso di ValueCollection ma Entry invece che valori

        //Metodo add(Object o) lo stesso di ValueCollection

        /**
         * Metodo che rimuove un elemento specifico dal set. Se il set non contiene l'elemento non fa niente. <br />
         * Questo set e' la vista delle coppie chiave-valore di una mappa e quindi puo' accettare solo elementi Entry.
         * 
         * @param o oggetto da rimuovere, se presente
         * @return true se il set cambiata come risultato della chiamata di questo metodo
         * @throws NullPointerException se l'elemento da eliminare e' null
         * @throws ClassCastException se l'elemento specificato non e' di tipo Entry
         */
        @Override
        public boolean remove(Object o)
        {
            if(o == null) throw new NullPointerException();
            if(o.getClass().getName() != (MapAdapter.Entry.class.getName()))
            {
                throw new ClassCastException();
            }
            HEntry temp = (HEntry)o;
            Object key = temp.getKey();
            if(contains(o))
            {
                return parentMap.remove(key) != null;
            }
            return false;
        }

        /**
         * Metodo che ritorna true se il set contiene tutti gli elementi del set specificato. <br />
         * Questo set e' la vista delle coppie chiave-valore di una mappa e quindi puo' accettare solo elementi Entry.
         * 
         * @param c Collezione che si controlla sia contenuta in questo set
         * @return true se la collezione contiene tutti gli elementi di c
         * @throws NullPointerException se la collezione specificata e' null o contiene elementi null
         * @throws ClassCastException se la collezione HCollection contiene elementi che non sono Entry
         */
        @Override
        public boolean containsAll(HCollection c)
        {
            if(c == null) throw new NullPointerException(); 

            boolean temp = true;
            HIterator it = c.iterator();
            while( it.hasNext() && temp)
            {
                Object sample = it.next();
                if(sample.getClass().getName() != (MapAdapter.Entry.class.getName()))
                {
                    throw new ClassCastException();
                }
                HEntry e = (HEntry) sample;
                temp = contains(e);      //lancia NullPointerException se it.next() e' un elemento null
            }
            return temp;
        }

        //Metodo addAll(HCollection c) lo stesso di ValueCollection

        /**
         * Metodo che rimuove tutti gli elementi da questo set che sono anche contenuti dalla collezione specifica c. <br />
         * Dopo una chiamata di questo metodo il set non conterra' nessun elemento della collezione specifica c. <br />
         * Questo set e' la vista delle coppie chiave-valore di una mappa e quindi puo' accettare solo elementi Entry.
         * 
         * 
         * @param c collezione che contiene gli elementi che si vogliono rimuovere
         * @return true se il set cambia come risultato della chiamata di questo metodo
         * @throws NullPointerException se la collezione da confrontare e' null o contiene elementi null
         * @throws ClassCastException se la collezione HCollection contiene elementi che non sono Entry
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
                if(t.getClass().getName() != (MapAdapter.Entry.class.getName()))
                {
                    throw new ClassCastException();
                }
                HEntry e = (HEntry) t;
                if(contains(e))
                {
                    remove(e);
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
            if(o == null) return false;
            if(!(o instanceof HSet))
            {
                return false;
            }
            HSet temp = (HSet) o;
            return size() == temp.size() && temp.containsAll(this);
        }

        //Metodo hashCode() lo stesso di ValueCollection


        //----------------------CLASSI PRIVATE -------------    

        /**
         * Classe che funge da iteratore per il set delle Entry che fa parte delle view di Map. 
         */
        private class EntrySetIterator implements HIterator
        {
            //dati privati
            protected MapAdapter parent;
            private Enumeration en;
            private int state; //stato dell'iteratore: remove non puo' essere chiamata se state == 0
            private Entry lastReturned;

            /**
             * Costruttore che inizializza l'iteratore sul primo elemento della hashtable
             * 
             * @param c hashtable su cui operera' l'iteratore
             */
            public EntrySetIterator(MapAdapter c)
            {
                
                parent = c;
                en = parent.hash.keys();
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
                Object key = en.nextElement();
                lastReturned = new Entry(parent, key) ;
                return lastReturned;
            }

            /**
             * Metodo che rimuove dal set l'ultimo elemento visto dall'iteratore
             * 
             * @throws IllegalStateException se il metodo next() non e' stato ancora chiamato o il metodo remove() e' stato gia' chiamato dall'ultima chiamata
             */
            @Override
            public void remove()
            {
                if(state == 0) throw new IllegalStateException();

                state = 0; //questo metodo puo' essere chiamato solo una volta per ogni next()
                parent.remove(lastReturned.getKey()); 
            }
        }



    }

}
