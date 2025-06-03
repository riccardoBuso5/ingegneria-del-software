// Implementazione di HListIterator per ListAdapter.
package myAdapter;


/**
 * Implementazione di un iteratore bidirezionale per la classe ListAdapter.
 * Questa classe permette di scorrere la lista sia in avanti che all'indietro,
 * e di modificare la lista durante l'iterazione tramite i metodi remove, set e add.
 *
 * <p>
 * L'iteratore mantiene un cursore che indica la posizione corrente nella lista,
 * e una variabile lastRet che tiene traccia dell'ultimo elemento restituito da next() o previous().
 * </p>
 *
 * <ul>
 *   <li>Il metodo {@code hasNext()} restituisce true se ci sono altri elementi successivi.</li>
 *   <li>Il metodo {@code next()} restituisce l'elemento successivo e avanza il cursore.</li>
 *   <li>Il metodo {@code hasPrevious()} restituisce true se ci sono elementi precedenti.</li>
 *   <li>Il metodo {@code previous()} restituisce l'elemento precedente e sposta il cursore indietro.</li>
 *   <li>Il metodo {@code nextIndex()} restituisce l'indice del prossimo elemento che verrebbe restituito da next().</li>
 *   <li>Il metodo {@code previousIndex()} restituisce l'indice dell'elemento che verrebbe restituito da previous().</li>
 *   <li>Il metodo {@code remove()} rimuove l'ultimo elemento restituito da next() o previous().</li>
 *   <li>Il metodo {@code set(Object o)} sostituisce l'ultimo elemento restituito con l'oggetto specificato.</li>
 *   <li>Il metodo {@code add(Object o)} inserisce un nuovo elemento nella posizione corrente del cursore.</li>
 * </ul>
 *
 * <p>
 * Dopo una chiamata a add(), non è possibile chiamare remove() o set() fino a quando non viene effettuata una nuova chiamata a next() o previous().
 * </p>
 *
 * @author Riccardo Buso
 */
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