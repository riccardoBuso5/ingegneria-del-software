package myAdapter;

/**
 * Interfaccia che rappresenta una lista, ovvero una collezione ordinata di elementi
 * che può contenere elementi duplicati. Ogni elemento in questa lista ha una posizione
 * (indice) che parte da 0.
 * <p>
 * Questa interfaccia è conforme a {@link java.util.List} di Java 1.4.2, ma utilizza
 * {@code Object} come tipo degli elementi per garantire compatibilità con versioni
 * precedenti di Java.
 * </p>
 *
 * <p>
 * I metodi definiti permettono di aggiungere, rimuovere, accedere e modificare elementi
 * in posizioni specifiche, oltre a fornire iterazione tramite {@link HListIterator}.
 * </p>
 *
 * @see HCollection
 * @see HListIterator
 */
public interface HList extends HCollection {
    /**
     * Inserisce l'elemento specificato nella posizione indicata.
     *
     * @param index posizione in cui inserire l'elemento
     * @param element elemento da inserire
     * @throws IndexOutOfBoundsException se l'indice è fuori dai limiti (index < 0 || index > size())
     * @throws UnsupportedOperationException se l'operazione non è supportata
     * @throws ClassCastException se la classe dell'elemento impedisce l'aggiunta (opzionale)
     * @throws NullPointerException se l'elemento è null e la lista non supporta elementi null (opzionale)
     * @throws IllegalArgumentException se qualche aspetto dell'elemento impedisce l'aggiunta (opzionale)
     */
    void add(int index, Object element);

    /**
     * Inserisce tutti gli elementi della collezione specificata a partire dalla posizione indicata.
     *
     * @param index posizione di inserimento iniziale
     * @param c collezione di elementi da aggiungere
     * @return {@code true} se la lista è stata modificata
     * @throws UnsupportedOperationException se l'operazione non è supportata
     * @throws ClassCastException se la classe di un elemento impedisce l'aggiunta (opzionale)
     * @throws NullPointerException se la collezione o un elemento è null e la lista non supporta null (opzionale)
     * @throws IllegalArgumentException se qualche aspetto di un elemento impedisce l'aggiunta (opzionale)
     */
    boolean addAll(int index, HCollection c);

    /**
     * Restituisce l'elemento nella posizione specificata.
     *
     * @param index indice dell'elemento da restituire
     * @return l'elemento nella posizione specificata
     * @throws IndexOutOfBoundsException se l'indice è fuori dai limiti (index < 0 || index >= size())
     */
    Object get(int index);

    /**
     * Restituisce l'indice della prima occorrenza dell'elemento specificato, o -1 se non presente.
     *
     * @param o elemento da cercare
     * @return l'indice della prima occorrenza, o -1 se non presente
     * @throws ClassCastException se il tipo dell'elemento è incompatibile (opzionale)
     * @throws NullPointerException se l'elemento è null e la lista non supporta null (opzionale)
     */
    int indexOf(Object o);

    /**
     * Restituisce l'indice dell'ultima occorrenza dell'elemento specificato, o -1 se non presente.
     *
     * @param o elemento da cercare
     * @return l'indice dell'ultima occorrenza, o -1 se non presente
     * @throws ClassCastException se il tipo dell'elemento è incompatibile (opzionale)
     * @throws NullPointerException se l'elemento è null e la lista non supporta null (opzionale)
     */
    int lastIndexOf(Object o);

    /**
     * Restituisce un iteratore bidirezionale per la lista.
     *
     * @return un {@code HListIterator} per la lista
     */
    HListIterator listIterator();

    /**
     * Restituisce un iteratore bidirezionale per la lista a partire dalla posizione specificata.
     *
     * @param index posizione iniziale dell'iteratore
     * @return un {@code HListIterator} per la lista
     * @throws IndexOutOfBoundsException se l'indice è fuori dai limiti (index < 0 || index > size())
     */
    HListIterator listIterator(int index);

    /**
     * Rimuove l'elemento nella posizione specificata e lo restituisce.
     *
     * @param index indice dell'elemento da rimuovere
     * @return l'elemento rimosso
     * @throws IndexOutOfBoundsException se l'indice è fuori dai limiti (index < 0 || index >= size())
     * @throws UnsupportedOperationException se l'operazione non è supportata
     */
    Object remove(int index);

    /**
     * Sostituisce l'elemento nella posizione specificata con l'elemento dato e restituisce l'elemento precedente.
     *
     * @param index indice dell'elemento da sostituire
     * @param element nuovo elemento
     * @return l'elemento precedentemente presente nella posizione specificata
     * @throws IndexOutOfBoundsException se l'indice è fuori dai limiti (index < 0 || index >= size())
     * @throws UnsupportedOperationException se l'operazione non è supportata
     * @throws ClassCastException se la classe dell'elemento impedisce la sostituzione (opzionale)
     * @throws NullPointerException se l'elemento è null e la lista non supporta null (opzionale)
     * @throws IllegalArgumentException se qualche aspetto dell'elemento impedisce la sostituzione (opzionale)
     */
    Object set(int index, Object element);

    /**
     * Restituisce una sottolista tra gli indici specificati (fromIndex incluso, toIndex escluso).
     *
     * @param fromIndex indice iniziale (incluso)
     * @param toIndex indice finale (escluso)
     * @return una sottolista della lista corrente
     * @throws IndexOutOfBoundsException se fromIndex o toIndex sono fuori dai limiti
     * @throws IllegalArgumentException se fromIndex > toIndex
     */
    HList subList(int fromIndex, int toIndex);
}