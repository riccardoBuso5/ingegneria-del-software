// Implementazione di HListIterator per ListAdapter.
package myAdapter;


public class ListAdapterListIterator implements HListIterator {
    private ListAdapter list;
    private int cursor = 0;
    private int lastRet = -1; //ultimo elemento restituito

    public ListAdapterListIterator(ListAdapter list, int index) {
        this.list = list;
        this.cursor = index;
    }

    public boolean hasNext() {
        if(cursor < list.size()){
            return true;
        };
        return false;
    }

    public Object next() {
        if (!hasNext()) {
            throw new java.util.NoSuchElementException();
        }else{
            //se ci sono ancora elementi, setto l'ultimo elemento restituito come il cursore
            lastRet = cursor;
        }
        //ritorna l'elemento corrente e incrementa il cursore
        return list.get(cursor++);
    }

    public boolean hasPrevious() {
        if( cursor > 0) {
            return true;
        }
        return false;
    }

    public Object previous() {
        if (!hasPrevious()){
             throw new java.util.NoSuchElementException();
        }else {
            //se ci sono elementi precedenti, setto l'ultimo elemento restituito come il cursore e poi lo decremento
            lastRet = --cursor;
        }
        return list.get(cursor);
    }

    public int nextIndex() {
        return cursor;
    }

    public int previousIndex() {
        return cursor - 1;
    }

    public void remove() {
        if (lastRet < 0){
             throw new IllegalStateException();
        }
        //rimuove l'ultimo elemento restituito dalla lista
        list.remove(lastRet);
        if (lastRet < cursor) {
            cursor--;
        }
        //invalida l'ultimo elemento restituito perchè è stato rimosso
        lastRet = -1;
    }

    public void set(Object o) {
        if (lastRet < 0) {
            // se lastRet è invalido, non si può impostare
            throw new IllegalStateException();
        }
        //setta l'ultimo elemento restituito con il nuovo oggetto
        list.set(lastRet, o);
    }

    public void add(Object o) {
        list.add(cursor++, o);
        // dopo l'aggiunta, l'ultimo elemento restituito viene invalidato, per rispettare le specifiche 
        /* dopo un operazione di aggiunta, non è possibile chiamare remove o set fino a quando non viene effettuata una nuova chiamata next() o previus()*/
        lastRet = -1;
    }
}