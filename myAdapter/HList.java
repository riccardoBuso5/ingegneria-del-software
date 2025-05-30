package myAdapter;

public interface HList extends HCollection {
    Object get(int index);
    Object set(int index, Object element);
    void add(int index, Object element);
    Object remove(int index);
    int indexOf(Object o);
    int lastIndexOf(Object o);
    HListIterator listIterator();
    HListIterator listIterator(int index);
}
