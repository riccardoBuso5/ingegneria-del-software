package myTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import myAdapter.*;

/**
 * Test suite per la classe {@link myAdapter.MapAdapter.EntrySet.EntrySetIterator}, che implementa {@link myAdapter.HIterator}
 * <p>
 * Summary: Verifica il corretto funzionamento dei metodi di un iteratore di una {@link myAdapter.HSet} implementata da {@link myAdapter.MapAdapter.EntrySet}
 * <p>
 * Design:  Si utilizzano JUnit 4.13.2, hamcrest-core-1.3 <br />
 *          La test suite include test cases per verificare il corretto e completo funzionamento dei metodi {@code hasNext()}, {@code next()}, e {@code remove()} <br />
 *          del EntrySetIterator su EntrySet vuoti e non vuoti. <br />
 *          Si suppongono funzionanti i metodi di {@link myAdapter.MapAdapter} e {@link myAdapter.MapAdapter.EntrySet}, di cui viene fornita un'istanza. <br />
 *          Prima di ogni test case si popola la mappa la cui vista delle entry verra' letta dall'iteratore.<br />
 *          Nella descrizoine dei diversi testCase, e' implicita l'esecuzione della funzione setUp annotata con @Before e che suppongono testati e funzionanti i metodi <br />
 *          {@link myAdapter.MapAdapter#put put}, {@link myAdapter.MapAdapter#entrySet() entrySet} usati per la funzione setUp.
 */
public class TestEntrySetIterator 
{
    private MapAdapter map = new MapAdapter();
    private HSet testEntrySet = map.entrySet();
    private HIterator it;

    //METODO PER POPOLARE UNA MAPPA
    
    /**
     * Configuara l'ambiente dei test cases popolando la mappa la cui vista di entry verra' letta dal EntrySetIterator.
     * 
     */
    @Before
    public void setUp()
    {
        //popolo la mappa vuota
        map.put(4, "one");
        map.put(2, "flower");
        map.put(7, "plum");
        map.put(0, "kiss");
        //EntrySetIterator che legge la vista delle entry
        it = testEntrySet.iterator();
    }

    //-------TEST DEL METODO hasNext() ----------
    
    /**
     * Test del metodo {@link myAdapter.MapAdapter.EntrySet.EntrySetIterator#hasNext() hasNext} sulla vista delle entry di una mappa con elementi.
     * 
     * @s.ummary Verifica che il metodo {@code hasNext()} ritorna correttamente true quando ci sono elementi successivi nel EntrySet, e falso altrimenti.
     * 
     * @d.esign     Si vuole analizzare il comportamento di {@code hasNext()} su un EntrySet fatto di piu' elementi gia' prima della sua chiamata (restituzione affermativa {@code true}). <br />
     *              La chiamata di {@code hasNext()} su un EntrySet vuoto o il cui iteratore ha percorso tutti gli elementi ci si aspetta ritorni {@code false}. <br />
     *              Ci si aspetta che la chiamata al metodo non comporti modifiche ne' al EntrySet ne' all'iteratore.
     * 
     * @d.escription   Si suppongono testati e funzionanti il metodo {@code next()}, {@link myAdapter.MapAdapter.EntrySet#size() size}, {@link myAdapter.MapAdapter.EntrySet#toArray() toArray},{@link myAdapter.MapAdapter.EntrySet#contains contains} <br />
     *                  1.  Si salvano le entry contenute sentro EntrySet. <br />
     *                  2.  Si verifica che hasNext() ritorni true.  <br />
     *                  3.  Si fa scorrere l'iteratore finche' ha oggetti da leggere. <br />
     *                  4.  Si verifica che ora hasNext() ritorni false.  <br />
     *                  5.  Si controlla che hashNext() su un EntrySet vuoto ritorni false. <br />
     *                  6.  Si controlla che il EntrySet letto dall'iteratore non sia stato modificato. <br />
     * 
     * @p.recond   La EntrySet che viene letta dall'iteratore e' stata popolata e l'iteratore e' stato posizionato all'inizio del set
     * 
     * @p.ostcond  La EntrySet letta dall'iteratore contiene ancora lo stesso numero di elementi.
     * 
     * @r.esult    {@code hasNext()} deve restuire {@code true} quando l'iteratore possiede ancora elementi da leggere, {@code false} quando non ci sono piu' elementi da leggere.
     * 
     */
    @Test
    public void test_EntrySetIterator_HasNext()
    {        
        //salvo gli elementi iniziali di testEntrySet
        Object[] a = testEntrySet.toArray();
        //Controllo che l'iteratore del testEntrySet abbia elementi
        assertTrue("Mi assicuro che ci siano altri elementi nella vista.", it.hasNext());
        while(it.hasNext())
        {
            it.next();    
        }    
        //controllo che non ci siano piu' elementi nella vista
        assertFalse("Controllo che non ci siano piu' elementi nell'iteratore di testEntrySet", it.hasNext());
    
        //creo una mappa vuota di supporto
        MapAdapter map2 = new MapAdapter();
        //controllo se un EntrySetIterator che punta alla vista di una mappa vuota ha elementi 
        it = map2.entrySet().iterator();
        //mi aspetto sia falso
        assertFalse(it.hasNext());

        //controllo che testEntrySet contenga ancora tutti gli elementi iniziali
        assertEquals((long) a.length, (long)testEntrySet.size());
        for(int i = 0; i < a.length; i++)
        {
            assertTrue(testEntrySet.contains(a[i]));
        }
    }

    //-------FINE TEST DEL METODO hasNext() ----------
    //-------TEST DEL METODO next() ----------
    
    /**
     * Test del metodo {@link myAdapter.MapAdapter.EntrySet.EntrySetIterator#next() next} sulla vista delle entry di una mappa con elementi.
     * 
     * @s.ummary   Verifica che il metodo {@code next()} ritorna correttamente gli elementi del EntrySet in un ordine non particolare e <br />
     *              lancia {@link NoSuchElementException} se iterato oltre la fine del EntrySet.
     * 
     * @d.esign     Si vuole analizzare il comportamento di {@code next()} su un EntrySet fatto di piu' elementi gia' prima della sua chiamata. Verifico che restituisce tutti gli <br />
     *              elementi del EntrySet come sono stati salvati sulla HashTable di MapAdapter, quindi in un ordine non preciso. <br />
     *              Verifico che lanci {@code NoSuchElementException} se iterato oltre la fine dell'iteratore.
     * 
     * @d.escription   Si suppongono testati e funzionanti il metodo {@code hasNext()}, {@link myAdapter.MapAdapter.EntrySet#size() size}, {@link myAdapter.MapAdapter.EntrySet#contains contains} <br />
     *                  1.  Si verifica che ogni elemento letto dall'iteratore appartenga al EntrySet.  <br />
     *                  2.  Si verifica che il numero di elementi letti sia lo stesso del numero di elementi del EntrySet. <br />
     *                  3.  Si chiama next() alla fine del set per verificare il lancio di NoSuchElementException.
     * 
     * @p.recond   La EntrySet che viene letta dall'iteratore e' stata popolata e l'iteratore e' stato posizionato all'inizio del set
     * 
     * @p.ostcond  La EntrySet letta dall'iteratore contiene ancora lo stesso numero di elementi.
     * 
     * @r.esult    {@code next()} deve restuire gli elementi del set in un ordine non definito e lanciare {@code NoSuchElementException} quando si cerca di iterarlo oltre la fine del set.
     */
    @Test (expected = NoSuchElementException.class)
    public void test_EntrySetIterator_Next()
    {
        int i = 0;
        //controllo che gli elementi dell'iteratore sono contenuti nel set
        while(it.hasNext())
        {
            i += 1;
            HEntry nextElem = (HEntry)it.next();
            //controllo che la entry nextElem appartiene a testEntrySet
            assertTrue(testEntrySet.contains(nextElem));
        }
        //controllo che i sia uguale a testEntrySet.size()
        assertEquals((long) i, (long) testEntrySet.size());
        it.next();
    }

    /**
     * Test del metodo {@link myAdapter.MapAdapter.EntrySet.EntrySetIterator#next() next} sulla vista delle entry di una mappa vuota.
     * 
     * @s.ummary   Verifica che il metodo {@code next()} lancia {@link NoSuchElementException} quando il EntrySet da leggere e' vuoto.
     * 
     * @d.esign     Si vuole analizzare il comportamento di {@code next()}  quando chiamato su una vista di entry vuota.
     * 
     * @d.escription   1.  Si crea un nuovo iteratore che punta ad una vista di entry vuota.  <br />
     *                  2.  Si chiama next() per verificare il lancio di NoSuchElementException su EntrySet vuoto.
     * 
     * @p.recond   La EntrySet che viene letta dall'iteratore e' stata popolata e l'iteratore e' stato posizionato all'inizio del set
     * 
     * @p.ostcond  La EntrySet e' vuota e il suo iteratore e' posizionato all'inizio di tale set.
     * 
     * @r.esult    {@code next()} deve lanciare {@code NoSuchElementException} se il set da leggere e' vuoto.
     */
    @Test (expected = NoSuchElementException.class)
    public void test_EntrySetIterator_Next_NoSuchElem()
    {
        //creo una mappa vuota
        map = new MapAdapter();
        //e il suo iteratore
        it = map.entrySet().iterator();
        //e provo a usare il metodo next()
        it.next();
    }
    
    //-------FINE TEST DEL METODO next() ----------
    //-------TEST DEL METODO remove() ----------
    
    /**
     * Test del metodo {@link myAdapter.MapAdapter.EntrySet.EntrySetIterator#remove() remove} sulla vista delle entry di una mappa con elementi.
     * 
     * @s.ummary   Verifica che il metodo {@code remove()} rimuove correttamente l'ultimo elemento restituito da {@code next()} aggiornando in modo appropriato il set.
     * 
     * @d.esign     Si vuole analizzare che il comportamento di {@code remove()} si appoggi in modo adeguato al metodo {@link myAdapter.MapAdapter#remove remove} per la rimozione dell'ultimo elemento <br />
     *              restituito da {@code next()}.
     * 
     * @d.escription   Si suppongono funzionanti i metodi {@code next}, {@link myAdapter.MapAdapter#remove remove}, {@link myAdapter.MapAdapter.EntrySet#contains contains}, {@link myAdapter.MapAdapter.EntrySet#size() size} <br />
     *                  1.  Si chiama next() per far avanzare l'iteratore e si salva il risultato ottenuto.  <br />
     *                  2.  Salvo il numero di elementi del EntrySet letto dall'iteratore. <br />
     *                  3.  Si chiama remove() e si verifica che l'elemento precedentemente restituito sia stato rimosso dal set. <br />
     *                  4.  Si verifica che la dimensione del set sia diminuita di 1.
     * 
     * @p.recond   La EntrySet che viene letta dall'iteratore e' stata popolata e l'iteratore e' stato posizionato all'inizio del set
     * 
     * @p.ostcond  Alla EntrySet letta e' stato eliminato un elemento tramite l'iteratore. La sua dimensione e' diminuita di 1.
     * 
     * @r.esult    {@code remove()} deve rimuovere correttamente l'elemento ritornato da {@code next()} e aggiornare la dimensione del set che legge.
     */
    @Test
    public void test_EntrySetIterator_Remove()
    {
        
        //salvo l'elemento next()
        HEntry nextElem = (HEntry)it.next();

        //salvo il numero di elementi contenuti in testEntrySet
        int i = testEntrySet.size();
        //Mi accerto che l'iteratore rimuova dal EntrySet l'ultimo elemento restituito da next()
        it.remove();
        //controllo che la entry nextElem non appartiene piu' a testEntrySet
        assertFalse(testEntrySet.contains(nextElem));

        //controllo che la dimensione del set sia diminuita di 1
        assertEquals((long) i-1, (long)testEntrySet.size());
    }

    /**
     * Test del metodo {@link myAdapter.MapAdapter.EntrySet.EntrySetIterator#remove() remove} prima dell'invocazione del metodo next().
     * 
     * @s.ummary   Verifica che il metodo {@code remove()} lancia {@link IllegalStateException} quando viene chiamato senza prima chiamare {@code next()}.
     * 
     * @d.esign     Si vuole verificare il comportamento di {@code remove()} quando viene chiamato senza una chiamata intermedia, a quella di questo metodo, di {@code next()}: mi aspetto lanci una eccezione. <br />
     * 
     * @d.escription    1.  Si chiama remove() senza chiamare prima il metodo next(). <br />
     *                  2.  Ci si aspetta il lancio di IllegalStateException.
     * 
     * @p.recond   La EntrySet che viene letta dall'iteratore e' stata popolata e l'iteratore e' stato posizionato all'inizio del set
     * 
     * @p.ostcond  La EntrySet contiene ancora tutti gli elementi e l'iteratore e' ancora posizionato all'inizio del set
     * 
     * @r.esult    {@code remove()} deve lanciare {@code IllegalStateException} quando chiamato senza una chiamata intermedia a {@code next()}.
     */
    @Test(expected = IllegalStateException.class)
    public void test_EntrySetIterator_Remove_IllegalState()
    { 
        it.remove();
    }
    
    //-------FINE TEST DEL METODO remove() ----------
    

}
