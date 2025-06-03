package myTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.Test;
import myAdapter.*;

/**
 * Test suite per la classe {@link myAdapter.ValueCollection}.
 * <p>
 * Summary: Verifica il corretto funzionamento dei metodi della classe {@code ValueCollection} che implementa l'interfaccia {@link myAdapter.HCollection}
 * <p>
 * Design:  Si utilizzano JUnit 4.13.2, hamcrest-core-1.3 <br />
 *          La test suite include test cases di metodi di accesso, modifica e interrogazione di una {@link myAdapter.HCollection} <br />
 *          tramite la classe {@link myAdapter.ValueCollection}, che implementa l'interfaccia. <br />
 *          Si suppone testato e funzionante il backing tra mappa e vista dei valori, di classe {@link myAdapter.ValueCollection}, che verra' testato in {@link myTest.TestMapAdapter}. <br />
 *          Si suppongono funzionanti i metodi di {@link myAdapter.MapAdapter}. <br /> 
 *          Nella descrizoine dei diversi testCase, e' implicita l'esecuzione della funzione setUp annotata con @Before che popola una mappa per ottenere la vista dei valori <br />
 *          e che si suppone testato e funzionante il metodo {@link myAdapter.MapAdapter#put put}, usata per la funzione setUp.
 */
public class TestValueCollection
{
    private MapAdapter map = new MapAdapter();
    private HCollection testCol = map.values();

    //METODO PER POPOLARE UNA MAPPA

    /**
     * Configuara l'ambiente dei test cases popolando la mappa la cui vista dei valori verra' manipolata.
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
    }

    //-------TEST DEL METODO size() ----------

    /**
     * Test del metodo {@link myAdapter.ValueCollection#size() size} su una vista dei valori di una mappa.
     * 
     * @s.ummary Verifica che il metodo {@code size()} ritorna correttamente la dimensione della collezione.
     * 
     * @d.esign    Si vuole verificare che {@code size()} si appoggi correttamente al metodo {@link myAdapter.MapAdapter#size() size} e ritorni la corretta dimensione della collezione senza causare errori.
     * 
     * @d.escription   Si suppongono testati e funzionanti il metodo {@link myAdapter.MapAdapter#size() size}, {@code clear()}, <br />
     *                  1.  Si controlla che la collezione sia della stessa dimensione della mappa. <br />
     *                  2.  Si controlla che la dimensione della collezione sia quella prevista. <br />
     *                  3.  Si svuota la collezione e si controlla che la dimensione diventi 0. <br />
     * 
     * @p.recond   La vista dei valori della mappa e' stata popolata con 4 elementi.
     * 
     * @p.ostcond  La vista dei valori e' stata svuotata.
     * 
     * @r.esult    {@code size()} deve restuire la dimensione della collezione, il numero degli elementi che la collezione contiene.
     * 
     */
    @Test
    public void test_Collection_Size()
    {   
        //Verifico che il Collection di valori sia della stessa dimensione della mappa
        assertEquals("Se il numero di elementi nel Collection e' uguale al numero di elementi della mappa, ritorna True.",(long) testCol.size(),(long)map.size());
        assertEquals((long) 4, testCol.size());

        //svuoto la collezione
        testCol.clear();;
        //controllo ora che la collezione abbia size == 0
        assertTrue(testCol.size() == 0);
    }

    //-------FINE TEST DEL METODO size() ----------
    //-------TEST DEL METODO isEmpty() ----------

    /**
     * Test del metodo {@link myAdapter.ValueCollection#isEmpty() isEmpty} su una vista dei valori di una mappa.
     * 
     * @s.ummary Verifica che il metodo {@code isEmpty()} restituisca correttamente {@code true} quando la collezione e' vuota, {@code false} altrimenti.
     * 
     * @d.esign    Si vuole verificare che {@code isEmpty()} si appoggi correttamente al metodo {@link myAdapter.MapAdapter#isEmpty() isEmpty} e <br />
     *              restituisca correttamente {@code true} quando la collezione e' vuota, {@code false} se la collezione contiene almeno un elemento.
     * 
     * @d.escription   Si suppongono testati e funzionanti il metodo {@code size()}, {@code clear()}, <br />
     *                  1.  Si controlla che la collezione inizialmente non sia vuota, isEmpty() restituisce false, e che abbia la dimensione attesa. <br />
     *                  2.  Si svuota la collezione. <br />
     *                  3.  Si verifica che isEmpty() restituisca true e che la dimensione attesa sia 0. <br />
     * 
     * @p.recond   La vista dei valori della mappa e' stata popolata con 4 elementi.
     * 
     * @p.ostcond  La vista dei valori e' stata svuotata.
     * 
     * @r.esult    {@code isEmpty()} deve restuire {@code true} se e solo se la collezione e' priva di elementi, {@code false} altrimenti.
     * 
     */
    @Test
    public void test_Collection_IsEmpty()
    {
        //verifico che la collezione non sia vuota, infatti ha dimensione 4
        assertFalse("Controllo che la collezione non sia vuota.", testCol.isEmpty());
        assertEquals((long)4, (long)testCol.size());
        //svuoto la collezione
        testCol.clear();
        //verifico che la collezoine ora sia vuota, di dimensione 0
        assertTrue("Controllo che la collezione e' vuota.", testCol.isEmpty());
        assertEquals((long)0, (long)testCol.size());
    }

    //-------FINE TEST DEL METODO isEmpty() ----------
    //-------TEST DEL METODO contains() ----------
    
    /**
     * Test del metodo {@link myAdapter.ValueCollection#contains contains} nel caso di argomento {@code null}
     * 
     * @s.ummary   Verifica che il metodo {@code contains()} lancia {@link NullPointerException} se fornito un argomento {@code null}.
     * 
     * @d.esign    Si vuole verificare che alla chiamata del metodo {@code contains()} nel caso di argomento {@code null} venga correttamente <br />
     *              lanciata l'eccezione {@link NullPointerException}.    
     * 
     * @d.escription   Si esegue direttamente il metodo contains fornendo come argomento null e si verifica che venga lanciata un'eccezione NullPointerException.
     * 
     * @p.recond   La vista dei valori della mappa e' stata popolata con 4 elementi.
     * 
     * @p.ostcond  La vista dei valori rimane invariata.
     * 
     * @r.esult    {@code contains()} deve lanciare {@link NullPointerException} se chiamata con argomento {@code null}.
     * 
     */
    @Test(expected = NullPointerException.class)
    public void test_COllection_Contains_Null() //Contains con argomento value null
    {
        //testo la presenza che l'elemento null sia presente, aspetto una eccezione
        testCol.contains(null);
    }

    /**
     * Test del metodo {@link myAdapter.ValueCollection#contains contains} su una collezione contenente elementi e non.
     * 
     * @s.ummary   Verifica che il corretto funzionamento del metodo {@code contains()} su una collezione vuota e non.
     * 
     * @d.esign    Si vuole verificare che il metodo {@code contains()} si appoggi correttamente al metodo {@link myAdapter.MapAdapter#containsValue(Object) containsValue} <br />
     *              e che restituisca {@code true} se il valore posto come argomento e' contenuto nella collezione, {@code false} altrimenti. 
     * 
     * @d.escription   Si suppone testato e funzionante il metodo {@code clear()} <br />
     *                  1.  Si controlla che la collezione contenga uno dei valori della mappa che e' stata popolata in fase di setUp <br />
     *                  2.  Si verifica che contains() restituisca true per il valore comparato. <br />
     *                  3.  Si verifica che contains() restituisca false per un oggetto non presente nella collezione  <br />
     *                  4.  Si svuota la collezione e si verifica che contains() restituisca false per qualsiasi oggetto se la collezione e' vuota.
     * 
     * @p.recond   La vista dei valori della mappa e' stata popolata con 4 elementi.
     * 
     * @p.ostcond  La vista dei valori e' stata svuotata.
     * 
     * @r.esult    {@code contains()} deve restituire {@code true} se l'argomento e' presente nella collezione, {@code false} altrimenti.
     * 
     */
    @Test
    public void test_Collection_Contains()
    {
        //controllo che l'elemento "flower" sia contenuto nela collection dei valori
        assertTrue("Se la collezione contiene l'elemento \"flower\", ritorna true", testCol.contains("flower"));
        
        //Verifico che l'elemento 9 non sia contenuto nel set di chiavi
        assertFalse("La collezione non contiene l'elemento \"squid\"", testCol.contains("squid"));

        //svuoto la collezione
        testCol.clear();
        //controllo che non a prescindere dall'oggetto posto come argomento, restituisce false
        assertFalse("La collezione e quindi non contiene nessun oggetto", testCol.contains(new Object()));

    }   

    //-------FINE TEST DEL METODO contains() ----------
    //-------TEST DEL METODO iterator() ----------
    
    /**
     * Test del metodo {@link myAdapter.ValueCollection#iterator() iterator} su una collezione contenente elementi e non.
     * 
     * @s.ummary   Verifica che il metodo {@code iterator()} restituisca un iteratore sulla collezione.
     * 
     * @d.esign    Si vuole verificare che il metodo {@code contains()} restituisca un'istanza di {@link myAdapter.HIterator}. <br />
     *              L'iteratore potra' leggere gli elementi della collezione e rimuovere elementi dalla collezione.
     * 
     * @d.escription   Si suppone testata e funzionante la classe {@link myAdapter.ValueCollection.CollectionIterator} e il metodo {@code contains()}. <br />
     *                  1.  Si verifica che l'oggetto restituito alla chiamata di iterator() sia un'istanza di HIterator. <br />
     *                  2.  Si crea un ciclo while che continua finche' l'iteratore ha elementi da leggere. <br />
     *                  3.  Dentro al ciclo while si legge l'elemento successivo della vista e si verifica che sia presente nella collezione. <br />
     *                  4.  Si rimuove l'elemento appena letto tramite l'iteratore e si controlla che non sia piu' contenuto nella collezione. <br />
     *                  5.  Alla fine del ciclo while la collezione e' vuota e si controlla che l'iteratore di una collezione vuota non abbia elementi successivi. <br />
     * 
     * @p.recond   La vista dei valori della mappa e' stata popolata con 4 elementi.
     * 
     * @p.ostcond  La vista dei valori e' stata svuotata.
     * 
     * @r.esult    {@code iterator()} deve restituire un'istanza di {@code HIterator} che compie correttamente i metodi descritti in {@link myAdapter.HIterator}.
     * 
     */
    @Test
    public void test_Collection_Iterator() //Controllo che effettivamente l'iteratore della collezione sia un iteratore
    {
        //mi assicuro che l'iteratore della collezione sia un iteratore
        assertTrue(testCol.iterator() instanceof HIterator);

        //controllo che funzioni l'iteratore
        HIterator it = testCol.iterator();
        while(it.hasNext())
        {
            Object v = it.next();
            assertTrue(testCol.contains(v));
            it.remove();
            assertFalse(testCol.contains(v));
        }
        assertTrue(testCol.size() == 0);
        //ora la collezione e' vuota, verifico che l'iteratore ad una collezione vuota non abbia elementi successivi
        it = testCol.iterator();
        assertFalse(it.hasNext());
    }
    
    //-------FINE TEST DEL METODO iterator() ----------
    //-------TEST DEL METODO toArray() ----------

    /**
     * Test del metodo {@link myAdapter.ValueCollection#toArray() toArray} su una collezione contenente elementi e non.
     * 
     * @s.ummary   Verifica la corretta conversione di una collezione in array di oggetti. L'ordine non e' preciso.
     * 
     * @d.esign    Si vuole verificare che il metodo {@code toArray()} crei e popoli correttamente un array di {@link Object} <br />
     *              della giusta dimensione e con tutti gli elementi contenuti nella collezione, senza causare errori.
     * 
     * @d.escription   Si suppone testato e funzionante il metodo {@code size()}, {@code contains()}. <br />
     *                  1.  Si verifica che una chiamata a toArray() e si salva l'array restituito. <br />
     *                  2.  Si controlla che la dimensione dell'array cosi' ottenuto sia uguale a quella della dimensione. <br />
     *                  3.  Si verifica che la collezione contiene tutti gli elementi contenuti nell'array. <br />
     *                  4.  Si svuota la collezione e si dimostra che alla chiamata di toArray() su una collezione vuota viene restituito un array Object vuoto.
     * 
     * @p.recond   La vista dei valori della mappa e' stata popolata con 4 elementi.
     * 
     * @p.ostcond  La vista dei valori e' stata svuotata.
     * 
     * @r.esult    {@code toArray()} restituisce un array di tipo Object della stessa dimensione della collezione e contenente tutti gli elementi della collezione.
     * 
     */
    @Test
    public void test_Collection_ToArray()
    {
        Object[] a = testCol.toArray();
        //controllo che la collezione e l'array abbiano la stessa dimensione
        assertEquals(testCol.size(), testCol.toArray().length);
        //controllo che la collezione e l'array abbiano gli stessi elementi
        for(int i = 0; i < a.length; i++)
        {
            assertTrue(testCol.contains(a[i]));
        }

        //svuoto la collezione
        testCol.clear();
        //controllo che la dimensione dell'array ritornato da toArray() sia 0
        assertTrue(testCol.toArray().length == 0);
    }

    /**
     * Test del metodo {@link myAdapter.ValueCollection#toArray() toArray}.
     * 
     * @s.ummary   Verifica che l'array ritornato da {@code toArray()} non contenga reference agli oggetti contenuti nella collezione.
     * 
     * @d.esign    Si vuole verificare che il metodo {@code toArray()} non restituisca un array contenente reference agli oggetti della collezione
     * 
     * @d.escription   Si suppone testato e funzionante il metodo {@code contains()}. <br />
     *                  1.  Si chiama il metodo toArray() e si salva l'array ottenuto. <br />
     *                  2,  Si cambia il valore contenuto nel primo elemento dell'array. <br />
     *                  3.  Si verifica che il nuovo valore del primo elemento dell'array non sia contenuto nella collezione, la collezione e' rimasta invariata.
     * 
     * @p.recond   La vista dei valori della mappa e' stata popolata con 4 elementi.
     * 
     * @p.ostcond  La vista dei valori e' rimasta invariata.
     * 
     * @r.esult    {@code toArray()} deve restituire un array che non contiene reference agli oggetti della collezione.
     * 
     */
    @Test
    public void test_Collection_ToArray_NoReference()
    {
        //controllo che l'array non abbia reference agli elementi della collezione
        Object[] a = testCol.toArray();
        //cambio valore al primo elemento di a
        a[0] = "leopardo";
        //testCol non e' stato modificato
        assertFalse(testCol.contains(a[0]));  
    }

    //-------FINE TEST DEL METODO toArray() ----------
    //-------TEST DEL METODO toArray(Object[] a) ----------

    /**
     * Test del metodo {@link myAdapter.ValueCollection#toArray(Object[] a) toArray(Object[] a)} quando {@code a} e' {@code null}.
     * 
     * @s.ummary   Verifica che venga lanciata l'eccezione {@link NullPointerException} quando l'array fornito e' {@code null}.
     * 
     * @d.esign    Si vuole verificare che il metodo {@code toArray(Object[] a)} lanci correttamente {@link NullPointerException} quando <br />
     *              l'array fornito {@code a} e' {@code null}. 
     * 
     * @d.escription   1.  Si chiama direttamente toArray(null) sulla collezione. <br />
     *                  2.  Si verifica il corretto lancio di NullPointerException
     * 
     * @p.recond   La vista dei valori della mappa e' stata popolata con 4 elementi.
     * 
     * @p.ostcond  La vista dei valori e' rimasta invariata.
     * 
     * @r.esult    {@code toArray(Object[] a)} deve lanciare {@link NullPointerException} quando {@code a} e' {@code null}. 
     * 
     */
    @Test (expected = NullPointerException.class)
    public void test_Collection_ToArray_Null()
    {
        testCol.toArray(null);
    }

    /**
     * Test del metodo {@link myAdapter.ValueCollection#toArray(Object[] a) toArray(Object[] a)} quando {@code a} non e' di tipo accettabile.
     * 
     * @s.ummary   Verifica che venga lanciata l'eccezione {@link ArrayStoreException} quando l'array fornito non e' di un supertipo di tutti gli elementi presenti nella collezione.
     * 
     * @d.esign    Si vuole verificare che il metodo {@code toArray(Object[] a)} lanci correttamente {@link ArrayStoreException} quando <br />
     *              l'array fornito non e' di un supertipo di tutti gli elementi presenti nella collezione. 
     * 
     * @d.escription   1.  Si crea un array di un tipo incompatibile con gli elementi della collezione e di dimensione maggiore della collezione. <br />
     *                  2.  Si chiama toArray() fornendo come parametro l'array appena creato. <br />
     *                  3.  Si verifica che avvenga il lancio di ArrayStoreException.
     * 
     * @p.recond   La vista dei valori della mappa e' stata popolata con 4 elementi.
     * 
     * @p.ostcond  La vista dei valori e' rimasta invariata.
     * 
     * @r.esult    {@code toArray(Object[] a)} deve lanciare {@link ArrayStoreException} quando {@code a} non e' di un supertipo di tutti gli elementi presenti nella collezione. 
     * 
     */
    @Test(expected = ArrayStoreException.class)
    public void test_Collection_ToArray_StoreException()
    {
        Object[] a = new Integer[9];
        //testCol e' fatto di valori String quindi non posso salvarle dentro un array Integer
        testCol.toArray(a);
    }

    /**
     * Test del metodo {@link myAdapter.ValueCollection#toArray(Object[] a) toArray(Object[] a)} quando {@code a} e' di dimensioni maggiori o uguali alla collezione.
     * 
     * @s.ummary   Verifica il funzionamento del metodo {@code toArray(Object[] a)} su una collezione contenente elementi e non.
     * 
     * @d.esign    Si vuole verificare che il metodo {@code toArray(Object[] a)} popola direttamente l'array {@code a} fornito come argomento <br />
     *              Con gli oggetti della collezione. Se {@code a} e' di dimensione maggiore alla collezione allora i primi {@code c1.size()} <br />
     *              valori sono i valori della collezione e i rimanenti sono valori null.
     * 
     * @d.escription   Si suppongono testati e funzionanti i metodi {@code clear()}, {@code size()}, {@code contains()} <br />
     *                  1.  Si crea un array Object di dimensione maggiore della collezione. <br />
     *                  2.  Si chiama toArray() fornendo come parametro l'array appena creato e si verifica che l'array ritornato sia uguale all'array precedentemente creato. <br />
     *                  3.  Si verifica che i primi {@code c1.size()} elementi dell'array creato sono contenuti nella collezione e che i rimanenti siano impostati a null. <br />
     *                  4.  Si svuota la collezione e si verifica che dopo la chiamata di toArray() usando come argomento l'array creato al punto 1., questo contenga solo elementi null.
     * 
     * @p.recond   La vista dei valori della mappa e' stata popolata con 4 elementi.
     * 
     * @p.ostcond  La vista dei valori e' stata svuotata.
     * 
     * @r.esult    {@code toArray(Object[] a)} riempie l'array fornito come argomento con gli oggetti della collezione, senza modificarne la dimensione.
     * 
     */
    @Test
    public void test_Collection_ToArray_FitInsideTrue()
    {
        //controllo che se l'array specificato da toArray(Object[] a) e' abbastanza grande
        //Allora ritorno l'array relativo alla collezione dentro a
        Object[] a = new Object[10];
        assertTrue(a.equals(testCol.toArray(a)));
        //Controllo che a contenga tutti gli elementi di testCol e per indici di valore maggiore alla dimensione della collezione che contenga null
        for(int i = 0; i < a.length; i++)
        {
            if(i < testCol.size())
            {
                assertTrue(testCol.contains(a[i]));
            }
            else
            {
                assertTrue(a[i] == null);
            }
        }

        //svuoto la collezione
        testCol.clear();
        //controllo che dopo la chiamata di toArray(a) a contenga solo elementi null
        testCol.toArray(a);
        for(int i = 0; i < a.length; i++)
        {
            assertTrue(a[i] == null); 
        }

    }

    /**
     * Test del metodo {@link myAdapter.ValueCollection#toArray(Object[] a) toArray(Object[] a)} quando {@code a} e' di dimensioni minori della collezione.
     * 
     * @s.ummary   Verifica che il metodo {@code toArray(Object[] a)} restituisca un array Object di opportune dimensioni con gli elementi della collezione.
     * 
     * @d.esign    Si vuole verificare che il metodo {@code toArray(Object[] a)} restituisca un array di Objects di dimensioni appropriate <br />
     *              contenente gli oggetti della collezione, quando {@code a} e' di dimensioni inferiori alla collezione. {@code a} non deve essere modificato.
     * 
     * @d.escription   Si suppongono testati e funzionanti i metodi {@code size()}, {@code contains()} <br />
     *                  1.  Si crea un array Object di dimensione minore della collezione. <br />
     *                  2.  Si chiama toArray() fornendo come parametro l'array appena creato e si verifica che l'array ritornato non sia uguale all'array precedentemente creato. <br />
     *                  3.  Si verifica che l'array ritornato nel punto 2. sia delle stesse dimensioni della collezione e contenga tutti gli elementi della collezione.
     * 
     * @p.recond   La vista dei valori della mappa e' stata popolata con 4 elementi.
     * 
     * @p.ostcond  La vista dei valori e' stata svuotata.
     * 
     * @r.esult    {@code toArray(Object[] a)} restituisce un nuovo array delle dimensioni appropriate e contenente tutti gli elementi della collezione se {@code a} e' di dimensione minore della collezione.
     * 
     */
    @Test
    public void test_Collection_ToArray_FitInsideFalse()
    {
        //Controllo che se l'array specificato a non e' abbastanza grande da contenere la collezione allora restituisco un nuovo array
        Object[] a = new Object[1];
        Object[] b = testCol.toArray(a);
        //controllo che l'array ritornato dal metodo non sia quello precedentemente creato
        assertFalse(a.equals(b));
        //controllo che b abbia la stessa dimensione della collezione e contenga gli stessi elementi
        assertTrue(b.length == testCol.size());
        for(int i = 0; i < a.length; i++)
        {
            assertTrue(testCol.contains(b[i]));
        }
    }

    //-------FINE TEST DEL METODO toArray(Object[] a) ----------
    //-------TEST DEL METODO add() ----------

    /**
     * Test del metodo {@link myAdapter.ValueCollection#add add)}.
     * 
     * @s.ummary   Verifica che venga lanciata l'eccezione {@link UnsupportedOperationException} alla chiamata del metodo add su una collezione.
     * 
     * @d.esign    Si vuole verificare che il metodo {@code add()} lanci correttamente {@link UnsupportedOperationException} quando <br />
     *              viene chiamato da una collezione. 
     * 
     * @d.escription   1.  Si chiama direttamente add() sulla collezione. <br />
     *                  2.  Si verifica il corretto lancio di UnsupportedOperationException
     * 
     * @p.recond   La vista dei valori della mappa e' stata popolata con 4 elementi.
     * 
     * @p.ostcond  La vista dei valori e' rimasta invariata.
     * 
     * @r.esult    {@code add()} deve lanciare {@link UnsupportedOperationException} se chiamata da una collezione.
     * 
     */
    @Test (expected = UnsupportedOperationException.class)
    public void test_Collection_Add()
    {
        testCol.add( "Ginocchio");
    }

    //-------FINE TEST DEL METODO add() ----------
    //-------TEST DEL METODO remove(Object o) ----------

    /**
     * Test del metodo {@link myAdapter.ValueCollection#remove(Object o) remove} quando l'argomento fornito e' {@code null}. 
     * 
     * @s.ummary   Verifica che venga lanciata l'eccezione {@link NullPointerException} quando l'oggetto fornito e' {@code null}.
     * 
     * @d.esign    Si vuole verificare che il metodo {@code remove(Object o)} lanci correttamente {@link NullPointerException} quando <br />
     *              l'argomento fornito e' {@code null}. 
     * 
     * @d.escription   1.  Si chiama direttamente remove(null) sulla collezione. <br />
     *                  2.  Si verifica il corretto lancio di NullPointerException
     * 
     * @p.recond   La vista dei valori della mappa e' stata popolata con 4 elementi.
     * 
     * @p.ostcond  La vista dei valori e' rimasta invariata.
     * 
     * @r.esult    {@code remove(Object o)} deve lanciare {@link NullPointerException} quando l'argomento e' {@code null}. 
     * 
     */
    @Test(expected = NullPointerException.class)
    public void test_Collection_Remove_Null()
    {
        testCol.remove(null);
    }

    /**
     * Test del metodo {@link myAdapter.ValueCollection#remove(Object o) remove} con {@code o} presente e non dalla collezione. 
     * 
     * @s.ummary   Verifica la corretta rimozione di un istanza di un elemento dalla collezione, se presente.
     * 
     * @d.esign    Si vuole verificare che il metodo {@code remove(Object o)} restituisca correttamente {@code true} quando l'oggetto specificato <br />
     *              e' presente nella collezione e una sua istanza viene rimossa correttamente, {@code false} altrimenti.
     * 
     * @d.escription   Si suppongono testati e funzionanti i metodi {@code contains()} <br />
     *                  1.  Si verifica che la collezione contenga un elemento noto. <br />
     *                  2.  Si invoca remove() usando come argomento l'elemento noto e si verifica che restituisca {@code true}. <br />
     *                  3.  Si verifica che l'elemento noto non sia piu' presente nella collezione. <br />
     *                  4.  Si invoca remove() usando come argomento un elemento non presente nella collezione e si verifica che restituisca {@code false}. 
     * 
     * @p.recond   La vista dei valori della mappa e' stata popolata con 4 elementi.
     * 
     * @p.ostcond  La vista dei valori e' stata modificata, ha perso un elemento.
     * 
     * @r.esult    {@code remove(Object o)} verifica se l'elemento specificato e' presente nella collezione, se presente <br />
     *              ritorna {@code true} e rimuove dalla collezione una delle istanze di quell'elemento, altrimenti ritorna {@code false}.
     */
    @Test
    public void test_Collection_Remove()
    {
        //controllo che la collezione contenga il valore "one"
        assertTrue(testCol.contains("one"));
        //rimuovo il valore "one" dalla collezione
        assertTrue(testCol.remove("one"));
        //controllo che la collezione non contenga piu' il valore "one"
        assertFalse(testCol.contains("one"));   

        //controllo che la collezione non cambia se si prova a rimuovere il valore "Marzapane"  
        assertFalse(testCol.remove("Marzapane"));
    }

    /**
     * Test del metodo {@link myAdapter.ValueCollection#remove(Object o) remove} con {@code o} presente piu' di una volta nella collezione. 
     * 
     * @s.ummary   Verifica la corretta rimozione di un istanza di un elemento dalla collezione che contiene piu' istanze di quell'elemento.
     * 
     * @d.esign    Si vuole verificare che il metodo {@code remove(Object o)} rimuova correttamente solo una delle istanze dell'elemento specifiacto <br />
     *              senza rimuoverne gli altri. La dimensione della collezione deve diminuire di 1 ma contenere ancora l'elemento specificato.
     * 
     * @d.escription   Si suppongono testati e funzionanti i metodi {@code contains()}, {@code size()} <br />
     *                  1.  Si verifica che la collezione contenga un elemento noto e si salva la dimensione della collezione. <br />
     *                  2.  Si aggiunge un nuovo elemento di valore uguale al valore noto presente nella collezione, la collezione contiene piu' istanze del valore noto. <br />
     *                  3.  Si controlla che la dimensione della collezione sia cresciuta di 1. <br />
     *                  4.  Si invoca remove() usando come argomento l'elemento noto. <br />
     *                  5.  Si verifica che l'elemento noto sia ancora presente nella collezione e che la dimensione della collezione sia quella di partenza calcolata nel punto 1. <br />
     * 
     * @p.recond   La vista dei valori della mappa e' stata popolata con 4 elementi.
     * 
     * @p.ostcond  La vista dei valori e' stata modificata. E' stato aggiunto e tolto un elemento.
     * 
     * @r.esult    {@code remove(Object o)} deve rimuove solo una istanza dell'elemento da rimuovere, lasciando le altre invariate.
     */
    @Test
    public void test_Collection_RemoveDuplicate()
    {
        //controllo che la collezione contenga il valore "one" e salvo la sua dimensione
        assertTrue(testCol.contains("one"));
        int i = testCol.size();
        //aggiungo un altra coppia chiave-valore alla mappa di cui la collezione e' vista di valore "one"
        map.put(55, "one");
        //controllo che la dimensione della collezione sia aumentata
        assertTrue((i+1) == testCol.size());
        //rimuovo il valore "one" dalla collezione
        assertTrue(testCol.remove("one"));
        //controllo che la collezione contenga ancora il valore "one"
        assertTrue(testCol.contains("one"));   
        //controllo che la dimensione della collezione sia diminuita di 1
        assertTrue(i == testCol.size());
    }

    //-------FINE TEST DEL METODO remove(Object o) ----------
    //-------TEST DEL METODO containsAll(HCollection c) ----------

    /**
     * Test del metodo {@link myAdapter.ValueCollection#containsAll containsAll} quando l'argomento fornito e' {@code null}. 
     * 
     * @s.ummary   Verifica che venga lanciata l'eccezione {@link NullPointerException} quando la collezione fornita e' {@code null}.
     * 
     * @d.esign    Si vuole verificare che il metodo {@code containsAll(HCollection c)} lanci correttamente {@link NullPointerException} quando <br />
     *              l'argomento fornito e' {@code null}. 
     * 
     * @d.escription   1.  Si esegue direttamente containsAll(null) e si verifica venga lanciata un'eccezione NullPointerException 
     * 
     * @p.recond   La vista dei valori della mappa e' stata popolata con 4 elementi.
     * 
     * @p.ostcond  La vista dei valori e' rimasta invariata.
     * 
     * @r.esult    {@code containsAll(HCollection c)} deve lanciare {@link NullPointerException} quando l'argomento e' {@code null}. 
     * 
     */
    @Test (expected = NullPointerException.class)
    public void test_Collection_ContainsAll_Null()
    {
        testCol.containsAll(null);
    }

    /**
     * Test del metodo {@link myAdapter.ValueCollection#containsAll(HCollection c) containsAll}.
     * 
     * @s.ummary   Verifica che venga il metodo {@code containsAll()} si comporti nel modo atteso sia nel caso gli elementi siano tutti contenuti, sia non.
     * 
     * @d.esign    Si vuole verificare che il metodo {@code containsAll(HCollection c)} verifichi correttamente la presenza di una collezione di oggetti al suo interno <br />
     *              restituendo {@code false} quando anche un solo oggetto dovesse mancare, {@code true} se tutti gli elementi della collezione specificata sono contenuti nella collezione chiamante. 
     * 
     * @d.escription   Si suppongono testati e funzionanti i metodi {@link myAdapter.MapAdapter#values() values}, {@link myAdapter.MapAdapter#put put} <br /> 
     *                  1.  Si istanzia una seconda collezione vuota. <br />
     *                  2.  Si chiama containsAll() usando come argomento la collezione vuota e si verifica che il metodo restituisca true. <br />
     *                  3.  Si popola la mappa e di conseguenza si modifica la collezione creata al punto 1. <br />
     *                  4.  Si chiama containsAll() usando come argomento la collezione appena riempita e si verifica che restituisca true. <br />
     *                  5.  Si aggiunge un ulteriore elemento alla collezione ausiliaria, che non appartiene anche alla collezione chiamante. <br />
     *                  6.  Si chiama containsAll() usando come argomento la collezione ausiliaria ora contenente un elemento non contenuto nella collezione chiamante. <br />
     *                  7   Si verifica che il metodo restituisca false in quanto non tutti gli elementi della collezione ausiliaria sono contenuti nella collezione chiamante
     * 
     * @p.recond   La vista dei valori della mappa e' stata popolata con 4 elementi.
     * 
     * @p.ostcond  La vista dei valori e' rimasta invariata.
     * 
     * @r.esult    Il metodo {@code containsAll(HCollection c)} deve restituire {@code false} se manca anche solo un oggetto, {@code true} se tutti gli oggetti sono presenti.
     * 
     */
    @Test
    public void test_Collection_ContainsAll()
    {
        //creo una nuova mappa
        MapAdapter map2 = new MapAdapter();
        //creo una collezione a partire dalla mappa
        HCollection contained = map2.values();

        //controllo che testCol contenga la collezione vuota contained
        assertTrue(testCol.containsAll(contained));

        //popolo la mappa 2
        map2.put(2, "one");
        map2.put(4, "kiss");
        //controllo che testCol contenga entrambi i valori di contained
        assertTrue(testCol.containsAll(contained));

        //aggiungo un elemento alla map2 che non appartiene a map
        map2.put(64, "low");
        //ora contained contiene 3 elementi, 2 sono anche contenuti in testCol, 1 no

        //controllo che testCol non contenga tutto contained
        assertFalse(testCol.containsAll(contained));
    }

    //-------FINE TEST DEL METODO containsAll(HCollection c) ----------
    //-------TEST DEL METODO addAll(HCollection c) ----------

    /**
     * Test del metodo {@link myAdapter.ValueCollection#addAll(HCollection c) addAll(HCollection c)}.
     * 
     * @s.ummary   Verifica che venga lanciata l'eccezione {@link UnsupportedOperationException} alla chiamata del metodo {@code addAll(HCollection c)} su una collezione.
     * 
     * @d.esign    Si vuole verificare che il metodo {@code addAll(HCollection c)} lanci correttamente {@link UnsupportedOperationException} quando <br />
     *              viene chiamato da una collezione. 
     * 
     * @d.escription   Si suppongono testati e funzionanti i metodi {@link myAdapter.MapAdapter#values() values}, {@link myAdapter.MapAdapter#put put} <br />
     *                  1.  Si crea una collezione ausiliaria. <br />
     *                  2.  Si chiama il metodo addAll(HCollection c) usando come argomento la collezione ausiliaria. <br />
     *                  3.  Si verifica il corretto lancio di UnsupportedOperationException
     * 
     * @p.recond   La vista dei valori della mappa e' stata popolata con 4 elementi.
     * 
     * @p.ostcond  La vista dei valori e' rimasta invariata.
     * 
     * @r.esult    {@code addAll(HCollection c)} deve lanciare {@link UnsupportedOperationException} se chiamata da una collezione.
     * 
     */
    @Test (expected = UnsupportedOperationException.class)
    public void test_Collection_AddAll()
    {
        //Creo una nuova collezione e provo ad aggiungerlo a testCol
        MapAdapter map2 = new MapAdapter();
        map2.put(9, "");
        HCollection addedCol = map2.values();
        //aggiungo la collezione a testCol
        testCol.addAll(addedCol);
    }

    //-------FINE TEST DEL METODO addAll(HCollection c) ----------
    //-------TEST DEL METODO removeAll(HCollection c) ----------
    
    /**
     * Test del metodo {@link myAdapter.ValueCollection#removeAll(HCollection c) removeAll} quando l'argomento fornito e' {@code null}. 
     * 
     * @s.ummary   Verifica che venga lanciata l'eccezione {@link NullPointerException} quando la collezione fornita e' {@code null}.
     * 
     * @d.esign    Si vuole verificare che il metodo {@code removeAll(HCollection c)} lanci correttamente {@link NullPointerException} quando <br />
     *              l'argomento fornito e' {@code null}. 
     * 
     * @d.escription   1.  Si chiama direttamente removeAll(null) sulla collezione. <br />
     *                  2.  Si verifica il corretto lancio di NullPointerException
     * 
     * @p.recond   La vista dei valori della mappa e' stata popolata con 4 elementi.
     * 
     * @p.ostcond  La vista dei valori e' rimasta invariata.
     * 
     * @r.esult    {@code removeAll(HCollection c)} deve lanciare {@link NullPointerException} quando l'argomento e' {@code null}. 
     * 
     */
    @Test(expected = NullPointerException.class)
    public void test_Collection_RemoveAll_Null()
    {
        testCol.removeAll(null);
    }

    /**
     * Test del metodo {@link myAdapter.ValueCollection#removeAll removeAll} quando le due collezione hanno elementi in comune. 
     * 
     * @s.ummary   Verifica la corretta rimozione di un'intera collezione dalla collezione di partenza, se hannoo elementi in comune.
     * 
     * @d.esign    Si vuole verificare che il metodo {@code removeAll(HCollection c)} restituisca correttamente {@code true} quando la collezione specificata <br />
     *              contiene anche elementi presenti nella collezione chiamante e ne rimuove tutte le istanze, {@code false} se le due collezioni non hanno elementi in comune.
     * 
     * @d.escription   Si suppongono testati e funzionanti i metodi {@code iterator()}, {@code size()}, {@code remove()}, {@code contains()} <br />
     *                  1.  Si crea una collezione di supporto uguale alla collezione di partenza. <br />
     *                  2.  Si rimuove un elemento dalla collezione ausiliaria in modo che le due collezioni non siano piu' uguali. <br />
     *                  3.  Si verifica che alla chiamata removeAll() usando come argomento la collezione ausiliaria il risultato sia true. <br />
     *                  4.  Si controlla che le due collezioni non abbiano piu' elementi in comune. <br />
     *                  5.  Si verifica che l'unico elemento presente nella collezione di partenza e' quello precedentemente rimosso dalla collezione di supporto. 
     * 
     * @p.recond   La vista dei valori della mappa e' stata popolata con 4 elementi.
     * 
     * @p.ostcond  La vista dei valori e' stata modificata, e' rimasto un solo elemento.
     * 
     * @r.esult    {@code removeAll(HCollection c)} deve restituire {@code true} se la collezione viene modificata come risultato della chiamata del metodo <br />
     *              e {@code false} altrimenti.
     * 
     */
    @Test
    public void test_Collection_RemoveAll_True()
    {
        //creo una nuova collezione e la popola per poi rimuovere i suoi elementi da testCol
        MapAdapter map2 = new MapAdapter(map);
        HCollection toRemove = map2.values();            //creo una copia del testCol
        toRemove.remove("kiss");                       //rimuovo dalla copia un elemento
        
        //controllo che testCol venga modificato dalla chiamata del metodo removeAll
        assertTrue(testCol.removeAll(toRemove));     //rimuovo dal testCol la copia

        //controllo che le due collezioni non abbiano elementi in comune
        HIterator it = toRemove.iterator();
        while(it.hasNext())
        {
            //il testCol non contiene piu' elementi contenuti anche nella copia
            assertFalse(testCol.contains(it.next()));  
        }

        //l'unico valore che testCol contiene e' quello precedentemente rimosso dalla copia
        assertTrue(testCol.contains("kiss"));      
        assertTrue(testCol.size() == 1);
    }

    /**
     * Test del metodo {@link myAdapter.ValueCollection#removeAll removeAll} quando le due collezione non hanno elementi in comune. 
     * 
     * @s.ummary   Verifica che se le due collezioni non hanno elementi in comune il metodo {@code removeAll(HCollection c)} <br />
     *              restituisce {@code false} e la collezione di partenza non cambia
     * 
     * @d.esign    Si vuole verificare che il metodo {@code removeAll(HCollection c)} restituisca correttamente {@code false} quando la collezione specificata <br />
     *               non ha elementi in comune con la collezione chiamante.
     * 
     * @d.escription   Si suppongono testati e funzionanti i metodi {@code iterator()}, {@code contains()} <br />
     *                  1.  Si crea una collezione di supporto di un elemento, diverso dalla collezione di partenza. <br />
     *                  2.  Si controlla che le due collezioni non abbiano elementi in comune. <br />
     *                  3.  Si verifica che alla chiamata removeAll() usando come argomento la collezione ausiliaria il risultato sia false. <br />
     *                  4.  Si verifica che la collezione di partenza non sia stata modificata.
     * 
     * @p.recond   La vista dei valori della mappa e' stata popolata con 4 elementi.
     * 
     * @p.ostcond  La vista dei valori rimasta invariata.
     * 
     * @r.esult    {@code removeAll(HCollection c)} deve restituire {@code false} se le due collezioni non presentano elementi in comune.
     *  
     */
    @Test
    public void test_Collection_RemoveAll_False()
    {
        //creo una nuova collezione e la popolo per poi rimuovere i suoi elementi da testCol
        MapAdapter map2 = new MapAdapter();
        map2.put(15, "sigaro");

        //creo una collezione che non ha elementi in comune con testCol
        HCollection toRemove = map2.values();  

        //controllo che le due collezioni non abbiano elementi in comune
        HIterator it = toRemove.iterator();
        while(it.hasNext())
        {
            //il testCol non contiene piu' elementi contenuti anche nella copia
            assertFalse(testCol.contains(it.next()));  
        }

        //controllo che testCol non cambia alla chiamata del metodo removeAll
        assertFalse(testCol.removeAll(toRemove));     
        assertTrue(testCol.contains("one") && testCol.contains("flower") && testCol.contains("plum") && testCol.contains("kiss"));  
    }

    //-------FINE TEST DEL METODO removeAll(HCollection c) ----------
    //-------TEST DEL METODO retainAll(HCollection c) ----------

    /**
     * Test del metodo {@link myAdapter.ValueCollection#retainAll retainAll} quando l'argomento fornito e' {@code null}. 
     * 
     * @s.ummary   Verifica che venga lanciata l'eccezione {@link NullPointerException} quando la collezione fornita e' {@code null}.
     * 
     * @d.esign    Si vuole verificare che il metodo {@code retainAll(HCollection c)} lanci correttamente {@link NullPointerException} quando <br />
     *              l'argomento fornito e' {@code null}. 
     * 
     * @d.escription   1.  Si chiama direttamente retainAll(null) sulla collezione. <br />
     *                  2.  Si verifica il corretto lancio di NullPointerException
     * 
     * @p.recond   La vista dei valori della mappa e' stata popolata con 4 elementi.
     * 
     * @p.ostcond  La vista dei valori e' rimasta invariata.
     * 
     * @r.esult    {@code retainAll(HCollection c)} deve lanciare {@link NullPointerException} quando l'argomento e' {@code null}. 
     * 
     */
    @Test(expected = NullPointerException.class)
    public void test_Collection_RetainAll_Null()
    {
        testCol.retainAll(null);
    }

    /**
     * Test del metodo {@link myAdapter.ValueCollection#retainAll retainAll} quando gli argomenti della collezione chiamante non sono compatibili con {@code c}. 
     * 
     * @s.ummary   Verifica che venga lanciata l'eccezione {@link ClassCastException} quando la collezione che lancia il metodo ha elementi incompatibili con la collezione {@code c}.
     * 
     * @d.esign    Si vuole verificare che il metodo {@code retainAll(HCollection c)} lanci correttamente {@link ClassCastException} quando <br />
     *              la collezione che lancia il metodo ha elementi incompatibili con la collezione {@code c}. 
     * 
     * @d.escription    1.  Si crea una collezione che ha elementi specifici. <br />
     *                  2.  Si chiama il metodo retainAll() usando come argomento la collezione creata al punto 1. <br />
     *                  3.  Si verifica il corretto lancio di ClassCastException
     * 
     * @p.recond   La vista dei valori della mappa e' stata popolata con 4 elementi.
     * 
     * @p.ostcond  La vista dei valori e' rimasta invariata.
     * 
     * @r.esult    {@code retainAll(HCollection c)} deve lanciare {@link ClassCastException} quando gli argomenti della collezione chiamante non sono compatibili con {@code c}. 
     * 
     */
    @Test(expected = ClassCastException.class)
    public void test_Collection_RetainAll_ClassCast()
    {
        HSet entrySet = map.entrySet();
        testCol.retainAll(entrySet);
    }

    /**
     * Test del metodo {@link myAdapter.ValueCollection#retainAll retainAll} quando la collezione di partenza viene modificata dal metodo.
     * 
     * @s.ummary   Verifica la corretta rimozione da questa collezione di tutti gli elementi non presesenti nella collezione data come argomento.
     * 
     * @d.esign    Si vuole verificare che il metodo {@code retainAll(HCollection c)} restituisca correttamente {@code true} quando la collezione di partenza <br />
     *              contiene anche elementi non presenti nella collezione specificata e rimuove tutte le istanze di questi elementi <br />
     *              dalla collezione di partenza, {@code false} se la collezione specificata contiene la collezione di partenza.
     * 
     * @d.escription   Si suppongono testati e funzionanti i metodi {@code containsAll()}, {@code size()}, {@code contains()} <br />
     *                  1.  Si crea una collezione di supporto che ha alcuni elementi in comune con la collezione da analizzare. <br />
     *                  3.  Si verifica che alla chiamata retainAll() usando come argomento la collezione ausiliaria il risultato sia true. <br />
     *                  4.  Si controlla che la dimensione della collezione analizzata e' diminuita e che ora la collezione ausiliaria la contiene. <br /> 
     *                  5.  Si verifica che la collezione ausiliaria contiene non solo la collezione di partenza ma anche altri elementi.
     * 
     * @p.recond   La vista dei valori della mappa e' stata popolata con 4 elementi.
     * 
     * @p.ostcond  La vista dei valori e' stata modificata.
     * 
     * @r.esult    {@code retainAll(HCollection c)} deve restituire {@code true} se la collezione viene modificata come risultato della chiamata del metodo <br />
     *              e {@code false} altrimenti.
     * 
     */
    @Test
    public void test_Collection_RetainAll_True()
    {
        //creo una nuova map e la popolo per poi mantenere solo i suoi valori su testCol
        MapAdapter map2 = new MapAdapter();
        map2.put(15, "one");
        map2.put(4, "kiss");
        map2.put(0, "Matteo");

        //creo una collezione che ha alcuni elementi in comune con testCol
        HCollection toRetain = map2.values();  

        //controllo che testCol cambia alla chiamata del metodo retainAll
        assertTrue(testCol.retainAll(toRetain));     //testCol cambia
        //da contenere 4 elementi ne contiene ora 2
        assertTrue(testCol.size() == 2);          
        assertTrue(toRetain.containsAll(testCol));   
        assertTrue(testCol.contains("one") == toRetain.contains("one")); 
        assertTrue(testCol.contains("kiss") == toRetain.contains("kiss"));

        //ToRetain contiene elementi che testCol non contiene anche dopo la chiamata del metodo retainAll
        assertTrue(!testCol.contains("Matteo") == toRetain.contains("Matteo"));  

    }

    /**
     * Test del metodo {@link myAdapter.ValueCollection#retainAll retainAll} quando la collezione specificata contiene la collezione chiamante. 
     * 
     * @s.ummary   Verifica che se la collezione specificata contiene la collezione chiamante il metodo {@code removeAll(HCollection c)} <br />
     *              restituisce {@code false} e la collezione di partenza non cambia
     * 
     * @d.esign    Si vuole verificare che il metodo {@code containsAll(HCollection c)} restituisca correttamente {@code false} quando la collezione specificata <br />
     *               contiene la collezione chiamante.
     * 
     * @d.escription   Si suppongono testati e funzionanti i metodi {@code containsAll()} <br />
     *                  1.  Si crea una collezione copia della collezione di partenza. <br />
     *                  2.  Si verifica che la collezione copia contiene la collezione di partenza. <br />
     *                  3.  Si verifica che alla chiamata del metodo retainAll() usando come argomento la collezione copia ritorni false.
     *                  4.  Si verifica che le due collezioni sono ancora uguali.
     * 
     * @p.recond   La vista dei valori della mappa e' stata popolata con 4 elementi.
     * 
     * @p.ostcond  La vista dei valori rimasta invariata.
     * 
     * @r.esult    {@code retainAll(HCollection c)} deve restituire {@code false} se la collezione specificata contiene la collezione chiamante.
     *  
     */
    @Test
    public void test_Collection_RetainAll_False()
    {
        //creo una nuova map e la popolo per poi mantenere solo i suoi valori su testCol
        MapAdapter map2 = new MapAdapter(map);

        //creo una collezione che ha tutti gli elementi in comune con testCol
        HCollection toRetain = map2.values();  

        //verifico che ToRetain contiene tutta la collezione testCol
        assertTrue(testCol.containsAll(toRetain));

        //controllo che testCol non cambia alla chiamata del metodo retainAll
        assertFalse(testCol.retainAll(toRetain));     //testCol non cambia

        //verifico che testCol non cambia
        assertEquals(testCol, toRetain);
        
    }

    /**
     * Test del metodo {@link myAdapter.ValueCollection#retainAll retainAll} quando la collezione data come argomento e' vuota.
     * 
     * @s.ummary   Verifica il corretto funzionamento del metodo retainAll quando si cerca di mantere una collezione vuota nella collezione di partenza.
     * 
     * @d.esign    Si vuole verificare che il metodo {@code retainAll(HCollection c)} restituisca correttamente {@code true} quando la collezione data come <br />
     *              argomento e' vuota che al termine della chiamata del metodo la collezione di partenza sia vuota.
     * 
     * @d.escription   Si suppongono testati e funzionanti i metodi {@code isEmpty()} <br />
     *                  1.  Si crea una collezione ausiliaria vuota. <br />
     *                  2.  Si chiama il metodo retainAll() usando come argomento la collezione vuota e si verifica restituisca true. <br />
     *                  3.  Si verifica che ora la collezione di partenza sia vuota.
     * 
     * @p.recond   La vista dei valori della mappa e' stata popolata con 4 elementi.
     * 
     * @p.ostcond  La vista dei valori e' stata svuotata.
     * 
     * @r.esult    {@code retainAll(HCollection c)} deve restituire {@code true} se collezione datagli come argomento e' vuota, infatti si svuota.
     * 
     */
    @Test
    public void test_Collection_RetainAll_EmptyCol()
    {
        //creo una collezione ausiliaria
        MapAdapter map2 = new MapAdapter();
        HCollection empty = map2.values();

        //chiamo il metodo retainAll() usando come argomento la collezione vuota
        assertTrue(testCol.retainAll(empty));
        //verifico che la collezione di partenza sia vuota dopo la chiamata del metodo
        assertTrue(testCol.isEmpty());
    }

    //-------FINE TEST DEL METODO retainAll(HCollection c) ----------
    //-------TEST DEL METODO clear() ----------
    
    /**
     * Test del metodo {@link myAdapter.ValueCollection#clear() clear} su una collezione vuota e non.
     * 
     * @s.ummary   Verifica che la collezione venga svuotata correttamente
     * 
     * @d.esign    Si vuole verificare che il metodo {@code clear()} rimuova correttamente tutti gli elementi <br />
     *              presenti con conseguente diminuzione delle dimensioni della collezione, rendendola di dimensione {@code 0}. <br />
     *              Alla fine della chiamata la collezione deve ritornare {@code true} alla chiamata di {@code isEmpty()}. 
     * 
     * @d.escription   Si suppongono testati e funzionanti i metodi {@code isEmpty()}, {@code size()} <br />
     *                  1.  Si controlla che la collezione non sia vuota all'inizio. <br />
     *                  2.  Si chiama il metodo clear() e si svuota la collezione. <br />
     *                  3.  Si controlla che la collezione sia vuota e di dimensione {@code 0}. <br />
     *                  4.  Si controlla che alla chiamata del metodo clear() su collezione vuota, il controllo fatto al punto 3 non cambia.
     * 
     * @p.recond   La vista dei valori della mappa e' stata popolata con 4 elementi.
     * 
     * @p.ostcond  La vista dei valori e' stata svuotata.
     * 
     * @r.esult    {@code clear())} svuota la collezione, rendendola di dimensione {@code 0}.
     * 
     */
    @Test
    public void test_Collection_Clear()
    {
        //controllo che la collezione non sia vuoto all'inizio
        assertTrue(testCol.size() > 0); 
        //svuoto la collezione
        testCol.clear();             
        //controllo ora che la collezione sia di vuota e di dimensione 0, priva di valori   
        assertTrue(testCol.isEmpty() && testCol.size() == 0);
        //controllo che la chiamata di clear() su una collezione vuota non ne cambia le dimensioni
        testCol.clear(); 
        assertTrue(testCol.isEmpty() && testCol.size() == 0 );
    }

    //-------FINE TEST DEL METODO clear() ----------
    //-------TEST DEL METODO equals() ----------

    /**
     * Test del metodo {@link myAdapter.ValueCollection#equals equals}.
     * 
     * @s.ummary   Verifica che il metodo {@code equals()} funzioni correttamente, fornendo HCollection uguali e non uguali.
     * 
     * @d.esign    Si vuole verificare che {@code equals()} restituisca correttamente {@code true} se l'oggetto fornito come argomento e' <br />
     *              anch'esso una HCollection, le due collezioni hanno le stesse dimensioni e una contiene l'altra. {@code false} quando anche una di queste condizioni viene a mancare.
     * 
     * 
     * @d.escription   Si suppongono testati e funzionanti il metodo {@code size()}, {@code containsAll()}, {@code remove()} <br />
     *                  1.  Si crea una seconda HCollection con gli stessi elementi della collezione di partenza.  <br />
     *                  2.  Si verifica che le due collezioni sono entrambe HCollection, hanno la stessa dimensione e si contengono una nell'altra. <br />
     *                  3.  Si controlla che le due HCollection siano uguali. <br />
     *                  4.  Si rimuove un elemento dalla collezione di supporto, ora le collezioni avranno dimensione diversa. <br />
     *                  5.  Si verifica che le due collezioni siano diverse. <br />
     *                  6.  Si aggiunge un elemento gia' presente nella collezione. <br />
     *                  7.  Si controlla che le due collezioni abbiano la stessa dimensione. <br />
     *                  8.  Si controlla che la collezione di partenza contenga la collezione di supporto ma non il viceversa. <br />
     *                  9.  Si verifica che il metodo equals() con argomento la collezione di supporto resulti false <br />
     * 
     * @p.recond   La vista dei valori e' stata popolata.
     * 
     * @p.ostcond  La vista dei valori non e' stata modificata.
     * 
     * @r.esult    Il metodo {@code equals()} deve restituire {@code true} quando le due HCollection rispettano le specifiche, {@code false} altrimenti.
     * 
     */
    @Test
    public void test_Collection_Equals_Col()
    {
        //creo una nuova mappa e la popolo 
        MapAdapter map2 = new MapAdapter(map);
        //creo una collezione che ha tutti gli elementi in comune con testCol
        HCollection colEquals = map2.values();  

        //una collezione e' uguale ad un altra se hanno la stessa dimensione e gli stessi valori 
        assertEquals(colEquals instanceof HCollection, testCol instanceof HCollection);
        assertTrue(testCol.size() == colEquals.size());
        assertTrue(testCol.containsAll(colEquals) && colEquals.containsAll(testCol));
        //si controlla che le due collezioni siano uguali
        assertTrue(testCol.equals(colEquals));

        //ottengo una collezione che non ha tutti gli elementi in comune con testCol
        colEquals.remove("kiss"); 

        //si verifica che le due collezioni non siano uguali, dimensione diversa
        assertFalse(testCol.size() == colEquals.size());
        assertFalse(testCol.equals(colEquals));

        //aggiungo un elemento a map2, duplico un valore
        map2.put(6, "one");

        //controllo che le dimensioni delle due collezioni siano uguali
        assertTrue(testCol.size() == colEquals.size());

        //controllo che testCol contenga colEquals ma non il contrario
        assertTrue(testCol.containsAll(colEquals));
        assertFalse(colEquals.containsAll(testCol));

        //controllo che le due collezioni non siano uguali
        assertFalse(testCol.equals(colEquals));
    }

    /**
     * Test del metodo {@link myAdapter.ValueCollection#equals equals} tra una collezione di tipo HCollection e un oggetto o un Set.
     * 
     * @s.ummary   Verifica che il metodo {@code equals()} restituisca sempre {@code false} se l'argomento di equals() e' un set onon e' un oggetto di tipo HCollection.
     * 
     * @d.esign    Si vuole verificare che {@code equals()} restituisca correttamente {@code false} se l'oggetto fornito come argomento non <br />
     *              e' di tipo HCollection oppure e' un set.
     * 
     * @d.escription   Si suppongono testati e funzionanti il metodo  {@code containsAll()} <br />
     *                  1.  Si crea un set con gli stessi valori della collezione. <br />
     *                  2.  Si verifica che la collezione contiene il set. <br />
     *                  3.  Si verifica che la collezione non e' uguale al set, equals() ritorna false. <br />
     *                  4.  Si verifica che la collezione non e' uguale ad un oggetto non di tipo HCollection, a prescindere dall'oggetto.
     *      
     * @p.recond   La vista dei valori e' stata popolata.
     * 
     * @p.ostcond  La vista dei valori non e' stata modificata.
     * 
     * @r.esult    Il metodo {@code equals()} deve restituire {@code false} quando si compara una collezione <br />
     *              con un oggetto non di tipo HCollection o con un Set.
     * 
     */
    @Test
    public void test_Collection_Equals_NotCol()
    {
        //creo una nuova mappa e la popolo
        MapAdapter map2 = new MapAdapter();
        map.put("one", 4);
        map.put("flower", 2);
        map.put("plum", 7);
        map.put("kiss", 0);
        //creo un set e controllo se e' uguale alla collezione  
        HSet setEquals = map2.keySet(); 

        //la collezione contiene il set
        assertTrue(testCol.containsAll(setEquals));     
        //ma non e' uguale al set, perche' non sono della stesa classe
        assertFalse(testCol.equals(setEquals));   
        
        //controllo che testCol non sia uguale ad un intero
        assertFalse(testCol.equals(4));
    }

    //-------FINE TEST DEL METODO equals() ----------
    //-------TEST DEL METODO hashCode() ----------

    /**
     * Test del metodo {@link myAdapter.ValueCollection#hashCode() hashCode}.
     * 
     * @s.ummary   Verifica che il metodo {@code hashCode()} generi correttamente l'hashCode della collezione che chiama il metodo.
     * 
     * @d.esign    Si vuole verificare che il metodo {@code hashCode()} preveda il corretto aggiornamento dell'hashCode della collezione al variare <br />
     *               degli elementi contenuti nella collezione, senza generare errori. <br /> 
     *              Se due collezioni hanno lo stesso hashCode allora si dimostra che se {@code c1.equals(c2)}, allora {@code c1.hashCode()} == {@code c2.hashCode()}.
     * 
     * @d.escription   Si suppongono testati e funzionanti il metodo {@code iterator()}, {@link myAdapter.ValueCollection.CollectionIterator#next() next}, <br />
     *                  1.  Si crea una collezione ausiliaria inizialmente uguale alla collezione chiamante. <br />
     *                  2.  Si verifica che le due collezioni sono uguali e che di conseguenza anche i loro hashCode sono uguali. <br />
     *                  3.  Si svuota la collezione di partenza e si verifica che l'hashCode di una collezione vuota e' 0. <br />
     *                  4.  Si confronta l'hashCode della collezione vuota con la collezione di partenza e si verifica che non siano uguali, modifiche alla collezione comportano modifiche all'hashcode.
     *      
     * @p.recond   La vista dei valori della mappa e' stata popolata con 4 elementi.
     * 
     * @p.ostcond  La vista dei valori e' stata svuotata.
     * 
     * @r.esult    Il metodo {@code hashCode()} restituisce i risultati attesi nei vari punti.
     * 
     */
    @Test
    public void test_Collection_HashCode()
    {
        //creo una seconda collezione uguale alla prima
        MapAdapter map2 = new MapAdapter(map);
        HCollection h = map2.values();

        //controllo che le due collezioni siano uguali
        assertEquals(testCol, h);
        //controllo che l'hashCode delle due collezioni sia uguale
        assertEquals((long)testCol.hashCode(),(long)h.hashCode());
        
        testCol.clear();
        //hashCode collezione vuota
        assertTrue(testCol.hashCode() == 0);

        //controllo che l'hashCode di testCol sia cambiato
        assertFalse(testCol.hashCode() == h.hashCode());
    }



}
