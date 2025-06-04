//devo usare la classe Vector di cldc per implementare List
package myAdapter;

import java.util.Vector;

/**
 * Una classe adapter che implementa l'interfaccia {@code HList} utilizzando un {@code Vector} come struttura dati sottostante.
 * Questa classe fornisce metodi per manipolare una lista di oggetti, inclusa l'aggiunta, la rimozione, il recupero e la ricerca di elementi.
 * Supporta inoltre operazioni in blocco ed estrazione di sottoliste.
 *
 * <p>
 * Il {@code ListAdapter} è progettato per essere compatibile con le interfacce {@code HList} e {@code HCollection},
 * fornendo un ponte tra le interfacce di collezione personalizzate e l'implementazione standard di {@code Vector}.
 * </p>
 *
 * <p>
 * Tutti i metodi sono implementati per seguire da vicino la semantica dell'interfaccia standard Java {@code List},
 * ma adattati alle interfacce personalizzate utilizzate in questo progetto.
 * </p>
 *
 * @author Riccardo Buso
 */
public class ListAdapter implements HList {
    private Vector vector;

    public ListAdapter() {
        vector = new Vector();
    }

    // HList methods

    // aggiunge un elemento all'indice specificato
    /**
     * Aggiunge un elemento all'indice specificato.
     *
     * @param index posizione in cui inserire l'elemento
     * @param element elemento da inserire
     * @throws IndexOutOfBoundsException se l'indice è fuori dai limiti (index < 0 || index > size())
     * @throws UnsupportedOperationException se il metodo add non è supportato
     * @throws ClassCastException se la classe dell'elemento impedisce l'aggiunta (opzionale)
     * @throws NullPointerException se l'elemento è null e la lista non supporta elementi null (opzionale)
     * @throws IllegalArgumentException se qualche aspetto dell'elemento impedisce l'aggiunta (opzionale)
     */
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

    /**
     * Restituisce l'elemento all'indice specificato.
     *
     * @param index indice dell'elemento da restituire
     * @return l'elemento all'indice specificato
     * @throws IndexOutOfBoundsException se l'indice è fuori dai limiti (index < 0 || index >= size())
     */
    public Object get(int index) {
        if (index < 0 || index >= vector.size()) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + vector.size());
        }
        return vector.elementAt(index);
    }

    /**
     * Restituisce l'indice della prima occorrenza dell'elemento specificato.
     *
     * @param o elemento da cercare
     * @return indice della prima occorrenza, o -1 se non presente
     */
    public int indexOf(Object o) {
        return vector.indexOf(o);
    }

    /**
     * Restituisce l'indice dell'ultima occorrenza dell'elemento specificato.
     *
     * @param o elemento da cercare
     * @return indice dell'ultima occorrenza, o -1 se non presente
     */
    public int lastIndexOf(Object o) {
        return vector.lastIndexOf(o);
    }

    /**
     * Restituisce un iteratore per la lista.
     *
     * @return un {@code HListIterator} per iterare sugli elementi della lista
     */
    public HListIterator listIterator() {
        return new ListAdapterListIterator(this, 0);
    }

    /**
     * Restituisce un iteratore per la lista a partire dall'indice specificato.
     *
     * @param index indice iniziale
     * @return un {@code HListIterator} per iterare sugli elementi della lista
     * @throws IndexOutOfBoundsException se l'indice è fuori dai limiti (index < 0 || index > size())
     */
    public HListIterator listIterator(int index) {
        return new ListAdapterListIterator(this, index);
    }

    /**
     * Rimuove l'elemento all'indice specificato e lo restituisce.
     *
     * @param index indice dell'elemento da rimuovere
     * @return l'elemento rimosso
     * @throws IndexOutOfBoundsException se l'indice è fuori dai limiti (index < 0 || index >= size())
     * @throws UnsupportedOperationException se il metodo remove non è supportato
     */
    public Object remove(int index) {
        Object obj = vector.elementAt(index);
        vector.removeElementAt(index);
        return obj;
    }

    /**
     * Sostituisce l'elemento all'indice specificato con l'elemento dato e restituisce l'elemento rimosso.
     *
     * @param index indice dell'elemento da sostituire
     * @param element nuovo elemento
     * @return l'elemento precedentemente presente all'indice specificato
     * @throws IndexOutOfBoundsException se l'indice è fuori dai limiti (index < 0 || index >= size())
     * @throws UnsupportedOperationException se il metodo set non è supportato
     * @throws ClassCastException se la classe dell'elemento impedisce la sostituzione (opzionale)
     * @throws NullPointerException se l'elemento è null e la lista non supporta null (opzionale)
     * @throws IllegalArgumentException se qualche aspetto dell'elemento impedisce la sostituzione (opzionale)
     */
    public Object set(int index, Object element) {
        Object old = vector.elementAt(index);
        vector.setElementAt(element, index);
        return old;
    }

    /**
     * Restituisce una sottolista tra gli indici specificati.
     *
     * @param fromIndex indice iniziale (incluso)
     * @param toIndex indice finale (escluso)
     * @return una sottolista
     * @throws IndexOutOfBoundsException se fromIndex o toIndex sono fuori dai limiti
     * @throws IllegalArgumentException se fromIndex > toIndex
     */
    public HList subList(int fromIndex, int toIndex) {
        ListAdapter sub = new ListAdapter();
        for (int i = fromIndex; i < toIndex; i++) {
            sub.add(this.get(i));
        }
        return sub;
    }

    // HCollection methods

    /**
     * Restituisce la dimensione della lista.
     *
     * @return il numero di elementi nella lista
     */
    public int size() {
        return vector.size();
    }

    /**
     * Verifica se la lista è vuota.
     *
     * @return {@code true} se la lista è vuota, {@code false} altrimenti
     */
    public boolean isEmpty() {
        return vector.isEmpty();
    }

    /**
     * Controlla se la lista contiene l'elemento specificato.
     *
     * @param o l'elemento da cercare nella lista
     * @return {@code true} se la lista contiene l'elemento, {@code false} altrimenti
     * @throws ClassCastException se il tipo dell'elemento è incompatibile (opzionale)
     * @throws NullPointerException se l'elemento è null e la lista non supporta null (opzionale)
     */
    public boolean contains(Object o) {
        return vector.contains(o);
    }

    /**
     * Controlla se la lista contiene tutti gli elementi della collezione specificata.
     *
     * @param c la collezione da verificare
     * @return {@code true} se la lista contiene tutti gli elementi della collezione, {@code false} altrimenti
     * @throws NullPointerException la mia lista supporta anche elementi null
     * @throws ClassCastException accetta qualsiasi tipo di oggetto
     * @throws UnsupportedOperationException la mia lista supporta operazioni su collezioni
     * @throws IllegalArgumentException se gli argomenti non sono validi, nel mio caso sono accettati  tutti gli oggetti 
     * @throws IndexOutOfBoundsException se la collezione contiene indici non validi
     * 
     */
    public boolean containsAll(HCollection c) {
        if(c.size() > vector.size()) throw  new IndexOutOfBoundsException("la collezione contiene più elementi della lista");
        HIterator it = c.iterator();
        while (it.hasNext()) {
            if (!vector.contains(it.next())) return false;
        }
        return true;
    }

    /**
     * Restituisce un iteratore per la lista.
     *
     * @return un {@code HIterator} per iterare sugli elementi della lista
     */
    public HIterator iterator() {
        return new ListAdapterIterator(this);
    }

    /**
     * Restituisce un array contenente tutti gli elementi della lista.
     *
     * @return un array di {@code Object} contenente gli elementi della lista
     
     */
    public Object[] toArray() {
        Object[] arr = new Object[vector.size()];
        vector.copyInto(arr);
        return arr;
    }

    /**
     * Restituisce un array contenente tutti gli elementi della lista, utilizzando l'array specificato se possibile.
     *
     * @param a l'array in cui inserire gli elementi della lista
     * @return un array contenente gli elementi della lista
     * @throws ArrayStoreException  se in eseguzione l'array passato non è valido, eccezzione lanciata di default
     * @throws NullPointerException se l'array è null
     */
    public Object[] toArray(Object[] a) {
        if (a.length < vector.size()) {
            a = new Object[vector.size()];
        }
        for (int i = 0; i < this.size(); i++) {
            a[i] = vector.elementAt(i); // Qui può verificarsi ArrayStoreException
        }
        if (a.length > this.size()) {
            a[this.size()] = null;
        }
        return a;
    }

    /**
     * Aggiunge un elemento alla lista.
     *
     * @param o l'elemento da aggiungere alla lista
     * @return {@code true} se l'elemento è stato aggiunto con successo, {@code false} altrimenti
     * @throws UnsupportedOperationException se il metodo add non è supportato dalla lista
     * @throws ClassCastException se la lista non supporta la classe passata
     * @throws NullPointerException se l'elemento passato è null
     * @throws IllegalArgumentException se l'argomento passato non è supportato
     */
    public boolean add(Object o) {
        vector.addElement(o);
        return true;
    }
    /**
     * Aggiunge tutti gli elementi della collezione specificata alla lista.
     *
     * @param c la collezione di elementi da aggiungere
     * @return {@code true} se la lista è stata modificata, {@code false} altrimenti
     * @throws UnsupportedOperationException se il metodo addAll non è supportato
     * @throws ClassCastException se la classe di un elemento impedisce l'aggiunta (opzionale)
     * @throws NullPointerException se la collezione o un elemento è null e la lista non supporta null (opzionale)
     * @throws IllegalArgumentException se qualche aspetto di un elemento impedisce l'aggiunta (opzionale)
     */
    public boolean addAll(HCollection c) {
        boolean modified = false;
        HIterator it = c.iterator();
        while (it.hasNext()) {
            vector.addElement(it.next());
            modified = true;
        }
        return modified;
    }

    /**
     * Rimuove un elemento dalla lista.
     *
     * @param o l'elemento da rimuovere dalla lista
     * @return {@code true} se l'elemento era presente e è stato rimosso, {@code false} altrimenti
     * @throws UnsupportedOperationException se il metodo remove non è supportato
     * @throws ClassCastException se la classe dell'elemento impedisce la rimozione (opzionale)
     * @throws NullPointerException se l'elemento è null e la lista non supporta null (opzionale)
     */
    public boolean remove(Object o) {
        boolean contained = vector.contains(o);
        vector.removeElement(o);
        return contained;
    }
    
    /**
     * Rimuove tutti gli elementi della collezione specificata dalla lista.
     *
     * @param c la collezione di elementi da rimuovere
     * @return {@code true} se la lista è stata modificata, {@code false} altrimenti
     * @throws UnsupportedOperationException se il metodo removeAll non è supportato
     * @throws ClassCastException se la classe di un elemento impedisce la rimozione (opzionale)
     * @throws NullPointerException se la collezione o un elemento è null e la lista non supporta null (opzionale)
     */
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

    /**
     * Rimuove dalla lista tutti gli elementi che non sono presenti nella collezione specificata.
     *
     * @param c la collezione di elementi da mantenere
     * @return {@code true} se la lista è stata modificata, {@code false} altrimenti
     * @throws UnsupportedOperationException se il metodo retainAll non è supportato
     * @throws ClassCastException se la classe di un elemento impedisce la verifica (opzionale)
     * @throws NullPointerException se la collezione o un elemento è null e la lista non supporta null (opzionale)
     */
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
    /**
     * Rimuove tutti gli elementi dalla lista.
     *
     * @throws UnsupportedOperationException se il metodo clear non è supportato
     */
    public void clear() {
        vector.removeAllElements();
    }

    /**
     * Confronta questa lista con un altro oggetto per verificare se sono uguali.
     * Due liste sono considerate uguali se hanno la stessa dimensione e gli stessi elementi negli stessi ordini.
     *
     * @param o l'oggetto da confrontare con questa lista
     * @return {@code true} se le liste sono uguali, {@code false} altrimenti
     */
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
    /**
     * Calcola l'hash code della lista.
     * L'hash code è calcolato sommando gli hash code di tutti gli elementi della lista.
     *
     * @return l'hash code della lista
     */
    public int hashCode() {
        int hashCode = 1;
        for (int i = 0; i < vector.size(); i++) {
            Object obj = vector.elementAt(i);
            hashCode = 31 * hashCode + (obj == null ? 0 : obj.hashCode());
        }
        return hashCode;
    }
    /**
     * Restituisce una rappresentazione in stringa della lista.
     * La rappresentazione è una stringa che contiene tutti gli elementi della lista, separati da virgole e racchiusi tra parentesi quadre.
     *
     * @return una stringa che rappresenta la lista
     */
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
