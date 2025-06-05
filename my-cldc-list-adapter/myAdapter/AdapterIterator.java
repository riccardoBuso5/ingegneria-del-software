package myAdapter;

/**
 * Implementazione dell'interfaccia {@code HIterator} per la classe {@link ListAdapter}.
 * Questo iteratore consente l'accesso sequenziale agli elementi di un {@code ListAdapter} e supporta la rimozione degli elementi.
 *
 * <p>
 * L'iteratore mantiene un cursore che punta al prossimo elemento da restituire e una variabile che memorizza l'ultimo elemento restituito.
 * </p>
 *
 * @author Riccardo Buso
 */
public class AdapterIterator implements HIterator {
    private ListAdapter list;
    private int cursor = 0;  // indica la posizione del prossimo elemento da restituire
    private int lastRet = -1; // salva l'ultimo elemento restituito

    /**
     * Costruisce un nuovo {@code ListAdapterIterator} per il {@code ListAdapter} specificato.
     *
     * @param list il {@code ListAdapter} su cui iterare
     */
    public AdapterIterator(ListAdapter list) {
        this.list = list;
    }

    /**
     * Restituisce {@code true} se l'iterazione ha altri elementi.
     *
     * @return {@code true} se ci sono altri elementi da iterare, {@code false} altrimenti
     * @implNote Questo metodo non lancia mai eccezioni.
     */
    public boolean hasNext() {
        return cursor < list.size();
    }

    /**
     * Restituisce il prossimo elemento nell'iterazione.
     *
     * @return il prossimo elemento nell'iterazione
     * @throws java.util.NoSuchElementException se l'iterazione non ha più elementi
     */
    public Object next() {
        if (!hasNext()) throw new java.util.NoSuchElementException();
        lastRet = cursor;
        return list.get(cursor++);
    }

    /**
     * Rimuove dalla collezione sottostante l'ultimo elemento restituito da questo iteratore.
     * Questo metodo può essere chiamato solo una volta per ogni chiamata a {@link #next()}.
     *
     * @throws IllegalStateException se il metodo {@code next} non è stato ancora chiamato,
     *         oppure se il metodo {@code remove} è già stato chiamato dopo l'ultima chiamata al metodo {@code next}
     * @throws UnsupportedOperationException se il metodo {@code remove} non è supportato dalla lista sottostante (mai lanciata da questa implementazione)
     */
    public void remove() {
        if (lastRet < 0) {
            throw new IllegalStateException();
        }
        list.remove(lastRet);
        // se l'ultimo elemento rimosso si trova prima del cursore, decremento il cursore, perchè è shiftata tutta la lista
        if (lastRet < cursor) {
            cursor--;
        }
        // invalido l'ultimo elemento restituito perchè è stato rimosso
        lastRet = -1;
    }
}