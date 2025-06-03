package myTest;

import myAdapter.ListAdapter;

import org.junit.*;
import static org.junit.Assert.*;
import org.junit.runner.Result;
import org.junit.runner.JUnitCore;
import org.junit.runner.notification.Failure;

/**
 * Test case per la classe ListAdapter.
 * <p>
 * <b>Summary:</b> Verifica il corretto funzionamento delle operazioni principali di ListAdapter.<br>
 * <b>Test Case Design:</b> Ogni metodo testa una funzionalità specifica della lista.<br>
 * <b>Pre-Condition:</b> La lista è vuota prima di ogni test.<br>
 * <b>Post-Condition:</b> La lista si trova nello stato atteso dopo ogni operazione.<br>
 */
public class ListAdapterTest {

    private ListAdapter list;

    /**
     * Inizializza una nuova lista prima di ogni test.
     */
    @Before
    public void setUp() {
        list = new ListAdapter();
    }

    /**
     * Test dell'aggiunta di elementi e della dimensione della lista.
     * <b>Summary:</b> Verifica che la dimensione aumenti dopo ogni aggiunta.<br>
     * <b>Pre-Condition:</b> Lista vuota.<br>
     * <b>Post-Condition:</b> Lista con 2 elementi.<br>
     */
    @Test
    public void testAddAndSize() {
        list.add("a");
        list.add("b");
        assertEquals(2, list.size());
    }

    /**
     * Test della rimozione di un elemento.
     * <b>Summary:</b> Verifica che la rimozione funzioni e la dimensione sia aggiornata.<br>
     * <b>Pre-Condition:</b> Lista con 2 elementi.<br>
     * <b>Post-Condition:</b> Lista con 1 elemento.<br>
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
     * <b>Pre-Condition:</b> Lista con almeno 1 elemento.<br>
     * <b>Post-Condition:</b> Primo elemento sostituito.<br>
     */
    @Test
    public void testSet() {
        list.add("x");
        list.set(0, "y");
        assertEquals("y", list.get(0));
    }

    /**
     * Test della presenza di un elemento.
     * <b>Summary:</b> Verifica che contains ritorni true per un elemento presente.<br>
     * <b>Pre-Condition:</b> Lista con almeno 1 elemento.<br>
     * <b>Post-Condition:</b> Nessuna modifica alla lista.<br>
     */
    @Test
    public void testContains() {
        list.add("z");
        assertTrue(list.contains("z"));
    }

    /**
     * Test della ricerca di un elemento.
     * <b>Summary:</b> Verifica che indexOf ritorni l'indice corretto di un elemento.<br>
     * <b>Pre-Condition:</b> Lista con almeno 1 elemento.<br>
     * <b>Post-Condition:</b> Nessuna modifica alla lista.<br>
     */
    @Test
    public void testIndexOf() {
        list.add("a");
        list.add("b");
        assertEquals(0, list.indexOf("a"));
        assertEquals(1, list.indexOf("b"));
        assertEquals(-1, list.indexOf("c")); // Elemento non presente
        

    /**
     * Metodo main per eseguire i test e stampare un output visivo, poi andranno uniti tutti in  un file test 
     */
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        Result result = JUnitCore.runClasses(ListAdapterTest.class);
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
        }
        System.out.println("Tempo impiegato: " + (end - start) + " ms");
        System.out.println("====================================");
    }
}