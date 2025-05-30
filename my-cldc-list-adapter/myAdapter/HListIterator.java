package myAdapter;

/**
 * Interfaccia HListIterator conforme a java.util.ListIterator (Java 1.4.2).
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