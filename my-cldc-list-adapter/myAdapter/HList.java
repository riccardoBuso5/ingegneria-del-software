/**
 * Interfaccia che rappresenta una lista, ovvero una collezione ordinata di elementi
 * che può contenere elementi duplicati. Ogni elemento in questa lista ha una posizione
 * (indice) che parte da 0.
 * <p>
 * Questa interfaccia è conforme a {@link java.util.List} di Java 1.4.2, ma utilizza
 * {@code Object} come tipo degli elementi per garantire compatibilità con versioni
 * precedenti di Java.
 * </p>
 *
 * <p>
 * I metodi definiti permettono di aggiungere, rimuovere, accedere e modificare elementi
 * in posizioni specifiche, oltre a fornire iterazione tramite {@link HListIterator}.
 * </p>
 *
 * @see HCollection
 * @see HListIterator
 */
package myAdapter;

/**
 * Interfaccia HList conforme a java.util.List (Java 1.4.2).
 */
public interface HList extends HCollection {
    // Metodi specifici di List
    void add(int index, Object element);
    boolean addAll(int index, HCollection c);
    Object get(int index);
    int indexOf(Object o);
    int lastIndexOf(Object o);
    HListIterator listIterator();
    HListIterator listIterator(int index);
    Object remove(int index);
    Object set(int index, Object element);
    HList subList(int fromIndex, int toIndex);
}