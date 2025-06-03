package myTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;
import myAdapter.MapAdapter;
import myAdapter.HSet;
import myAdapter.HIterator;
import myAdapter.HCollection;

/**
 * Test suite per la classe {@link myAdapter.KeySet}.
 * <p>
 * Summary: Verifica il corretto funzionamento dei metodi della classe {@code KeySet} che implementa l'interfaccia {@link myAdapter.HSet} ed e' figlia di {@link myAdapter.ValueCollection}
 * <p>
 * Design:  Si utilizzano JUnit 4.13.2, hamcrest-core-1.3 <br />
 *          La test suite include test cases di metodi di accesso, modifica e interrogazione di una {@link myAdapter.HSet} <br />
 *          tramite la classe {@link myAdapter.KeySet}, che implementa l'interfaccia. <br />
 *          Si testa il corretto funzionamento solo dei metodi di cui si e' effettuato l'override nell'implementazione di {@link myAdapter.KeySet}. <br />
 *          Si suppone testato e funzionante il backing tra mappa e vista delle chiavi, di classe {@link myAdapter.KeySet}, che verra' testato in {@link myTest.TestMapAdapter}. <br />
 *          Si suppongono funzionanti i metodi di {@link myAdapter.MapAdapter} e {@link myAdapter.ValueCollection}. <br /> 
 *          Nella descrizoine dei diversi testCase, e' implicita l'esecuzione della funzione setUp annotata con @Before che popola una mappa per ottenere la vista delle chiavi <br />
 *          e che si suppone testato e funzionante il metodo {@link myAdapter.MapAdapter#put put}, usata per la funzione setUp.
 */
public class TestKeySet 
{    

    private MapAdapter map = new MapAdapter();
    private HSet testSet = map.keySet();

    //METODO PER POPOLARE UNA MAPPA

    /**
     * Configuara l'ambiente dei test cases popolando la mappa la cui vista delle chiavi verra' manipolata.
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
     * Test del metodo {@link myAdapter.KeySet#contains contains} nel caso di argomento {@code null}
     * 
     * @s.ummary  Verifica che il metodo {@code contains()} lancia {@link NullPointerException} se fornito un argomento {@code null}.
     * 
     * @d.esign     Si vuole verificare che alla chiamata del metodo {@code contains()} nel caso di argomento {@code null} venga correttamente <br />
     *              lanciata l'eccezione {@link NullPointerException}.    
     * 
     * @d.escription   Si esegue direttamente il metodo contains fornendo come argomento null e si verifica che venga lanciata un'eccezione NullPointerException.
     * 
     * @p.recond    La vista delle chiavi della mappa e' stata popolata con 4 elementi.
     * 
     * @p.ostcond  La vista delle chiavi rimane invariata.
     * 
     * @r.esult    {@code contains()} deve lanciare {@link NullPointerException} se chiamata con argomento {@code null}.
     * 
     */
    @Test(expected = NullPointerException.class)
    public void test_KeySet_Contains_Null() //Contains con argomento key null
    {
        //testo la presenza che l'elemento null sia presente, aspetto una eccezione
        testSet.contains(null);
    }
    
    /**
     * Test del metodo {@link myAdapter.KeySet#contains contains} su un set contenente elementi e non.
     * 
     * @s.ummary  Verifica che il corretto funzionamento del metodo {@code contains()} su un set vuoto e non.
     * 
     * @d.esign     Si vuole verificare che il metodo {@code contains()} si appoggi correttamente al metodo {@link myAdapter.MapAdapter#containsKey(Object) containsKey} <br />
     *              e che restituisca {@code true} se il valore posto come argomento e' contenuto nel set, {@code false} altrimenti. 
     * 
     * @d.escription   Si suppone testato e funzionante il metodo {@code clear()} <br />
     *                  1.  Si controlla che il set contenga una delle chiavi note della mappa che e' stata popolata in fase di setUp <br />
     *                  2.  Si verifica che contains() restituisca true per il valore comparato. <br />
     *                  3.  Si verifica che contains() restituisca false per un oggetto che si sa non sia presente nel set  <br />
     *                  4.  Si svuota il set e si verifica che contains() restituisca false per qualsiasi oggetto se il set e' vuoto.
     * 
     * @p.recond    La vista delle chiavi della mappa e' stata popolata con 4 elementi.
     * 
     * @p.ostcond  La vista delle chiavi e' stata svuotata.
     * 
     * @r.esult    {@code contains()} deve restituire {@code true} se l'argomento e' presente nel set, {@code false} altrimenti.
     * 
     */
    @Test
    public void test_KeySet_Contains() //caso di successo e fallimento
    {
        //controllo che l'elemento 4 sia contenuto nel set delle chiavi
        assertTrue("Se il set contiene l'elemento '4', ritorna true", testSet.contains(4));
    
        //Verifico che l'elemento 9 non sia contenuto nel set di chiavi
        assertFalse("Il set non contiene l'elemento '9'", testSet.contains(9));
        
        //svuoto il set
        testSet.clear();
        //controllo che non a prescindere dall'oggetto posto come argomento, restituisce false
        assertFalse("Il e quindi non contiene nessun oggetto", testSet.contains(new Object()));

    }

    //-------FINE TEST DEL METODO contains() ----------
    //-------TEST DEL METODO iterator() ----------
    
    /**
     * Test del metodo {@link myAdapter.KeySet#iterator() iterator} su un set contenente elementi e non.
     * 
     * @s.ummary  Verifica che il metodo {@code iterator()} restituisca un iteratore sugli elementi del set.
     * 
     * @d.esign     Si vuole verificare che il metodo {@code contains()} restituisca un'istanza di {@link myAdapter.HIterator}. <br />
     *              L'iteratore potra' leggere gli elementi del set e rimuovere elementi da esso.
     * 
     * @d.escription   Si suppone testata e funzionante la classe {@link myAdapter.KeySet.SetIterator} e il metodo {@code contains()}. <br />
     *                  1.  Si verifica che l'oggetto restituito alla chiamata di iterator() sia un'istanza di HIterator. <br />
     *                  2.  Si crea un ciclo while che continua finche' l'iteratore ha elementi da leggere. <br />
     *                  3.  Dentro al ciclo while si legge l'elemento successivo della vista e si verifica che sia presente nel set. <br />
     *                  4.  Si rimuove l'elemento appena letto tramite l'iteratore e si controlla che non sia piu' contenuto nel set. <br />
     *                  5.  Alla fine del ciclo while il set e' vuoto e si controlla che l'iteratore di un set vuoto non abbia elementi successivi. <br />
     * 
     * @p.recond    La vista delle chiavi della mappa e' stata popolata con 4 elementi.
     * 
     * @p.ostcond  La vista delle chiavi e' stata svuotata.
     * 
     * @r.esult    {@code iterator()} deve restituire un'istanza di {@code HIterator} che compie correttamente i metodi descritti in {@link myAdapter.HIterator}.
     * 
     */
    @Test
    public void test_KeySet_Iterator() //Controllo che effettivamente l'iteratore di set sia un iteratore
    {
        //mi assicuro che l'iteratore del set sia un iteratore
        assertTrue(testSet.iterator() instanceof HIterator);
        
        //controllo che funzioni l'iteratore
        HIterator it = testSet.iterator();
        while(it.hasNext())
        {
            Object k = it.next();
            assertTrue(testSet.contains(k));
            it.remove();
            assertFalse(testSet.contains(k));
        }
        assertTrue(testSet.size() == 0);
        //ora il set e' vuoto, verifico che l'iteratore ad un set vuoto non abbia elementi successivi
        it = testSet.iterator();
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
     * Test del metodo {@link myAdapter.KeySet#remove(Object o) remove} quando l'argomento fornito e' {@code null}. 
     * 
     * @s.ummary  Verifica che venga lanciata l'eccezione {@link NullPointerException} quando l'oggetto fornito e' {@code null}.
     * 
     * @d.esign     Si vuole verificare che il metodo {@code remove(Object o)} lanci correttamente {@link NullPointerException} quando <br />
     *              l'argomento fornito e' {@code null}. 
     * 
     * @d.escription   1.  Si chiama direttamente remove(null) sul set. <br />
     *                 2.  Si verifica il corretto lancio di NullPointerException
     * 
     * @p.recond    La vista delle chiavi della mappa e' stata popolata con 4 elementi.
     * 
     * @p.ostcond  La vista delle chiavi e' rimasta invariata.
     * 
     * @r.esult    {@code remove(Object o)} deve lanciare {@link NullPointerException} quando l'argomento e' {@code null}. 
     * 
     */
    @Test(expected = NullPointerException.class)
    public void test_KeySet_Remove_Null()
    {
        testSet.remove(null);
    }

    /**
     * Test del metodo {@link myAdapter.KeySet#remove(Object o) remove} con {@code o} presente e non dal set. 
     * 
     * @s.ummary  Verifica la corretta rimozione dell'elemento dal set, se presente.
     * 
     * @d.esign     Si vuole verificare che il metodo {@code remove(Object o)} restituisca correttamente {@code true} quando l'oggetto specificato <br />
     *              e' presente nel set e viene rimosso correttamente, {@code false} altrimenti. <br />
     *              Si verifica che il metodo {@code remove(Object o)} si appoggia al metodo {@link myAdapter.MapAdapter#remove remove} per la rimozione della chiave.
     * 
     * @d.escription   Si suppongono testati e funzionanti i metodi {@code contains()} <br />
     *                  1.  Si verifica che il set contenga un elemento noto. <br />
     *                  2.  Si invoca remove() usando come argomento l'elemento noto e si verifica che restituisca {@code true}. <br />
     *                  3.  Si verifica che l'elemento noto non sia piu' presente nel set. <br />
     *                  4.  Si invoca remove() usando come argomento un elemento non presente nel set e si verifica che restituisca {@code false}. <br />
     *                  5.  Si verifica che anche la mappa ha invocato il metodo remove, perdendo la coppia con chiave il valore noto.
     * 
     * @p.recond    La vista delle chiavi della mappa e' stata popolata con 4 elementi.
     * 
     * @p.ostcond  La vista delle chiavi e' stata modificata, ha perso un elemento.
     * 
     * @r.esult    {@code remove(Object o)} verifica se l'elemento specificato e' presente nel set, se presente <br />
     *              ritorna {@code true} e rimuove dal set l'istanza di quell'elemento, altrimenti ritorna {@code false}.
     */
    @Test
    public void test_KeySet_Remove()   //caso di successo e fallimento
    {
        //controllo che il set contenga la chiave 4
        assertTrue(testSet.contains(4));
        //rimuovo la chiave 4
        assertTrue(testSet.remove(4));
        //controllo che il set non contenga altri elementi di chiave 4
        assertFalse(testSet.contains(4));

        //controllo che il set non cambia se si prova a rimuove la chiave 24, che non appartiene al set
        assertFalse(testSet.remove(24));

        //controllo che anche la mappa abbia rimosso la chiave 4
        assertFalse(map.containsKey(4));
    }

    //-------FINE TEST DEL METODO remove() ----------
    //-------TEST DEL METODO containsAll(HCollection c) ----------

    /**
     * Test del metodo {@link myAdapter.KeySet#containsAll(HCollection c) containsAll} quando l'argomento fornito e' {@code null}. 
     * 
     * @s.ummary  Verifica che venga lanciata l'eccezione {@link NullPointerException} quando la collezione fornita e' {@code null}.
     * 
     * @d.esign     Si vuole verificare che il metodo {@code containsAll(HCollection c)} lanci correttamente {@link NullPointerException} quando <br />
     *              l'argomento fornito e' {@code null}. 
     * 
     * @d.escription   1.  Si esegue direttamente containsAll(null) e si verifica venga lanciata un'eccezione NullPointerException 
     * 
     * @p.recond    La vista delle chiavi della mappa e' stata popolata con 4 elementi.
     * 
     * @p.ostcond  La vista delle chiavi e' rimasta invariata.
     * 
     * @r.esult    {@code containsAll(HCollection c)} deve lanciare {@link NullPointerException} quando l'argomento e' {@code null}. 
     * 
     */
    @Test (expected = NullPointerException.class)
    public void test_KeySet_ContainsAll_Null()
    {
        testSet.containsAll(null);
    }
    
    /**
     * Test del metodo {@link myAdapter.KeySet#containsAll(HCollection c) containsAll}.
     * 
     * @s.ummary  Verifica che venga il metodo {@code containsAll()} si comporti nel modo atteso sia nel caso gli elementi siano tutti contenuti, sia non.
     * 
     * @d.esign     Si vuole verificare che il metodo {@code containsAll(HCollection c)} verifichi correttamente la presenza nel set di una collezione di oggetti <br />
     *              restituendo {@code false} quando anche un solo oggetto dovesse mancare, {@code true} se tutti gli elementi della collezione specificata sono contenuti nel set chiamante. 
     * 
     * @d.escription   Si suppongono testati e funzionanti i metodi {@link myAdapter.MapAdapter#keySet() keySet}, {@link myAdapter.MapAdapter#put put} <br /> 
     *                  1.  Si istanzia un secondo set vuoto. <br />
     *                  2.  Si chiama containsAll() usando come argomento il set vuoto e si verifica che il metodo restituisca true. <br />
     *                  3.  Si popola la mappa e di conseguenza si modifica il set creato al punto 1. <br />
     *                  4.  Si chiama containsAll() usando come argomento il set appena riempito e si verifica che restituisca true. <br />
     *                  5.  Si aggiunge un ulteriore elemento al set ausiliario, che non appartiene anche al set chiamante. <br />
     *                  6.  Si chiama containsAll() usando come argomento il set ausiliario ora contenente un elemento non contenuto nel set chiamante. <br />
     *                  7   Si verifica che il metodo restituisca false in quanto non tutti gli elementi del set ausiliario sono contenuti nel set chiamante
     * 
     * @p.recond    La vista delle chiavi della mappa e' stata popolata con 4 elementi.
     * 
     * @p.ostcond  La vista delle chiavi e' rimasta invariata.
     * 
     * @r.esult    Il metodo {@code containsAll(HCollection c)} deve restituire {@code false} se manca anche solo un oggetto, {@code true} se tutti gli oggetti sono presenti.
     * 
     */
    @Test
    public void test_KeySet_ContainsAll() //caso di successo e fallimento
    {
        //creo una nuova mappa e la popolo
        MapAdapter map2 = new MapAdapter();
        //creo un set a partire dalla mappa
        HSet contained = map2.keySet(); 

        //controllo che testSet contenga il set vuoto contained
        assertTrue(testSet.containsAll(contained));

        //popolo la mappa 2
        map2.put(2, "");
        map2.put(4, "");
        //controllo che testSet contenga sia la chiave 2, che la chiave 4
        assertTrue(testSet.containsAll(contained));

        //aggiungo un elemento alla map2 che non appartiene a map
        map2.put(64, "");
        //ora contained contiene 3 elementi, 2 sono anche contenuti in testSet, 1 no

        //controllo che testSet non contenga tutto il set contained
        assertFalse(testSet.containsAll(contained));
    }

    //-------FINE TEST DEL METODO containsAll(HCollection c) ----------
    //-------TEST DEL METODO addAll(HCollection c) ----------

    //quelli del metodo ValueCollection

    //-------FINE TEST DEL METODO addAll(HCollection c) ----------
    //-------TEST DEL METODO removeAll(HCollection c) ----------

    /**
     * Test del metodo {@link myAdapter.KeySet#removeAll(HCollection c) removeAll} quando l'argomento fornito e' {@code null}. 
     * 
     * @s.ummary  Verifica che venga lanciata l'eccezione {@link NullPointerException} quando la collezione fornita e' {@code null}.
     * 
     * @d.esign     Si vuole verificare che il metodo {@code removeAll(HCollection c)} lanci correttamente {@link NullPointerException} quando <br />
     *              l'argomento fornito e' {@code null}. 
     * 
     * @d.escription    1.  Si chiama direttamente removeAll(null) sul set. <br />
     *                  2.  Si verifica il corretto lancio di NullPointerException
     * 
     * @p.recond    La vista delle chiavi della mappa e' stata popolata con 4 elementi.
     * 
     * @p.ostcond  La vista delle chiavi e' rimasta invariata.
     * 
     * @r.esult    {@code removeAll(HCollection c)} deve lanciare {@link NullPointerException} quando l'argomento e' {@code null}. 
     * 
     */
    @Test(expected = NullPointerException.class)
    public void test_KeySet_RemoveAll_Null()
    {
        testSet.removeAll(null);
    }
    
    /**
     * Test del metodo {@link myAdapter.KeySet#removeAll removeAll} quando il set e la collezione hanno elementi in comune. 
     * 
     * @s.ummary  Verifica la corretta rimozione di un'intera collezione dal set di partenza, se hannoo elementi in comune.
     * 
     * @d.esign     Si vuole verificare che il metodo {@code removeAll(HCollection c)} restituisca correttamente {@code true} quando la collezione specificata 
     *              contiene anche elementi presenti nel set chiamante e rimuove tutte le istanze da questo, {@code false} se collezione e set non hanno elementi in comune.
     * 
     * @d.escription   Si suppongono testati e funzionanti i metodi {@code iterator()}, {@code size()}, {@code remove()}, {@code contains()} <br />
     *                  1.  Si crea una collezione di supporto con gli stessi elementi al set di partenza. <br />
     *                  2.  Si rimuove un elemento dalla collezione ausiliaria in modo che set e collezione non abbiano piu' gli stessi elementi. <br />
     *                  3.  Si verifica che alla chiamata removeAll() usando come argomento la collezione ausiliaria il risultato sia true. <br />
     *                  4.  Si controlla che il set e la collezione non abbiano piu' elementi in comune. <br />
     *                  5.  Si verifica che l'unico elemento presente nel set di partenza e' quello precedentemente rimosso dalla collezione di supporto. 
     * 
     * @p.recond    La vista delle chiavi della mappa e' stata popolata con 4 elementi.
     * 
     * @p.ostcond  La vista delle chiavi e' stata modificata, e' rimasto un solo elemento.
     * 
     * @r.esult    {@code removeAll(HCollection c)} deve restituire {@code true} se il set viene modificato come risultato della chiamata del metodo 
     *              e {@code false} altrimenti.
     * 
     */
    @Test
    public void test_KeySet_RemoveAll_True()    
    {
        //creo un nuovo set e lo popolo per poi rimuovere i suoi elementi da testSet
        MapAdapter map2 = new MapAdapter(map);
        HSet setToRemove = map2.keySet();                //creo una copia del testSet
        setToRemove.remove(4);                        //e rimuovo dalla copia un elemento
        
        //controllo che testSet venga modificato dalla chiamata del metodo RemoveAll    
        assertTrue(testSet.removeAll(setToRemove));     //rimuovo dal testSet la copia

        //controllo che i due set non abbiano elementi in comune
        HIterator it = setToRemove.iterator();
        while(it.hasNext())
        {
            //il testSet non contiene piu' elementi contenuti anche nella copia
            assertFalse(testSet.contains(it.next()));  
        }

        //l'unico elemento che testSet contiene e' quello precedentemente rimosso dalla copia
        assertTrue(testSet.contains(4));  
        assertTrue(testSet.size() == 1);            
    }

    /**
     * Test del metodo {@link myAdapter.KeySet#removeAll removeAll} quando il set e la collezione non hanno elementi in comune. 
     * 
     * @s.ummary  Verifica che se set e collezione non hanno elementi in comune il metodo {@code removeAll(HCollection c)} <br />
     *              restituisce {@code false} e il set di partenza non cambia.
     * 
     * @d.esign     Si vuole verificare che il metodo {@code removeAll(HCollection c)} restituisca correttamente {@code false} quando la collezione specificata <br />
     *               non ha elementi in comune con il set chiamante.
     * 
     * @d.escription   Si suppongono testati e funzionanti i metodi {@code iterator()}, {@code contains()} <br />
     *                  1.  Si crea una collezione di supporto di un elemento, diverso dal set. <br />
     *                  2.  Si controlla che set e collezione non abbiano elementi in comune. <br />
     *                  3.  Si verifica che alla chiamata removeAll() usando come argomento la collezione ausiliaria il risultato sia false. <br />
     *                  4.  Si verifica che il set di partenza non sia stato modificato.
     * 
     * @p.recond    La vista delle chiavi della mappa e' stata popolata con 4 elementi.
     * 
     * @p.ostcond  La vista delle chiavi rimasta invariata.
     * 
     * @r.esult    {@code removeAll(HCollection c)} deve restituire {@code false} se il set e la collezione non presentano elementi in comune.
     *  
     */
    @Test
    public void test_KeySet_RemoveAll_False()
    {
        //creo un nuovo set e lo popolo per poi rimuovere i suoi elementi da testSet
        MapAdapter map2 = new MapAdapter();
        map2.put(15, "");

        //creo un set che non ha elementi in comune con testSet
        HSet setToRemove = map2.keySet();  

        //controllo che i due set non abbiano elementi in comune
        HIterator it = setToRemove.iterator();
        while(it.hasNext())
        {
            //il testSet non contiene elementi contenuti anche nel set di supporto
            assertFalse(testSet.contains(it.next()));  
        }

        //controllo che testSet non cambia alla chiamata del metodo removeAll
        assertFalse(testSet.removeAll(setToRemove));   
        assertTrue(testSet.contains(4) && testSet.contains(2) && testSet.contains(7) && testSet.contains(0));  

    }

    //-------FINE TEST DEL METODO removeAll(HCollection c) ----------
    //-------TEST DEL METODO retainAll(HCollection c) ----------

    //quelli del metodo ValueCollection

    //-------FINE TEST DEL METODO retainAll(HCollection c) ----------
    //-------TEST DEL METODO clear() ----------

    //quelli del metodo ValueCollection

    //-------FINE TEST DEL METODO clear() ----------
    //-------TEST DEL METODO equals() ----------

    /**
     * Test del metodo {@link myAdapter.KeySet#equals equals}.
     * 
     * @s.ummary  Verifica che il metodo {@code equals()} funzioni correttamente, fornendo HSet uguali e non uguali.
     * 
     * @d.esign     Si vuole verificare che {@code equals()} restituisca correttamente {@code true} se l'oggetto fornito come argomento e' <br />
     *              anch'esso un HSet, i due set hanno le stesse dimensioni e uno contiene l'altro, {@code false} quando anche una di queste condizioni viene a mancare.
     * 
     * @d.escription   Si suppongono testati e funzionanti il metodo {@code size()}, {@code containsAll()}, {@code remove()} <br />
     *                  1.  Si crea un secondo HSet con gli stessi elementi del set di partenza.  <br />
     *                  2.  Si verifica che le due viste di chiavi sono entrambe HSet, hanno la stessa dimensione e si contengono una nell'altra. <br />
     *                  3.  Si controlla che le due HSet siano uguali. <br />
     *                  4.  Si rimuove un elemento dal set di supporto, ora i set avranno dimensione diversa. <br />
     *                  5.  Si verifica che i due set siano diversi. <br />
     *                  6.  Si aggiunge un elemento nuovo al set di supporto, non contenuto nel set di partenza. <br />
     *                  7.  Si controlla che i due set abbiano la stessa dimensione. <br />
     *                  8.  Si controlla che i due set non si contenga uno nell'altro anche se della stessa dimensione. <br />
     *                  9.  Si verifica che il metodo equals() con argomento il set di supporto resulti false. <br />
     * 
     * @p.recond    La vista delle chiavi e' stata popolata.
     * 
     * @p.ostcond  La vista delle chiavi non e' stata modificata.
     * 
     * @r.esult    Il metodo {@code equals()} deve restituire {@code true} quando i due HSet rispettano le specifiche, {@code false} altrimenti.
     * 
     */
    @Test
    public void test_KeySet_Equals_Set()
    {
        //creo una nuova mappa e la popolo 
        MapAdapter map2 = new MapAdapter(map);
        //creo un set che ha tutti gli elementi in comune con testSet
        HSet setEquals = map2.keySet();  

        //un set e' uguale ad un altro se hanno la stessa dimensione e contengono gli stessi argomenti
        assertEquals(setEquals.getClass(), testSet.getClass());
        assertTrue(testSet.size() == setEquals.size());
        assertTrue(testSet.containsAll(setEquals) && setEquals.containsAll(testSet));
        //controllo allora che il metodo equals() restituisca true
        assertTrue(testSet.equals(setEquals));

        //creo un set che non ha tutti gli elementi in comune con testSet
        setEquals.remove(4);

        //si verifica che i due set non siano uguali, dimensione diversa
        assertFalse(testSet.size() == setEquals.size());
        assertFalse(testSet.equals(setEquals));

        //aggiungo un elemento a map2
        map2.put(6, "one");
        //controllo che le dimensioni dei set siano uguali
        assertTrue(testSet.size() == setEquals.size());

        //controllo che testSet non contenga setEquals e vice-versa
        assertFalse(testSet.containsAll(setEquals));
        assertFalse(setEquals.containsAll(testSet));

        //controllo che le due collezioni non siano uguali
        assertFalse(testSet.equals(setEquals));
    }

    /**
     * Test del metodo {@link myAdapter.KeySet#equals equals} tra un set di tipo HSet e un oggetto non di tipo HSet.
     * 
     * @s.ummary  Verifica che il metodo {@code equals()} restituisca sempre {@code false} se l'argomento di equals() non e' un oggetto di tipo HSet.
     * 
     * @d.esign     Si vuole verificare che {@code equals()} restituisca correttamente {@code false} se l'oggetto fornito come argomento non <br />
     *              e' di tipo HSet, anche se si tratta di una collezione e contengono gli stessi elementi.
     * 
     * @d.escription   Si suppongono testati e funzionanti il metodo  {@code containsAll()} <br />
     *                  1.  Si crea una collezione con gli stessi elementi del set. <br />
     *                  2.  Si verifica che il set contiene la collezione. <br />
     *                  3.  Si verifica che il set non e' uguale alla collezione, equals() ritorna false. <br />
     *                  4.  Si verifica che il set non e' uguale ad un oggetto non di tipo HSet, a prescindere dall'oggetto.
     *      
     * @p.recond    La vista delle chiavi e' stata popolata.
     * 
     * @p.ostcond  La vista delle chiavi non e' stata modificata.
     * 
     * @r.esult    Il metodo {@code equals()} deve restituire {@code false} quando si compara un HSet con un oggetto non di tipo HSet.
     * 
     */
    @Test
    public void test_KeySet_Equals_NotSet()
    {
        //creo una nuova mappa e la popolo
        MapAdapter map2 = new MapAdapter();
        map.put("one", 4);
        map.put("flower", 2);
        map.put("plum", 7);
        map.put("kiss", 0);
        //creo una collezione e controllo se e' uguale al set 
        HCollection colEquals = map2.values(); 

        //il set contiene la collezione
        assertTrue(testSet.containsAll(colEquals));     
        //ma non e' uguale alla collezione perche' non sono della stessa classe
        assertNotEquals(testSet.getClass(), colEquals.getClass());
        assertFalse(testSet.equals(colEquals));      
        
        //provo ora a usare un oggetto di tipo String, che non e' un set, mi aspetto falso
        assertFalse(testSet.equals("lattuga"));
    }

    //-------FINE TEST DEL METODO equals() ----------
    //-------TEST DEL METODO hashCode() ----------

    //quelli del metodo ValueCollection



}
