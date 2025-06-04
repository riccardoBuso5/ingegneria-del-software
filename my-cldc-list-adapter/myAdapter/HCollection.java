package myAdapter;

public interface HCollection {
    boolean add(Object o);
    boolean addAll(HCollection c);
    void clear();
    boolean contains(Object o);
     /**
     * Restituisce true se questa collezione contiene l'elemento specificato.
     * @param o elemento la cui presenza in questa collezione deve essere verificata
     * @return true se questa collezione contiene l'elemento specificato
     * @throws ClassCastException se il tipo dell'elemento specificato non è compatibile con questa collezione
     * @throws NullPointerException se l'elemento specificato è null e questa collezione non permette elementi null
     */
    
    boolean containsAll(HCollection c);
    boolean equals(Object o);
    int hashCode();
    boolean isEmpty();  
    HIterator iterator();
    boolean remove(Object o);
    boolean removeAll(HCollection c);
    boolean retainAll(HCollection c);
    int size();
    Object[] toArray();
    Object[] toArray(Object[] a);
}



