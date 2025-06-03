package myAdapter;

/**
 * Un oggetto che associa chiavi a valori. <br />
 * Una mappa non puo' contenere chiavi duplicate, ogni chiave puo' essere associata al massimo ad un valore. <br />
 * L'interfaccia HMap fornisce tre viste che guardano i contenuti della mappa come un set di chiavi, una collezione di valori, un set di coppie chiave-valore.
 */
public interface HMap 
{
    /**
     * Metodo che ritorna il numero delle mappature, coppie chiave-valore
     * 
     * @return numero di entry nella mappa
     */
    public int size();

    /**
     * Metodo che determina se la mappa e' vuota o no.
     * 
     * @return true se la mappa e' vuota
     */
    public boolean isEmpty();

    /**
     * Metodo che stabilisce se la mappa contiene una coppia chiave-valore con la chiave specificata. <br />
     * Questa mappa puo' contenere oggetti di qualsiasi tipo, quindi non lancera' mai l'eccezione ClassCastException
     * 
     * @param key la chiave specificata
     * @return true se la chiave specificata e' una chiave della mappa
     * @throws NullPointerException se la chiave e' nulla
     */
    public boolean containsKey(Object key) throws NullPointerException;

    /**
     * Metodo che stabilisce se la mappa contiene una coppia chiave-valore con il valore specificato. <br />
     * Questa mappa puo' contenere oggetti di qualsiasi tipo, quindi non lancera' mai l'eccezione ClassCastException
     * 
     * @param value il valore specificato
     * @return true se il valore specificato e' un valore della mappa
     * @throws NullPointerException se il valore e' nullo
     */
    public boolean containsValue(Object value) throws NullPointerException;

    /**
     * Metodo che data una chiave restituisce il valore ad essa associata. <br />
     * Questa mappa puo' contenere oggetti di qualsiasi tipo, quindi non lancera' mai l'eccezione ClassCastException
     * 
     * @param key la chiave il cui valore si sta cercando
     * @return il valore associato alla chiave, null se la chiave non e' mappata nella mappa
     * @throws NullPointerException se la chiave e' nulla
     */
    public Object get(Object key) throws NullPointerException;

    /**
     * Metodo che mappa la specifica chiave al valore specifico.<br />
     * Se la mappa precedentemente conteneva un valore associato a quella chiave allora il vecchio valore viene restituito. <br />
     * Questa mappa puo' contenere oggetti di qualsiasi tipo, quindi non lancera' mai l'eccezione ClassCastException
     * 
     * @param key la chiave a cui il valore deve essere associato
     * @param value il valore da associare alla chiave
     * @return il precedente valore associato alla chiave specifica, null se non era associato nessun valore
     * @throws NullPointerException se la chiave o il valore sono null.
     */
    public Object put(Object key, Object value) throws NullPointerException;

    /**
     * Metodo che rimuove la coppia chiave-valore data la chiave specifica dalla mappa. <br />
     * Questa mappa puo' contenere oggetti di qualsiasi tipo, quindi non lancera' mai l'eccezione ClassCastException
     * 
     * @param key la chiave che si vuole rimuovere
     * @return il valore precedentemente associato alla chiave rimossa o null se la chiave non era presente
     * @throws NullPointerException se la chiave e' null
     */
    public Object remove(Object key) throws NullPointerException;

    /**
     * Metodo che copia tutti gli elementi di una mappa in questa mappa. <br />
     * Effetto equivalente a chiamare un put(k, v) in questa mappa per ogni entry della mappa specifica. <br />
     * Questa mappa puo' contenere oggetti di qualsiasi tipo, quindi non lancera' mai l'eccezione ClassCastException
     * 
     * @param t mappa che si vuole copiare
     * @throws NullPointerException se la mappa da copiare e' null o contiene elementi null
     */
    public void putAll(HMap t) throws NullPointerException;

    /**
     * Metodo che svuota la mappa da tutte le coppie chiave-valore
     * 
     */
    public void clear();

    /**
     * Metodo che restituisce una vista delle chiavi contenute in questa mappa. <br />
     * Cambiamenti nella mappa sono riflessi nel set e vice-versa. <br />
     * Il set supporta la rimozione degli elementi, che rimuove le mappatura dalla mappa, ma non le operazioni di aggiunta di elementi.
     * 
     * @return un set vista delle chiavi contenute in questa mappa
     */
    public HSet keySet();

    /**
     * Metodo che restituisce una vista dei valori contenuti in questa mappa. <br />
     * Cambiamenti nella mappa sono riflessi nella collezione e vice-versa. <br />
     * La collezione supporta la rimozione degli elementi, che rimuove le mappatura dalla mappa, ma non le operazioni di aggiunta di elementi.
     * 
     * @return una collezione vista dei valori contenuti in questa mappa
     */
    public HCollection values();

    /**
     * Metodo che ritorna un set vista delle coppie contenute in questa mappa. <br />
     * Cambiamenti nella mappa sono riflessi nel set delle Entry e vice-versa. <br />
     * Il set supporta la rimozione degli elementi, che rimuove le mappatura dalla mappa, ma non le operazioni di aggiunta di elementi.
     * 
     * @return un set vista delle coppie di questa mappa
     */
    public HSet entrySet();

    /**
     * Metodo per comparare due mappe. Ritorna true se l'oggetto da comparare e' 
     * anch'esso una mappa e le due mappe hanno lo stesso mapping.
     * 
     * @param o l'ogetto da comparare
     * @return true se le due mappe sono uguali
     */
    public boolean equals(Object o);

    /**
     * Metodo che restituisce l'hash code della mappa. <br />
     * L'hash code della mappa e' la somma degli hashCodes di ogni coppia nella vista entrySet
     * 
     * @return l'hash code della mappa
     */
    public int hashCode();
}
