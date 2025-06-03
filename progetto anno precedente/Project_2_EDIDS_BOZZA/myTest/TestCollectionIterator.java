package myTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import myAdapter.MapAdapter;
import myAdapter.ValueCollection;
import myAdapter.HCollection;
import myAdapter.HIterator;

/**
 * Test suite per la classe {@link myAdapter.ValueCollection.CollectionIterator}, che implementa {@link myAdapter.HIterator}
 * <p>
 * Summary: Verifica il corretto funzionamento dei metodi di un iteratore di una {@link myAdapter.HCollection} implementata da {@link ValueCollection}.
 * <p>
 * Design:  Si utilizzano JUnit 4.13.2, hamcrest-core-1.3 <br />
 *          La test suite include test cases per verificare il corretto e completo funzionamento dei metodi {@code hasNext()}, {@code next()}, e {@code remove()} <br />
 *          del CollectionIterator su ValueCollection vuoti e non vuoti. <br />
 *          Si suppongono funzionanti i metodi di {@link myAdapter.MapAdapter} e {@link myAdapter.ValueCollection}, di cui viene fornita un'istanza. <br />
 *          Prima di ogni test case si popola la mappa la cui vista delle chiavi verra' letta dall'iteratore.
 *          Nella descrizoine dei diversi testCase, e' implicita l'esecuzione della funzione setUp annotata con @Before e che suppongono testati e funzionanti i metodi <br />
 *          {@link myAdapter.MapAdapter#put put}, {@link myAdapter.MapAdapter#values() values} usati per la funzione setUp.
 */
public class TestCollectionIterator 
{
    private MapAdapter map = new MapAdapter();
    private HCollection testCol = map.values();
    private HIterator it;

    //METODO PER POPOLARE UNA MAPPA
    
    /**
     * Configuara l'ambiente dei test cases popolando la mappa la cui vista dei valori verra' letta dal CollectionIterator.
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
        it = testCol.iterator();
    }

    //-------TEST DEL METODO hasNext() ----------
    
    /**
     * Test del metodo {@link myAdapter.ValueCollection.CollectionIterator#hasNext() hasNext} sulla vista dei valori di una mappa con elementi.
     * 
     * @s.ummary Verifica che il metodo {@code hasNext()} ritorna correttamente true quando ci sono elementi successivi nel ValueCollection, e false altrimenti.
     * 
     * @d.esign    Si vuole analizzare il comportamento di {@code hasNext()} su un ValueCollection fatto di piu' elementi gia' prima della sua chiamata (restituzione affermativa {@code true}). <br />
     *              La chiamata di {@code hasNext()} su un ValueCollection vuoto. o il cui iteratore ha percorso tutti gli elementi, ci si aspetta ritorni {@code false}. <br />
     *              Ci si aspetta che la chiamata al metodo non comporti modifiche ne' al ValueCollection ne' all'iteratore.
     * 
     * @d.escription   Si suppongono testati e funzionanti il metodo {@code next()}, {@link myAdapter.ValueCollection#size() size}, {@link myAdapter.ValueCollection#contains contains} <br />
     *                  1.  Si verifica che hasNext() ritorni true.  <br />
     *                  2.  Si fa scorrere l'iteratore finche' ha oggetti da leggere. <br />
     *                  3.  Si verifica che ora hasNext() ritorni false.  <br />
     *                  4.  Si controlla che hashNext() su un ValueCollection vuoto ritorni false. <br />
     *                  5.  Si controlla che il ValueCollection letto dall'iteratore non sia stato modificato. <br />
     * 
     * @p.recond   La ValueCollection che viene letta dall'iteratore e' stata popolata e l'iteratore e' stato posizionato all'inizio della collezione
     * 
     * @p.ostcond  La ValueCollection letta dall'iteratore contiene ancora lo stesso numero di elementi.
     * 
     * @r.esult    {@code hasNext()} deve restuire {@code true} quando l'iteratore possiede ancora elementi da leggere, {@code false} quando non ci sono piu' elementi da leggere.
     * 
     */
    @Test
    public void test_CollectionIterator_HasNext()
    {        
        
        //Controllo che l'iteratore del testCol abbia elementi
        assertTrue("Mi assicuro che ci siano altri elementi nel Collection.", it.hasNext());
        while(it.hasNext())
        {
            it.next();
        }    
        //controllo che non ci siano piu' elementi nella collezione
        assertFalse("Controllo che non ci siano pie' elementi nell'iteratore di testCol", it.hasNext());
    
        //creo una mappa vuota di supporto
        MapAdapter map2 = new MapAdapter();
        //controllo se un CollectionIterator che punta alla vista di una mappa vuota ha elementi 
        it = map2.values().iterator();
        //mi aspetto sia falso
        assertFalse(it.hasNext());
    
        //controllo che testCol contenga ancora tutti gli elementi iniziali
        assertEquals((long) 4, (long)testCol.size());
        assertTrue(testCol.contains("one") && testCol.contains("flower") && testCol.contains("plum") && testCol.contains("kiss"));
      
    }
    
    //-------FINE TEST DEL METODO hasNext() ----------
    //-------TEST DEL METODO next() ----------
    
    /**
     * Test del metodo {@link myAdapter.ValueCollection.CollectionIterator#next() next} sulla vista dei valori di una mappa con elementi.
     * 
     * @s.ummary   Verifica che il metodo {@code next()} ritorna correttamente gli elementi del ValueCollection in un ordine non particolare e <br />
     *              lancia {@link NoSuchElementException} se iterato oltre la fine dell'iteratore di ValueCollection.
     * 
     * @d.esign    Si vuole analizzare il comportamento di {@code next()} su un ValueCollection fatto di piu' elementi gia' prima della sua chiamata. Verifico che restituisce tutti gli <br />
     *              elementi del ValueCollection come sono stati salvati sulla HashTable di MapAdapter, quindi in un ordine non preciso. <br />
     *              Verifico che lanci {@code NoSuchElementException} se iterato oltre la fine dell'iteratore.
     * 
     * @d.escription   Si suppongono testati e funzionanti il metodo {@code hasNext()}, {@link myAdapter.ValueCollection#size() size}, {@link myAdapter.ValueCollection#contains contains} <br />
     *                  1.  Si verifica che ogni elemento letto dall'iteratore appartenga al ValueCollection.  <br />
     *                  2.  Si verifica che il numero di elementi letti sia lo stesso del numero di elementi del ValueCollection. <br />
     *                  3.  Si chiama next() alla fine della collezione per verificare il lancio di NoSuchElementException.
     * 
     * @p.recond   La ValueCollection che viene letta dall'iteratore e' stata popolata e l'iteratore e' stato posizionato all'inizio della collezione
     * 
     * @p.ostcond  La ValueCollection letta dall'iteratore contiene ancora lo stesso numero di elementi.
     * 
     * @r.esult    {@code next()} deve restuire gli elementi della collezione in un ordine non definito e lanciare {@code NoSuchElementException} quando si cerca di iterarlo oltre la fine della collezione.
     */
    @Test (expected = NoSuchElementException.class)
    public void test_CollectionIterator_Next()
    {
        int i = 0;
        //controllo che gli elementi dell'iteratore sono contenuti nel Collection
        while(it.hasNext())
        {
            i +=1;
            String nextElem = (String)it.next();
            assertTrue("Controllo che l'elemento "+ nextElem + " appartiene al Collection.", testCol.contains(nextElem));
        }
        //controllo che i sia uguale a testCol.size()
        assertEquals((long) i, testCol.size());
        it.next();
    }

    /**
     * Test del metodo {@link myAdapter.ValueCollection.CollectionIterator#next() next} sulla vista dei valori di una mappa vuota.
     * 
     * @s.ummary   Verifica che il metodo {@code next()} lancia {@link NoSuchElementException} quando il ValueCollection da leggere e' vuoto.
     * 
     * @d.esign    Si vuole analizzare il comportamento di {@code next()}  quando chiamato su una vista di chiavi vuota.
     * 
     * @d.escription   1.  Si crea un nuovo iteratore che punta ad una vista di valori vuota.  <br />
     *                  2.  Si chiama next() per verificare il lancio di NoSuchElementException su ValueCollection vuoto.
     * 
     * @p.recond   La ValueCollection che viene letta dall'iteratore e' stata popolata e l'iteratore e' stato posizionato all'inizio della collezione
     * 
     * @p.ostcond  La ValueCollection e' vuota e il suo iteratore e' posizionato all'inizio di tale collezione.
     * 
     * @r.esult    {@code next()} deve lanciare {@code NoSuchElementException} se la collezione da leggere e' vuota.
     */
    @Test (expected = NoSuchElementException.class)
    public void test_CollectionIterator_Next_NoSuchElem()
    {
        
        //creo una mappa vuota
        map = new MapAdapter();
        //e il suo iteratore
        it = map.values().iterator();
        //e provo a usare il metodo next()
        it.next();
    }
    
    //-------FINE TEST DEL METODO next() ----------
    //-------TEST DEL METODO remove() ----------
    
    /**
     * Test del metodo {@link myAdapter.ValueCollection.CollectionIterator#remove() remove} sulla vista dei valori di una mappa con elementi.
     * 
     * @s.ummary   Verifica che il metodo {@code remove()} rimuove correttamente l'ultimo elemento restituito da {@code next()} aggiornando in modo appropriato il set.
     * 
     * @d.esign    Si vuole analizzare che il comportamento di {@code remove()} si appoggi in modo adeguato al metodo {@link myAdapter.MapAdapter#remove remove} per la rimozione dell'ultimo elemento <br />
     *              restituito da {@code next()}.
     * 
     * @d.escription   Si suppongono funzionanti i metodi {@code next}, {@link myAdapter.MapAdapter#remove remove}, {@link myAdapter.ValueCollection#contains contains}, {@link myAdapter.ValueCollection#size() size} <br />
     *                  1.  Si chiama next() per far avanzare l'iteratore e si salva il risultato ottenuto.  <br />
     *                  2.  Si salva il numero di elementi del ValueCollection letto dall'iteratore. <br />
     *                  3.  Si chiama remove() e si verifica che l'elemento precedentemente restituito sia stato rimosso dalla collezione. <br />
     *                  4.  Si verifica che la dimensione della collezione sia diminuita di 1.
     * 
     * @p.recond   La ValueCollection che viene letta dall'iteratore e' stata popolata e l'iteratore e' stato posizionato all'inizio della collezione.
     * 
     * @p.ostcond  Alla ValueCollection letta e' stato eliminato un elemento tramite l'iteratore. La sua dimensione e' diminuita di 1.
     * 
     * @r.esult    {@code remove()} deve rimuovere correttamente l'elemento ritornato da {@code next()} e aggiornare la dimensione della collezione che legge.
     */
    @Test
    public void test_CollectionIterator_Remove()
    {
        
        //salvo l'elemento next()
        String nextElem = (String)it.next();

        //salvo il numero di elementi contenuti in testCol
        int i = testCol.size();
        //Mi accerto che l'iteratore rimuova dal ValueCollection l'ultimo elemento restituito da next()
        it.remove();
        assertFalse("Controllo che l'elemento "+ nextElem + " appartenga piu' al Collection.", testCol.contains(nextElem));
    
        //controllo che la dimensione della collezione sia diminuita di 1
        assertEquals((long) i-1, (long)testCol.size());
    }

    /**
     * Test del metodo {@link myAdapter.ValueCollection.CollectionIterator#remove() remove} prima dell'invocazione del metodo next().
     * 
     * @s.ummary   Verifica che il metodo {@code remove()} lancia {@link IllegalStateException} quando viene chiamato senza prima chiamare {@code next()}.
     * 
     * @d.esign    Si vuole verificare che il comportamento di {@code remove()} quando viene chiamato senza una chiamata intermedia, a quella di questo metodo, di {@code next()} lanci una eccezione. <br />
     * 
     * @d.escription    1.   Si chiama remove() senza chiamare prima il metodo next(). <br />
     *                  2.   Ci si aspetta il lancio di IllegalStateException.
     * 
     * @p.recond   La ValueCollection che viene letta dall'iteratore e' stata popolata e l'iteratore e' stato posizionato all'inizio della collezione
     * 
     * @p.ostcond  La ValueCollection contiene ancora tutti gli elementi e l'iteratore e' ancora posizionato all'inizio della collezione
     * 
     * @r.esult    {@code remove()} deve lanciare {@code IllegalStateException} quando chiamato senza una chiamata intermedia a {@code next()}.
     */
    @Test(expected = IllegalStateException.class)
    public void test_CollectionIterator_Remove_IllegalState()
    {
        it.remove();
    }
    
    //-------FINE TEST DEL METODO remove() ----------
       
}
