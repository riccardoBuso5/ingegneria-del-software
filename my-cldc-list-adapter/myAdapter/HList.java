package myAdapter;

/**
 * Interfaccia HList conforme a java.util.List (Java 1.4.2).
 */
public interface HList extends HCollection {
    // Metodi specifici di List
    void add(int index, Object element);
    boolean addAll(int index, HCollection c);
    Object get(int index);
    int indexOf(Object o);
    int lastIndexOf(Object o);
    HListIterator listIterator();
    HListIterator listIterator(int index);
    Object remove(int index);
    Object set(int index, Object element);
    HList subList(int fromIndex, int toIndex);
}