package myTest;

import myAdapter.HIterator;
import myAdapter.HList;
import myAdapter.ListAdapter;


public class HIteratorTest {

    /**
     * Metodo principale per il test delle funzionalità di HIterator e ListAdapter.
     * <p>
     * Questo metodo esegue le seguenti operazioni:
     * <ul>
     *   <li>Crea una lista e aggiunge tre elementi ("Element 1", "Element 2", "Element 3").</li>
     *   <li>Stampa il contenuto iniziale della lista utilizzando un ciclo for con HIterator.</li>
     *   <li>Itera e stampa nuovamente gli elementi della lista tramite HIterator e un ciclo while.</li>
     *   <li>Rimuove il primo elemento della lista tramite il metodo {@code remove()} dell'iteratore.</li>
     *   <li>Stampa gli elementi rimasti nella lista dopo la rimozione.</li>
     * </ul>
     *
     * @param args argomenti della riga di comando (non utilizzati)
     */
    public static void main(String[] args) {
        
        System.out.println("Creazione della lista e aggiunta degli elementi...");
        HList list = new ListAdapter();
        
        list.add("Element 1");
        list.add("Element 2");
        list.add("Element 3");
        System.out.println("Lista iniziale:");
        for (HIterator it = list.iterator(); it.hasNext();) {
            System.out.println(" - " + it.next());
        }

        System.out.println("\nIterazione e stampa degli elementi con HIterator:");
        HIterator iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println("Iterato: " + iterator.next());
        }

        System.out.println("\nTest della rimozione del primo elemento tramite iterator:");
        iterator = list.iterator();
        if (iterator.hasNext()) {
            Object toRemove = iterator.next();
            System.out.println("Removing: " + toRemove);
            iterator.remove(); // This should remove "Element 1"
        }

        System.out.println("\nElementi rimasti dopo la rimozione:");
        for (HIterator it = list.iterator(); it.hasNext();) {
            System.out.println(" - " + it.next());
        }
    }

}