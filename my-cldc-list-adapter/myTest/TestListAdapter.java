package myTest;

import myAdapter.*;
import org.junit.*;
import static org.junit.Assert.*;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;


/**
 * Homework - Test Case
 * <p>
 * <b>Test Case Name:</b> TestListAdapter<br>
 * <b>Summary:</b> Questa classe verifica il corretto funzionamento delle principali operazioni di {@code ListAdapter}:
 * <ul>
 *   <li>{@link ListAdapter#add(Object)} - Aggiunge un elemento alla lista.</li>
 *   <li>{@link ListAdapter#remove(Object)} - Rimuove un elemento dalla lista.</li>
 *   <li>{@link ListAdapter#get(int)} - Restituisce l’elemento in una posizione specifica.</li>
 *   <li>Altri metodi fondamentali della lista.</li>
 * </ul>
 * <b>Pre-condizione:</b> Ogni test parte da una lista vuota o con elementi noti.<br>
 * <b>Post-condizione:</b> La lista si trova nello stato atteso dopo ogni operazione.<br>
 * <b>Note:</b> I test verificano anche il corretto lancio delle eccezioni previste dalla specifica J2SE 1.4.2.
 * </p>
 *
 * @author Riccardo Buso
 */
public class TestListAdapter {

    private ListAdapter list;

    /**
     * Inizializza una nuova lista prima di ogni test.
     */
    @Before
    public void setUp() {
        list = new ListAdapter();
    }

    /**
     * <p>
     * <p> <summary>Test dell'aggiunta di elementi alla lista.</summary>
     * <b>Summary:</b> Verifica che l'aggiunta di elementi aumenti la dimensione della lista.<br>
     * <b>Test method desing:</b> Aggiunge due elementi e verifica che la dimensione sia 2.<br>
     * <b>Pre-Condition:</b> Lista vuota.<br>
     * <b>Post-Condition:</b> Lista con 2 elementi.<br>
     *<b> exptected results:</b> La dimensione della lista deve essere 2 dopo l'aggiunta di due elementi.<br>
     */
    @Test
    public void testAddAndSize() {
        list.add("a");
        list.add("b");
        assertEquals(2, list.size());
    }

    /**
     * <p>
     * <p> <summary>Test dell'aggiunta di tutti gli elementi di una collezione alla lista.</summary>
     * <b>Summary:</b> Verifica che venga aggiunta l'intera collezione alla mia lista, .<br>
     * <b>Test method desing:</b> creo una collezione di 2 elementi, e la aggiungo ad una lista, 
     * alla fine devo avere gli elementi della mia lista + quelli della collezione.<br>
     * <b>Pre-Condition:</b> collezione non vuota, lista .<br>
     * <b>Post-Condition:</b> Lista con 3 elementi.<br>
     *<b> exptected results:</b> La dimensione della lista deve essere dimensione lista + elementi della collezione dopo l'aggiunta degli elementi.<br>
     */
    @Test
    public void testAddAll() {
        ListAdapter collection = new ListAdapter();
        collection.add("c");
        collection.add("d");
        list.add("a");
        list.addAll(1, collection); // Aggiunge "c" e "d" dopo "a"
        assertEquals(3, list.size());
        assertEquals("a", list.get(0));
        assertEquals("c", list.get(1));
        assertEquals("d", list.get(2));
    } 
    
    
    /**
     * Test metodo get con indice negativo.
     * <b>Summary:</b> Verifica che get lanci IndexOutOfBoundsException per indice negativo.<br>
     * <b>Test method design:</b> Aggiunge un elemento e verifica che get lanci l'eccezione per indice negativo.<br>
     * <b>Pre-Condition:</b> Lista con almeno 1 elemento.<br>
     * <b>Post-Condition:</b> Nessuna modifica alla lista.<br>
     * <b>exptected results:</b> L'eccezione IndexOutOfBoundsException deve essere lanciata quando si tenta di accedere a un indice negativo.<br>
     * <b>Note:</b> Questo test verifica che il metodo get gestisca correttamente gli indici negativi.<br>
     */
   
    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetThrowsExceptionForNegativeIndex() {
        list.add("x");
        list.get(-1);
    }

    /**
     * Test metodo get con indice uguale alla dimensione della lista.
     * <b>Summary:</b> Verifica che get lanci IndexOutOfBoundsException per indice uguale alla dimensione della lista.<br>
     * <b>Test method design:</b> Aggiunge un elemento e verifica che get lanci l'eccezione per indice uguale alla dimensione.<br>
     * <b>Pre-Condition:</b> Lista con almeno 1 elemento.<br>
     * <b>Post-Condition:</b> Nessuna modifica alla lista.<br>
     * <b>exptected results:</b> L'eccezione IndexOutOfBoundsException deve essere lanciata quando si tenta di accedere a un indice uguale alla dimensione della lista.<br>
     * <b>Note:</b> Questo test verifica che il metodo get gestisca correttamente gli indici fuori dai limiti superiori.<br>
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetThrowsExceptionForIndexEqualToSize() {
        list.add("x");
        list.get(1);
    }

     /* * Test get 
     * <b>Summary:</b> Verifica che get ritorni l'elemento corretto all'indice specificato.<br>
     * <b>Test method design:</b> Aggiunge un elemento e verifica che get ritorni l'elemento corretto.<br>
     * <b>Pre-Condition:</b> Lista con almeno 1 elemento.<br>
     * <b>Post-Condition:</b> Nessuna modifica alla lista.<br>
     * <b> exptected results:</b> get deve ritornare l'elemento corretto all'indice specificato.<br>
     */
    @Test
    public void testGet() {
        list.add("x");
        list.add("y");
        assertEquals("x", list.get(0));
        assertEquals("y", list.get(1));
    }

    /**
     * Test indexOf e lastIndexOf.
     * <b>Summary:</b> Verifica che indexOf e lastIndexOf funzionino correttamente.<br>
     * <b>Test method design:</b> Aggiunge elementi e verifica che indexOf ritorni il primo indice e lastIndexOf l'ultimo.<br>
     * <b>Pre-Condition:</b> Lista con almeno 2 elementi.<br>
     * <b>Post-Condition:</b> Nessuna modifica alla lista.<br>
     * <b> exptected results:</b> indexOf deve ritornare il primo indice dell'elemento, lastIndexOf l'ultimo.<br>
     * <b>Note:</b> indexOf ritorna il primo indice, lastIndexOf l'ultimo.<br>
     */
    @Test
    public void testIndexOfAndLastIndexOf() {
        list.add("a");
        list.add("b");
        list.add("a"); // Aggiunge un duplicato
        assertEquals(0, list.indexOf("a")); // Primo indice di "a"
        assertEquals(2, list.lastIndexOf("a")); // Ultimo indice di "a"
        assertEquals(-1, list.indexOf("c")); // Elemento non presente
    }

    /**
     *Test ListIterator.
     * <b>Summary:</b> Verifica che listIterator funzioni correttamente.<br>
     * <b>Test method design:</b> Aggiunge elementi e verifica che l'iteratore ritorni gli elementi nell'ordine corretto.<br>
     * <b>Pre-Condition:</b> Lista con almeno 2 elementi.<br>
     * <b>Post-Condition:</b> Nessuna modifica alla lista.<br>
     * <b> exptected results:</b> listIterator deve ritornare gli elementi nell'ordine in cui sono stati aggiunti.<br>
     */
    @Test
    public void testListIterator() {
        list.add("a");
        list.add("b");
        list.add("c");
        HListIterator it = list.listIterator();
        assertTrue(it.hasNext());
        assertEquals("a", it.next());
        assertTrue(it.hasNext());
        assertEquals("b", it.next());
        assertTrue(it.hasNext());
        assertEquals("c", it.next());
        assertFalse(it.hasNext());
    }


    /**
     * Test della rimozione di un elemento.
     * <b>Summary:</b> Verifica che la rimozione funzioni e la dimensione sia aggiornata.<br>
     * <b>Test method design:</b> Aggiunge due elementi, rimuove il primo e verifica la dimensione e l'elemento rimanente.<br>
     * <b>Pre-Condition:</b> Lista con 2 elementi.<br>
     * <b>Post-Condition:</b> Lista con 1 elemento.<br>
     * <b> exptected results:</b> La dimensione della lista deve essere 1 dopo la rimozione di un elemento, e l'elemento rimanente deve essere quello atteso.<br>
     */
    @Test
    public void testRemove() {
        list.add("a");
        list.add("b");
        list.remove(0);
        assertEquals("b", list.get(0));
        assertEquals(1, list.size());
    }

    /**
     * Test della sostituzione di un elemento.
     * <b>Summary:</b> Verifica che set sostituisca correttamente un elemento.<br>
     * <b>Test method design:</b> Aggiunge un elemento, lo sostituisce e verifica il nuovo valore.<br>
     * <b>Pre-Condition:</b> Lista con almeno 1 elemento.<br>
     * <b>Post-Condition:</b> Primo elemento sostituito.<br>
     * <b> exptected results:</b> L'elemento all'indice specificato deve essere sostituito con il nuovo valore.<br>
     */
    @Test
    public void testSet() {
        list.add("x");
        list.set(0, "y");
        assertEquals("y", list.get(0));
    }

    /**
     * Test della sottolista.
     * <b>Summary:</b> Verifica che subList ritorni una lista corretta.<br>
     * <b>Test method design:</b> Aggiunge tre elementi, crea una sottolista e verifica la dimensione e gli elementi.<br>
     * <b>Pre-Condition:</b> Lista con almeno 3 elementi.<br>
     * <b>Post-Condition:</b> Nessuna modifica alla lista originale.<br>
     * <b> exptected results:</b> La sottolista deve contenere gli elementi corretti e la sua dimensione deve essere quella attesa.<br>
     */
    @Test
    public void testSubList() {
        list.add("a");
        list.add("b");
        list.add("c");
        HList subList = list.subList(0, 2); // Sottolista da 0 a 2 (escluso)
        assertEquals(2, subList.size());
        assertEquals("a", subList.get(0));
        assertEquals("b", subList.get(1));
    }

    /**
     * Test size della lista.
     * già provata sopra.
    */

    /**
     * Test isEmpty.
     * <b>Summary:</b> Verifica che isEmpty ritorni true per una lista vuota.<br>
     * <b>Test method design:</b> Controlla che isEmpty ritorni true per una lista vuota e false dopo l'aggiunta di un elemento.<br>
     * <b>Pre-Condition:</b> Lista vuota.<br>
     * <b>Post-Condition:</b> Nessuna modifica alla lista.<br>
     * <b> exptected results:</b> isEmpty deve ritornare true per una lista vuota e false dopo l'aggiunta di un elemento.<br>
     * 
     * 
     */
    @Test
    public void testIsEmpty() {
        assertTrue(list.isEmpty());
        list.add("a");
        assertFalse(list.isEmpty());
    }

    /**
     * Test della presenza di un elemento.
     * <b>Summary:</b> Verifica che contains ritorni true per un elemento presente.<br>
     * <b>Test method design:</b> Aggiunge un elemento e verifica che contains ritorni true per quell'elemento.<br>
     * <b>Pre-Condition:</b> Lista con almeno 1 elemento.<br>
     * <b>Post-Condition:</b> Nessuna modifica alla lista.<br>
     * <b> exptected results:</b> contains deve ritornare true se l'elemento è presente nella lista.<br>
     */
    @Test
    public void testContains() {
        list.add("z");
        assertTrue(list.contains("z"));
    }


    /**
     * Test della presenza di tutti gli elementi di una collezione.
     * <b>Summary:</b> Verifica che containsAll ritorni true se tutti gli elementi sono presenti.<br>
     * <b>Test method design:</b> Aggiunge due elementi alla lista, crea una collezione con gli stessi elementi e verifica containsAll.<br>
     * <b>Pre-Condition:</b> Lista con almeno 1 elemento.<br>
     * <b>Post-Condition:</b> Nessuna modifica alla lista.<br>
     * <b> exptected results:</b> containsAll deve ritornare true se tutti gli elementi della collezione sono presenti nella lista, altrimenti false.<br>
     */

    @Test
    public void testContainsAll() {
        list.add("a");
        list.add("b");
        ListAdapter collection = new ListAdapter();
        collection.add("a");
        collection.add("b");
        assertTrue(list.containsAll(collection));
        
        collection.add("c"); // Aggiunge un elemento non presente
        assertFalse(list.containsAll(collection));
    }

  /**
   * test iterator.
   * <b>Summary:</b> Verifica che l'iteratore funzioni correttamente.<br>
   * <b>Test method design:</b> Aggiunge due elementi e verifica che l'iteratore ritorni gli elementi nell'ordine corretto.<br>
   * <b>Pre-Condition:</b> Lista con almeno 2 elementi.<br>
   * <b>Post-Condition:</b> Nessuna modifica alla lista.<br>
   * <b> exptected results:</b> L'iteratore deve ritornare gli elementi nell'ordine in cui sono stati aggiunti.<br>
   */
    @Test
    public void testIterator() {
        list.add("a");
        list.add("b");
        HIterator it = list.iterator();
        assertTrue(it.hasNext());
        assertEquals("a", it.next());
        assertTrue(it.hasNext());
        assertEquals("b", it.next());
        assertFalse(it.hasNext());
    }

    /** 
     * Test toArray.
     * <b>Summary:</b> Verifica che toArray ritorni un array con gli elementi della lista.<br>  
     * <b>Test method design:</b> Aggiunge due elementi e verifica che toArray ritorni un array con gli stessi elementi.<br>
     * <b>Pre-Condition:</b> Lista con almeno 1 elemento.<br>
     * <b>Post-Condition:</b> Nessuna modifica alla lista.<br>
     * <b> exptected results:</b> toArray deve ritornare un array con gli elementi della lista nell'ordine in cui sono stati aggiunti.<br>
     */
    @Test
    public void testToArray() {
        list.add("a");
        list.add("b");
        Object[] array = list.toArray();
        assertEquals(2, array.length);
        assertEquals("a", array[0]);
        assertEquals("b", array[1]);
    }
    /**
     * Test toArray con array specificato.
     * <b>Summary:</b> Verifica che toArray(Object[] a) funzioni correttamente.<br>
     * <b>Test method design:</b> Aggiunge due elementi e verifica che toArray(Object[] a) ritorni un array con gli stessi elementi.<br>
     * <b>Pre-Condition:</b> Lista con almeno 1 elemento.<br>
     * <b>Post-Condition:</b> Nessuna modifica alla lista.<br>
     * <b> exptected results:</b> toArray(Object[] a) deve ritornare un array con gli elementi della lista nell'ordine in cui sono stati aggiunti.<br>
     */
    @Test
    public void testToArrayWithSpecifiedArray() {
        list.add("a");
        list.add("b");
        Object[] array = new Object[2];
        array = list.toArray(array);
        assertEquals(2, array.length);
        assertEquals("a", array[0]);
        assertEquals("b", array[1]);
    }

  
    /**
     * Test della pulizia della lista.
     * <b>Summary:</b> Verifica che clear rimuova tutti gli elementi.<br>
     * <b>Test method design:</b> Aggiunge due elementi, chiama clear e verifica che la lista sia vuota.<br>
     * <b>Pre-Condition:</b> Lista con almeno 1 elemento.<br>
     * <b>Post-Condition:</b> Lista vuota.<br>
     * <b> exptected results:</b> La dimensione della lista deve essere 0 dopo la chiamata a clear, e non deve contenere gli elementi precedenti.<br>
     */
    @Test
    public void testClear() {
        list.add("a");
        list.add("b");
        list.clear();
        assertEquals(0, list.size());
        assertFalse(list.contains("a"));
        assertFalse(list.contains("b"));
    }

    /**
     * test equals.
     * <b>Summary:</b> Verifica che equals funzioni correttamente tra due liste.<br>
     * <b>Test method design:</b> Crea due liste con gli stessi elementi e verifica che siano considerate uguali, poi modifica una lista e verifica che non siano più uguali.<br>
     * <b>Pre-Condition:</b> Due liste con gli stessi elementi.<br>
     * <b>Post-Condition:</b> Nessuna modifica alle liste.<br>
     * <b>Note:</b> Verifica che due liste con gli stessi elementi siano considerate uguali.<br>
     * <b> exptected results:</b> Le due liste devono essere considerate uguali se contengono gli stessi elementi, altrimenti false.<br>
     */

    @Test
    public void testEquals() {
        ListAdapter list1 = new ListAdapter();
        ListAdapter list2 = new ListAdapter();
        list1.add("a");
        list1.add("b");
        list2.add("a");
        list2.add("b");
        assertTrue(list1.equals(list2)); // Le due liste sono uguali
        list2.add("c"); // Aggiunge un elemento diverso
        assertFalse(list1.equals(list2)); // Le liste non sono più uguali
    }

    //inizio test specifici per eccezioni

    /**
     * Test della aggiunta di elementi in una posizione specifica.
     * <b>Summary:</b> Verifica che add(int index, E element) funzioni correttamente.<br>
     * <b>Test method design:</b> Aggiunge due elementi, poi aggiunge un terzo elemento in una posizione specifica e verifica l'ordine degli elementi.<br>
     * <b>Pre-Condition:</b> Lista con almeno 1 elemento.<br>
     * <b>Post-Condition:</b> Elemento aggiunto nella posizione specificata.<br>
     * <b> exptected results:</b> L'elemento deve essere aggiunto nella posizione specificata, e gli altri elementi devono essere spostati di conseguenza.<br>
     * 
     */
    @Test
    public void testAddAtIndex() {
        list.add("a");
        list.add("b");
        list.add(1, "c"); // Aggiunge "c" tra "a" e "b"
        assertEquals("a", list.get(0));
        assertEquals("c", list.get(1));
        assertEquals("b", list.get(2));
        assertEquals(3, list.size());
    }


    /**
     * test aggiunta di elementi null.
     * verifica che l'aggiunta di un elemento null non causi errori e che la lista lo gestisca correttamente.
     * <b>Summary:</b> Verifica che l'aggiunta di un elemento null non causi errori.<br>
     * <b>Test method design:</b> Aggiunge un elemento null alla lista e verifica che la dimensione sia aggiornata correttamente.<br>
     * <b>Pre-Condition:</b> Lista vuota.<br>
     * <b>Post-Condition:</b> Lista contiene un elemento null.<br>
     * <b>exptected results:</b> La dimensione della lista deve essere 1 dopo l'aggiunta di un elemento null, e l'elemento all'indice 0 deve essere null.<br>
     * 
     */
    @Test
    public void testAddNullElement() {
        list.add(null); // Aggiunge un elemento null
        assertEquals(1, list.size()); // La dimensione della lista deve essere 1
        assertNull(list.get(0)); // L'elemento all'indice 0 deve essere null
    }

    /**
     * test eccezione per indice fuori dai limiti.
     * <b>Summary:</b> Verifica che venga lanciata un'eccezione IndexOutOfBoundsException quando si tenta di accedere a un indice non valido.<br>
     * <b>Test method design:</b> Aggiunge un elemento e tenta di accedere a un indice non valido, verificando che venga lanciata l'eccezione.<br>
     * <b>Pre-Condition:</b> Lista con almeno 1 elemento.<br>
     * <b>Post-Condition:</b> Nessuna modifica alla lista.<br>
     * <b>exptected results:</b> Deve essere lanciata un'eccezione IndexOutOfBoundsException quando si tenta di accedere a un indice non valido.<br>
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testIndexOutOfBoundsException() {
        list.add("a");
        list.get(1); // Tenta di accedere a un indice non valido (1) in una lista con un solo elemento
    }



    /**
     * Metodo run per eseguire i test e stampare un output visivo, poi andranno uniti tutti in  un file test 
     */
    public static void run() {
        long start = System.currentTimeMillis();
        Result result = JUnitCore.runClasses(TestListAdapter.class);
        long end = System.currentTimeMillis();
        System.out.println("====================================");
        System.out.println("Risultati dei test ListAdapterTest:");
        System.out.println("Totale test eseguiti: " + result.getRunCount());
        System.out.println("Test riusciti: " + (result.getRunCount() - result.getFailureCount()));
        System.out.println("Test falliti: " + result.getFailureCount());
        if (!result.wasSuccessful()) {
            System.out.println("Dettagli dei fallimenti:");
            for (Failure failure : result.getFailures()) {
                System.out.println("- " + failure.toString());
            }
        } else {
            System.out.println("Tutti i test sono stati superati con successo.");
        }
        System.out.println("Tempo impiegato: " + (end - start) + " ms");
        System.out.println("====================================");
    }
}