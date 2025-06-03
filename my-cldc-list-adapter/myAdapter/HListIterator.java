package myAdapter;

/**
 * Interfaccia HListIterator conforme a java.util.ListIterator (Java 1.4.2).
 */
/**
 * Un iteratore per liste che permette al programmatore di attraversare la lista in entrambe le direzioni,
 * modificarla durante l'iterazione e ottenere la posizione corrente dell'iteratore nella lista.
 * <p>
 * Questa interfaccia estende {@link HIterator} e fornisce metodi aggiuntivi per supportare
 * l'iterazione bidirezionale e la modifica della lista sottostante.
 * </p>
 *
 * <p>
 * </p>
 *
 * @see HIterator
 */
public interface HListIterator extends HIterator {
    boolean hasNext();
    Object next();
    boolean hasPrevious();
    Object previous();
    int nextIndex();
    int previousIndex();
    void remove();
    void set(Object o);
    void add(Object o);
}