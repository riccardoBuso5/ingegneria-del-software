package myAdapter;

import java.util.ListIterator;

public class ListAdapterListIterator implements HListIterator {
    private ListIterator internalIterator;

    public ListAdapterListIterator(ListIterator it) {
        internalIterator = it;
    }

    public boolean hasNext() {
        return internalIterator.hasNext();
    }

    public Object next() {
        return internalIterator.next();
    }

    public void remove() {
        internalIterator.remove();
    }

    public boolean hasPrevious() {
        return internalIterator.hasPrevious();
    }

    public Object previous() {
        return internalIterator.previous();
    }

    public int nextIndex() {
        return internalIterator.nextIndex();
    }

    public int previousIndex() {
        return internalIterator.previousIndex();
    }

    public void set(Object o) {
        internalIterator.set(o);
    }

    public void add(Object o) {
        internalIterator.add(o);
    }
}
