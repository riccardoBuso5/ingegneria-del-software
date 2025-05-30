package myAdapter;

public interface HCollection {
    int size();
    boolean isEmpty();
    boolean contains(Object o);
    HIterator iterator();
    Object[] toArray();
    boolean add(Object o);
    boolean remove(Object o);
    boolean containsAll(HCollection c);
    boolean addAll(HCollection c);
    boolean removeAll(HCollection c);
    boolean retainAll(HCollection c);
    void clear();
}
