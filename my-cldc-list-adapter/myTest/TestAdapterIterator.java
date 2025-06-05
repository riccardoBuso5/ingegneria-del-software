package myTest;

import org.junit.Before;
import org.junit.Test;

import myAdapter.*;


/**
 * Classe di test per {@link AdapterIterator}.
 * <p>
 * Questa classe verifica il corretto funzionamento dei principali metodi di {@code ListAdapterIterator}:
 * <ul>
 *   <li>{@link AdapterIterator#hasNext()} - Verifica se ci sono altri elementi da iterare.</li>
 *   <li>{@link AdapterIterator#next()} - Restituisce il prossimo elemento nell'iterazione.</li>
 *   <li>{@link AdapterIterator#remove()} - Rimuove l'ultimo elemento restituito dall'iteratore.</li>
 * </ul>
 * <b>Pre-condizione:</b> Ogni test parte da una lista con tre elementi ("Element 1", "Element 2", "Element 3").<br>
 * <b>Post-condizione:</b> La lista e l'iteratore si trovano nello stato atteso dopo ogni operazione.<br>
 * <b>Note:</b> I test verificano anche il corretto lancio delle eccezioni {@code NoSuchElementException} e {@code IllegalStateException}.
 * </p>
 *
 * @author Riccardo Buso
 */
public class TestAdapterIterator {
    private ListAdapter listAdapter;
    private AdapterIterator iterator;

    @Before
    public void setUp() {
        // Inizializza un ListAdapter con alcuni elementi per i test
        listAdapter = new ListAdapter();
        listAdapter.add("Element 1");
        listAdapter.add("Element 2");
        listAdapter.add("Element 3");
        iterator = new AdapterIterator(listAdapter);
    }

    /**
     * Verifica che il metodo {@code hasNext()} ritorni {@code true} quando ci sono elementi da iterare
     * e che continui a funzionare dopo aver avanzato l'iteratore.
     * <br><b>Expected:</b> {@code hasNext()} è {@code true} prima e dopo una chiamata a {@code next()} (finché ci sono elementi).
     */
    @Test
    public void testHasNext() {
        // Testa se hasNext funziona correttamente
        assert(iterator.hasNext());
        iterator.next(); // Avanza l'iteratore
        assert(iterator.hasNext());
    }

    /**
     * Verifica che il metodo {@code next()} restituisca correttamente gli elementi in ordine.
     * <br><b>Expected:</b> La prima chiamata a {@code next()} restituisce "Element 1", la seconda "Element 2".
     */
    @Test
    public void testNext() {
        // Testa se next restituisce il prossimo elemento correttamente
        assert("Element 1".equals(iterator.next()));
        assert("Element 2".equals(iterator.next()));
    }

    /**
     * Verifica che il metodo {@code next()} lanci {@code NoSuchElementException} quando non ci sono più elementi.
     * <br><b>Expected:</b> {@code next()} lancia {@code NoSuchElementException} dopo aver iterato tutti gli elementi.
     */
    @Test(expected = java.util.NoSuchElementException.class)
    public void testNextNoSuchElement() {
        // Testa se next lancia NoSuchElementException quando non ci sono più elementi
        while (iterator.hasNext()) {
            iterator.next();
        }
        iterator.next(); // Dovrebbe lanciare l'eccezione
    }

    /**
     * Verifica che il metodo {@code remove()} rimuova correttamente l'ultimo elemento restituito da {@code next()}.
     * <br><b>Expected:</b> Dopo la rimozione, "Element 1" non è più presente nella lista e la dimensione è 2.
     */
    @Test
    public void testRemove() {
        // Testa se remove funziona correttamente
        iterator.next(); // Avanza all'elemento "Element 1"
        iterator.remove(); // Rimuove "Element 1"
        
        assert(!listAdapter.contains("Element 1"));
        assert(listAdapter.size() == 2);
    }

    /**
     * Verifica che il metodo {@code remove()} lanci {@code IllegalStateException} se chiamato prima di {@code next()}.
     * <br><b>Expected:</b> {@code remove()} lancia {@code IllegalStateException} se non è stato chiamato {@code next()}.
     */
    @Test(expected = IllegalStateException.class)
    public void testRemoveIllegalState() {
        // Testa se remove lancia IllegalStateException se non è stato chiamato next()
        iterator.remove(); // Dovrebbe lanciare l'eccezione
    }
    /**
     * Metodo main per eseguire manualmente i test della classe.
     * Stampa a console il risultato di ciascun test.
     */
    public static void run() {
        long start = System.currentTimeMillis();
        org.junit.runner.Result result = org.junit.runner.JUnitCore.runClasses(TestAdapterIterator.class);
        long end = System.currentTimeMillis();
        System.out.println("====================================");
        System.out.println("Risultati dei test TestAdapterIterator:");
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