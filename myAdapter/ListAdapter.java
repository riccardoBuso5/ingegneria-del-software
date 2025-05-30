package myAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class ListAdapter implements HList {
    private List internalList;

    public ListAdapter() {
        internalList = new ArrayList();
    }

    @Override
    public int size() {
        return internalList.size();
    }

    @Override
    public boolean isEmpty() {
        return internalList.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return internalList.contains(o);
    }

    @Override
    public boolean add(Object o) {
        return internalList.add(o);
    }

    @Override
    public Object get(int index) {
        return internalList.get(index);
    }

    // ... Implementa tutti i metodi richiesti da HList e HCollection
}
