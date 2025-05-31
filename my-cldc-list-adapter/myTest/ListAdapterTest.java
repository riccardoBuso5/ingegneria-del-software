package myTest;
import myAdapter.ListAdapter;

public class ListAdapterTest {
    public static void main(String[] args) {

        // creo una lista
        ListAdapter list = new ListAdapter();

        // aggiungo elementi alla lista
        list.add("elemento 1");
        list.add("elemento 2");
        list.add("elemento 3");

        System.out.println("Contenuto della lista: " + list.toString());
        System.out.println("Dimensione della lista: " + list.size());

        // rimuovo un elemento dalla lista
        list.remove(1);
        System.out.println("Contenuto dopo rimozione: " + list.toString());
        System.out.println("Dimensione dopo rimozione: " + list.size());

        // aggiungo un nuovo elemento
        list.add("elemento 4");
        System.out.println("Contenuto dopo aggiunta: " + list.toString());
        System.out.println("Dimensione dopo aggiunta: " + list.size());

        // sostituisco un elemento
        list.set(0, "elemento 5");
        System.out.println("Contenuto dopo sostituzione: " + list.toString());
    }
}