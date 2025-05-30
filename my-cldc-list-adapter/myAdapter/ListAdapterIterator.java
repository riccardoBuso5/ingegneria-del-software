// implementazione di Hiterator per ListAdapter
package myAdapter;


public class ListAdapterIterator implements HIterator {
    private ListAdapter list;
    private int cursor = 0;  //indica la posizione del prossimo elemento da restituire
    private int lastRet = -1; //salva l'ultimo elemento restituito

    public ListAdapterIterator(ListAdapter list) {
        this.list = list;
    }

    // Controlla se ci sono ancora elementi nella lista
    public boolean hasNext() {
        if(cursor < list.size()){
            return true;
        };
        return false;
    }

    // se non ci sono più elementi, lancia un'eccezione, e setta l'ultimo elemento restituito come il cursore
    public Object next() {
        if (!hasNext()) throw new java.util.NoSuchElementException();
        lastRet = cursor;
        return list.get(cursor++);
    }

    // Rimuove l'ultimo elemento restituito dalla lista
    public void remove() {
        if (lastRet < 0) {
            throw new IllegalStateException();
        }
        list.remove(lastRet);
        //se l'ultimo elemento rimosso si trova prima del cursore, decremento il cursore, perchè è shiftata tutta la lista
        if (lastRet < cursor) {
            cursor--;
        };
        //invalido l'ultimo elemento restituito perchè è stato rimosso
        lastRet = -1;
    }
}