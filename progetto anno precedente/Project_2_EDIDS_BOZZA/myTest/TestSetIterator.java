package myTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import myAdapter.*;

/**
 * Test suite per la classe {@link myAdapter.KeySet.SetIterator}, che implementa {@link myAdapter.HIterator}
 * <p>
 * Summary: Verifica il corretto funzionamento dei metodi di un iteratore di una {@link myAdapter.HSet} implementata da {@link KeySet}
 * <p>
 * Design:  Si utilizzano JUnit 4.13.2, hamcrest-core-1.3 <br />
 *          La test suite include test cases per verificare il corretto e completo funzionamento dei metodi {@code hasNext()}, {@code next()}, e {@code remove()} <br />
 *          del SetIterator su KeySet vuoti e non vuoti. <br />
 *          Si suppongono funzionanti i metodi di {@link myAdapter.MapAdapter} e {@link myAdapter.KeySet}, di cui viene fornita un'istanza. <br />
 *          Prima di ogni test case si popola la mappa la cui vista delle chiavi verra' letta dall'iteratore.
 *          Nella descrizoine dei diversi testCase, e' implicita l'esecuzione della funzione setUp annotata con @Before e che suppongono testati e funzionanti i metodi <br />
 *          {@link myAdapter.MapAdapter#put put}, {@link myAdapter.MapAdapter#keySet() keySet} usati per la funzione setUp.
 */
public class TestSetIterator
{
    
    private MapAdapter map = new MapAdapter();
    private HSet testSet = map.keySet();
    private HIterator it;

    //METODO PER POPOLARE UNA MAPPA

    /**
     * Configuara l'ambiente dei test cases popolando la mappa la cui vista di chiavi verra' letta dal SetIterator.
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
        //SetIterator che legge la vista delle chiavi
        it = testSet.iterator();
    }

    //-------TEST DEL METODO hasNext() ----------
    
    /**
     * Test del metodo {@link myAdapter.KeySet.SetIterator#hasNext() hasNext} sulla vista delle chiavi di una mappa con elementi.
     * 
     * @s.ummary Verifica che il metodo {@code hasNext()} ritorna correttamente true quando ci sono elementi successivi nel KeySet, e falso altrimenti.
     * 
     * @d.esign    Si vuole analizzare il comportamento di {@code hasNext()} su un KeySet fatto di piu' elementi gia' prima della sua chiamata (restituzione affermativa {@code true}). <br />
     *              La chiamata di {@code hasNext()} su un KeySet vuoto o il cui iteratore ha percorso tutti gli elementi ci si aspetta ritorni {@code false}. <br />
     *              Ci si aspetta che la chiamata al metodo non comporti modifiche ne' al KeySet ne' all'iteratore.
     * 
     * @d.escription   Si suppongono testati e funzionanti il metodo {@code next()}, {@link myAdapter.KeySet#size() size}, {@link myAdapter.KeySet#contains contains} <br />
     *                  1.  Si verifica che hasNext() ritorni true.  <br />
     *                  2.  Si fa scorrere l'iteratore finche' ha oggetti da leggere. <br />
     *                  3.  Si verifica che ora hasNext() ritorni false.  <br />
     *                  4.  Si controlla che hashNext() su un KeySet vuoto ritorni false. <br />
     *                  5.  Si controlla che il KeySet letto dall'iteratore non sia stato modificato. <br />
     * 
     * @p.recond   La KeySet che viene letta dall'iteratore e' stata popolata e l'iteratore e' stato posizionato all'inizio del set
     * 
     * @p.ostcond  La KeySet letta dall'iteratore contiene ancora lo stesso numero di elementi.
     * 
     * @r.esult    {@code hasNext()} deve restuire {@code true} quando l'iteratore possiede ancora elementi da leggere, {@code false} quando non ci sono piu' elementi da leggere.
     * 
     */
    @Test
    public void test_SetIterator_HasNext()
    {        
        
        //Controllo che l'iteratore del testSet abbia elementi
        assertTrue("Mi assicuro che ci siano altri elementi nel set.", it.hasNext());
        while(it.hasNext())
        {
            it.next();    
        }    
        //controllo che non ci siano piu' elementi da leggere nel set
        assertFalse("Controllo che non ci siano piu' elementi nell'iteratore di testSet", it.hasNext());

        //creo una mappa vuota di supporto
        MapAdapter map2 = new MapAdapter();
        //controllo se un SetIterator che punta alla vista di una mappa vuota ha elementi 
        it = map2.keySet().iterator();
        //mi aspetto sia falso
        assertFalse(it.hasNext());
    
        //controllo che testSet contenga ancora tutti gli elementi iniziali
        assertEquals((long) 4, (long)testSet.size());
        assertTrue(testSet.contains(4) && testSet.contains(2) && testSet.contains(7) && testSet.contains(0));
        
    }
    
    //-------FINE TEST DEL METODO hasNext() ----------
    //-------TEST DEL METODO next() ----------
    
    /**
     * Test del metodo {@link myAdapter.KeySet.SetIterator#next() next} sulla vista delle chiavi di una mappa con elementi.
     * 
     * @s.ummary   Verifica che il metodo {@code next()} ritorna correttamente gli elementi del KeySet in un ordine non particolare e <br />
     *              lancia {@link NoSuchElementException} se iterato oltre la fine dell'iteratore del KeySet.
     * 
     * @d.esign    Si vuole analizzare il comportamento di {@code next()} su un KeySet fattp di piu' elementi gia' prima della sua chiamata. Verifico che restituisce tutti gli <br />
     *              elementi del KeySet come sono stati salvati sulla HashTable di MapAdapter, quindi in un ordine non preciso. <br />
     *              Verifico che lanci {@code NoSuchElementException} se iterato oltre la fine dell'iteratore.
     * 
     * @d.escription   Si suppongono testati e funzionanti il metodo {@code hasNext()}, {@link myAdapter.KeySet#size() size}, {@link myAdapter.KeySet#contains contains} <br />
     *                  1.  Si verifica che ogni elemento letto dall'iteratore appartenga al KeySet.  <br />
     *                  2.  Si verifica che il numero di elementi letti sia lo stesso del numero di elementi del KeySet. <br />
     *                  3.  Si chiama next() alla fine del set per verificare il lancio di NoSuchElementException.
     * 
     * @p.recond   La KeySet che viene letta dall'iteratore e' stata popolata e l'iteratore e' stato posizionato all'inizio del set
     * 
     * @p.ostcond  La KeySet letta dall'iteratore contiene ancora lo stesso numero di elementi.
     * 
     * @r.esult    {@code next()} deve restuire gli elementi del set in un ordine non definito e lanciare {@code NoSuchElementException} quando si cerca di iterarlo oltre la fine del set.
     */
    @Test (expected = NoSuchElementException.class)
    public void test_SetIterator_Next()
    {
        int i = 0;
        //controllo che gli elementi dell'iteratore sono contenuti nel set
        while(it.hasNext())
        {
            i += 1;
            Integer nextElem = (Integer)it.next();
            assertTrue("Controllo che l'elemento "+ nextElem + " appartiene al set.", testSet.contains(nextElem));
        }
        //controllo che i sia uguale a testSet.size()
        assertEquals((long) i, testSet.size());
        it.next();
    }

    /**
     * Test del metodo {@link myAdapter.KeySet.SetIterator#next() next} sulla vista delle chiavi di una mappa vuota.
     * 
     * @s.ummary   Verifica che il metodo {@code next()} lancia {@link NoSuchElementException} quando il KeySet da leggere e' vuoto.
     * 
     * @d.esign    Si vuole analizzare il comportamento di {@code next()}  quando chiamato su una vista di chiavi vuota.
     * 
     * @d.escription   1.  Si crea un nuovo iteratore che punta ad una vista di chiavi vuota.  <br />
     *                  2.  Si chiama next() per verificare il lancio di NoSuchElementException su KeySet vuoto.
     * 
     * @p.recond   La KeySet che viene letta dall'iteratore e' stata popolata e l'iteratore e' stato posizionato all'inizio del set
     * 
     * @p.ostcond  La KeySet e' vuota e il suo iteratore e' posizionato all'inizio di tale set.
     * 
     * @r.esult    {@code next()} deve lanciare {@code NoSuchElementException} se il set da leggere e' vuoto.
     */
    @Test (expected = NoSuchElementException.class)
    public void test_SetIterator_Next_NoSuchElem()
    {
        //creo una mappa vuota
        map = new MapAdapter();
        //e il suo iteratore
        it = map.keySet().iterator();
        //e provo a usare il metodo next()
        it.next();
    }
    
    //-------FINE TEST DEL METODO next() ----------
    //-------TEST DEL METODO remove() ----------
    
    /**
     * Test del metodo {@link myAdapter.KeySet.SetIterator#remove() remove} sulla vista delle chiavi di una mappa con elementi.
     * 
     * @s.ummary   Verifica che il metodo {@code remove()} rimuove correttamente l'ultimo elemento restituito da {@code next()} aggiornando in modo appropriato il set.
     * 
     * @d.esign    Si vuole analizzare che il comportamento di {@code remove()} si appoggi in modo adeguato al metodo {@link myAdapter.MapAdapter#remove remove} per la rimozione dell'ultimo elemento <br />
     *              restituito da {@code next()}.
     * 
     * @d.escription   Si suppongono funzionanti i metodi {@code next}, {@link myAdapter.MapAdapter#remove remove}, {@link myAdapter.KeySet#contains contains}, {@link myAdapter.KeySet#size() size} <br />
     *                  1.  Si chiama next() per far avanzare l'iteratore e si salva il risultato ottenuto.  <br />
     *                  2.  Salvo il numero di elementi del KeySet letto dall'iteratore. <br />
     *                  3.  Si chiama remove() e si verifica che l'elemento precedentemente restituito sia stato rimosso dal set. <br />
     *                  4.  Si verifica che la dimensione del set sia diminuita di 1.
     * 
     * @p.recond   La KeySet che viene letta dall'iteratore e' stata popolata e l'iteratore e' stato posizionato all'inizio del set
     * 
     * @p.ostcond  Alla KeySet letta e' stato eliminato un elemento tramite l'iteratore. La sua dimensione e' diminuita di 1.
     * 
     * @r.esult    {@code remove()} deve rimuovere correttamente l'elemento ritornato da {@code next()} e aggiornare la dimensione del set che legge.
     */
    @Test
    public void test_SetIterator_Remove()
    {
        
        //Salvo l'elemento next()
        Integer nextElem = (Integer)it.next();

        //salvo il numero di elementi contenuti in testSet
        int i = testSet.size();
        //Mi accerto che l'iteratore rimuova dal KeySet l'ultimo elemento restituito da next()
        it.remove();
        assertFalse("Controllo che l'elemento "+ nextElem + " appartenga piu' al set.", testSet.contains(nextElem));
        
        //controllo che la dimensione del set sia diminuita di 1
        assertEquals((long) i-1, (long)testSet.size());
    }

    /**
     * Test del metodo {@link myAdapter.KeySet.SetIterator#remove() remove} prima dell'invocazione del metodo next().
     * 
     * @s.ummary   Verifica che il metodo {@code remove()} lancia {@link IllegalStateException} quando viene chiamato senza prima chiamare {@code next()}.
     * 
     * @d.esign    Si vuole verificare il comportamento di {@code remove()} quando viene chiamato senza una chiamata intermedia, a quella di questo metodo, di {@code next()}: mi aspetto lanci una eccezione. <br />
     * 
     * @d.escription    1.  Si chiama remove() senza chiamare prima il metodo next(). <br />
     *                  2.  Ci si aspetta il lancio di IllegalStateException.
     * 
     * @p.recond   La KeySet che viene letta dall'iteratore e' stata popolata e l'iteratore e' stato posizionato all'inizio del set
     * 
     * @p.ostcond  La KeySet contiene ancora tutti gli elementi e l'iteratore e' ancora posizionato all'inizio del set
     * 
     * @r.esult    {@code remove()} deve lanciare {@code IllegalStateException} quando chiamato senza una chiamata intermedia a {@code next()}.
     */
    @Test(expected = IllegalStateException.class)
    public void test_SetIterator_Remove_IllegalState()
    {
        
        it.remove();
    }
    
    
}
