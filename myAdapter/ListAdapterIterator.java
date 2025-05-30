package myAdapter;

import java.util.Iterator;

public class ListAdapterIterator implements HIterator {
    private Iterator internalIterator;

    public ListAdapterIterator(Iterator it) {
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
}
