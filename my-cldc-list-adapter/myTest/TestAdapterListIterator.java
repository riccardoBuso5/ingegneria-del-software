package myTest;

import myAdapter.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Classe di test per {@code HListIterator} tramite {@code ListAdapter}.
 * <p>
 * <summary>Questa classe verifica il corretto funzionamento di {@code HListIterator} tramite {@code ListAdapter}:</summary>
 * <ul>
 *   <li>{@link HListIterator#hasNext()} e {@link HListIterator#next()} - Navigazione avanti.</li>
 *   <li>{@link HListIterator#hasPrevious()} e {@link HListIterator#previous()} - Navigazione indietro.</li>
 *   <li>{@link HListIterator#add(Object)}, {@link HListIterator#remove()}, {@link HListIterator#set(Object)} - Operazioni di modifica.</li>
 * </ul>
 * <b>Pre-condizione:</b> Ogni test parte da una lista con tre elementi ("A", "B", "C").<br>
 * <b>Post-condizione:</b> La lista e l’iteratore si trovano nello stato atteso dopo ogni operazione.<br>
 * <b>Note:</b> I test coprono anche i casi di errore e le eccezioni previste dalla specifica.<br>
 * </p>
 *
 * @author Riccardo Buso
 */
public class TestAdapterListIterator {

    ListAdapter list;
    HListIterator it;

    @Before
    public void setUp() {
        list = new myAdapter.ListAdapter();
        list.add("A");
        list.add("B");
        list.add("C");
        it = list.listIterator();
    }

    /**
     * <p>
     * <summary>Test del metodo {@code hasNext()}</summary>
     * <b>Test Case Name:</b> TestHasNext<br>
     * <b>Summary:</b> Verifica il corretto funzionamento di {@code HListIterator} tramite {@code ListAdapter}.<br>
     * <b>Test Methods:</b> creo una lista e controllo se ha elementi in coda<br>
     * <b>Pre-condition:</b> La lista contiene tre elementi ("A", "B", "C").<br>
     * <b>Post-condition:</b> {@code hasNext()} restituisce true per i primi tre elementi e false dopo averli iterati tutti.<br>
     * <b>Expected:</b> {@code hasNext()} restituisce true per i primi tre elementi e false dopo averli iterati tutti.<br>
     * </p>
     */
    @Test
    public void testHasNext() {
        assertTrue(it.hasNext());
        it.next();
        it.next();
        it.next();
        assertFalse(it.hasNext());
    }

    /**
     * <p>
     * <summary>Test del metodo {@code next()}</summary>
     * <b>Test Case Name:</b> TestNext<br>
     * <b>Summary:</b> Verifica il corretto funzionamento di {@code HListIterator} tramite {@code ListAdapter}.<br>
     * <b>Test Methods:</b> creo una lista e ritorno gli elementi in coda<br>
     * <b>Pre-condition:</b> La lista contiene tre elementi ("A", "B", "C").<br>
     * <b>Post-condition:</b> {@code hasNext()} restituisce true per i primi tre elementi e false dopo averli iterati tutti.<br>
     * <b>Expected:</b> {@code next()} restituisce i primi tre elementi ("A", "B", "C") in ordine.<br>
     * </p>
     */
    @Test
    public void testNext() {
        assertEquals("A", it.next());
        assertEquals("B", it.next());
        assertEquals("C", it.next());
    }

    /**
     * <p>
     * <summary>Test del metodo {@code next()} quando non ci sono più elementi</summary>
     * <b>Test Case Name:</b> TestNextException<br>
     * <b>Summary:</b> Verifica il corretto funzionamento di {@code HListIterator} tramite {@code ListAdapter}.<br>
     * <b>Test Methods:</b> creo una lista e ritorno gli elementi in coda<br>
     * <b>Pre-condition:</b> La lista contiene tre elementi ("A", "B", "C").<br>
     * <b>Post-condition:</b> {@code hasNext()} restituisce true per i primi tre elementi e false dopo averli iterati tutti.<br>
     * <b>Expected:</b> {@code next()} lancia {@code NoSuchElementException} quando non ci sono più elementi.<br>
     * </p>
     */
    @Test(expected = myException.NoSuchElementException.class)
    public void testNextException() {
        it.next();
        it.next();
        it.next();
        it.next(); // should throw
    }

    /**
     * <p>
     * <summary>Test del metodo {@code hasPrevious()}</summary>
     * <b>Test Case Name:</b> TestHasPrevious<br>
     * <b>Summary:</b> Verifica il corretto funzionamento di {@code HListIterator} tramite {@code ListAdapter}.<br>
     * <b>Test Methods:</b> creo una lista e controllo se ha elementi precedenti<br>
     * <b>Pre-condition:</b> La lista contiene tre elementi ("A", "B", "C").<br>
     * <b>Post-condition:</b> {@code hasPrevious()} restituisce false prima di chiamare next() e true dopo.<br>
     * <b>Expected:</b> {@code hasPrevious()} restituisce false prima di chiamare next() e true dopo.<br>
     * </p>
     */
    @Test
    public void testHasPrevious() {
        assertFalse(it.hasPrevious());
        it.next();
        assertTrue(it.hasPrevious());
    }

    /**
     * <p>
     * <summary>Test del metodo {@code previous()}</summary>
     * <b>Test Case Name:</b> TestPrevious<br>
     * <b>Summary:</b> Verifica il corretto funzionamento di {@code HListIterator} tramite {@code ListAdapter}.<br>
     * <b>Test Methods:</b> creo una lista e ritorno gli elementi precedenti<br>
     * <b>Pre-condition:</b> La lista contiene tre elementi ("A", "B", "C").<br>
     * <b>Post-condition:</b> {@code hasPrevious()} restituisce true dopo aver chiamato next() e false prima.<br>
     * <b>Expected:</b> {@code previous()} restituisce gli elementi in ordine inverso ("B", "A") dopo aver chiamato next() due volte.<br>
     * </p>
     */
    @Test
    public void testPrevious() {
        it.next();
        it.next();
        assertEquals("B", it.previous());
        assertEquals("A", it.previous());
    }

    /**
     * <p>
     * <summary>Test del metodo {@code previous()} quando non ci sono elementi precedenti</summary>
     * <b>Test Case Name:</b> TestPreviousException<br>
     * <b>Summary:</b> Verifica il corretto funzionamento di {@code HListIterator} tramite {@code ListAdapter}.<br>
     * <b>Test Methods:</b> creo una lista e ritorno gli elementi precedenti<br>
     * <b>Pre-condition:</b> La lista contiene tre elementi ("A", "B", "C").<br>
     * <b>Post-condition:</b> {@code hasPrevious()} restituisce true dopo aver chiamato next() e false prima.<br>
     * <b>Expected:</b> {@code previous()} lancia {@code NoSuchElementException} quando non ci sono elementi precedenti.<br>
     * </p>
     */
    @Test(expected = myException.NoSuchElementException.class)
    public void testPreviousException() {
        it.previous(); // should throw
    }

    /**
     * <p>
     * <summary>Test del metodo {@code nextIndex()}</summary>
     * <b>Test Case Name:</b> TestNextIndex<br>
     * <b>Summary:</b> Verifica il corretto funzionamento di {@code HListIterator} tramite {@code ListAdapter}.<br>
     * <b>Test Methods:</b> creo una lista e controllo l'indice del prossimo elemento<br>
     * <b>Pre-condition:</b> La lista contiene tre elementi ("A", "B", "C").<br>
     * <b>Post-condition:</b> {@code nextIndex()} restituisce l'indice del prossimo elemento.<br>
     * <b>Expected:</b> {@code nextIndex()} restituisce 0 prima di chiamare next() e 1 dopo.<br>
     * </p>
     */
    @Test
    public void testNextIndex() {
        assertEquals(0, it.nextIndex());
        it.next();
        assertEquals(1, it.nextIndex());
    }

    /**
     * <p>
     * <summary>Test del metodo {@code previousIndex()}</summary>
     * <b>Test Case Name:</b> TestPreviousIndex<br>
     * <b>Summary:</b> Verifica il corretto funzionamento di {@code HListIterator} tramite {@code ListAdapter}.<br>
     * <b>Test Methods:</b> creo una lista e controllo l'indice dell'elemento precedente<br>
     * <b>Pre-condition:</b> La lista contiene tre elementi ("A", "B", "C").<br>
     * <b>Post-condition:</b> {@code previousIndex()} restituisce l'indice dell'elemento precedente.<br>
     * <b>Expected:</b> {@code previousIndex()} restituisce -1 prima di chiamare next() e 0 dopo.<br>
     * </p>
     */
    @Test
    public void testPreviousIndex() {
        assertEquals(-1, it.previousIndex());
        it.next();
        assertEquals(0, it.previousIndex());
    }

    /**
     * <p>
     * <summary>Test del metodo {@code remove()}</summary>
     * <b>Test Case Name:</b> TestRemove<br>
     * <b>Summary:</b> Verifica il corretto funzionamento di {@code HListIterator} tramite {@code ListAdapter}.<br>
     * <b>Test Methods:</b> creo una lista e rimuovo un elemento<br>
     * <b>Pre-condition:</b> La lista contiene tre elementi ("A", "B", "C").<br>
     * <b>Post-condition:</b> {@code remove()} rimuove l'elemento corrente e aggiorna la lista.<br>
     * <b>Expected:</b> {@code remove()} rimuove l'elemento corrente e aggiorna la lista.<br>
     * </p>
     */
    @Test
    public void testRemove() {
        it.next();
        it.remove();
        assertEquals(2, list.size());
        assertEquals("B", list.get(0));
    }

    /**
     * <p>
     * <summary>Test dell'aggiunta di elementi alla lista</summary>
     * <b>Summary:</b> Verifica che l'aggiunta di elementi aumenti la dimensione della lista.<br>
     * <b>Test method design:</b> Aggiunge due elementi e verifica che la dimensione sia 2.<br>
     * <b>Pre-Condition:</b> Lista vuota.<br>
     * <b>Post-Condition:</b> Lista con 2 elementi.<br>
     * <b>Expected Results:</b> La dimensione della lista deve essere 2 dopo l'aggiunta di due elementi.<br>
     * </p>
     */
    @Test(expected = myException.IllegalStateException.class)
    public void testRemoveException() {
        it.remove(); // should throw
    }

    /**
     * <p>
     * <summary>Test del metodo {@code set()}</summary>
     * <b>Test Case Name:</b> TestSet<br>
     * <b>Summary:</b> Verifica il corretto funzionamento di {@code HListIterator} tramite {@code ListAdapter}.<br>
     * <b>Test Methods:</b> creo una lista e sostituisco un elemento<br>
     * <b>Pre-condition:</b> La lista contiene tre elementi ("A", "B", "C").<br>
     * <b>Post-condition:</b> {@code set()} sostituisce l'elemento corrente con il nuovo valore.<br>
     * <b>Expected:</b> {@code set()} sostituisce l'elemento corrente con il nuovo valore.<br>
     * </p>
     */
    @Test
    public void testSet() {
        it.next();
        it.set("Z");
        assertEquals("Z", list.get(0));
    }

    /**
     * <p>
     * <summary>Test del metodo {@code set()} senza aver chiamato next() o previous()</summary>
     * <b>Test Case Name:</b> TestSetException<br>
     * <b>Summary:</b> Verifica il corretto funzionamento di {@code HListIterator} tramite {@code ListAdapter}.<br>
     * <b>Test Methods:</b> creo una lista e sostituisco un elemento senza aver chiamato next()<br>
     * <b>Pre-condition:</b> La lista contiene tre elementi ("A", "B", "C").<br>
     * <b>Post-condition:</b> {@code set()} lancia {@code IllegalStateException} se non è stato chiamato next() o previous().<br>
     * <b>Expected:</b> {@code set()} lancia {@code IllegalStateException} se non è stato chiamato next() o previous().<br>
     * </p>
     */
    @Test(expected = myException.IllegalStateException.class)
    public void testSetException() {
        it.set("Z"); // should throw
    }

    /**
     * <p>
     * <summary>Test del metodo {@code add()}</summary>
     * <b>Test Case Name:</b> TestAdd<br>
     * <b>Summary:</b> Verifica il corretto funzionamento di {@code HListIterator} tramite {@code ListAdapter}.<br>
     * <b>Test Methods:</b> creo una lista e aggiungo un elemento<br>
     * <b>Pre-condition:</b> La lista contiene tre elementi ("A", "B", "C").<br>
     * <b>Post-condition:</b> {@code add()} inserisce un nuovo elemento nella posizione corrente del cursore.<br>
     * <b>Expected:</b> {@code add()} aumenta la dimensione della lista e inserisce l'elemento all'inizio.<br>
     * </p>
     */
    @Test
    public void testAdd() {
        it.add("X");
        assertEquals(4, list.size());
        assertEquals("X", list.get(0));
    }

    public static void run() {
        long start = System.currentTimeMillis();
        org.junit.runner.Result result = org.junit.runner.JUnitCore.runClasses(TestAdapterListIterator.class);
        long end = System.currentTimeMillis();
        System.out.println("====================================");
        System.out.println("Risultati dei test TestAdapterListIteratorTest:");
        System.out.println("Totale test eseguiti: " + result.getRunCount());
        System.out.println("Test riusciti: " + (result.getRunCount() - result.getFailureCount()));
        System.out.println("Test falliti: " + result.getFailureCount());
        if (!result.wasSuccessful()) {
            System.out.println("Dettagli dei fallimenti:");
            for (org.junit.runner.notification.Failure failure : result.getFailures()) {
                System.out.println("- " + failure.toString());
            }
        } else {
            System.out.println("Tutti i test sono stati superati con successo.");
        }
        System.out.println("Tempo impiegato: " + (end - start) + " ms");
        System.out.println("====================================");
    }
}