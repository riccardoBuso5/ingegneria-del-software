package myTest;

import myAdapter.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;



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

    @Test
    public void testHasNext() {
        assertTrue(it.hasNext());
        it.next();
        it.next();
        it.next();
        assertFalse(it.hasNext());
    }

    @Test
    public void testNext() {
        assertEquals("A", it.next());
        assertEquals("B", it.next());
        assertEquals("C", it.next());
    }

    @Test(expected = java.util.NoSuchElementException.class)
    public void testNextException() {
        it.next();
        it.next();
        it.next();
        it.next(); // should throw
    }

    @Test
    public void testHasPrevious() {
        assertFalse(it.hasPrevious());
        it.next();
        assertTrue(it.hasPrevious());
    }

    @Test
    public void testPrevious() {
        it.next();
        it.next();
        assertEquals("B", it.previous());
        assertEquals("A", it.previous());
    }

    @Test(expected = java.util.NoSuchElementException.class)
    public void testPreviousException() {
        it.previous(); // should throw
    }

    @Test
    public void testNextIndex() {
        assertEquals(0, it.nextIndex());
        it.next();
        assertEquals(1, it.nextIndex());
    }

    @Test
    public void testPreviousIndex() {
        assertEquals(-1, it.previousIndex());
        it.next();
        assertEquals(0, it.previousIndex());
    }

    @Test
    public void testRemove() {
        it.next();
        it.remove();
        assertEquals(2, list.size());
        assertEquals("B", list.get(0));
    }

    @Test(expected = IllegalStateException.class)
    public void testRemoveException() {
        it.remove(); // should throw
    }

    @Test
    public void testSet() {
        it.next();
        it.set("Z");
        assertEquals("Z", list.get(0));
    }

    @Test(expected = IllegalStateException.class)
    public void testSetException() {
        it.set("Z"); // should throw
    }

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