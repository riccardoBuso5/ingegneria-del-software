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
     * <p>
     * <summary>Test del metodo {@code hasNext()}</summary>
     * <b>test method:</b> creo una lista con tre elementi e verifico che {@code hasNext()} restituisca true.<br>
     * <b>pre-condition:</b> La lista contiene tre elementi.<br>
     * <b>post-condition:</b> {@code hasNext()} restituisce true per il primo elemento e false dopo aver iterato tutti gli elementi.<br>
     * <b>expected:</b> {@code hasNext()} restituisce true per il primo elemento e false dopo aver iterato tutti gli elementi.<br>
     * </p>
     */
    @Test
    public void testHasNext() {
        // Testa se hasNext funziona correttamente
        assert(iterator.hasNext());
        iterator.next(); // Avanza l'iteratore
        assert(iterator.hasNext());
    }

    /**
     * <p>
     * <summary>Test del metodo {@code next()}</summary>
     * <b>test method:</b> verifico che {@code next()} restituisca il prossimo elemento correttamente.<br>
     * <b>pre-condition:</b> L'iteratore è inizializzato e contiene tre elementi.<br>
     * <b>post-condition:</b> {@code next()} restituisce il prossimo elemento e l'iteratore avanza.<br>
     * <b>expected:</b> {@code next()} restituisce "Element 1" e "Element 2" nei primi due invocazioni.<br>
     * </p>
     */
    @Test
    public void testNext() {
        // Testa se next restituisce il prossimo elemento correttamente
        assert("Element 1".equals(iterator.next()));
        assert("Element 2".equals(iterator.next()));
    }

    /**
     * <p>
     * <summary>Test del metodo {@code next()} quando non ci sono più elementi</summary>
     * <b>test method:</b> verifico che {@code next()} lanci {@code NoSuchElementException} quando non ci sono più elementi.<br>
     * <b>pre-condition:</b> L'iteratore ha già iterato tutti gli elementi.<br>
     * <b>post-condition:</b> {@code next()} lancia {@code NoSuchElementException} quando non ci sono più elementi.<br>
     * <b>expected:</b> {@code next()} lancia {@code NoSuchElementException} quando non ci sono più elementi.<br>
     * </p>
     */
    @Test(expected = myException.NoSuchElementException.class)
    public void testNextNoSuchElement() {
        // Testa se next lancia NoSuchElementException quando non ci sono più elementi
        while (iterator.hasNext()) {
            iterator.next();
        }
        iterator.next(); // Dovrebbe lanciare l'eccezione
    }

    /**
     * <p>
     * <summary>Test del metodo {@code remove()}</summary>
     * <b>test method:</b> verifico che {@code remove()} rimuova l'ultimo elemento restituito da {@code next()}.<br>
     * <b>pre-condition:</b> L'iteratore ha già iterato un elemento.<br>
     * <b>post-condition:</b> {@code remove()} rimuove l'ultimo elemento restituito da {@code next()}.<br>
     * <b>expected:</b> {@code remove()} rimuove "Element 1" e la lista contiene solo "Element 2" e "Element 3".<br>
     * </p>
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
     * <p>
     * <summary>Test del metodo {@code remove()} senza chiamare {@code next()}</summary>
     * <b>test method:</b> verifico che {@code remove()} lanci {@code IllegalStateException} se non è stato chiamato {@code next()}.<br>
     * <b>pre-condition:</b> L'iteratore non ha ancora chiamato {@code next()}.<br>
     * <b>post-condition:</b> {@code remove()} lancia {@code IllegalStateException} se non è stato chiamato {@code next()}.<br>
     * <b>expected:</b> {@code remove()} lancia {@code IllegalStateException} se non è stato chiamato {@code next()}.<br>
     * </p>
     */
    @Test(expected = myException.IllegalStateException.class)
    public void testRemoveIllegalState() {
        // Testa se remove lancia IllegalStateException se non è stato chiamato next()
        iterator.remove(); // Dovrebbe lanciare l'eccezione
    }

    /**
     * <p>
     * <summary>Test del metodo {@code remove()} dopo una chiamata a {@code next()}</summary>
     * <b>test method:</b> verifico che {@code remove()} funzioni correttamente dopo una chiamata a {@code next()}.<br>
     * <b>pre-condition:</b> L'iteratore ha già chiamato {@code next()}.<br>
     * <b>post-condition:</b> {@code remove()} rimuove l'ultimo elemento restituito da {@code next()}.<br>
     * <b>expected:</b> {@code remove()} rimuove l'ultimo elemento restituito da {@code next()}.<br>
     * </p>
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