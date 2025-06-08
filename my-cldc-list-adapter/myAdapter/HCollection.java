package myAdapter;


public interface HCollection {
    /**
     * Aggiunge l'elemento specificato alla collezione.
     *
     * @param o elemento da aggiungere
     * @return {@code true} se la collezione è stata modificata
     * @throws UnsupportedOperationException se l'operazione non è supportata
     * @throws ClassCastException se la classe dell'elemento impedisce l'aggiunta (opzionale)
     * @throws NullPointerException se l'elemento è null e la collezione non supporta null (opzionale)
     * @throws IllegalArgumentException se qualche aspetto dell'elemento impedisce l'aggiunta (opzionale)
     */
    boolean add(Object o);
    boolean addAll(HCollection c);

    void clear();

    /**
     * Restituisce true se questa collezione contiene l'elemento specificato.
     * @param o elemento la cui presenza in questa collezione deve essere verificata
     * @return true se questa collezione contiene l'elemento specificato
     * @throws ClassCastException se il tipo dell'elemento specificato non è compatibile con questa collezione
     * @throws NullPointerException se l'elemento specificato è null e questa collezione non permette elementi null
     */
    boolean contains(Object o);


     /**
     * Restituisce true se questa collezione contiene l'elemento specificato.
     * @param o elemento la cui presenza in questa collezione deve essere verificata
     * @return true se questa collezione contiene l'elemento specificato
     * @throws ClassCastException se il tipo dell'elemento specificato non è compatibile con questa collezione
     * @throws NullPointerException se l'elemento specificato è null e questa collezione non permette elementi null
     */
    boolean containsAll(HCollection c);

    /**
     * Restituisce true se questa collezione è uguale all'oggetto specificato.
     * Due collezioni sono considerate uguali se contengono gli stessi elementi.
     * @param o oggetto da confrontare con questa collezione
     * @return true se questa collezione è uguale all'oggetto specificato
     */
    boolean equals(Object o);

    /**
     * Restituisce un codice hash per questa collezione.
     * Il codice hash è calcolato in base agli elementi contenuti nella collezione.
     * @return un codice hash per questa collezione
     */
    int hashCode();

    /**
     * Restituisce true se questa collezione è vuota.
     * @return true se questa collezione non contiene elementi
     */
    boolean isEmpty();  

    /**
     * Restituisce un iteratore per questa collezione.
     * L'iteratore permette di scorrere gli elementi della collezione.
     * @return un iteratore per questa collezione
     */
    HIterator iterator();

    /**
     * Rimuove tutti gli elementi da questa collezione.
     * Dopo la chiamata a questo metodo, la collezione sarà vuota.
     */
    boolean remove(Object o);

    /**
     * Rimuove tutti gli elementi specificati dalla collezione.
     * @param c collezione di elementi da rimuovere
     * @return true se questa collezione è stata modificata
     * @throws ClassCastException se il tipo di un elemento impedisce la rimozione
     * @throws NullPointerException se un elemento è null e questa collezione non supporta null
     */
    boolean removeAll(HCollection c);

    /**
     * Rimuove tutti gli elementi da questa collezione che non sono contenuti nella collezione specificata.
     * @param c collezione di elementi da mantenere
     * @return true se questa collezione è stata modificata
     * @throws ClassCastException se il tipo di un elemento impedisce la rimozione
     * @throws NullPointerException se un elemento è null e questa collezione non supporta null
     */
    boolean retainAll(HCollection c);

    /**
     * Restituisce il numero di elementi in questa collezione.
     * @return il numero di elementi in questa collezione
     */
    int size();

    /**
     * Restituisce un array contenente tutti gli elementi di questa collezione.
     * L'array è di tipo Object[].
     * @return un array contenente tutti gli elementi di questa collezione
     */
    Object[] toArray();

    /**
     * Restituisce un array contenente tutti gli elementi di questa collezione.
     * Se l'array specificato è abbastanza grande, gli elementi saranno copiati in esso.
     * Altrimenti, verrà creato un nuovo array di tipo Object[].
     * @param a l'array in cui copiare gli elementi, o null per creare un nuovo array
     * @return un array contenente tutti gli elementi di questa collezione
     * @throws ArrayStoreException se il tipo degli elementi non è compatibile con l'array specificato
     */
    Object[] toArray(Object[] a);
}



