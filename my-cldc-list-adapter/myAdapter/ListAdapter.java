//devo usare la classe Vector di cldc per implementare List
package myAdapter;

import java.util.Vector;

public class ListAdapter implements HList {
    private Vector vector;

    public ListAdapter() {
        vector = new Vector();
    }

    // HList methods
    public void add(int index, Object element) {
        vector.insertElementAt(element, index);
    }

    //inserisce tutti gli elementi della collezione c a partire dall'indice index
    public boolean addAll(int index, HCollection c) {
        boolean modified = false;
        HIterator it = c.iterator();
        while (it.hasNext()) {
            vector.insertElementAt(it.next(), index++);
            modified = true;
        }
        return modified;
    }

    public Object get(int index) {
        return vector.elementAt(index);
    }

    public int indexOf(Object o) {
        return vector.indexOf(o);
    }

    public int lastIndexOf(Object o) {
        return vector.lastIndexOf(o);
    }

    public HListIterator listIterator() {
        return new ListAdapterListIterator(this, 0);
    }

    public HListIterator listIterator(int index) {
        return new ListAdapterListIterator(this, index);
    }

    // Rimuove l'elemento all'indice specificato e lo restituisce
    public Object remove(int index) {
        Object obj = vector.elementAt(index);
        vector.removeElementAt(index);
        return obj;
    }

    // Sostituisce l'elemento all'indice specificato con l'elemento dato e restituisce l'elemento rimosso
    public Object set(int index, Object element) {
        Object old = vector.elementAt(index);
        vector.setElementAt(element, index);
        return old;
    }

    // Restituisce una  sottolista tra gli indici specificati
    public HList subList(int fromIndex, int toIndex) {
        ListAdapter sub = new ListAdapter();
        for (int i = fromIndex; i < toIndex; i++) {
            sub.add(this.get(i));
        }
        return sub;
    }

    // HCollection methods
    public int size() {
        return vector.size();
    }

    public boolean isEmpty() {
        return vector.isEmpty();
    }

    public boolean contains(Object o) {
        return vector.contains(o);
    }

    public HIterator iterator() {
        return new ListAdapterIterator(this);
    }

    public Object[] toArray() {
        Object[] arr = new Object[vector.size()];
        vector.copyInto(arr);
        return arr;
    }

    public Object[] toArray(Object[] a) {
        if (a.length < vector.size()) {
            a = new Object[vector.size()];
        }
        vector.copyInto(a);
        return a;
    }

    public boolean add(Object o) {
        vector.addElement(o);
        return true;
    }

    public boolean remove(Object o) {
        boolean contained = vector.contains(o);
        vector.removeElement(o);
        return contained;
    }

    public boolean containsAll(HCollection c) {
        HIterator it = c.iterator();
        while (it.hasNext()) {
            if (!vector.contains(it.next())) return false;
        }
        return true;
    }

    public boolean addAll(HCollection c) {
        boolean modified = false;
        HIterator it = c.iterator();
        while (it.hasNext()) {
            vector.addElement(it.next());
            modified = true;
        }
        return modified;
    }

    public boolean removeAll(HCollection c) {
        boolean modified = false;
        HIterator it = c.iterator();
        while (it.hasNext()) {
            Object o = it.next();
            while (vector.contains(o)) {
                vector.removeElement(o);
                modified = true;
            }
        }
        return modified;
    }

    public boolean retainAll(HCollection c) {
        boolean modified = false;
        for (int i = vector.size() - 1; i >= 0; i--) {
            if (!c.contains(vector.elementAt(i))) {
                vector.removeElementAt(i);
                modified = true;
            }
        }
        return modified;
    }

    public void clear() {
        vector.removeAllElements();
    }

    public boolean equals(Object o) {
        if (!(o instanceof HList)) return false;
        HList other = (HList) o;
        if (this.size() != other.size()) return false;
        for (int i = 0; i < this.size(); i++) {
            Object e1 = this.get(i);
            Object e2 = other.get(i);
            if (e1 == null ? e2 != null : !e1.equals(e2)) return false;
        }
        return true;
    }

    public int hashCode() {
        int hashCode = 1;
        for (int i = 0; i < vector.size(); i++) {
            Object obj = vector.elementAt(i);
            hashCode = 31 * hashCode + (obj == null ? 0 : obj.hashCode());
        }
        return hashCode;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < vector.size(); i++) {
            if (i > 0) sb.append(", ");
            sb.append(vector.elementAt(i));
        }
        sb.append("]");
        return sb.toString();
    }
}
