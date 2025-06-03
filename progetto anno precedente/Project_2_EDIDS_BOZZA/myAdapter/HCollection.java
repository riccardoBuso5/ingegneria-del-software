package myAdapter;

/**
 * Una collezione rappresenta un gruppo di oggetti, i suoi elementi.
 * Alcune collezioni permettono elementi duplicati, altri no.
 */
public interface HCollection
{
    /**
     * Metodo che restituisce il numero di elementi della collezione
     * 
     * @return numero di valori della collezione
     */
    public int size();

    /**
     * Metodo che indica se la collezione e' vuota o meno
     * 
     * @return true se la collezione e' priva di elementi
     */
    public boolean isEmpty();

    /**
     * Metodo che restituisce true se l'oggetto specificato e' contenuto nella collezione
     * 
     * @param o oggetto specifico da controllare
     * @return true se l'oggetto specifico e' contenuto nella collezione
     * @throws NullPointerException se o e' null
     * @throws ClassCastException se o e' di un tipo incompatibile con la collezione
     */
    public boolean contains(Object o) throws NullPointerException, ClassCastException;

    /**
     * Metodo che restituisce un iteratore agli elementi di questa collezione
     * 
     * @return un iteratore agli elementi della collezione
     */
    public HIterator iterator();

    /**
     * Metodo che restituisce un array contenente tutti i valori della collezione. <br />
     * L'array restituito non contiene reference ad elementi della collezione, il chiamante puo' modificarlo liberamente.
     * 
     * @return un array contenente tutti gli elementi della collezione
     */
    public Object[] toArray();

    /**
     * Metodo che restituisce un array contenente tutti gli elementi della colleazione. <br />
     * Se la collezione puo' essere contenuta nell'array a allora e' ritornata li' dentro. <br />
     * Se l'array specificato non riesce a contenere la collezione, viene allocato un nuovo array della dimensione della collezione.
     * 
     * @param a array in cui gli elementi della collezione vanno inseriti, se e' abbastanza grande
     * @return un array contenente gli elementi della collezione
     * @throws NullPointerException se l'array specificato e' nullo.
     * @throws ArrayStoreException se il tipo di a non e' un supertipo di ogni elemento del set
     */
    public Object[] toArray(Object[] a) throws NullPointerException, ArrayStoreException;

    /**
     * Metodo che si assicura che questa collezione contenga l'elemento specificato. <br />
     * Ritorna true se la collezione e' cambiata come risultato della chiamata.
     * 
     * @param o oggetto di cui ci si vuole assicurare la presenza nella collezione
     * @return true se la collezione cambia come risultato della chiamata
     * @throws UnsupportedOperationException se l'operazione non e' supportata.
     */
    public boolean add(Object o) throws UnsupportedOperationException;

    /**
     * Metodo che rimuove una singola istanza di un elemento specifico dalla collezione. <br />
     * Se la collezione non contiene l'elemento restituisce false e non modifica la collezione.
     * 
     * @param o oggetto da rimuovere, se presente
     * @return true se la collezione e' cambiata come risultato della chiamata di questo metodo
     * @throws NullPointerException se l'elemento da eliminare e' null
     * @throws ClassCastException se il tipo dell'elemento specificato e' incompatibile con gli elementi della collezione.
     */
    public boolean remove(Object o) throws NullPointerException, ClassCastException;

    /**
     * Metodo che ritorna true se la collezione contiene tutti gli elementi della collezione specificata.
     * 
     * @param c Collezione che si controlla sia contenuta in questa collezione
     * @return true se la collezione contiene tutti gli elementi di c
     * @throws NullPointerException se la collezione specificata e' null o contiene elementi null
     * @throws ClassCastException se la collezione specificata contiene elementi incompatibili con la collezione
     */
    public boolean containsAll(HCollection c) throws NullPointerException, ClassCastException;

    /**
     * Metodo che aggiunge tutti gli elementi di una specifica collezione a questa collezione. <br />
     * Se la collezione e' cambiata al seguito della chiamata di questo metodo, restituisce true, false altrimenti.
     * 
     * @param c Collezione i cui elementi si vogliono aggiungere a questa collezione
     * @return true se la collezione e' cambiata come risultato della chiamata
     * @throws UnsupportedOperationException se l'operazione non e' supportata dalla collezione
     */    
    public boolean addAll(HCollection c) throws UnsupportedOperationException;

    /**
     * Metodo che rimuove tutti gli elementi da questa collezione che sono anche contenuti dalla collezione specifica c. <br />
     * Dopo una chiamata di questo metodo la collezione non conterra' nessun elemento della collezione specifica c.
     * 
     * @param c collezione che contiene gli elementi che si vogliono rimuovere
     * @return true se la collezione cambia come risultato della chiamata di questo metodo
     * @throws NullPointerException se la collezione da confrontare e' null o contiene elementi null
     * @throws ClassCastException se uno o piu' elementi in questa collezione sono incompatibili con la collezione specificata 
     */
    public boolean removeAll(HCollection c) throws NullPointerException, ClassCastException;

    /**
     * Metodo che mantiene in questa collezione solo gli elementi che sono anche contenuti nella collezione specificata. <br />
     * Rimuove dalla collezione tutti gli elementi non presenti nella collezione specificata
     * 
     * @param c Collezione che contiene gli elementi da mantenere in questa collezione
     * @return true se questa collezione e' cambiata come risultato della chiamata di questo metodo
     * @throws NullPointerException se la collezione specificata c e' null o contiene elementi null
     * @throws ClassCastException se uno o piu' oggetti di questa collezione non sono compatibili con la collezione specificata
     */
    public boolean retainAll(HCollection c) throws NullPointerException, ClassCastException;

    /**
     * Metodo che rimuove tutti gli elementi dalla collezione. <br />
     * La collezione sara' vuota dopo la chiamata di questo metodo.
     * 
     */
    public void clear();
    
    /**
     * Metodo che compara l'oggetto specificato con la collezione per uguaglianza.
     * 
     * @param o oggetto da comparare per uguaglianza con la collezione
     * @return true se l'oggetto o e' uguale alla collezione
     */
    public boolean equals(Object o);

    /**
     * Metodo che ritorna l'hash code della collezione. <br />
     * L'hash code di una collezione e' la somma degli hash code dei suoi membri.
     * 
     * @return l'hash code della collezione
     */
    public int hashCode();
}