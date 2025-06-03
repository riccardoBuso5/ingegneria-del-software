package myTest;
import myAdapter.*;

public class HListIteratorTest {
    /**
     * Metodo principale di test per la classe ListAdapterListIterator.
     * <p>
     * Questo metodo esegue una serie di operazioni su una lista e sul relativo iteratore,
     * mostrando il funzionamento dei principali metodi dell'iteratore:
     * <ul>
     *   <li>Creazione di una lista e di un iteratore associato</li>
     *   <li>Aggiunta di elementi alla lista</li>
     *   <li>Verifica della presenza di elementi successivi e precedenti</li>
     *   <li>Recupero del prossimo e del precedente elemento</li>
     *   <li>Recupero degli indici del prossimo e del precedente elemento</li>
     *   <li>Rimozione di un elemento tramite l'iteratore</li>
     *   <li>Aggiunta di un nuovo elemento tramite l'iteratore</li>
     *   <li>Sostituzione di un elemento tramite l'iteratore</li>
     *   <li>Stampa dello stato della lista e della posizione dell'iteratore dopo ogni operazione</li>
     * </ul>
     * L'output prodotto permette di verificare il corretto funzionamento dei metodi dell'iteratore
     * e la coerenza dello stato della lista dopo ogni operazione.
     *
     * @param args argomenti da linea di comando (non utilizzati)
     */
    public static void main(String[] args) {

        //creo una una lista 
        ListAdapter list = new ListAdapter();

        //creo una una lista di iteratori
        ListAdapterListIterator listIterator = new ListAdapterListIterator(list, 0);

        //aggiungo elementi alla lista
        list.add("elemento 1");
        list.add("elemento 2");
        list.add("elemento 3");
        
        System.out.println("Contenuto iniziale della lista: " + list.toString());
        System.out.println("Posizione iniziale dell'iteratore: " + listIterator.nextIndex());
        //primo metodo hasNext
        System.out.println("Ha un elemento successivo? " + listIterator.hasNext()); 
        //secondo metodo next
        System.out.println("Prossimo elemento: " + listIterator.next()); 
        System.out.println("Posizione attuale dell'iteratore: " + listIterator.nextIndex());
        //terzo metodo hasPrevious
        System.out.println("Ha un elemento precedente? " + listIterator.hasPrevious());
        //quarto metodo previous
        System.out.println("Elemento precedente: " + listIterator.previous());
        System.out.println("Posizione attuale dell'iteratore: " + listIterator.nextIndex());
        //quinto metodo nextIndex
        System.out.println("Indice del prossimo elemento: " + listIterator.nextIndex());
        //sesto metodo previousIndex
        System.out.println("Indice dell'elemento precedente: " + listIterator.previousIndex());
        //settimo metodo remove
        listIterator.remove(); //rimuove l'ultimo elemento restituito
        System.out.println("Elemento rimosso. Dimensione attuale della lista: " + list.size());
        //ottavo metodo add
        listIterator.add("elemento 4"); //aggiunge un nuovo elemento
        System.out.println("Elemento aggiunto. Dimensione attuale della lista: " + list.size());

        System.out.println("Prossimo elemento: " + listIterator.next());

        //nono metodo set
        listIterator.set("elemento 5"); //sostituisce l'ultimo elemento restituito  
        System.out.println("Elemento sostituito. Dimensione attuale della lista: " + list.size());
        
        //stampa finale della lista
        System.out.println("Contenuto finale della lista: " + list.toString());
        //stampa finale dell'iteratore
        System.out.println("Posizione finale dell'iteratore: " + listIterator.nextIndex());
       


        
    }
}