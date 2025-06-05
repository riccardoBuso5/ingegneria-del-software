package myAdapter;

/**
 * Un iteratore per liste che permette al programmatore di attraversare la lista in entrambe le direzioni,
 * modificarla durante l'iterazione e ottenere la posizione corrente dell'iteratore nella lista.
 * <p>
 * Questa interfaccia estende {@link HIterator} e fornisce metodi aggiuntivi per supportare
 * l'iterazione bidirezionale e la modifica della lista sottostante.
 * </p>
 *
 * @see HIterator
 */
public interface HListIterator extends HIterator {
    /**
     * Restituisce {@code true} se ci sono altri elementi in avanti nella lista.
     *
     * @return {@code true} se ci sono altri elementi in avanti
     */
    boolean hasNext();

    /**
     * Restituisce il prossimo elemento nella lista.
     *
     * @return il prossimo elemento nella lista
     * @throws java.util.NoSuchElementException se non ci sono altri elementi in avanti
     */
    Object next();

    /**
     * Restituisce {@code true} se ci sono elementi precedenti nella lista.
     *
     * @return {@code true} se ci sono elementi precedenti
     */
    boolean hasPrevious();

    /**
     * Restituisce l'elemento precedente nella lista.
     *
     * @return l'elemento precedente nella lista
     * @throws java.util.NoSuchElementException se non ci sono elementi precedenti
     */
    Object previous();

    /**
     * Restituisce l'indice dell'elemento che verrebbe restituito da una successiva chiamata a {@code next()}.
     *
     * @return l'indice del prossimo elemento, oppure la dimensione della lista se l'iteratore è alla fine della lista
     */
    int nextIndex();

    /**
     * Restituisce l'indice dell'elemento che verrebbe restituito da una successiva chiamata a {@code previous()}.
     *
     * @return l'indice dell'elemento precedente, oppure -1 se l'iteratore è all'inizio della lista
     */
    int previousIndex();

    /**
     * Rimuove l'ultimo elemento restituito da {@code next()} o {@code previous()}.
     *
     * @throws UnsupportedOperationException se la rimozione non è supportata
     * @throws IllegalStateException se né {@code next()} né {@code previous()} sono stati chiamati,
     *         oppure se {@code remove()} o {@code add()} sono già stati chiamati dopo l'ultima chiamata a {@code next()} o {@code previous()}
     */
    void remove();

    /**
     * Sostituisce l'ultimo elemento restituito da {@code next()} o {@code previous()} con l'elemento specificato.
     *
     * @param o l'elemento con cui sostituire quello corrente
     * @throws UnsupportedOperationException se la sostituzione non è supportata
     * @throws ClassCastException se la classe dell'elemento impedisce la sostituzione (opzionale)
     * @throws NullPointerException se l'elemento è null e la lista non supporta elementi null (opzionale)
     * @throws IllegalArgumentException se qualche aspetto dell'elemento impedisce la sostituzione (opzionale)
     * @throws IllegalStateException se né {@code next()} né {@code previous()} sono stati chiamati,
     *         oppure se {@code remove()} o {@code add()} sono già stati chiamati dopo l'ultima chiamata a {@code next()} o {@code previous()}
     */
    void set(Object o);

    /**
     * Inserisce l'elemento specificato nella lista nella posizione corrente dell'iteratore.
     *
     * @param o l'elemento da aggiungere
     * @throws UnsupportedOperationException se l'aggiunta non è supportata
     * @throws ClassCastException se la classe dell'elemento impedisce l'aggiunta (opzionale)
     * @throws NullPointerException se l'elemento è null e la lista non supporta elementi null (opzionale)
     * @throws IllegalArgumentException se qualche aspetto dell'elemento impedisce l'aggiunta (opzionale)
     */
    void add(Object o);
}