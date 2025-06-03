package myTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;
import myAdapter.*;

/**
 * Test suite per la classe {@link myAdapter.MapAdapter.EntrySet}.
 * <p>
 * Summary: Verifica il corretto funzionamento dei metodi della classe {@code MapAdapter.EntrySet} che implementa l'interfaccia {@link myAdapter.HSet} ed e' figlia di {@link myAdapter.ValueCollection}
 * <p>
 * Design:  Si utilizzano JUnit 4.13.2, hamcrest-core-1.3 <br />
 *          La test suite include test cases di metodi di accesso, modifica e interrogazione di una {@link myAdapter.HSet} <br />
 *          tramite la classe {@link myAdapter.MapAdapter.EntrySet}, di cui viene fornita un'istanza pre popolata prima dell'esecuzione di ogni metodo. <br />
 *          Si testa il corretto funzionamento solo dei metodi di cui si e' effettuato l'override nell'implementazione di {@link myAdapter.MapAdapter.EntrySet}. <br />
 *          Si suppone testato e funzionante il backing tra mappa e vista delle Entry, di classe {@link myAdapter.MapAdapter.EntrySet}, che verra' testato in {@link myTest.TestMapAdapter}. <br />
 *          Si suppongono funzionanti i metodi di {@link myAdapter.MapAdapter} e {@link myAdapter.ValueCollection}. <br /> 
 *          Nella descrizoine dei diversi testCase, e' implicita l'esecuzione della funzione setUp annotata con @Before che popola una mappa per ottenere la vista delle entrys <br />
 *          e che si suppone testato e funzionante il metodo {@link myAdapter.MapAdapter#put put}, usata per la funzione setUp.
 */
public class TestEntrySet
{
    private MapAdapter map = new MapAdapter();
    private HSet testEntrySet = map.entrySet();

    //METODO PER POPOLARE UNA MAPPA

    /**
     * Configuara l'ambiente dei test cases popolando la mappa la cui vista delle Entry verra' manipolata.
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
    
    //quelli del metodo ValueCollection

    //-------FINE TEST DEL METODO size() ----------
    //-------TEST DEL METODO isEmpty() ----------

    //quelli del metodo ValueCollection

    //-------FINE TEST DEL METODO isEmpty() ----------
    //-------TEST DEL METODO contains() ----------

    /**
     * Test del metodo {@link myAdapter.MapAdapter.EntrySet#contains contains} nel caso di argomento {@code null}.
     * 
     * @s.ummary   Verifica che il metodo {@code contains()} lancia {@link NullPointerException} se fornito un argomento {@code null}.
     * 
     * @d.esign    Si vuole verificare che alla chiamata del metodo {@code contains()} nel caso di argomento {@code null} venga correttamente <br />
     *              lanciata l'eccezione {@link NullPointerException}.    
     * 
     * @d.escription   Si esegue direttamente il metodo contains fornendo come argomento null e si verifica che venga lanciata un'eccezione NullPointerException.
     * 
     * @p.recond   La vista delle Entry della mappa e' stata popolata con 4 elementi.
     * 
     * @p.ostcond  La vista delle Entry rimane invariata.
     * 
     * @r.esult     {@code contains()} deve lanciare {@link NullPointerException} se chiamata con argomento {@code null}.
     * 
     */
    @Test(expected = NullPointerException.class)
    public void test_EntrySet_Contains_Null() //Contains con argomento entry null
    {
        //testo la presenza che l'elemento null sia presente, aspetto una eccezione
        testEntrySet.contains(null);
    }
    
    /**
     * Test del metodo {@link myAdapter.MapAdapter.EntrySet#contains contains} nel caso in cui l'argomento non sia un'istanza di {@link myAdapter.HEntry}.
     * 
     * @s.ummary   Verifica che il metodo {@code contains()} lancia {@link ClassCastException} se fornito un argomento che non sia un'istanza di {@link myAdapter.HEntry}.
     * 
     * @d.esign    Si vuole verificare che alla chiamata del metodo {@code contains()} nel caso di argomento non sia un'istanza di {@link myAdapter.HEntry} <br />
     *               venga correttamente lanciata l'eccezione {@link ClassCastException}.    
     * 
     * @d.escription   Si esegue direttamente il metodo contains fornendo come argomento un oggetto non di classe Entry e si verifica che venga lanciata un'eccezione ClassCastException.
     * 
     * @p.recond   La vista delle Entry della mappa e' stata popolata con 4 elementi.
     * 
     * @p.ostcond  La vista delle Entry rimane invariata.
     * 
     * @r.esult     {@code contains()} deve lanciare {@link ClassCastException} se chiamata con argomento che non e' un'istanza di {@link myAdapter.HEntry}.  
     * 
     */
    @Test(expected = ClassCastException.class)
    public void test_EntrySet_Contains_ClassCast()
    {
        //provo a controllare se il set contiene elementi oltre a entry
        testEntrySet.contains("felicita'");
    }

    /**
     * Test del metodo {@link myAdapter.MapAdapter.EntrySet#contains contains} su un set di HEntry contenente elementi e non.
     * 
     * @s.ummary   Verifica che il corretto funzionamento del metodo {@code contains()} su un set di Entry vuoto e non.
     * 
     * @d.esign    Si vuole verificare che il metodo {@code contains()} si appoggi correttamente al metodo {@link myAdapter.MapAdapter#containsKey(Object) containsKey} e <br />
     *              {@link myAdapter.MapAdapter#put put} e che restituisca {@code true} se il valore posto come argomento e' contenuto nel set <br />
     *              di Entry, {@code false} altrimenti. 
     * 
     * @d.escription   Si suppone testato e funzionante il metodo {@code clear()}, {@code iterator()}, {@link myAdapter.MapAdapter.EntrySet.EntrySetIterator#next next} <br />
     *                  1.  Si creano due mappe e si popolano con un elemento ciascuna, una coppia contenuta nella mappa di partenza, una no. <br />
     *                  2.  Si creano le viste delle Entry per le due mappe create e si usa l'iteratore dei set per ottenere gli oggetti di tipo Entry. <br />
     *                  3.  Si verifica che contains() restituisca true per la Entry che e' anche contenuta nella mappa di partenza. <br />
     *                  4.  Si verifica che contains() restituisca false per una Entry che si sa non sia presente nella mappa di partenza.  <br />
     *                  5.  Si svuota il set e si verifica che contains() restituisca false per qualsiasi Entry se il set e' vuoto.
     * 
     * @p.recond   La vista delle Entry della mappa e' stata popolata con 4 elementi.
     * 
     * @p.ostcond  La vista delle Entry e' stata svuotata.
     * 
     * @r.esult     {@code contains()} deve restituire {@code true} se l'argomento e' presente nel set, {@code false} altrimenti.
     * 
     */
    @Test
    public void test_EntrySet_Contains() //caso di successo e fallimento
    {
        //creo due nuove mappe di supporto e le popolo
        MapAdapter map2 = new MapAdapter();
        MapAdapter map3 = new MapAdapter();
        map2.put(14, 65);
        map3.put(4, "one");

        //creo la vista delle entry delle mappe appena create
        HSet es2 = map2.entrySet();
        HSet es3 = map3.entrySet();
        //creo gli iteratori a queste viste per ottenere degli oggetti di tipo entry
        HIterator it2 = es2.iterator(); 
        HIterator it3 = es3.iterator();
        HEntry eIn = (HEntry) it3.next();
        HEntry eOut = (HEntry) it2.next();

        //controllo che la entry [4, "one"] sia contenuto nel testEntrySet
        assertTrue("Se il set contiene a entry [4, \"one\"], ritorna true", testEntrySet.contains(eIn));
    
        //Verifico che la entry [14, 65] non sia contenuto nel testEntrySet
        assertFalse("Il set non contiene la entry [14, 65]", testEntrySet.contains(eOut));
        
        //svuoto il set
        testEntrySet.clear();

        //controllo che non contenga piu' l'elemento che conteneva
        assertFalse(testEntrySet.contains(eIn));
    }

    //-------FINE TEST DEL METODO contains() ----------
    //-------TEST DEL METODO iterator() ----------
    
    /**
     * Test del metodo {@link myAdapter.MapAdapter.EntrySet#iterator() iterator} su un set di Entry contenente elementi e non.
     * 
     * @s.ummary  Verifica che il metodo {@code iterator()} restituisca un iteratore sugli elementi del set delle Entry.
     * 
     * @d.esign     Si vuole verificare che il metodo {@code contains()} restituisca un'istanza di {@link myAdapter.HIterator}. <br />
     *              L'iteratore potra' leggere gli elementi del set delle Entry e rimuovere elementi da esso.
     * 
     * @d.escription   Si suppone testata e funzionante la classe {@link myAdapter.MapAdapter.EntrySet.EntrySetIterator} e il metodo {@code contains()}. <br />
     *                  1.  Si verifica che l'oggetto restituito alla chiamata di iterator() sia un'istanza di HIterator. <br />
     *                  2.  Si crea un ciclo while che continua finche' l'iteratore ha elementi da leggere. <br />
     *                  3.  Dentro al ciclo while si legge l'elemento successivo della vista e si verifica che sia presente nel set. <br />
     *                  4.  Si rimuove l'elemento appena letto tramite l'iteratore e si controlla che non sia piu' contenuto nel set. <br />
     *                  5.  Alla fine del ciclo while il set e' vuoto e si controlla che l'iteratore di un set vuoto non abbia elementi successivi. <br />
     * 
     * @p.recond    La vista delle Entry della mappa e' stata popolata con 4 elementi.
     * 
     * @p.ostcond  La vista delle Entry e' stata svuotata.
     * 
     * @r.esult    {@code iterator()} deve restituire un'istanza di {@code HIterator} che compie correttamente i metodi descritti in {@link myAdapter.HIterator}.
     * 
     */
    @Test
    public void test_EntrySet_Iterator() //Controllo che effettivamente l'iteratore di set sia un iteratore
    {
        //mi assicuro che l'iteratore del set sia un iteratore
        assertTrue(testEntrySet.iterator() instanceof HIterator);
        
        //controllo che funzioni l'iteratore
        HIterator it = testEntrySet.iterator();
        while(it.hasNext())
        {
            Object k = it.next();
            assertTrue(testEntrySet.contains(k));
            it.remove();
            assertFalse(testEntrySet.contains(k));
        }
        assertTrue(testEntrySet.size() == 0);
        //ora il set e' vuoto, verifico che l'iteratore ad un set vuoto non abbia elementi successivi
        it = testEntrySet.iterator();
        assertFalse(it.hasNext());
    }
    
    //-------FINE TEST DEL METODO iterator() ----------
    //-------TEST DEL METODO toArray() ----------

    //quelli del metodo ValueCollection

    //-------FINE TEST DEL METODO toArray() ----------
    //-------TEST DEL METODO toArray(Object[] a) ----------

    //quelli del metodo ValueCollection

    //-------FINE TEST DEL METODO toArray(Object[] a) ----------
    //-------TEST DEL METODO add() ----------

    //quelli del metodo ValueCollection

    //-------FINE TEST DEL METODO add() ----------
    //-------TEST DEL METODO remove() ----------

    /**
     * Test del metodo {@link myAdapter.MapAdapter.EntrySet#remove(Object o) remove} quando l'argomento fornito e' {@code null}. 
     * 
     * @s.ummary  Verifica che venga lanciata l'eccezione {@link NullPointerException} quando l'oggetto fornnito e' {@code null}.
     * 
     * @d.esign     Si vuole verificare che il metodo {@code remove(Object o)} lanci correttamente {@link NullPointerException} quando <br />
     *              l'argomento fornito e' {@code null}. 
     * 
     * @d.escription   1.  Si chiama direttamente remove(null) sul set. <br />
     *                 2.  Si verifica il corretto lancio di NullPointerException
     * 
     * @p.recond    La vista delle Entry della mappa e' stata popolata con 4 elementi.
     * 
     * @p.ostcond  La vista delle Entry e' rimasta invariata.
     * 
     * @r.esult    {@code remove(Object o)} deve lanciare {@link NullPointerException} quando l'argomento e' {@code null}. 
     * 
     */
    @Test(expected = NullPointerException.class)
    public void test_EntrySet_Remove_Null()
    {
        testEntrySet.remove(null);
    }

    /**
     * Test del metodo {@link myAdapter.MapAdapter.EntrySet#remove(Object o) remove} quando l'argomento fornito non e' istanza di {@link myAdapter.HEntry}.  
     * 
     * @s.ummary    Verifica che venga lanciata l'eccezione {@link ClassCastException} quando l'oggetto fornito non e' istanza di {@link myAdapter.HEntry}.  
     * 
     * @d.esign     Si vuole verificare che il metodo {@code remove(Object o)} lanci correttamente {@link ClassCastException} quando <br />
     *              l'argomento fornito non e' istanza di {@link myAdapter.HEntry}.  
     * 
     * @d.escription   1.  Si chiama direttamente remove() sul set delle Entry usando come argomento un oggetto non Entry. <br />
     *                 2.  Si verifica il corretto lancio di ClassCastException
     * 
     * @p.recond    La vista delle Entry della mappa e' stata popolata con 4 elementi.
     * 
     * @p.ostcond  La vista delle Entry e' rimasta invariata.
     * 
     * @r.esult    {@code remove(Object o)} deve lanciare {@link ClassCastException} quando l'argomento non e' istanza di {@link myAdapter.HEntry}.  
     * 
     */
    @Test(expected = ClassCastException.class)
    public void test_EntrySet_Remove_ClassCast()    //provo a rimuove un oggetto non entry
    {
        testEntrySet.remove("vuoto");
    }

    /**
     * Test del metodo {@link myAdapter.MapAdapter.EntrySet#remove(Object o) remove} con {@code o} presente e non dal set. 
     * 
     * @s.ummary  Verifica la corretta rimozione dell'elemento dal set, se presente.
     * 
     * @d.esign     Si vuole verificare che il metodo {@code remove(Object o)} restituisca correttamente {@code true} quando l'oggetto specificato <br />
     *              e' una Entry presente nel set e viene rimosso correttamente, {@code false} altrimenti. <br />
     *              Si verifica che il metodo {@code remove(Object o)} si appoggia al metodo {@link myAdapter.MapAdapter#remove remove} <br />
     *              per la rimozione della chiave associata alla Entry da rimuovere.
     * 
     * @d.escription   Si suppongono testati e funzionanti i metodi {@code contains()}, {@code toArray()}, {@code remove()} <br />
     *                  1.  Si crea una mappa contenente un elemento noto contenuto anche nella mappa di partenza. <br />
     *                  2.  Si crea la vista delle Entry della mappa ausiliaria e si salva il valore noto in una variabile tramite la chiamata di toArray(). <br />
     *                  3.  Si verifica che il set delle Entry contenga l'elemento noto. <br />
     *                  4.  Si invoca remove() usando come argomento l'elemento noto e si verifica che restituisca {@code true}. <br />
     *                  5.  Si verifica che l'elemento noto non sia piu' presente nel set. <br />
     *                  6.  Si invoca remove() usando come argomento un elemento non presente nel set e si verifica che restituisca {@code false}. <br />
     *                  7.  Si verifica che anche la mappa ha invocato il metodo remove, perdendo la coppia nota.
     * 
     * @p.recond    La vista delle Entry della mappa e' stata popolata con 4 elementi.
     * 
     * @p.ostcond   La vista delle Entry e' stata modificata, ha perso un elemento.
     * 
     * @r.esult    {@code remove(Object o)} verifica se l'elemento specificato e' presente nel set delle Entry, se presente <br />
     *              ritorna {@code true} e rimuove dal set l'istanza di quell'elemento, altrimenti ritorna {@code false}.
     */
    @Test
    public void test_EntrySet_Remove()   //caso di successo e fallimento
    {
        //creo una mappa usialiaria e la popolo
        MapAdapter map2 = new MapAdapter();
        map2.put(4, "one");
        // creo la sua vista
        HSet es = map2.entrySet();
        Object[] a = es.toArray();
        
        //controllo che il set contenga la coppia [4, "one"]
        assertTrue(testEntrySet.contains(a[0]));
        //rimuovo la coppia [4, "one"]
        assertTrue(testEntrySet.remove(a[0]));
        //controllo che il set non contenga altre coppie [4, "one"]
        assertFalse(testEntrySet.contains(a[0]));

        map2.remove(4);
        map2.put(5, 5);
        a = es.toArray();
        //controllo che il set non cambia se si prova a rimuove la coppia [5, 5], che non appartiene al set
        assertFalse(testEntrySet.remove(a[0]));

        //controllo che anche la mappa abbia rimosso la chiave 4
        assertFalse(map.containsKey(4));
    }

    //-------FINE TEST DEL METODO remove() ----------
    //-------TEST DEL METODO containsAll(HCollection c) ----------

    /**
     * Test del metodo {@link myAdapter.MapAdapter.EntrySet#containsAll(HCollection c) containsAll} quando l'argomento fornito e' {@code null}. 
     * 
     * @s.ummary  Verifica che venga lanciata l'eccezione {@link NullPointerException} quando la collezione fornita e' {@code null}.
     * 
     * @d.esign     Si vuole verificare che il metodo {@code containsAll(HCollection c)} lanci correttamente {@link NullPointerException} quando <br />
     *              l'argomento fornito e' {@code null}. 
     * 
     * @d.escription   1.  Si esegue direttamente containsAll(null) e si verifica venga lanciata un'eccezione NullPointerException 
     * 
     * @p.recond    La vista delle Entry della mappa e' stata popolata con 4 elementi.
     * 
     * @p.ostcond  La vista delle Entry e' rimasta invariata.
     * 
     * @r.esult    {@code containsAll(HCollection c)} deve lanciare {@link NullPointerException} quando l'argomento e' {@code null}. 
     * 
     */
    @Test (expected = NullPointerException.class)
    public void test_EntrySet_ContainsAll_Null()
    {
        testEntrySet.containsAll(null);
    }

    /**
     * Test del metodo {@link myAdapter.MapAdapter.EntrySet#containsAll(HCollection c) containsAll} quando {@code c} contiene elementi che non sono istanze di {@link myAdapter.HEntry}.  
     * 
     * @s.ummary  Verifica che venga lanciata l'eccezione {@link ClassCastException} quando la collezione fornita contiene elementi che non sono istanze di {@link myAdapter.HEntry}.  
     * 
     * @d.esign     Si vuole verificare che il metodo {@code containsAll(HCollection c)} lanci correttamente {@link ClassCastException} quando <br />
     *              la collezione forita come argomento contiene elementi che non sono istanze di {@link myAdapter.HEntry}.  
     * 
     * @d.escription   1.  Si esegue direttamente containsAll() usando come argomento una collezione non di Entry.
     *                 2.  Si verifica venga lanciata un'eccezione ClassCastException 
     * 
     * @p.recond    La vista delle Entry della mappa e' stata popolata con 4 elementi.
     * 
     * @p.ostcond  La vista delle Entry e' rimasta invariata.
     * 
     * @r.esult    {@code containsAll(HCollection c)} deve lanciare {@link ClassCastException} quando la collezione fornita contiene elementi che non sono istanze di {@link myAdapter.HEntry}.  
     * 
     */
    @Test (expected = ClassCastException.class)
    public void test_EntrySet_ContainsAll_ClassCast()
    {
        testEntrySet.containsAll(map.values());
    }
    
    /**
     * Test del metodo {@link myAdapter.MapAdapter.EntrySet#containsAll(HCollection c) containsAll}.
     * 
     * @s.ummary  Verifica che venga il metodo {@code containsAll()} si comporti nel modo atteso sia nel caso gli elementi siano tutti contenuti, sia non.
     * 
     * @d.esign     Si vuole verificare che il metodo {@code containsAll(HCollection c)} verifichi correttamente la presenza nel set di una collezione di oggetti <br />
     *              Entry, restituendo {@code false} quando anche una sola Entry dovesse mancare, {@code true} se tutti gli elementi della collezione specificata sono contenuti nel set delle Entry. 
     * 
     * @d.escription   Si suppongono testati e funzionanti i metodi {@link myAdapter.MapAdapter#keySet() keySet}, {@link myAdapter.MapAdapter#put put} <br /> 
     *                  1.  Si istanzia un secondo set di Entry vuoto. <br />
     *                  2.  Si chiama containsAll() usando come argomento il set vuoto e si verifica che il metodo restituisca true. <br />
     *                  3.  Si popola la seconda mappa con elementi noti e di conseguenza si modifica il set creato al punto 1. <br />
     *                  4.  Si chiama containsAll() usando come argomento il set appena riempito e si verifica che restituisca true. <br />
     *                  5.  Si aggiunge un ulteriore elemento al set ausiliario, che non appartiene anche al set chiamante. <br />
     *                  6.  Si chiama containsAll() usando come argomento il set ausiliario ora contenente un elemento non contenuto nel set chiamante. <br />
     *                  7   Si verifica che il metodo restituisca false in quanto non tutti gli elementi del set ausiliario sono contenuti nel set chiamante
     * 
     * @p.recond    La vista delle Entry della mappa e' stata popolata con 4 elementi.
     * 
     * @p.ostcond   La vista delle Entry e' rimasta invariata.
     * 
     * @r.esult    Il metodo {@code containsAll(HCollection c)} deve restituire {@code false} se manca anche solo una Entry, {@code true} se tutte le Entry contenute <br />
     *             nella collezione sono presenti anche nel set.
     * 
     */
    @Test
    public void test_EntrySet_ContainsAll() //caso di successo e fallimento
    {
        //creo una nuova mappa e la popolo
        MapAdapter map2 = new MapAdapter();
        
        //creo una vista delle entry a partire dalla mappa
        HSet contained = map2.entrySet();

        //controllo che testEntrySet contenga il set vuoto contained
        assertTrue(testEntrySet.containsAll(contained));

        //popolo la mappa 2
        map2.put(2, "flower");
        map2.put(4, "one");
        //controllo che testEntrySet contenga sia la coppia [2, "flower"] che la coppia [4, "one"]
        assertTrue(testEntrySet.containsAll(contained));

        //aggiungo un elemento alla map2 che non appartiene a map
        map2.put(64, "");
        //ora contained contiene 3 elementi, 2 sono anche contenuti in testEntrySet, 1 no

        //controllo che testEntrySet non contenga tutto il set contained
        assertFalse(testEntrySet.containsAll(contained));
    }

    //-------FINE TEST DEL METODO containsAll(HCollection c) ----------
    //-------TEST DEL METODO addAll(HCollection c) ----------

    //quelli del metodo ValueCollection

    //-------FINE TEST DEL METODO addAll(HCollection c) ----------
    //-------TEST DEL METODO removeAll(HCollection c) ----------

    /**
     * Test del metodo {@link myAdapter.MapAdapter.EntrySet#removeAll(HCollection c) removeAll} quando l'argomento fornito e' {@code null}. 
     * 
     * @s.ummary  Verifica che venga lanciata l'eccezione {@link NullPointerException} quando la collezione fornita e' {@code null}.
     * 
     * @d.esign     Si vuole verificare che il metodo {@code removeAll(HCollection c)} lanci correttamente {@link NullPointerException} quando <br />
     *              l'argomento fornito e' {@code null}. 
     * 
     * @d.escription    1.  Si chiama direttamente removeAll(null) sul set. <br />
     *                  2.  Si verifica il corretto lancio di NullPointerException
     * 
     * @p.recond    La vista delle Entry della mappa e' stata popolata con 4 elementi.
     * 
     * @p.ostcond  La vista delle Entry e' rimasta invariata.
     * 
     * @r.esult    {@code removeAll(HCollection c)} deve lanciare {@link NullPointerException} quando l'argomento e' {@code null}. 
     * 
     */
    @Test(expected = NullPointerException.class)
    public void test_EntrySet_RemoveAll_Null()
    {
        testEntrySet.removeAll(null);
    }

    /**
     * Test del metodo {@link myAdapter.MapAdapter.EntrySet#removeAll(HCollection c) removeAll} quando {@code c} contiene elementi che non sono istanze di {@link myAdapter.HEntry}.  
     * 
     * @s.ummary  Verifica che venga lanciata l'eccezione {@link ClassCastException} quando la collezione fornita contiene elementi che non sono istanze di {@link myAdapter.HEntry}.  
     * 
     * @d.esign     Si vuole verificare che il metodo {@code removeAll(HCollection c)} lanci correttamente {@link ClassCastException} quando <br />
     *              la collezione fornita contiene elementi che non sono istanze di {@link myAdapter.HEntry}.  
     * 
     * @d.escription    1.  Si chiama direttamente removeAll() sul set usando una collezione non contenente elementi Entry. <br />
     *                  2.  Si verifica il corretto lancio di ClassCastException
     * 
     * @p.recond    La vista delle Entry della mappa e' stata popolata con 4 elementi.
     * 
     * @p.ostcond   La vista delle Entry e' rimasta invariata.
     * 
     * @r.esult    {@code removeAll(HCollection c)} deve lanciare {@link ClassCastException} quando la collezione fornita contiene elementiche non sono istanze di {@link myAdapter.HEntry}.  
     */
    @Test(expected = ClassCastException.class)
    public void test_EntrySet_RemoveAll_ClassCast()
    {
        testEntrySet.removeAll(map.values());
    }
    
    /**
     * Test del metodo {@link myAdapter.MapAdapter.EntrySet#removeAll removeAll} quando il set delle Entry e la collezione hanno elementi in comune. 
     * 
     * @s.ummary  Verifica la corretta rimozione di un'intera collezione dal set di partenza, se hannoo elementi in comune.
     * 
     * @d.esign     Si vuole verificare che il metodo {@code removeAll(HCollection c)} restituisca correttamente {@code true} quando la collezione specificata 
     *              contiene anche elementi Entry presenti anche nel set chiamante e ne rimuove tutte le istanze, {@code false} se collezione e set di Entry non hanno elementi in comune.
     * 
     * @d.escription   Si suppongono testati e funzionanti i metodi {@code iterator()}, {@code size()}, {@code remove()}, {@code contains()} <br />
     *                  1.  Si crea una collezione di supporto con gli stessi elementi al set di Entry di partenza. <br />
     *                  2.  Si rimuove un elemento dalla collezione ausiliaria in modo che set e collezione non abbiano piu' gli stessi elementi. <br />
     *                  3.  Si verifica che alla chiamata removeAll() usando come argomento la collezione ausiliaria il risultato sia true. <br />
     *                  4.  Si controlla che il set e la collezione non abbiano piu' elementi in comune. <br />
     *                  5.  Si verifica che l'unico elemento presente nel set di partenza e' quello precedentemente rimosso dalla collezione di supporto. 
     * 
     * @p.recond    La vista delle Entry della mappa e' stata popolata con 4 elementi.
     * 
     * @p.ostcond  La vista delle Entry e' stata modificata, e' rimasto un solo elemento.
     * 
     * @r.esult    {@code removeAll(HCollection c)} deve restituire {@code true} se il set delle Entry viene modificato come risultato della chiamata del metodo 
     *              e {@code false} altrimenti.
     * 
     */
    @Test
    public void test_EntrySet_RemoveAll_True()    
    {
        //creo un nuovo set di entry e lo popolo per poi rimuovere i suoi elementi da testEntrySet
        MapAdapter map2 = new MapAdapter(map);
        HSet setToRemove = map2.entrySet();                //creo una copia del testSet
        map2.remove(4);                              //e rimuovo dalla copia un elemento
        
        //controllo che testEntrySet venga modificato dalla chiamata del metodo RemoveAll    
        assertTrue(testEntrySet.removeAll(setToRemove));     //rimuovo dal testEntrySet la copia

        //controllo che i due set non abbiano elementi in comune
        HIterator it = setToRemove.iterator();
        while(it.hasNext())
        {
            //il testEntrySet non contiene piu' elementi contenuti anche nella copia
            assertFalse(testEntrySet.contains(it.next()));  
        }

        //l'unico elemento che testEntrySet contiene e' quello precedentemente rimosso dalla copia  
        assertTrue(testEntrySet.size() == 1);            
        Object[] a = testEntrySet.toArray();
        HEntry pair = (HEntry) a[0];
        assertTrue(pair.getKey().equals(4) && pair.getValue().equals("one"));
        //controllo che testEntrySet abbia dimensione 1
        assertTrue(testEntrySet.size() == 1);        
    }

    /**
     * Test del metodo {@link myAdapter.MapAdapter.EntrySet#removeAll removeAll} quando il set di Entry e la collezione non hanno elementi in comune. 
     * 
     * @s.ummary  Verifica che se set e collezione non hanno elementi in comune il metodo {@code removeAll(HCollection c)} <br />
     *              restituisce {@code false} e il set delle Entry di partenza non cambia.
     * 
     * @d.esign     Si vuole verificare che il metodo {@code removeAll(HCollection c)} restituisca correttamente {@code false} quando la collezione specificata <br />
     *               non ha elementi Entry in comune con il set chiamante.
     * 
     * @d.escription   Si suppongono testati e funzionanti i metodi {@code toArray()}, {@code iterator()}, {@code contains()} <br />
     *                  1.  Si crea una collezione di Entry di supporto di un elemento, non contenuto nel set. <br />
     *                  2.  Si controlla che set e collezione non abbiano elementi in comune. <br />
     *                  3.  Si verifica che alla chiamata removeAll() usando come argomento la collezione ausiliaria il risultato sia false. <br />
     *                  4.  Si verifica che il set di partenza non sia stato modificato.
     * 
     * @p.recond    La vista delle Entry della mappa e' stata popolata con 4 elementi.
     * 
     * @p.ostcond  La vista delle Entry rimasta invariata.
     * 
     * @r.esult    {@code removeAll(HCollection c)} deve restituire {@code false} se il set e la collezione non presentano elementi Entry in comune.
     *  
     */
    @Test
    public void test_EntrySet_RemoveAll_False()
    {
        //creo un nuovo set e lo popolo per poi rimuovere i suoi elementi da testEntrySet
        MapAdapter map2 = new MapAdapter();
        map2.put(15, "");

        //creo un set di entry che non ha elementi in comune con testEntrySet
        HSet setToRemove = map2.entrySet();  

        //controllo che i due set non abbiano elementi in comune
        HIterator it = setToRemove.iterator();
        while(it.hasNext())
        {
            //il testEntrySet non contiene elementi contenuti anche nella vista della mappa di supporto
            assertFalse(testEntrySet.contains(it.next()));  
        }

        //salvo i precedenti valori di testEntrySet
        Object[] a = testEntrySet.toArray();

        //controllo che testEntrySet non cambia alla chiamata del metodo removeAll
        assertFalse(testEntrySet.removeAll(setToRemove));  
        for(int i = 0; i < a.length; i++) 
        {
            assertTrue(testEntrySet.contains(a[i]));
        }

    }

    //-------FINE TEST DEL METODO removeAll(HCollection c) ----------
    //-------TEST DEL METODO retainAll(HCollection c) ----------

    //-------FINE TEST DEL METODO retainAll(HCollection c) ----------
    //-------TEST DEL METODO clear() ----------

    //quelli del metodo ValueCollection

    //-------FINE TEST DEL METODO clear() ----------
    //-------TEST DEL METODO equals() ----------

    /**
     * Test del metodo {@link myAdapter.MapAdapter.EntrySet#equals equals}.
     * 
     * @s.ummary  Verifica che il metodo {@code equals()} funzioni correttamente, fornendo HSet uguali e non uguali.
     * 
     * @d.esign     Si vuole verificare che {@code equals()} restituisca correttamente {@code true} se l'oggetto fornito come argomento e' <br />
     *              anch'esso un HSet, i due set hanno le stesse dimensioni e uno contiene l'altro, {@code false} quando anche una di queste condizioni viene a mancare.
     * 
     * @d.escription   Si suppongono testati e funzionanti il metodo {@code size()}, {@code containsAll()} <br />
     *                  1.  Si crea una seconda HSet con gli stessi elementi del set di Entry di partenza.  <br />
     *                  2.  Si verifica che le due viste di Entry sono entrambe HSet, hanno la stessa dimensione e si contengono una nell'altra. <br />
     *                  3.  Si controlla che le due HSet siano uguali. <br />
     *                  4.  Si rimuove un elemento dal set di supporto, ora i set avranno dimensione diversa. <br />
     *                  5.  Si verifica che i due set siano diversi. <br />
     *                  6.  Si aggiunge un elemento nuovo al set di supporto, non contenuto nel set di partenza. <br />
     *                  7.  Si controlla che i due set abbiano la stessa dimensione. <br />
     *                  8.  Si verifica che il metodo equals() con argomento il set di supporto resulti false. <br />
     * 
     * @p.recond    La vista delle Entry e' stata popolata.
     * 
     * @p.ostcond  La vista delle Entry non e' stata modificata.
     * 
     * @r.esult    Il metodo {@code equals()} deve restituire {@code true} quando le due HSet rispettano le specifiche, {@code false} altrimenti.
     * 
     */
    @Test
    public void test_EntrySet_Equals_EntrySet()
    {
        //creo una nuova mappa e la popolo 
        MapAdapter map2 = new MapAdapter(map);
        //creo un set di entry che ha tutti gli elementi in comune con testEntrySet
        HSet setEquals = map2.entrySet();  

        //un set e' uguale ad un altro se hanno la stessa dimensione e contengono gli stessi argomenti
        assertEquals(setEquals.getClass(), testEntrySet.getClass());
        assertTrue(testEntrySet.size() == setEquals.size());
        assertTrue(testEntrySet.containsAll(setEquals) && setEquals.containsAll(testEntrySet));
        //controllo allora che il metodo equals() restituisca true
        assertTrue(testEntrySet.equals(setEquals));

        //elimino un elemento dal set ausiliario, cosi' i due set non hanno tutti gli elementi in comune
        map2.remove(4);

        //si verifica che i due set non siano uguali, dimensione diversa
        assertFalse(testEntrySet.size() == setEquals.size());
        assertFalse(testEntrySet.equals(setEquals));


        //aggiungo un elemento a map2 che non e' contenuto in testEntrySet
        map2.put(6, "one");
        //controllo che le dimensioni dei set siano uguali
        assertTrue(testEntrySet.size() == setEquals.size());
        //controllo che le due collezioni comunque non siano uguali
        assertFalse(testEntrySet.equals(setEquals));
    }

    /**
     * Test del metodo {@link myAdapter.MapAdapter.EntrySet#equals equals} tra un set di tipo HSet e un oggetto non di tipo HSet.
     * 
     * @s.ummary  Verifica che il metodo {@code equals()} restituisca sempre {@code false} se l'argomento di equals() non e' un oggetto di tipo HSet.
     * 
     * @d.esign     Si vuole verificare che {@code equals()} restituisca correttamente {@code false} se l'oggetto fornito come argomento non <br />
     *              e' di tipo HSet, anche se si tratta di una collezione e contengono gli stessi elementi.
     * 
     * @d.escription   Si suppongono testati e funzionanti il metodo  {@code toArray()}, {@code size()}, {@code containsAll()} <br />
     *                  1.  Si crea una collezione con gli stessi elementi del set. <br />
     *                  2.  Si verifica che il set contiene la collezione e che hanno la stessa dimensione. <br />
     *                  3.  Si verifica che il set non e' uguale alla collezione, equals() ritorna false. <br />
     *                  4.  Si verifica che il set non e' uguale ad un oggetto non di tipo HSet, a prescindere dall'oggetto.
     *      
     * @p.recond    La vista delle Entry e' stata popolata.
     * 
     * @p.ostcond  La vista delle Entry non e' stata modificata.
     * 
     * @r.esult    Il metodo {@code equals()} deve restituire {@code false} quando si compara un HSet con un oggetto non di tipo HSet.
     * 
     */
    @Test
    public void test_EntrySet_Equals_NotEntrySet()
    {   
        //se provo a usare un oggetto non di classe EntrySet nel metodo equals allora non saranno mai uguali
        MapAdapter map2 = new MapAdapter();
        Object[] a = testEntrySet.toArray();
        for(int i = 0; i < a.length; i++)
        {
            map2.put(a[i], a[i]);
        }
        //collezione che contiene gli stessi elementi di testEntryTest
        HCollection c = map2.values();
        assertEquals((long) c.size(), (long) testEntrySet.size()); //hanno la stessa dimensione
        assertTrue(testEntrySet.containsAll(c));

        //ma non sono uguali perche' di classi diverse
        assertNotEquals(testEntrySet.getClass(), c.getClass());
        assertFalse(testEntrySet.equals(c));                 
        
        //provo ora a usare un oggetto di tipo String
        assertFalse(testEntrySet.equals("lattuga"));
    }   

    //-------FINE TEST DEL METODO equals() ----------
    //-------TEST DEL METODO hashCode() ----------

    //quelli del metodo ValueCollection




}
