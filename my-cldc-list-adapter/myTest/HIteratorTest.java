package myTest;
import myAdapter.HIterator;
import myAdapter.HList;
import myAdapter.HListIterator;
import myAdapter.HCollection;
import myAdapter.ListAdapter;
import myAdapter.ListAdapterListIterator;
import java.util.Vector;

public class HIteratorTest {

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