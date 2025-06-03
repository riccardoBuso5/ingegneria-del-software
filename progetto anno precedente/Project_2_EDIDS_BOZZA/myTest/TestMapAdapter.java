package myTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import myAdapter.*;

/**
 * Test suite per la classe {@link myAdapter.MapAdapter}.
 * <p>
 * Summary: Verifica il corretto funzionamento dei metodi della classe {@code MapAdapter} che implementa l'interfaccia {@link myAdapter.HMap}.
 * <p>
 * Design:  Si utilizzano JUnit 4.13.2, hamcrest-core-1.3 <br />
 *          La test suite include test cases di metodi di accesso, modifica e interrogazione di una {@link myAdapter.HMap} <br />
 *          tramite la classe {@link myAdapter.MapAdapter}, di cui viene fornita un'istanza ripopolata prima di ogni testCase. <br />
 *          Si suppongono funzionanti le classi {@link myAdapter.KeySet}, {@link myAdapter.ValueCollection}, {@link myAdapter.MapAdapter.EntrySet} e <br />
 *          {@link myAdapter.MapAdapter.Entry} che verranno testate nelle loro testSuite apposite. <br />
 *          Oltre ai testCase relativi ai metodi, sono presenti anche i testCase che controllano l'avvenimento del backing tra mappa e le sue liste associate. <br />
 *          Nella descrizione dei diversi test case e' implicita l'esecuzione della funzione di setUp annotata con @Before.
 */
public class TestMapAdapter 
{
    private MapAdapter testMap = new MapAdapter();

    //METODO PER POPOLARE UNA MAPPA

    /**
     * Configuara l'ambiente dei test cases popolando la mappa che verra' manipolata.
     * 
     */
    @Before
    public void setUp()
    {
        //popolo la mappa vuota
        testMap.put(4, "one");
        testMap.put(2, "flower");
        testMap.put(7, "plum");
        testMap.put(0, "kiss");
    }

    //-------TEST DEL METODO size() ----------
    
    /**
     * Test del metodo {@link myAdapter.MapAdapter#size() size}.
     * 
     * @s.ummary    Verifica che il metodo {@code size()} ritorna correttamente la dimensione della mappa.
     * 
     * @d.esign     Si vuole verificare che {@code size()} si appoggi correttamente al metodo {@link java.util.Hashtable#size() size} e ritorni la corretta <br />
     *               dimensione della mappa senza causare errori.
     * 
     * @d.escription   Si suppongono testati e funzionanti il metodo {@code size}, {@code clear()}, {@code remove()} <br />
     *                  1.  Si controlla che la dimensione della mappa sia quella prevista. <br />
     *                  2.  Si rimuove un elemento dalla mappa e si controlla che la dimensione sia diminuita di 1. <br />
     *                  3.  Si svuota la mappa e si controlla che la dimensione diventi 0. <br />
     * 
     * @p.recond   La mappa e' stata popolata con 4 elementi.
     * 
     * @p.ostcond  La mappa stata svuotata.
     * 
     * @r.esult    {@code size()} deve restuire la dimensione della mappa, il numero degli elementi che la mappa contiene.
     * 
     */
    @Test
    public void test_Map_Size()
    {   
        //Verifico che la mappa sia della giusta dimensione
        assertEquals("Se il numero di elementi della mappa e' 4, ritorna True.",(long) 4,(long)testMap.size());

        //rimuovo un elemento dalla mappa e controllo che la dimensione sia diminuita di 1
        testMap.remove(4);
        assertEquals("Se il numero di elementi della mappa e' 3, ritorna True.",(long) 3,(long)testMap.size());
        
        //svuoto la mappa
        testMap.clear();
        //controllo che la dimensione della mappa sia 0
        assertTrue(testMap.size() == 0);
    }

    //-------FINE TEST DEL METODO size() ----------
    //-------TEST DEL METODO isEmpty() ----------

    /**
     * Test del metodo {@link myAdapter.MapAdapter#isEmpty() isEmpty} su una mappa.
     * 
     * @s.ummary Verifica che il metodo {@code isEmpty()} restituisca correttamente {@code true} quando la mappa e' vuota, {@code false} altrimenti.
     * 
     * @d.esign    Si vuole verificare che {@code isEmpty()} si appoggi correttamente al metodo {@link java.util.Hashtable#isEmpty() isEmpty} e <br />
     *              restituisca correttamente {@code true} quando la mappa e' vuota, {@code false} se la mappa contiene almeno un elemento.
     * 
     * @d.escription   Si suppongono testati e funzionanti il metodo {@code size()}, {@code clear()}, <br />
     *                  1.  Si controlla che la mappa inizialmente non sia vuota, isEmpty() restituisce false, e che abbia la dimensione attesa. <br />
     *                  2.  Si svuota la mappa. <br />
     *                  3.  Si verifica che isEmpty() restituisca true e che la dimensione attesa sia 0. <br />
     * 
     * @p.recond   La mappa e' stata popolata con 4 elementi.
     * 
     * @p.ostcond  La mappa e' stata svuotata.
     * 
     * @r.esult    {@code isEmpty()} deve restuire {@code true} se e solo se la mappa e' priva di elementi, {@code false} altrimenti.
     * 
     */
    @Test
    public void test_Map_IsEmpty()
    {
        //verifico che la mappa non sia vuota
        assertFalse("Controllo che la mappa non sia vuota.", testMap.isEmpty());
        //controllo che la mappa sia della dimensione aspettata
        assertTrue(testMap.size() == 4);
        //Svuoto la mappa
        testMap.clear();
        //verifico che la mappa sia vuota
        assertTrue("Controllo che la mappa sia vuota.", testMap.isEmpty());
        //controllo che la mappa sia della dimensione aspettata
        assertTrue(testMap.size() == 0);
    }

    //-------FINE TEST DEL METODO isEmpty() ----------
    //-------TEST DEL METODO containsKey() ----------

    /**
     * Test del metodo {@link myAdapter.MapAdapter#containsKey containsKey} nel caso di argomento {@code null}
     * 
     * @s.ummary   Verifica che il metodo {@code containsKey} lancia {@link NullPointerException} se fornito un argomento {@code null}.
     * 
     * @d.esign    Si vuole verificare che alla chiamata del metodo {@code containsKey} nel caso di argomento {@code null} venga correttamente <br />
     *              lanciata l'eccezione {@link NullPointerException}.    
     * 
     * @d.escription    1.  Si esegue direttamente il metodo containsKey(null) fornendo come argomento null. <br />
     *                  2.  Si verifica che venga lanciata un'eccezione NullPointerException.
     * 
     * @p.recond   La mappa e' stata popolata con 4 elementi.
     * 
     * @p.ostcond  La mappa rimane invariata.
     * 
     * @r.esult    {@code containsKey} deve lanciare {@link NullPointerException} se chiamata con argomento {@code null}.
     * 
     */
    @Test(expected = NullPointerException.class)
    public void test_Map_ContainsKey_Null()
    {
        testMap.containsKey(null);
    }

    /**
     * Test del metodo {@link myAdapter.MapAdapter#containsKey containsKey} su una mappa contenente elementi e non.
     * 
     * @s.ummary   Verifica che il corretto funzionamento del metodo {@code containsKey()} su una mappa vuota e non.
     * 
     * @d.esign    Si vuole verificare che il metodo {@code containsKey()} si appoggi correttamente al metodo {@link java.util.Hashtable#containsKey(Object) containsKey} <br />
     *              e che restituisca {@code true} se il valore posto come argomento e' una chiave della mappa, {@code false} altrimenti. 
     * 
     * @d.escription   Si suppone testato e funzionante il metodo {@code clear()} <br />
     *                  1.  Si controlla che la mappa contenga una delle chiavi note con di cui e' stata popolata. <br />
     *                  2.  Si verifica che containsKey() restituisca true per la chiave comparata. <br />
     *                  3.  Si verifica che containsKey() restituisca false per una chiave non presente nella mappa.  <br />
     *                  4.  Si svuota la mappa e si verifica che containsKey() restituisca false per qualsiasi oggetto se la mappa e' vuota.
     * 
     * @p.recond   La mappa e' stata popolata con 4 elementi.
     * 
     * @p.ostcond  La mappa e' stata svuotata.
     * 
     * @r.esult    {@code containsKey()} deve restituire {@code true} se la chiave cercata e' presente nella mappa, {@code false} altrimenti.
     * 
     */
    @Test   
    public void test_Map_ContainsKey()
    {
        //controllo che la testMap contenga la chiave 0
        assertTrue(testMap.containsKey(0));

        //controllo che la testMap non contenga la chiave 10
        assertFalse(testMap.containsKey(10));

        //svuoto la mappa
        testMap.clear();
        //controllo che non contiene piu' la chiave nota
        assertFalse(testMap.containsKey(0));
    }

    //-------FINE TEST DEL METODO containsKey() ----------
    //-------TEST DEL METODO containsValue() ----------

    /**
     * Test del metodo {@link myAdapter.MapAdapter#containsValue containsValue} nel caso di argomento {@code null}
     * 
     * @s.ummary   Verifica che il metodo {@code containsValue} lancia {@link NullPointerException} se fornito un argomento {@code null}.
     * 
     * @d.esign    Si vuole verificare che alla chiamata del metodo {@code containsValue} nel caso di argomento {@code null} venga correttamente <br />
     *              lanciata l'eccezione {@link NullPointerException}.    
     * 
     * @d.escription    1.  Si esegue direttamente il metodo containsValue(null) fornendo come argomento null. <br />
     *                  2.  Si verifica che venga lanciata un'eccezione NullPointerException.
     * 
     * @p.recond   La mappa e' stata popolata con 4 elementi.
     * 
     * @p.ostcond  La mappa rimane invariata.
     * 
     * @r.esult    {@code containsValue} deve lanciare {@link NullPointerException} se chiamata con argomento {@code null}.
     * 
     */
    @Test(expected = NullPointerException.class)
    public void test_Map_ContainsValue_Null()
    {
        testMap.containsValue(null);
    }

    /**
     * Test del metodo {@link myAdapter.MapAdapter#containsValue containsValue} su una mappa contenente elementi e non.
     * 
     * @s.ummary   Verifica che il corretto funzionamento del metodo {@code containsValue()} su una mappa vuota e non.
     * 
     * @d.esign    Si vuole verificare che il metodo {@code containsValue()} si appoggi correttamente al metodo {@link java.util.Hashtable#contains(Object) contains} <br />
     *              e che restituisca {@code true} se il valore posto come argomento e' uno dei valori della mappa, {@code false} altrimenti. 
     * 
     * @d.escription   Si suppone testato e funzionante il metodo {@code clear()} <br />
     *                  1.  Si controlla che la mappa contenga uno dei valori noti con di cui e' stata popolata. <br />
     *                  2.  Si verifica che containsValue() restituisca true per il valore comparato. <br />
     *                  3.  Si verifica che containsValue() restituisca false per un valore non presente nella mappa.  <br />
     *                  4.  Si svuota la mappa e si verifica che containsValue() restituisca false per qualsiasi oggetto se la mappa e' vuota.
     * 
     * @p.recond   La mappa e' stata popolata con 4 elementi.
     * 
     * @p.ostcond  La mappa e' stata svuotata.
     * 
     * @r.esult    {@code containsValue()} deve restituire {@code true} se il valore specificato e' presente nella mappa, {@code false} altrimenti.
     * 
     */
    @Test
    public void test_Map_ContainsValue()
    {
        //controllo che la testMap contenga il valore "plum"
        assertTrue(testMap.containsValue("plum"));

        //controllo che la testMap non contenga il valore 10
        assertFalse(testMap.containsValue(10));

        //svuoto la mappa
        testMap.clear();
        //controllo che non contiene piu' la chiave nota
        assertFalse(testMap.containsValue("plum"));
    }

    //-------FINE TEST DEL METODO containsValue() ----------
    //-------TEST DEL METODO get() ----------

    /**
     * Test del metodo {@link myAdapter.MapAdapter#get get} nel caso di argomento {@code null}
     * 
     * @s.ummary   Verifica che il metodo {@code get} lancia {@link NullPointerException} se fornito un argomento {@code null}.
     * 
     * @d.esign    Si vuole verificare che alla chiamata del metodo {@code get} nel caso di argomento {@code null} venga correttamente <br />
     *              lanciata l'eccezione {@link NullPointerException}.    
     * 
     * @d.escription    1.  Si esegue direttamente il metodo get(null) fornendo come argomento null. <br />
     *                  2.  Si verifica che venga lanciata un'eccezione NullPointerException.
     * 
     * @p.recond   La mappa e' stata popolata con 4 elementi.
     * 
     * @p.ostcond  La mappa rimane invariata.
     * 
     * @r.esult    {@code get} deve lanciare {@link NullPointerException} se chiamata con argomento {@code null}.
     * 
     */
    @Test(expected = NullPointerException.class)
    public void test_Map_Get_Null()
    {
        testMap.get(null);
    }

    /**
     * Test del metodo {@link myAdapter.MapAdapter#get get}.
     * 
     * @s.ummary   Verifica che il metodo {@code get} restituisca il corretto valore associato alla chiave.
     * 
     * @d.esign    Si vuole verificare che il metodo {@code get} si appoggi al metodo {@link java.util.Hashtable#get get} per <br />
     *              ritornare il valore associato alla chiave specificata nella mappa. <br /> 
     *              Se la chiave non e' presente nella mappa, restituisce {@code null}.
     *     
     * @d.escription    Si suppone testato e funzionante il metodo {@code put}. <br />
     *                  1.  Si controlla che la chiamata del metodo get() usando come argomento una chiave nota restituisca il corretto valore ad essa associata nella mappa. <br />
     *                  2.  Si modifica il valore associato alla chiave tramite put(). <br />
     *                  3.  Si controlla che il valore restituito da get() usando la chiave nota sia quello cambiato nel punto 2. <br />
     *                  4.  Si controlla che se non esista una mappatura con la chiave specificata allora get() restituisce null.
     * 
     * @p.recond   La mappa e' stata popolata con 4 elementi.
     * 
     * @p.ostcond  E' stato modificato il valore di una delle chiavi della mappa.
     * 
     * @r.esult    {@code get} deve restituire il valore associato alla chiave specificata nella mappa, se tale chiave non e' <br />
     *              associata ad un valore, restituisce {@code null}.
     * 
     */
    @Test
    public void test_Map_Get()
    {
        //controllo che il valore relativo alla chiave 0 sia "kiss"
        assertEquals("kiss", testMap.get(0));

        //modifico il valore associato alla chiave 0
        testMap.put(0, "fragile");
        //controllo che il valore relativo alla chiave 0 sia "fragile"
        assertEquals("fragile", testMap.get(0));

        //controllo che non esiste nessun valore accoppiato alla chiave "alleluia"
        assertEquals(null, testMap.get("alleluia"));
    }

    //-------FINE TEST DEL METODO get() ----------
    //-------TEST DEL METODO put() ----------

    /**
     * Test del metodo {@link myAdapter.MapAdapter#put put} nel caso di chiave qualsiasi e valore {@code null}
     * 
     * @s.ummary   Verifica che il metodo {@code put} lancia {@link NullPointerException} se fornito un valore {@code null}.
     * 
     * @d.esign    Si vuole verificare che alla chiamata del metodo {@code put} nel caso di valore {@code null} venga correttamente <br />
     *              lanciata l'eccezione {@link NullPointerException}.    
     * 
     * @d.escription    1.  Si esegue direttamente il metodo put(k, null) fornendo chiave qualsiasi e valore null. <br />
     *                  2.  Si verifica che venga lanciata un'eccezione NullPointerException.
     * 
     * @p.recond   La mappa e' stata popolata con 4 elementi.
     * 
     * @p.ostcond  La mappa rimane invariata.
     * 
     * @r.esult    {@code put} deve lanciare {@link NullPointerException} se chiamata con chiave qualsiasi e valore {@code null}.
     * 
     */
    @Test(expected = NullPointerException.class)
    public void test_Map_Put_Null_Value()
    {
        testMap.put("Austria", null);
    }

    /**
     * Test del metodo {@link myAdapter.MapAdapter#put put} nel caso di chiave {@code null} e valore qualsiasi
     * 
     * @s.ummary   Verifica che il metodo {@code put} lancia {@link NullPointerException} se fornita una chiave {@code null}.
     * 
     * @d.esign    Si vuole verificare che alla chiamata del metodo {@code put} nel caso di chiave {@code null} venga correttamente <br />
     *              lanciata l'eccezione {@link NullPointerException}.    
     * 
     * @d.escription    1.  Si esegue direttamente il metodo put(null, v) fornendo chiave null e valore qualsiasi. <br />
     *                  2.  Si verifica che venga lanciata un'eccezione NullPointerException.
     * 
     * @p.recond   La mappa e' stata popolata con 4 elementi.
     * 
     * @p.ostcond  La mappa rimane invariata.
     * 
     * @r.esult    {@code put} deve lanciare {@link NullPointerException} se chiamata con chiave {@code null} e valore qualsiasi.
     * 
     */
    @Test(expected = NullPointerException.class)
    public void test_Map_Put_Null_Key()
    {
        testMap.put(null, 9);
    }

    /**
     * Test del metodo {@link myAdapter.MapAdapter#put put} nel caso di chiave {@code null} e valore {@code null}.
     * 
     * @s.ummary   Verifica che il metodo {@code put} lancia {@link NullPointerException} se fornita una chiave {@code null} e valore {@code null}.
     * 
     * @d.esign    Si vuole verificare che alla chiamata del metodo {@code put} nel caso di chiave e valore entrambi {@code null} venga correttamente <br />
     *              lanciata l'eccezione {@link NullPointerException}.    
     * 
     * @d.escription    1.  Si esegue direttamente il metodo put(null, null) fornendo chiave null e valore null. <br />
     *                  2.  Si verifica che venga lanciata un'eccezione NullPointerException.
     * 
     * @p.recond   La mappa e' stata popolata con 4 elementi.
     * 
     * @p.ostcond  La mappa rimane invariata.
     * 
     * @r.esult    {@code put} deve lanciare {@link NullPointerException} se chiamata con chiave {@code null} e valore {@code null}.
     * 
     */
    @Test(expected = NullPointerException.class)
    public void test_Map_Put_Null_KeyValue()
    {
        testMap.put(null, null);
    }

    /**
     * Test del metodo {@link myAdapter.MapAdapter#put put}.
     * 
     * @s.ummary   Verifica che il metodo {@code put} mappa la specifica chiave al valore specifico.
     * 
     * @d.esign    Si vuole verificare che il metodo {@code put} si appoggia al metodo {@link java.util.Hashtable#put put} <br />
     *              per mappare una chiave specifica ad un valore specifico. Si verifica che il metodo restituisce il precedente <br />
     *              valore associato alla chiave specifica, {@code null} se la mappatura e' nuova.   
     * 
     * @d.escription    Si suppongono testati e funzionanti i metodi {@code containsKey}, {@code get} <br />
     *                  1.  Si controlla che la chiave che si vuole aggiungere non sia presente nella mappa. <br />
     *                  2.  Si aggiunge la nuova chiave nella mappa tramite put() e si verifica che il metodo restituisca null. <br />
     *                  3.  Si controlla che una coppia specifica sia presente nella mappa. <br />
     *                  4.  Si chiama il metodo put() con argomento la chiave della coppia del punto 3. e valore a piacere. <br />
     *                  5.  Si verifica che la chiamata del metodo put() nel punto 5. ritorni il valore della coppia del punto 3. <br />
     *                  6.  Si controlli che il valore associato alla chiave nota dal punto 3. sia il valore nuovo del punto 4.
     * 
     * 
     * @p.recond   La mappa e' stata popolata con 4 elementi.
     * 
     * @p.ostcond  La mappa e' stata modificata, sono stati aggiunte coppie nuove e modificate esistenti.
     * 
     * @r.esult    {@code put} deve mappare la specifica chiave al valore specifico e ritornare il precedente <br />
     *              valore associato alla chiave nella mappa, {@code null} se la chiave non era associata alla mappa
     * 
     */
    @Test
    public void test_Map_Put()
    {
        //controllo che la mappa non contenga la chiave 67
        assertFalse(testMap.containsKey(67));
        //controllo che aggiungendo una nuova chiave, il valore ritornato da put e' null, ovvero non esisteva nessun valore accoppiato a quella chiave
        assertEquals(null, testMap.put(67, "kiss"));

        //controllo che la mappa contenga gia' la chiave 0 e il suo valore sia "kiss"
        assertTrue(testMap.containsKey(0));
        assertEquals("kiss", testMap.get(0));
        //controllo che aggiungendo una coppia usando una chiave gia' presente nella mappa, put mi restituisce il precedente valore associato alla chiave usata
        assertEquals("kiss", testMap.put(0, "devil")); //mi aspetto put ritorni "kiss"
        
        //ora il valore associato alla chiave 0 e' "devil", controllo
        assertEquals("devil",testMap.get(0));
    }

    //-------FINE TEST DEL METODO put() ----------
    //-------TEST DEL METODO remove() ----------

    /**
     * Test del metodo {@link myAdapter.MapAdapter#remove(Object o) remove} quando l'argomento fornito e' {@code null}. 
     * 
     * @s.ummary   Verifica che venga lanciata l'eccezione {@link NullPointerException} quando l'oggetto fornito e' {@code null}.
     * 
     * @d.esign    Si vuole verificare che il metodo {@code remove(Object o)} lanci correttamente {@link NullPointerException} quando <br />
     *              l'argomento fornito e' {@code null}. 
     * 
     * @d.escription   1.  Si chiama direttamente remove(null) sulla mappa. <br />
     *                  2.  Si verifica il corretto lancio di NullPointerException
     * 
     * @p.recond   La mappa e' stata popolata con 4 elementi.
     * 
     * @p.ostcond  La mappa e' rimasta invariata.
     * 
     * @r.esult    {@code remove(Object o)} deve lanciare {@link NullPointerException} quando l'argomento e' {@code null}. 
     * 
     */
    @Test(expected = NullPointerException.class)
    public void test_Map_Remove_Null()
    {
        testMap.remove(null);
    }

    /**
     * Test del metodo {@link myAdapter.MapAdapter#remove(Object o) remove} con {@code o} chiave presente e non dalla mappa. 
     * 
     * @s.ummary   Verifica la corretta rimozione di una coppia chiave-valore dalla mappa, se presente.
     * 
     * @d.esign    Si vuole verificare che il metodo {@code remove(Object o)} si appoggi al metodo {@link java.util.Hashtable#remove remove}  per rimuove una coppia chiave-valore e<br />
     *              che restituisca il valore precedentemente associato alla chiave rimossa o {@code null} se la chiave non era mappata nella mappa. 
     * 
     * @d.escription   Si suppongono testati e funzionanti i metodi {@code containsKey()}, {@code get()} <br />
     *                  1.  Si verifica che la mappa contenga una coppia nota. <br />
     *                  2.  Si invoca remove() usando come argomento la chiave della coppia nota e si verifica che restituisca il valore della coppia nota. <br />
     *                  3.  Si verifica che la mappa non contenga piu' ne' la chiave ne' il valore della coppia nota. <br />
     *                  4.  Si invoca remove() usando come argomento una chiave non presente nella mappa e si verifica che restituisca null. 
     * 
     * @p.recond   La mappa e' stata popolata con 4 elementi.
     * 
     * @p.ostcond  La mappa e' stata modificata, ha perso un elemento.
     * 
     * @r.esult    {@code remove(Object o)} rimuove la coppia con chiave specificata dalla mappa. Se la chiave specificata era presente, <br />
     *               restituisce il valore associato prima della rimozione, altrimenti restituisce {@code null}.
     * 
     */
    @Test
    public void test_Map_Remove()
    {
        //controllo la mappa contenga la coppia [0, "kiss"]
        assertTrue(testMap.containsKey(0) && testMap.get(0).equals("kiss"));

        //controllo che rimuovendo la chiave 0 dalla mappa viene restituito il valore "kiss"
        assertEquals(testMap.get(0), testMap.remove(0));
        //ora la mappa non contiene piu' il valore "kiss"
        assertFalse(testMap.containsValue("kiss"));
        //e neanche la chiave 0
        assertFalse(testMap.containsKey(0));

        //Se la mappa non contiene la chiave da rimuovere, non modifica la mappa e restituisce null
        assertFalse(testMap.containsKey(10));
        assertEquals(null, testMap.remove(10));

    }

    //-------FINE TEST DEL METODO remove() ----------
    //-------TEST DEL METODO putAll() ----------

    /**
     * Test del metodo {@link myAdapter.MapAdapter#putAll putAll} nel caso la mappa data come argomento sia {@code null}
     * 
     * @s.ummary   Verifica che il metodo {@code putAll} lancia {@link NullPointerException} se fornito come argomento {@code null}.
     * 
     * @d.esign    Si vuole verificare che alla chiamata del metodo {@code putAll} nel caso di argomento {@code null} venga correttamente <br />
     *              lanciata l'eccezione {@link NullPointerException}.    
     * 
     * @d.escription    1.  Si esegue direttamente il metodo putAll(null) fornendo come argomento null. <br />
     *                  2.  Si verifica che venga lanciata un'eccezione NullPointerException.
     * 
     * @p.recond   La mappa e' stata popolata con 4 elementi.
     * 
     * @p.ostcond  La mappa rimane invariata.
     * 
     * @r.esult    {@code putAll} deve lanciare {@link NullPointerException} se chiamata argomento {@code null}.
     * 
     */
    @Test (expected = NullPointerException.class)
    public void test_Map_PutAll_Null()
    {
        testMap.putAll(null);
    }

    /**
     * Test del metodo {@link myAdapter.MapAdapter#putAll putAll} tra due mappe che non hanno chiavi in comune.
     * 
     * @s.ummary   Verifica il corretto funzionamento del metodo {@code putAll} tra due mappe che non hanno chiavi in comune.
     * 
     * @d.esign    Si vuole verificare che il metodo {@code putAll} usando come argomento una mappa che non ha chiavi in comune <br />
     *              con la mappa chiamante trasforma quest'ultima in una mappa con tutte le coppie delle due mappe iniziali.
     *     
     * @d.escription    Si suppone testato e funzionante il metodo {@code put}, {@code size()}, {@code get} <br />
     *                  1.  Si crea una mappa ausiliaria e si aggiungono coppie con chiavi non presenti nella mappa iniziale. <br />
     *                  2.  Si controlla che la dimensione della mappa iniziale sia quella attesa. <br />
     *                  3.  Si invoca il metodo putAll usando come argomento la mappa creata nel punto 1. <br />
     *                  4.  Si controlla che la dimensione della mappa iniziale sia aumentata della dimensione della mappa ausiliaria. <br />
     *                  5.  Si controlla che la mappa iniziale ora contenga tutte le coppie della mappa ausiliaria. <br />
     * 
     * @p.recond   La mappa e' stata popolata con 4 elementi.
     * 
     * @p.ostcond  La mappa e' stata ulteriormente popolata.
     * 
     * @r.esult    Il metodo {@code putAll} deve invocare il metodo {@code put} nella mappa chiamante per ogni coppia della mappa specificata.
     * 
     */
    @Test
    public void test_Map_PutAll_NoInCommon()
    {
        //creo una nuova mappa e la popolo
        MapAdapter map2 = new MapAdapter();
        map2.put("aragosta", 5);
        map2.put("cipolla", 0);
        map2.put(14, 0);

        //Controllo la dimensione di testMap sia 4
        assertTrue(testMap.size() == 4);

        //chiamo il metodo putAll
        testMap.putAll(map2);

        //controllo che la dimensione di testMap sia ora 7
        assertTrue(testMap.size() == 7);

        //controllo che testMap contenga tutti gli elementi di map2 come mappati in map2
        assertTrue(testMap.containsKey("aragosta") && testMap.get("aragosta").equals(5));
        assertTrue(testMap.containsKey("cipolla") && testMap.get("cipolla").equals(0));
        assertTrue(testMap.containsKey(14) && testMap.get(14).equals(0));
    }

    /**
     * Test del metodo {@link myAdapter.MapAdapter#putAll putAll} tra due mappe che hanno alcune chiavi in comune.
     * 
     * @s.ummary   Verifica il corretto funzionamento del metodo {@code putAll} tra due mappe che hanno alcune chiavi in comune.
     * 
     * @d.esign    Si vuole verificare che il metodo {@code putAll} usando come argomento una mappa che ha alcune chiavi in comune <br />
     *              con la mappa chiamante  aggiunge a questa le coppie con le nuove chiavi e cambia il valore delle coppie con chiavi in comune.
     *     
     * @d.escription    Si suppone testato e funzionante il metodo {@code containsKey}, {@code put}, {@code size()}, {@code get} <br />
     *                  1.  Si crea una mappa ausiliaria e si aggiungono coppie alcune con chiavi non presenti nella mappa iniziale, altre con chiavi presenti. <br />
     *                  2.  Si controlla che la dimensione della mappa iniziale sia quella attesa. <br />
     *                  3.  Si invoca il metodo putAll usando come argomento la mappa creata nel punto 1. <br />
     *                  4.  Si controlla che la dimensione della mappa iniziale sia aumentata del numero di coppie con chiavi nuove della mappa ausiliaria. <br />
     *                  5.  Si controlla che la mappa iniziale ora contenga tutte le coppie della mappa ausiliaria. <br />
     *                  6.  Si controlla che la mappa di partenza non contenga piu' le coppie iniziali con chiavi in comune con quelle della mappa ausiliaria.
     * 
     * @p.recond   La mappa e' stata popolata con 4 elementi.
     * 
     * @p.ostcond  La mappa e' stata ulteriormente popolata.
     * 
     * @r.esult    Il metodo {@code putAll} deve invocare il metodo {@code put} nella mappa chiamante per ogni coppia della mappa specificata, <br />
     *              aggiungendo coppie nuove se la mappa usata come argomento presenta chiavi nuove o modificando coppie gia' presenti se la mappa <br />
     *              da copiare ha chiavi in comune con la mappa di partenza.
     * 
     */
    @Test
    public void test_Map_PutAll_SomeInCommon()
    {
        //creo una nuova mappa e la popolo
        MapAdapter map2 = new MapAdapter();
        map2.put("aragosta", 5);
        map2.put("cipolla", 0);
        map2.put(0, 0);

        //Controllo la dimensione di testMap sia 4
        assertTrue(testMap.size() == 4);

        //chiamo il metodo putAll
        testMap.putAll(map2);

        //controllo che la dimensione di testMap sia ora 6 e non 7
        assertTrue(testMap.size() == 6);

        //controllo che testMap contenga tutti gli elementi di map2 come mappati in map2
        assertTrue(testMap.containsKey("aragosta") && testMap.get("aragosta").equals(5));
        assertTrue(testMap.containsKey("cipolla") && testMap.get("cipolla").equals(0));
        assertTrue(testMap.containsKey(0) && testMap.get(0).equals(0));
        
        //controllo che testMap non contenga piu' il pairing 0,"kiss"
        assertNotEquals("kiss",testMap.get(0));
    }

    /**
     * Test del metodo {@link myAdapter.MapAdapter#putAll putAll} tra due mappe con entry diverse ma di chiave uguali.
     * 
     * @s.ummary   Verifica il corretto funzionamento del metodo {@code putAll} tra due mappe che hanno chiavi in comune ma valori ad esse associate diversi.
     * 
     * @d.esign    Si vuole verificare che il metodo {@code putAll} non aggiunga elementi nuovi ma modifichi le coppie gia' esistenti se <br />
     *              come argomento viene usata una mappa con entry diverse dalla mappa iniziale ma di chiavi gia' presenti in essa.
     * 
     * @d.escription    Si suppone testato e funzionante il metodo {@code containsKey}, {@code put}, {@code size()}, {@code get} <br />
     *                  1.  Si crea una mappa ausiliaria con entry con chiavi gia' presenti nella mappa iniziale ma valori diversi. <br />
     *                  2.  Si controlla che la mappa iniziale abbia la dimensione attesa. <br />
     *                  3.  Si chiama il metodo putAll usando come argomento la mappa creata al punto 1. <br />
     *                  4.  Si controlla che le dimensioni della mappa non siano cambiate. <br />
     *                  5.  Si controlla che la mappa iniziale contenga tutte le coppie della mappa creata al punto 1. <br />
     *                  6.  Si controlla che la mappa iniziale abbia perso la precedente mappatura per le coppie che avevano chiavi in comune con quelle della mappa del punto 1.
     * 
     * @p.recond   La mappa e' stata popolata con 4 elementi.
     * 
     * @p.ostcond  La mappa ha lo stesso numero i elementi ma di valori diversi da quelli iniziali.
     * 
     * @r.esult    Il metodo {@code putAll} deve invocare il metodo {@code put} nella mappa chiamante modificando coppie gia' presenti se la mappa <br />
     *              da copiare ha solo chiavi in comune con la mappa di partenza.
     * 
     */
    @Test
    public void test_Map_PutAll_ChangeValues()
    {
        //creo una nuova mappa e la popolo
        MapAdapter map2 = new MapAdapter();
        map2.put(4, 0);
        map2.put(7, 0);
        map2.put(0, 0);

        //Controllo la dimensione di testMap sia 4
        assertTrue(testMap.size() == 4);

        //chiamo il metodo putAll
        testMap.putAll(map2);

        //controllo che la dimensione di testMap sia ora 4 e non 7
        assertTrue(testMap.size() == 4); //quindi la dimensione non e' cambiata
        
        //controllo che testMap contenga tutti gli elementi di map2 come mappati in map2
        assertTrue(testMap.containsKey(4) && testMap.get(4).equals(0));
        assertTrue(testMap.containsKey(7) && testMap.get(7).equals(0));
        assertTrue(testMap.containsKey(0) && testMap.get(0).equals(0));

        //controllo che testMap non contenga piu' i pairing 0,"kiss" 4,"one" 7,"plum"
        assertNotEquals("kiss",testMap.get(0));
        assertNotEquals("one",testMap.get(4));
        assertNotEquals("plum",testMap.get(7));
    }

    /**
     * Test del metodo {@link myAdapter.MapAdapter#putAll putAll} tra una mappa e la sua copia.
     * 
     * @s.ummary   Verifica che il metodo {@code putAll} tra una mappa e la sua copia non modifica la mappa chiamante.
     * 
     * @d.esign    Si vuole verificare che il metodo {@code putAll} usando come argomento una copia della mappa chiamante <br />
     *              non modifica la mappa iniziale.
     *     
     * 
     * @d.escription    Si suppone testato e funzionante il metodo {@code size()}, {@code get} <br />
     *                  1.  Si crea una mappa ausiliaria che e' una copia della mappa iniziale. <br />
     *                  2.  Si invoca il metodo putAll() usando come argomento la mappa ausiliaria. <br />
     *                  3.  Si controlla che la dimensione e le coppie chiave-valore della mappa iniziale non sono cambiate.
     * 
     * @p.recond   La mappa e' stata popolata con 4 elementi.
     * 
     * @p.ostcond  La mappa rimane invariata.
     * 
     * @r.esult    Il metodo {@code putAll} non deve modificare la mappa iniziale se ne viene usata una sua copia come argomento.
     * 
     */
    @Test
    public void test_Map_PutAll_NoChange()
    {
        //creo una nuova mappa uguale a testMap
        MapAdapter map2 = new MapAdapter(testMap);

        //chiamo il metodo putAll
        testMap.putAll(map2);

        //controllo che la dimensione di testMap sia sempre 4
        assertTrue(testMap.size() == 4); //quindi la dimensione non e' cambiata
        
        //controllo che testMap contenga tutti gli elementi che precedentemente conteneva
        assertEquals("kiss",testMap.get(0));
        assertEquals("one",testMap.get(4));
        assertEquals("plum",testMap.get(7));
        assertEquals("flower",testMap.get(2));
    }

    //-------FINE TEST DEL METODO putAll() ----------
    //-------TEST DEL METODO clear() ----------

    /**
     * Test del metodo {@link myAdapter.MapAdapter#clear() clear} su una mappa vuota e non.
     * 
     * @s.ummary   Verifica che la mappa venga svuotata correttamente
     * 
     * @d.esign    Si vuole verificare che il metodo {@code clear()} rimuova correttamente tutti gli elementi <br />
     *              presenti con conseguente diminuzione delle dimensioni della mappa, rendendola di dimensione {@code 0}. <br />
     *              Alla fine della chiamata la mappa deve ritornare {@code true} alla chiamata di {@code isEmpty()}. 
     * 
     * @d.escription   Si suppongono testati e funzionanti i metodi {@code isEmpty()}, {@code size()} <br />
     *                  1.  Si controlla che la mappa non sia vuota all'inizio. <br />
     *                  2.  Si chiama il metodo clear() e si svuota la mappa. <br />
     *                  3.  Si controlla che la mappa sia vuota e di dimensione {@code 0}. <br />
     *                  4.  Si controlla che alla chiamata del metodo clear() su mappa vuota, il controllo fatto al punto 3 non cambia.
     * 
     * @p.recond   La mappa e' stata popolata con 4 elementi.
     * 
     * @p.ostcond  La mappa e' stata svuotata.
     * 
     * @r.esult    {@code clear()} svuota la mappa, rendendola di dimensione {@code 0}.
     * 
     */
    @Test
    public void test_Map_Clear()
    {
        //controllo che la mappa non sia vuota all'inizio
        assertTrue(testMap.size() > 0); 
        //svuoto la mappa
        testMap.clear();        
        //controllo che la mappa sia di dimensione 0, priva di mappamenti        
        assertTrue(testMap.size() == 0);
        //controllo che la chiamata di clear() su una mappa vuota non ne cambia le dimensioni
        testMap.clear(); 
        assertTrue(testMap.isEmpty() && testMap.size() == 0 );
    }

    //-------FINE TEST DEL METODO clear() ----------
    //-------TEST DEL METODO keySet() ----------

    /**
     * Test del metodo {@link myAdapter.MapAdapter#keySet() keySet}.
     * 
     * @s.ummary   Verifica che il metodo {@code keySet()} restituisca un'istanza di {@link myAdapter.HSet} per la vista delle chiavi.
     * 
     * @d.esign    Si vuole verificare che il metodo {@code keySet()} restituisca un'istanza di {@link myAdapter.HSet} <br />
     *              sotto forma di un oggetto della classe {@link myAdapter.KeySet}.
     * 
     * @d.escription    Si supponga testato e funzionante il metodo {@code containsKey} <br />
     *                  1.  Si verifica che l'oggetto restituito dal metodo keySet sia un'istanza di HSet. <br />
     *                  2.  Si usa un iteratore della vista delle chiavi per ottenere tutti gli elementi del set. <br />
     *                  3.  Si verifica che ogni elemento del set restituito dal metodo keySet() e' una chiave della mappa. <br />
     *                  4.  Si verifica che il numero di elementi del set sia lo stesso degli elementi della mappa.
     * 
     * @p.recond   La mappa e' stata popolata con 4 elementi.
     * 
     * @p.ostcond  La mappa e' rimasta invariata.
     * 
     * @r.esult    {@code keySet()} restituisce un set che contiene le chiavi della mappa.
     * 
     */
    @Test
    public void test_Map_KeySet_Instance()
    {
        assertTrue(testMap.keySet() instanceof HSet);

        HSet s = testMap.keySet();
        HIterator it = s.iterator();
        int i = 0;
        while(it.hasNext())
        {
            //controllo che tutti gli elementi del set siano chiavi della mappa
            assertTrue(testMap.containsKey(it.next()));
            i +=1;
        }
        assertTrue(testMap.size() == i);
    }
    
    /**
     * Test del metodo {@link myAdapter.MapAdapter#keySet() keySet} per backing tra mappa e set tramite rimozione via iteratore.
     * 
     * @s.ummary   Verifica che la vista delle chiavi restituita dal metodo {@code keySet()} supporti la rimozione tramite iteratore e rifletta i suoi cambiamenti nella mappa.
     * 
     * @d.esign    Si vuole verificare che il metodo {@code keySet()} restituisca una vista di chiavi che supporti <br />
     *              la rimozione tramite iteratore e rifletta i suoi cambiamenti nella mappa.
     * 
     * @d.escription    Si supponga testato e funzionante il metodo {@code isEmpty}, {@code containsKey}, {@code put} <br />
     *                  1.  Si crea una mappa di 1 elemento. <br />
     *                  2.  Si crea la vista delle chiavi della mappa e il suo iteratore. <br />
     *                  3.  Si controlla che l'elemento visto dall'iteratore sia contenuto nella mappa. <br />
     *                  4.  Si rimuove l'elemento dal set tramite l'iteratore. <br />
     *                  5.  Si controlla che l'elemento e' stato rimosso anche dalla mappa. <br />  
     * 
     * @p.recond   La mappa e' stata popolata con 1 elemento.
     * 
     * @p.ostcond  Alla mappa e' stato rimosso l'elemento tramite l'iteratore della vista delle chiavi.
     * 
     * @r.esult    La vista restituita da {@code keySet()} deve supportare la rimozione tramite iteratore e riflettere i cambiamenti dal set alla mappa.
     * 
     */
    @Test
    public void test_Map_KeySet_RemovalViaIterator()
    {
        //creo una mappa e ci aggiungo un elemento
        testMap = new MapAdapter();
        testMap.put("six", 7);

        //creo la vista delle chiavi e il suo iteratore
        HSet set = testMap.keySet();
        HIterator it = set.iterator();

        //Controllo che l'elemento visto dall'iteratore sia contenuto nella mappa
        Object temp = it.next(); 
        assertTrue(testMap.containsKey(temp));
        
        //rimuovo un elemento dal set
        it.remove();

        //controllo che temp non sia piu' contenuto nella mappa
        assertFalse(testMap.containsKey(temp));
        assertTrue(testMap.isEmpty());
    }

    /**
     * Test del metodo {@link myAdapter.MapAdapter#keySet() keySet} per backing tra mappa e set tramite rimozione via metodo {@link myAdapter.KeySet#remove(Object) remove} del set.
     * 
     * @s.ummary   Verifica che la vista delle chiavi restituita dal metodo {@code keySet()} supporti la rimozione tramite {@link myAdapter.KeySet#remove(Object) remove} e rifletta i suoi cambiamenti nella mappa.
     * 
     * @d.esign    Si vuole verificare che il metodo {@code keySet()} restituisca una vista di chiavi che supporti <br />
     *              la rimozione tramite {@link myAdapter.KeySet#remove(Object) remove} e rifletta i suoi cambiamenti nella mappa.
     * 
     * @d.escription    Si supponga testato e funzionante il metodo {@code isEmpty}, {@code containsKey}, {@code put} <br />
     *                  1.  Si crea una mappa di 1 elemento. <br />
     *                  2.  Si crea la vista delle chiavi della mappa. <br />
     *                  3.  Si controlla che l'elemento contenuto nel set sia contenuto nella mappa. <br />
     *                  4.  Si rimuove l'elemento dal set tramite il metodo remove del set. <br />
     *                  5.  Si controlla che l'elemento e' stato rimosso anche dalla mappa. <br />  
     * 
     * @p.recond   La mappa e' stata popolata con 1 elemento.
     * 
     * @p.ostcond  Alla mappa e' stato rimosso l'elemento tramite il metodo {@link myAdapter.KeySet#remove(Object) remove} della vista delle chiavi.
     * 
     * @r.esult    La vista restituita da {@code keySet()} deve supportare la rimozione tramite {@link myAdapter.KeySet#remove(Object) remove} e riflettere i cambiamenti dal set alla mappa.
     * 
     */
    @Test
    public void test_Map_KeySet_RemovalViaSet_Remove()
    {
        //creo una mappa e ci aggiungo un elemento
        MapAdapter map2 = new MapAdapter();
        map2.put("six", 7);

        //creo la vista delle chiavi
        HSet set = map2.keySet();

        //Controllo che l'elemento "six" sia contenuto nella mappa e nel set
        assertTrue(set.contains("six"));
        assertTrue(map2.containsKey("six"));

        //rimuovo un elemento dal set
        set.remove("six");

        //controllo che "six" non sia piu' contenuto nella mappa
        assertFalse(map2.containsKey("six"));
        assertTrue(map2.isEmpty());
    }

    /**
     * Test del metodo {@link myAdapter.MapAdapter#keySet() keySet} per backing tra mappa e vista tramite rimozione via metodo {@link myAdapter.KeySet#removeAll removeAll} del set.
     * 
     * @s.ummary   Verifica che la vista delle chiavi restituita dal metodo {@code keySet()} supporti la rimozione tramite {@link myAdapter.KeySet#removeAll removeAll} e rifletta i suoi cambiamenti nella mappa.
     * 
     * @d.esign    Si vuole verificare che il metodo {@code keySet()} restituisca una vista di chiavi che supporti <br />
     *              la rimozione tramite {@link myAdapter.KeySet#removeAll removeAll} e rifletta i suoi cambiamenti nella mappa.
     * 
     * @d.escription    Si supponga testato e funzionante il metodo {@code containsKey}, {@code put} <br />
     *                  1.  Si crea una mappa di 2 elementi. <br />
     *                  2.  Si crea la vista delle chiavi della mappa. <br />
     *                  3.  Si controlla che gli elementi contenuti nella vista  sono le chiavi della mappa. <br />
     *                  4.  Si rimuove una collezione, con 1 elemento in comune con la vista, dal set tramite il metodo removeAll. <br />
     *                  5.  Si controlla che l'elemento della vista che era in comune con la collezione da rimuovere e' stato rimosso anche dalla mappa mentre l'altro e' ancora presente. <br />  
     * 
     * @p.recond   La mappa e' stata popolata con 2 elementi. La collezione da rimuove e' una vista della mappa popolata in fase di setUp.
     * 
     * @p.ostcond  Alla mappa e' stato rimosso un elemento tramite il metodo {@link myAdapter.KeySet#removeAll removeAll} della vista delle chiavi.
     * 
     * @r.esult    La vista restituita da {@code keySet()} deve supportare la rimozione tramite {@link myAdapter.KeySet#removeAll removeAll} e riflettere i cambiamenti dal set alla mappa.
     * 
     */
    @Test
    public void test_Map_KeySet_RemovalViaSet_RemoveAll()
    {
        //creo una mappa e ci aggiungo degli elementi
        MapAdapter map2 = new MapAdapter();
        map2.put("six", 7);
        map2.put(0, 65);

        //creo la vista delle chiavi
        HSet set = map2.keySet();

        //Controllo che le chiavi "six" e 0 siano contenute nella mappa
        assertTrue(map2.containsKey("six"));
        assertTrue(set.contains("six"));
        assertTrue(map2.containsKey(0));
        assertTrue(set.contains(0));

        //rimuovo una collezione dal set
        HSet toRemove = testMap.keySet();
        set.removeAll(toRemove);

        //controllo che 0 non sia piu' contenuto nella mappa mentre "six" si'
        assertTrue(map2.containsKey("six"));
        assertFalse(map2.containsKey(0));
    }

    /**
     * Test del metodo {@link myAdapter.MapAdapter#keySet() keySet} per backing tra mappa e vista tramite rimozione via metodo {@link myAdapter.KeySet#retainAll retainAll} del set.
     * 
     * @s.ummary   Verifica che la vista delle chiavi restituita dal metodo {@code keySet()} supporti la rimozione tramite {@link myAdapter.KeySet#retainAll retainAll} e rifletta i suoi cambiamenti nella mappa.
     * 
     * @d.esign    Si vuole verificare che il metodo {@code keySet()} restituisca una vista di chiavi che supporti <br />
     *              la rimozione tramite {@link myAdapter.KeySet#retainAll retainAll} e rifletta i suoi cambiamenti nella mappa.
     * 
     * @d.escription    Si supponga testato e funzionante il metodo {@code containsKey}, {@code put} <br />
     *                  1.  Si crea una mappa di 2 elementi. <br />
     *                  2.  Si crea la vista delle chiavi della mappa. <br />
     *                  3.  Si controlla che gli elementi contenuti nella vista sono le chiavi della mappa. <br />
     *                  4.  Si mantiene una collezione, con 1 elemento in comune con la vista, nel set tramite il metodo retainAll. <br />
     *                  5.  Si controlla che l'elemento della vista che non era in comune con la collezione da mantere e' stato rimosso anche dalla mappa mentre l'altro e' ancora presente. <br />  
     * 
     * @p.recond   La mappa e' stata popolata con 2 elementi. La collezione da mantenere e' una vista della mappa popolata in fase di setUp.
     * 
     * @p.ostcond  Alla mappa e' stato rimosso un elemento tramite il metodo {@link myAdapter.KeySet#retainAll retainAll} della vista delle chiavi.
     * 
     * @r.esult    La vista restituita da {@code keySet()} deve supportare la rimozione tramite {@link myAdapter.KeySet#retainAll retainAll} e riflettere i cambiamenti dal set alla mappa.
     * 
     */
    @Test
    public void test_Map_KeySet_RemovalViaSet_RetainAll()
    {
        //creo una mappa e ci aggiungo degli elementi
        MapAdapter map2 = new MapAdapter();
        map2.put("six", 7);
        map2.put(0, 65);

        //creo la vista delle chiavi
        HSet set = map2.keySet();

        //Controllo che le chiavi "six" e 0 siano contenute nella mappa e nel set
        assertTrue(map2.containsKey("six"));
        assertTrue(set.contains("six"));
        assertTrue(map2.containsKey(0));
        assertTrue(set.contains(0));

        //mantengo solo gli elementi contenuti in una collezione nel set
        HSet toRetain = testMap.keySet();
        set.retainAll(toRetain);

        //controllo che "six" non sia piu' contenuto nella mappa mentre 0 si'
        assertFalse(map2.containsKey("six"));
        assertTrue(map2.containsKey(0));
    }

    /**
     * Test del metodo {@link myAdapter.MapAdapter#keySet() keySet} per backing tra mappa e vista tramite rimozione via metodo {@link myAdapter.KeySet#clear clear} del set.
     * 
     * @s.ummary   Verifica che la vista delle chiavi restituita dal metodo {@code keySet()} supporti la rimozione tramite {@link myAdapter.KeySet#clear clear} e rifletta i suoi cambiamenti nella mappa.
     * 
     * @d.esign    Si vuole verificare che il metodo {@code keySet()} restituisca una vista di chiavi che supporti <br />
     *              la rimozione tramite {@link myAdapter.KeySet#clear clear} e rifletta i suoi cambiamenti nella mappa.
     * 
     * @d.escription    Si supponga testato e funzionante il metodo {@code isEmpty}, {@code containsKey}, {@code put} <br />
     *                  1.  Si crea una mappa di 2 elementi. <br />
     *                  2.  Si crea la vista delle chiavi della mappa. <br />
     *                  3.  Si controlla che gli elementi contenuti nella vista sono le chiavi della mappa. <br />
     *                  4.  Si svuota il set tramite il metodo clear. <br />
     *                  5.  Si controlla che la mappa sia stata svuotata. <br />
     * 
     * @p.recond   La mappa e' stata popolata con 2 elementi. 
     * 
     * @p.ostcond  La mappa e' stato svuotata tarmite il metodo {@link myAdapter.KeySet#clear clear} della vista delle chiavi.
     * 
     * @r.esult    La vista restituita da {@code keySet()} deve supportare la rimozione tramite {@link myAdapter.KeySet#clear clear} e riflettere i cambiamenti dal set alla mappa.
     * 
     */
    @Test
    public void test_Map_KeySet_RemovalViaSet_Clear()
    {
        //creo una mappa e ci aggiungo degli elementi
        MapAdapter map2 = new MapAdapter();
        map2.put("six", 7);
        map2.put(0, 65);

        //creo la vista delle chiavi
        HSet set = map2.keySet();

        //Controllo che le chiavi "six" e 0 sia contenuto nella mappa
        assertTrue(map2.containsKey("six"));
        assertTrue(set.contains("six"));
        assertTrue(map2.containsKey(0));
        assertTrue(set.contains(0));

        //svuoto il set
        set.clear();

        //controllo che "six" e 0 non siano piu' contenuti nella mappa
        assertFalse(map2.containsKey("six"));
        assertFalse(map2.containsKey(0));
        assertTrue(map2.isEmpty());
    }

    /**
     * Test del metodo {@link myAdapter.MapAdapter#keySet() keySet} per modifiche alla mappa.
     * 
     * @s.ummary   Verifica che la vista delle chiavi restituita dal metodo {@code keySet()} venga modificata in seguito a modifiche apportate alla mappa di cui e' vista.
     * 
     * @d.esign    Si vuole verificare che il metodo {@code keySet()} restituisca una vista di chiavi che venga modificata <br />
     *              in seguito a modifiche apportate alla mappa di cui e' vista.
     * 
     * @d.escription    Si supponga testato e funzionante il metodo {@code isEmpty}, {@code remove}, {@code clear}, {@code put} <br />
     *                  1.  Si crea la vista delle chiavi della mappa e si controlla non sia vuota. <br />
     *                  2.  Si svuota la mappa e si controlla si sia svuotata anche la vista delle chiavi. <br />
     *                  3.  Si aggiuge un elemento alla mappa e si controlla sia stato aggiunto nel set. <br />
     *                  4.  Si rimuove un elemento dalla mappa e si controlla che sia rimosso dal set. <br />
     *                  5.  Si controlla che sia mappa che vista sono vuote.
     * 
     * @p.recond   La mappa e' stata popolata con 4 elementi. 
     * 
     * @p.ostcond  La mappa e' stata svuotata.
     * 
     * @r.esult    La vista restituita da {@code keySet()} deve modificarsi se la mappa di cui e' vista viene modificata.
     * 
     */
    @Test
    public void test_Map_KeySet_ChangedMapChangedSet()
    {
        //creo la vista delle chiavi di testMap e mi assicuro non sia vuoto
        HSet set = testMap.keySet();
        assertFalse(set.isEmpty());

        //svuoto la mappa e controllo che anche il set sia vuoto
        testMap.clear();
        assertTrue(set.isEmpty());

        //aggiungo un elemento a testMap e controllo che sia aggiunto anche al set
        testMap.put(9, 11);
        assertTrue(set.contains(9));

        //rimuovo un elemento dalla mappa e controllo che sia rimosso dal set
        testMap.remove(9);
        assertFalse(set.contains(9));

        //controllo che sia mappa che set sono vuoti
        assertTrue(set.isEmpty());
        assertTrue(testMap.isEmpty());
    }

    //-------FINE TEST DEL METODO keySet() ----------
    //-------TEST DEL METODO values() ----------

    /**
     * Test del metodo {@link myAdapter.MapAdapter#values() values}.
     * 
     * @s.ummary   Verifica che il metodo {@code values()} restituisca un'istanza di {@link myAdapter.HCollection} per la vista dei valori.
     * 
     * @d.esign    Si vuole verificare che il metodo {@code values()} restituisca un'istanza di {@link myAdapter.HCollection} <br />
     *              sotto forma di un oggetto della classe {@link myAdapter.ValueCollection}.
     * 
     * @d.escription    Si supponga testato e funzionante il metodo {@code containsValue} <br />
     *                  1.  Si verifica che l'oggetto restituito dal metodo values() sia un'istanza di HCollection. <br />
     *                  2.  Si usa un iteratore della vista dei valori per ottenere tutti gli elementi della collezione. <br />
     *                  3.  Si verifica che ogni elemento della collezione restituito dal metodo values() e' un valore della mappa. <br />
     *                  4.  Si verifica che il numero di elementi della collezione sia lo stesso degli elementi della mappa.
     * 
     * @p.recond   La mappa e' stata popolata con 4 elementi.
     * 
     * @p.ostcond  La mappa e' rimasta invariata.
     * 
     * @r.esult    {@code values()} restituisce una collezione che contiene i valori della mappa.
     * 
     */
    @Test
    public void test_Map_Values_Instance()
    {
        assertTrue(testMap.values() instanceof HCollection);

        HCollection s = testMap.values();
        HIterator it = s.iterator();
        int i = 0;
        while(it.hasNext())
        {
            //controllo che tutti gli elementi del set siano chiavi della mappa
            assertTrue(testMap.containsValue(it.next()));
            i +=1;
        }
        assertTrue(testMap.size() == i);
    }

    /**
     * Test del metodo {@link myAdapter.MapAdapter#values() values} per backing tra mappa e collezione tramite rimozione via iteratore.
     * 
     * @s.ummary   Verifica che la vista dei valori restituita dal metodo {@code values()} supporti la rimozione tramite iteratore e rifletta i suoi cambiamenti nella mappa.
     * 
     * @d.esign    Si vuole verificare che il metodo {@code values()} restituisca una vista dei valori che supporti <br />
     *              la rimozione tramite iteratore e rifletta i suoi cambiamenti nella mappa.
     * 
     * @d.escription    Si supponga testato e funzionante il metodo {@code isEmpty}, {@code containsValue}, {@code put} <br />
     *                  1.  Si crea una mappa di 1 elemento. <br />
     *                  2.  Si crea la vista dei valori della mappa e il suo iteratore. <br />
     *                  3.  Si controlla che l'elemento visto dall'iteratore sia contenuto nella mappa. <br />
     *                  4.  Si rimuove l'elemento dalla collezione tramite l'iteratore. <br />
     *                  5.  Si controlla che l'elemento e' stato rimosso anche dalla mappa. <br />  
     * 
     * @p.recond   La mappa e' stata popolata con 1 elemento.
     * 
     * @p.ostcond  Alla mappa e' stato rimosso l'elemento tramite l'iteratore della vista dei valori.
     * 
     * @r.esult    {@code values()} deve supportare la rimozione tramite iteratore e riflettere i cambiamenti dalla vista alla mappa.
     * 
     */
    @Test
    public void test_Map_Values_RemovalViaIterator()
    {
        //creo una mappa e ci aggiungo un elemento
        MapAdapter map2 = new MapAdapter();
        map2.put("six", 7);

        //creo la vista dei valori e il suo iteratore
        HCollection col = map2.values();
        HIterator it = col.iterator();

        //Controllo che l'elemento visto dall'iteratore sia contenuto nella mappa
        Object temp = it.next(); 
        assertTrue(map2.containsValue(temp));

        //rimuovo un elemento dalla collezione
        it.remove();

        //controllo che temp non sia piu' contenuto nella mappa
        assertFalse(map2.containsValue(temp));
        assertTrue(map2.isEmpty());
    }

    /**
     * Test del metodo {@link myAdapter.MapAdapter#values() values} per backing tra mappa e vista tramite rimozione via metodo {@link myAdapter.ValueCollection#remove(Object) remove} della collezione.
     * 
     * @s.ummary   Verifica che la vista dei valori restituita dal metodo {@code values()} supporti la rimozione tramite {@link myAdapter.ValueCollection#remove(Object) remove} e rifletta i suoi cambiamenti nella mappa.
     * 
     * @d.esign    Si vuole verificare che il metodo {@code values()} restituisca una vista dei valori che supporti <br />
     *              la rimozione tramite {@link myAdapter.ValueCollection#remove(Object) remove} e rifletta i suoi cambiamenti nella mappa.
     * 
     * @d.escription    Si supponga testato e funzionante il metodo {@code isEmpty}, {@code containsValue}, {@code put} <br />
     *                  1.  Si crea una mappa di 1 elemento. <br />
     *                  2.  Si crea la vista dei valori della mappa. <br />
     *                  3.  Si controlla che l'elemento contenuto nella collezione sia contenuto nella mappa. <br />
     *                  4.  Si rimuove l'elemento dalla collezinoe tramite il metodo remove della collezione. <br />
     *                  5.  Si controlla che l'elemento e' stato rimosso anche dalla mappa. <br />  
     * 
     * @p.recond   La mappa e' stata popolata con 1 elemento.
     * 
     * @p.ostcond  Alla mappa e' stato rimosso l'elemento tramite il metodo {@link myAdapter.ValueCollection#remove(Object) remove} della vista dei valori.
     * 
     * @r.esult    La vista restituita da {@code values()} deve supportare la rimozione tramite {@link myAdapter.ValueCollection#remove(Object) remove} e riflettere i cambiamenti dal set alla mappa.
     * 
     */
    @Test
    public void test_Map_Values_RemovalViaCol_Remove()
    {
        //creo una mappa e ci aggiungo un elemento
        MapAdapter map2 = new MapAdapter();
        map2.put("six", 7);

        //creo la vista dei valori
        HCollection col = map2.values();

        //Controllo che l'elemento 7 sia contenuto nella mappa
        assertTrue(map2.containsValue(7));
        assertTrue(col.contains(7));

        //rimuovo un elemento dalla collezione
        col.remove(7);

        //controllo che 7 non sia piu' contenuto nella mappa
        assertFalse(map2.containsValue(7));
        assertTrue(map2.isEmpty());
    }

    /**
     * Test del metodo {@link myAdapter.MapAdapter#values() values} per backing tra mappa e vista tramite rimozione via metodo {@link myAdapter.ValueCollection#removeAll removeAll} della collezione.
     * 
     * @s.ummary   Verifica che la vista dei valori restituita dal metodo {@code values()} supporti la rimozione tramite {@link myAdapter.ValueCollection#removeAll removeAll} e rifletta i suoi cambiamenti nella mappa.
     * 
     * @d.esign    Si vuole verificare che il metodo {@code values()} restituisca una vista di valori che supporti <br />
     *              la rimozione tramite {@link myAdapter.ValueCollection#removeAll removeAll} e rifletta i suoi cambiamenti nella mappa.
     * 
     * @d.escription    Si supponga testato e funzionante il metodo {@code containsValue}, {@code put} <br />
     *                  1.  Si crea una mappa di 2 elementi. <br />
     *                  2.  Si crea la vista dei valori della mappa. <br />
     *                  3.  Si controlla che gli elementi contenuti nella vista sono i valori della mappa. <br />
     *                  4.  Si rimuove una collezione, con 1 elemento in comune con la vista, dalla collezione tramite il metodo removeAll. <br />
     *                  5.  Si controlla che l'elemento della vista che era in comune con la collezione da rimuovere e' stato rimosso anche dalla mappa mentre l'altro e' ancora presente. <br />  
     * 
     * @p.recond   La mappa e' stata popolata con 2 elementi. La collezione da rimuove e' una vista della mappa popolata in fase di setUp.
     * 
     * @p.ostcond  Alla mappa e' stato rimosso un elemento tramite il metodo {@link myAdapter.ValueCollection#removeAll removeAll} della vista dei valori.
     * 
     * @r.esult    La vista restituita da {@code values()} deve supportare la rimozione tramite {@link myAdapter.ValueCollection#removeAll removeAll} e riflettere i cambiamenti dalla collezione alla mappa.
     * 
     */
    @Test
    public void test_Map_Values_RemovalViaCol_RemoveAll()
    {
        //creo una mappa e ci aggiungo degli elementi
        MapAdapter map2 = new MapAdapter();
        map2.put("six", "one");
        map2.put(0, 65);

        //creo la vista dei valori
        HCollection col = map2.values();

        //Controllo che i valori 65 e "one" siano contenuti nella mappa
        assertTrue(map2.containsValue("one"));
        assertTrue(col.contains("one"));
        assertTrue(map2.containsValue(65));
        assertTrue(col.contains(65));

        //rimuovo una collezione dalla vista
        HCollection toRemove = testMap.values();
        col.removeAll(toRemove);

        //controllo che "one" non sia piu' contenuto nella mappa mentre 65 si'
        assertTrue(map2.containsValue(65));
        assertFalse(map2.containsValue("one"));
    }

    /**
     * Test del metodo {@link myAdapter.MapAdapter#values() values} per backing tra mappa e vista tramite rimozione via metodo {@link myAdapter.ValueCollection#retainAll retainAll} della collezione.
     * 
     * @s.ummary   Verifica che la vista dei valori restituita dal metodo {@code values()} supporti la rimozione tramite {@link myAdapter.ValueCollection#retainAll retainAll} e rifletta i suoi cambiamenti nella mappa.
     * 
     * @d.esign    Si vuole verificare che il metodo {@code values()} restituisca una vista di valori che supporti <br />
     *              la rimozione tramite {@link myAdapter.ValueCollection#retainAll retainAll} e rifletta i suoi cambiamenti nella mappa.
     * 
     * @d.escription    Si supponga testato e funzionante il metodo {@code containsValue}, {@code put} <br />
     *                  1.  Si crea una mappa di 2 elementi. <br />
     *                  2.  Si crea la vista dei valori della mappa. <br />
     *                  3.  Si controlla che gli elementi contenuti nella vista sono i valori della mappa. <br />
     *                  4.  Si mantiene una collezione, con 1 elemento in comune con la vista, nella collezione tramite il metodo retainAll. <br />
     *                  5.  Si controlla che l'elemento della vista che non era in comune con la collezione da mantere e' stato rimosso anche dalla mappa mentre l'altro e' ancora presente. <br />  
     * 
     * @p.recond   La mappa e' stata popolata con 2 elementi. La collezione da mantenere e' una vista della mappa popolata in fase di setUp.
     * 
     * @p.ostcond  Alla mappa e' stato rimosso un elemento tramite il metodo {@link myAdapter.ValueCollection#retainAll retainAll} della vista dei valori.
     * 
     * @r.esult    La vista restituita da {@code values()} deve supportare la rimozione tramite {@link myAdapter.ValueCollection#retainAll retainAll} e riflettere i cambiamenti dalla collezione alla mappa.
     * 
     */
    @Test
    public void test_Map_Values_RemovalViaCol_RetainAll()
    {
        //creo una mappa e ci aggiungo degli elementi
        MapAdapter map2 = new MapAdapter();
        map2.put("six", "flower");
        map2.put(0, 65);

        //creo la vista dei valori
        HCollection col = map2.values();

        //Controllo che i valori "flower" e 65 siano contenuti nella mappa
        assertTrue(map2.containsValue("flower"));
        assertTrue(col.contains("flower"));
        assertTrue(map2.containsValue(65));
        assertTrue(col.contains(65));

        //mantengo solo gli elementi contenuti in una collezione nella vista
        HCollection toRetain = testMap.values();
        col.retainAll(toRetain);

        //controllo che 65 non sia piu' contenuto nella mappa mentre "flower" si'
        assertFalse(map2.containsValue(65));
        assertTrue(map2.containsValue("flower"));
    }

    /**
     * Test del metodo {@link myAdapter.MapAdapter#values() values} per backing tra mappa e vista tramite rimozione via metodo {@link myAdapter.ValueCollection#clear clear} della collezione.
     * 
     * @s.ummary   Verifica che la vista dei valori restituita dal metodo {@code values()} supporti la rimozione tramite {@link myAdapter.ValueCollection#clear clear} e rifletta i suoi cambiamenti nella mappa.
     * 
     * @d.esign    Si vuole verificare che il metodo {@code values()} restituisca una vista di valori che supporti <br />
     *              la rimozione tramite {@link myAdapter.ValueCollection#clear clear} e rifletta i suoi cambiamenti nella mappa.
     * 
     * @d.escription    Si supponga testato e funzionante il metodo {@code isEmpty}, {@code containsKey}, {@code put} <br />
     *                  1.  Si crea una mappa di 2 elementi. <br />
     *                  2.  Si crea la vista dei valori della mappa. <br />
     *                  3.  Si controlla che gli elementi contenuti nella vista sono i valori della mappa. <br />
     *                  4.  Si svuota la collezione tramite il metodo clear. <br />
     *                  5.  Si controlla che la mappa sia stata svuotata. <br />
     * 
     * @p.recond   La mappa e' stata popolata con 2 elementi. 
     * 
     * @p.ostcond  La mappa e' stata svuotata tarmite il metodo {@link myAdapter.ValueCollection#clear clear} della vista dei valori.
     * 
     * @r.esult    La vista restituita da {@code values()} deve supportare la rimozione tramite {@link myAdapter.ValueCollection#clear clear} e riflettere i cambiamenti dalla collezione alla mappa.
     * 
     */
    @Test
    public void test_Map_Values_RemovalViaCol_Clear()
    {
        //creo una mappa e ci aggiungo degli elementi
        MapAdapter map2 = new MapAdapter();
        map2.put("six", 7);
        map2.put(0, 65);
        //creo la vista dei valori
        HCollection col = map2.values();
        //Controllo che i valori 7 e 65 siano contenuti nella mappa
        assertTrue(map2.containsValue(7));
        assertTrue(map2.containsValue(65));
        //svuoto la collezione
        col.clear();
        //controllo che 7 e 65 non siano piu' contenuti nella mappa
        assertFalse(map2.containsValue(7));
        assertFalse(map2.containsValue(65));
    }

    /**
     * Test del metodo {@link myAdapter.MapAdapter#values() values} per modifiche alla mappa.
     * 
     * @s.ummary   Verifica che la vista dei valori restituita dal metodo {@code values()} venga modificata in seguito a modifiche apportate alla mappa di cui e' vista.
     * 
     * @d.esign    Si vuole verificare che il metodo {@code values()} restituisca una vista di valori che venga modificata <br />
     *              in seguito a modifiche apportate alla mappa di cui e' vista.
     * 
     * @d.escription    Si supponga testato e funzionante il metodo {@code isEmpty}, {@code remove}, {@code clear}, {@code put} <br />
     *                  1.  Si crea la vista dei valori della mappa e si controlla non sia vuota. <br />
     *                  2.  Si svuota la mappa e si controlla si sia svuotata anche la vista dei valori. <br />
     *                  3.  Si aggiuge un elemento alla mappa e si controlla sia stato aggiunto nela vista. <br />
     *                  4.  Si rimuove un elemento dalla mappa e si controlla che sia rimosso dalla vista. <br />
     *                  5.  Si controlla che sia mappa che vista sono vuote.
     * 
     * @p.recond   La mappa e' stata popolata con 4 elementi. 
     * 
     * @p.ostcond  La mappa e' stata svuotata.
     * 
     * @r.esult    La vista restituita da {@code values()} deve modificarsi se la mappa di cui e' vista viene modificata.
     * 
     */
    @Test
    public void test_Map_Values_ChangedMapChangedCol()
    {
        //creo la vista dei valori di testMap e mi assicuro non sia vuoto
        HCollection col = testMap.values();
        assertFalse(col.isEmpty());

        //svuoto la mappa e controllo che anche la vista sia vuota
        testMap.clear();
        assertTrue(col.isEmpty());

        //aggiungo un elemento a testMap e controllo che sia aggiunto anche alla vista
        testMap.put(9, 11);
        assertTrue(col.contains(11));

        //rimuovo un elemento dalla mappa e controllo che sia rimosso dalla vista
        testMap.remove(9);
        assertFalse(col.contains(11));

        //controllo che sia mappa che vista sono vuote
        assertTrue(testMap.isEmpty());
        assertTrue(col.isEmpty());
    }

    //-------FINE TEST DEL METODO values() ----------
    //-------TEST DEL METODO entrySet() ----------

    /**
     * Test del metodo {@link myAdapter.MapAdapter#entrySet() entrySet}.
     * 
     * @s.ummary   Verifica che il metodo {@code entrySet()} restituisca un'istanza di {@link myAdapter.HSet} per la vista delle Entry.
     * 
     * @d.esign    Si vuole verificare che il metodo {@code entrySet()} restituisca un'istanza di {@link myAdapter.HSet} <br />
     *              sotto forma di un oggetto della classe {@link myAdapter.MapAdapter.EntrySet}.
     * 
     * @d.escription    Si supponga testato e funzionante il metodo {@code containsKey}, {@code get} <br />
     *                  1.  Si verifica che l'oggetto restituito dal metodo entrySet() sia un'istanza di HSet. <br />
     *                  2.  Si usa un iteratore della vista delle Entry per ottenere tutti gli elementi del set. <br />
     *                  3.  Si verifica che ogni elemento del set restituito dal metodo entrySet() e' una coppia della mappa. <br />
     *                  4.  Si verifica che il numero di elementi del set sia lo stesso degli elementi della mappa.
     * 
     * @p.recond   La mappa e' stata popolata con 4 elementi.
     * 
     * @p.ostcond  La mappa e' rimasta invariata.
     * 
     * @r.esult    {@code entrySet()} restituisce un set che contiene le coppie chiave-valore della mappa.
     * 
     */
    @Test
    public void test_Map_EntrySet_Instance()
    {
        assertTrue(testMap.entrySet() instanceof HSet);

        HSet s = testMap.entrySet();
        HIterator it = s.iterator();
        int i = 0;
        while(it.hasNext())
        {
            HEntry e = (HEntry) it.next();
            //controllo che tutti gli elementi del set siano coppie della mappa
            assertTrue(testMap.containsKey(e.getKey()) && testMap.get(e.getKey()).equals(e.getValue()));
            i +=1;
        }
        assertTrue(testMap.size() == i);
    }

    /**
     * Test del metodo {@link myAdapter.MapAdapter#entrySet() entrySet} per backing tra mappa e set tramite rimozione via iteratore.
     * 
     * @s.ummary   Verifica che la vista delle entry restituita dal metodo {@code entrySet()} supporti la rimozione tramite iteratore e rifletta i suoi cambiamenti nella mappa.
     * 
     * @d.esign    Si vuole verificare che il metodo {@code entrySet()} restituisca una vista di {@link myAdapter.HEntry} che supporti <br />
     *              la rimozione tramite iteratore e rifletta i suoi cambiamenti nella mappa.
     * 
     * @d.escription    Si supponga testato e funzionante il metodo {@code get}, {@code isEmpty}, {@code containsKey}, {@code put} <br />
     *                  1.  Si crea una mappa di 1 elemento. <br />
     *                  2.  Si crea la vista delle entry della mappa e il suo iteratore. <br />
     *                  3.  Si controlla che l'elemento visto dall'iteratore sia la coppia chiave-valore della mappa. <br />
     *                  4.  Si rimuove l'elemento dal set tramite l'iteratore. <br />
     *                  5.  Si controlla che l'elemento e' stato rimosso anche dalla mappa. <br />  
     * 
     * @p.recond   La mappa e' stata popolata con 1 elemento.
     * 
     * @p.ostcond  Alla mappa e' stato rimosso l'elemento tramite l'iteratore della vista delle entry.
     * 
     * @r.esult    La vista restituita da {@code entrySet()} deve supportare la rimozione tramite iteratore e riflettere i cambiamenti dal set alla mappa.
     * 
     */
    @Test
    public void test_Map_EntrySet_RemovalViaIterator()
    {
        //creo una mappa e ci aggiungo un elemento
        MapAdapter map2 = new MapAdapter();
        map2.put("six", 7);

        //creo la vista delle entry e il suo iteratore
        HSet e = map2.entrySet();
        HIterator it = e.iterator();

        //Controllo che l'elemento visto dall'iteratore sia contenuto nella mappa
        HEntry temp = (HEntry)it.next(); 
        assertTrue(map2.containsKey(temp.getKey()) && map2.get(temp.getKey()).equals(temp.getValue()));

        //rimuovo un elemento dalla vista
        it.remove();

        //controllo che temp non sia piu' contenuto nella mappa
        assertFalse(map2.containsKey(temp.getKey()) && map2.get(temp.getKey()).equals(temp.getValue()));
        assertTrue(map2.isEmpty());
    }

    /**
     * Test del metodo {@link myAdapter.MapAdapter#entrySet() entrySet} per backing tra mappa e vista tramite rimozione via metodo {@link myAdapter.MapAdapter.EntrySet#remove(Object) remove} del set delle Entry.
     * 
     * @s.ummary   Verifica che la vista delle Entry restituita dal metodo {@code entrySet()} supporti la rimozione tramite {@link myAdapter.MapAdapter.EntrySet#remove(Object) remove} e rifletta i suoi cambiamenti nella mappa.
     * 
     * @d.esign    Si vuole verificare che il metodo {@code entrySet()} restituisca una vista di {@link myAdapter.HEntry} che supporti <br />
     *              la rimozione tramite {@link myAdapter.MapAdapter.EntrySet#remove(Object) remove} e rifletta i suoi cambiamenti nella mappa.
     * 
     * @d.escription    Si supponga testato e funzionante il metodo {@code isEmpty}, {@code containsKey}, {@code put} <br />
     *                  1.  Si crea una mappa di 1 elemento. <br />
     *                  2.  Si crea la vista delle Entry della mappa e il suo iteratore. <br />
     *                  3.  Si controlla che l'elemento contenuto nel set sia contenuto nella mappa. <br />
     *                  4.  Si rimuove l'elemento dal set tramite il metodo remove del set. <br />
     *                  5.  Si controlla che l'elemento e' stato rimosso anche dalla mappa. <br />  
     * 
     * @p.recond   La mappa e' stata popolata con 1 elemento.
     * 
     * @p.ostcond  Alla mappa e' stato rimosso l'elemento tramite il metodo {@link myAdapter.MapAdapter.EntrySet#remove(Object) remove} della vista delle Entry.
     * 
     * @r.esult    La vista restituita da {@code entrySet()} deve supportare la rimozione tramite {@link myAdapter.MapAdapter.EntrySet#remove(Object) remove} e riflettere i cambiamenti dal set alla mappa.
     * 
     */
    @Test
    public void test_Map_EntrySet_RemovalViaEntrySet_Remove()
    {
        //creo una mappa e ci aggiungo un elemento
        MapAdapter map2 = new MapAdapter();
        map2.put("six", 7);

        //creo la vista delle entry e il suo iteratore
        HSet e = map2.entrySet();
        HIterator it = e.iterator();

        //Controllo che la coppia sia contenuta nella mappa
        HEntry temp = (HEntry)it.next(); 
        assertTrue(map2.containsKey(temp.getKey()) && map2.get(temp.getKey()).equals(temp.getValue()));
        
        //rimuovo un elemento dalla collezione
        e.remove(temp);

        //controllo che 7 non sia piu' contenuto nella mappa
        assertFalse(map2.containsKey(temp.getKey()) && map2.get(temp.getKey()).equals(temp.getValue()));
        assertTrue(map2.isEmpty());
    }

    /**
     * Test del metodo {@link myAdapter.MapAdapter#entrySet() entrySet} per backing tra mappa e vista tramite rimozione via metodo {@link myAdapter.MapAdapter.EntrySet#removeAll removeAll} del set delle Entry.
     * 
     * @s.ummary   Verifica che la vista delle Entry restituita dal metodo {@code entrySet()} supporti la rimozione tramite {@link myAdapter.MapAdapter.EntrySet#removeAll removeAll} e rifletta i suoi cambiamenti nella mappa.
     * 
     * @d.esign    Si vuole verificare che il metodo {@code entrySet()} restituisca una vista di {@link myAdapter.HEntry} che supporti <br />
     *              la rimozione tramite {@link myAdapter.MapAdapter.EntrySet#removeAll removeAll} e rifletta i suoi cambiamenti nella mappa.
     * 
     * @d.escription    Si supponga testato e funzionante il metodo {@code get}, {@code containsKey}, {@code put} <br />
     *                  1.  Si crea una mappa di 2 elementi. <br />
     *                  2.  Si crea la vista delle Entry della mappa e il suo iteratore. <br />
     *                  3.  Si controlla che gli elementi contenuti nella vista sono le Entry della mappa. <br />
     *                  4.  Si rimuove una collezione, con 1 elemento in comune con la vista, dal set tramite il metodo removeAll. <br />
     *                  5.  Si controlla che l'elemento della vista che era in comune con la collezione da rimuovere e' stato rimosso anche dalla mappa mentre l'altro e' ancora presente. <br />  
     * 
     * @p.recond   La mappa e' stata popolata con 2 elementi. La collezione da rimuove e' una vista della mappa popolata in fase di setUp.
     * 
     * @p.ostcond  Alla mappa e' stato rimosso un elemento tramite il metodo {@link myAdapter.MapAdapter.EntrySet#removeAll removeAll} della vista delle Entry.
     * 
     * @r.esult    La vista restituita da {@code entrySet()} deve supportare la rimozione tramite {@link myAdapter.MapAdapter.EntrySet#removeAll removeAll} e riflettere i cambiamenti dal set delle Entry alla mappa.
     * 
     */
    @Test
    public void test_Map_EntrySet_RemovalViaEntrySet_RemoveAll()
    {
        //creo una mappa e ci aggiungo degli elementi
        MapAdapter map2 = new MapAdapter();
        map2.put(4, "one");
        map2.put(0, 65);

        //creo la vista delle entry e il suo iteratore
        HSet e = map2.entrySet();
        HIterator it = e.iterator();

        //Controllo che le coppie di e siano contenute nella mappa
        HEntry temp1 = (HEntry)it.next(); 
        assertTrue(map2.containsKey(temp1.getKey()) && map2.get(temp1.getKey()).equals(temp1.getValue()));
        HEntry temp2 = (HEntry)it.next();
        assertTrue(map2.containsKey(temp2.getKey()) && map2.get(temp2.getKey()).equals(temp2.getValue()));

        //rimuovo una collezione dalla vista
        HSet toRemove = testMap.entrySet();
        e.removeAll(toRemove);

        //controllo che la coppia ["six","one"] non sia piu' contenuto nella mappa mentre [0,65] si'
        assertTrue(map2.get(0).equals(65));
        assertFalse(map2.containsKey(4) && map2.get(4).equals("one"));
    }

    /**
     * Test del metodo {@link myAdapter.MapAdapter#entrySet() entrySet} per backing tra mappa e vista tramite rimozione via metodo {@link myAdapter.MapAdapter.EntrySet#retainAll retainAll} del set delle Entry.
     * 
     * @s.ummary   Verifica che la vista delle Entry restituita dal metodo {@code entrySet()} supporti la rimozione tramite {@link myAdapter.MapAdapter.EntrySet#retainAll retainAll} e rifletta i suoi cambiamenti nella mappa.
     * 
     * @d.esign    Si vuole verificare che il metodo {@code entrySet()} restituisca una vista di {@link myAdapter.HEntry} che supporti <br />
     *              la rimozione tramite {@link myAdapter.MapAdapter.EntrySet#retainAll retainAll} e rifletta i suoi cambiamenti nella mappa.
     * 
     * @d.escription    Si supponga testato e funzionante il metodo {@code get}, {@code containsKey}, {@code put} <br />
     *                  1.  Si crea una mappa di 2 elementi. <br />
     *                  2.  Si crea la vista delle Entry della mappa e il suo iteratore. <br />
     *                  3.  Si controlla che gli elementi contenuti nella vista sono le Entry della mappa. <br />
     *                  4.  Si mantiene una collezione, con 1 elemento in comune con la vista, nel set tramite il metodo retainAll. <br />
     *                  5.  Si controlla che l'elemento della vista che non era in comune con la collezione da mantere e' stato rimosso anche dalla mappa mentre l'altro e' ancora presente. <br />  
     * 
     * @p.recond   La mappa e' stata popolata con 2 elementi. La collezione da mantenere e' una vista della mappa popolata in fase di setUp.
     * 
     * @p.ostcond  Alla mappa e' stato rimosso un elemento tramite il metodo {@link myAdapter.MapAdapter.EntrySet#retainAll retainAll} della vista delle Entry.
     * 
     * @r.esult    La vista restituita da {@code entrySet()} deve supportare la rimozione tramite {@link myAdapter.MapAdapter.EntrySet#retainAll retainAll} e riflettere i cambiamenti dal set delle Entry alla mappa.
     */
    @Test
    public void test_Map_EntrySet_RemovalViaEntrySet_RetainAll()
    {
        //creo una mappa e ci aggiungo degli elementi
        MapAdapter map2 = new MapAdapter();
        map2.put("six", "flower");
        map2.put(0, "kiss");

        //creo la vista delle entry e il suo iteratore
        HSet e = map2.entrySet();
        HIterator it = e.iterator();

        //Controllo che le coppie di e siano contenute nella mappa
        HEntry temp1 = (HEntry)it.next(); 
        assertTrue(map2.containsKey(temp1.getKey()) && map2.get(temp1.getKey()).equals(temp1.getValue()));
        HEntry temp2 = (HEntry)it.next();
        assertTrue(map2.containsKey(temp2.getKey()) && map2.get(temp2.getKey()).equals(temp2.getValue()));

        //mantengo solo gli elementi contenuti in una collezione nella vista
        HSet toRetain = testMap.entrySet();
        e.retainAll(toRetain);

        //controllo che la coppia ["six", "flower"] non sia piu' contenuto nella mappa mentre [0, "kiss"] si'
        assertTrue(map2.containsValue("kiss") && map2.containsKey(0));
        assertFalse(map2.containsValue("flower") && map2.containsKey("six"));
    }

    /**
     * Test del metodo {@link myAdapter.MapAdapter#entrySet() entrySet} per backing tra mappa e vista tramite rimozione via metodo {@link myAdapter.MapAdapter.EntrySet#clear clear} del set delle Entry.
     * 
     * @s.ummary   Verifica che la vista delle Entry restituita dal metodo {@code entrySet()} supporti la rimozione tramite {@link myAdapter.MapAdapter.EntrySet#clear clear} e rifletta i suoi cambiamenti nella mappa.
     * 
     * @d.esign    Si vuole verificare che il metodo {@code entrySet()} restituisca una vista di {@link myAdapter.HEntry} che supporti <br />
     *              la rimozione tramite {@link myAdapter.MapAdapter.EntrySet#clear clear} e rifletta i suoi cambiamenti nella mappa.
     * 
     * @d.escription    Si supponga testato e funzionante il metodo {@code get}, {@code isEmpty}, {@code containsKey}, {@code put} <br />
     *                  1.  Si crea una mappa di 2 elementi. <br />
     *                  2.  Si crea la vista delle Entry della mappa e il suo iteratore. <br />
     *                  3.  Si controlla che gli elementi contenuti nella vista sono le Entry della mappa. <br />
     *                  4.  Si svuota il set tramite il metodo clear. <br />
     *                  5.  Si controlla che la mappa sia stata svuotata. <br />
     * 
     * @p.recond   La mappa e' stata popolata con 2 elementi. 
     * 
     * @p.ostcond  La mappa e' stato svuotata tarmite il metodo {@link myAdapter.MapAdapter.EntrySet#clear clear} della vista delle Entry.
     * 
     * @r.esult    La vista restituita da {@code entrySet()} deve supportare la rimozione tramite {@link myAdapter.MapAdapter.EntrySet#clear clear} e riflettere i cambiamenti dal set delle Entry alla mappa.
     * 
     */
    @Test
    public void test_Map_EntrySet_RemovalViaEntrySet_Clear()
    {
        //creo una mappa e ci aggiungo degli elementi
        MapAdapter map2 = new MapAdapter();
        map2.put("six", "flower");
        map2.put(0, "kiss");

        //creo la vista delle entry e il suo iteratore
        HSet e = map2.entrySet();
        HIterator it = e.iterator();

        //Controllo che le coppie di e siano contenute nella mappa
        HEntry temp1 = (HEntry)it.next(); 
        assertTrue(map2.containsKey(temp1.getKey()) && map2.get(temp1.getKey()).equals(temp1.getValue()));
        HEntry temp2 = (HEntry)it.next();
        assertTrue(map2.containsKey(temp2.getKey()) && map2.get(temp2.getKey()).equals(temp2.getValue()));

        //svuoto la vista
        e.clear();
        //controllo che le coppie non siano piu' contenute nella mappa
        assertFalse(map2.containsKey(temp1.getKey()) && map2.get(temp1.getKey()).equals(temp1.getValue()));
        assertFalse(map2.containsKey(temp2.getKey()) && map2.get(temp2.getKey()).equals(temp2.getValue()));
        assertTrue(map2.isEmpty());
    }

    /**
     * Test del metodo {@link myAdapter.MapAdapter#entrySet() entrySet} per modifiche alla mappa.
     * 
     * @s.ummary   Verifica che la vista delle Entry restituita dal metodo {@code entrySet()} venga modificata in seguito a modifiche apportate alla mappa di cui e' vista.
     * 
     * @d.esign    Si vuole verificare che il metodo {@code entrySet()} restituisca una vista di {@link myAdapter.HEntry} che venga modificata <br />
     *              in seguito a modifiche apportate alla mappa di cui e' vista.
     * 
     * @d.escription    Si supponga testato e funzionante il metodo {@code isEmpty}, {@code remove}, {@code clear}, {@code put} <br />
     *                  1.  Si crea la vista delle Entry della mappa e si controlla non sia vuota. <br />
     *                  2.  Si svuota la mappa e si controlla si sia svuotata anche la vista delle Entry. <br />
     *                  3.  Si aggiuge un elemento alla mappa e si controlla sia stato aggiunto nel set delle Entry. <br />
     *                  4.  Si rimuove un elemento dalla mappa e si controlla che sia rimosso dal set delle Entry. <br />
     *                  5.  Si controlla che sia mappa che vista sono vuote.
     * 
     * @p.recond   La mappa e' stata popolata con 4 elementi. 
     * 
     * @p.ostcond  La mappa e' stata svuotata.
     * 
     * @r.esult    La vista restituita da {@code entrySet()} deve modificarsi se la mappa di cui e' vista viene modificata.
     * 
     */
    @Test
    public void test_Map_Values_ChangedMapChangedEntrySet()
    {
        //creo la vista delle entry di testMap e mi assicuro non sia vuoto
        HSet e = testMap.entrySet();
        assertFalse(e.isEmpty());

        //svuoto la mappa e controllo che anche la vista sia vuota
        testMap.clear();
        assertTrue(e.isEmpty());

        //aggiungo un elemento a testMap e controllo che sia aggiunto anche alla vista
        testMap.put(9, 11);
        HIterator it = e.iterator();
        HEntry entry = (HEntry) it.next();
        assertTrue(entry.getKey().equals(9) && entry.getValue().equals(11));

        //rimuovo un elemento dalla mappa e controllo che sia rimosso dalla vista
        testMap.remove(9);
        assertFalse(e.contains(entry));

        //controllo che mappa e vista siano vuote
        assertTrue(testMap.isEmpty());
        assertTrue(e.isEmpty());
    }

    //-------FINE TEST DEL METODO entrySet() ----------
    //-------TEST DEL METODO equals() ----------

    /**
     * Test del metodo {@link myAdapter.MapAdapter#equals equals}.
     * 
     * @s.ummary   Verifica che il metodo {@code equals()} funzioni correttamente, fornendo HMap uguali e non uguali.
     * 
     * @d.esign    Si vuole verificare che {@code equals()} restituisca correttamente {@code true} se l'oggetto fornito come argomento e' <br />
     *              anch'esso una HMap e le due mappe la stessa mappatura, {@code false} quando anche una di queste condizioni viene a mancare.
     * 
     * @d.escription   Si suppongono testati e funzionanti il metodo {@code size()}, {@code containsAll()}, {@code remove()} <br />
     *                  1.  Si crea una seconda HMap con gli stessi elementi della mappa di partenza.  <br />
     *                  2.  Si verifica che le due mappe sono entrambe istanze di HMap e hanno la stessa mappatura. <br />
     *                  3.  Si controlla che le due HMap siano uguali. <br />
     *                  4.  Si rimuove un elemento dalla mappa di supporto. <br />
     *                  5.  Si verifica che le due mappe siano diverse, non presentano la stessa mappatura. <br />
     *   
     * @p.recond   La mappa e' stata popolata.
     * 
     * @p.ostcond  La mappa non e' stata modificata.
     * 
     * @r.esult    Il metodo {@code equals()} deve restituire {@code true} quando le due HMap rispettano le specifiche, {@code false} altrimenti.
     * 
     */
    @Test
    public void test_Map_Equals_Map()
    {
        //creo una mappa di supporto 
        MapAdapter map2 = new MapAdapter(testMap);

        //controllo che le due mappe siano istanze di HMap
        assertTrue(map2 instanceof HMap && testMap instanceof HMap);
        //controllo che le due mappe presentino la stessa mappatura
        assertEquals(map2.entrySet(), testMap.entrySet());
        //controllo che le due mappe siano uguali
        assertEquals(testMap, map2);

        //modifico map2
        map2.put(3, "florida");

        //controllo che le due mappe non siano piu' uguali, non hanno la stessa mappatura
        assertNotEquals(map2.entrySet(), testMap.entrySet());
        assertNotEquals(testMap, map2);
    }

    /**
     * Test del metodo {@link myAdapter.MapAdapter#equals equals} tra una mappa di tipo HMap e un oggetto non di tipo HMap.
     * 
     * @s.ummary   Verifica che il metodo {@code equals()} restituisca sempre {@code false} se l'argomento di equals() non e' un oggetto di tipo HMap.
     * 
     * @d.esign    Si vuole verificare che {@code equals()} restituisca correttamente {@code false} se l'oggetto fornito come argomento non <br />
     *              e' di tipo HMap.
     * 
     * @d.escription   Si suppongono testati e funzionanti il metodo  {@code entrySet()} <br />
     *                  1.  Si crea un set delle Entry della mappa. <br />
     *                  2.  Si verifica che la mappa non e' uguale al set delle Entry, equals() ritorna false. <br />
     *                  4.  Si verifica che la mappa  non e' uguale ad un oggetto non di tipo HMap, a prescindere dall'oggetto.
     *      
     * @p.recond   La mappa e' stata popolata.
     * 
     * @p.ostcond  La mappa non e' stata modificata.
     * 
     * @r.esult    Il metodo {@code equals()} deve restituire {@code false} quando si compara una HMap con un oggetto non di tipo HMap.
     * 
     */
    @Test
    public void test_Map_Equals_NotMap()
    {
        //creo una vista qualsiasi
        HSet set = testMap.entrySet();
        //controllo che i due oggetti siano uguali, mi aspetto che non lo siano
        assertNotEquals(testMap, set);

        //controllo che testCol non sia uguale ad un intero
        assertFalse(testMap.equals(4));
    }

    //-------FINE TEST DEL METODO equals() ----------
    //-------TEST DEL METODO hashCode() ----------

    /**
     * Test del metodo {@link myAdapter.MapAdapter#hashCode() hashCode}.
     * 
     * @s.ummary   Verifica che il metodo {@code hashCode()} generi correttamente l'hashCode della mappa che chiama il metodo.
     * 
     * @d.esign    Si vuole verificare che il metodo {@code hashCode()} preveda il corretto aggiornamento dell'hashCode della mappa al variare <br />
     *               degli elementi contenuti nella mappa, senza generare errori. <br /> 
     *              Se due mappe hanno lo stesso hashCode allora si dimostra che se {@code m1.equals(m2)}, allora {@code m1.hashCode()} == {@code m2.hashCode()}.
     * 
     * @d.escription   Si suppongono testati e funzionanti il metodo {@code entrySet()}, 
     *                  1.  Si crea una mappa ausiliaria inizialmente uguale alla mappa chiamante. <br />
     *                  2.  Si verifica che l'hashCode della mappa sia la somma degli hashCodes di ogni entry della vista delle Entry della mappa. <br />
     *                  3.  Si verifica che le due mappe sono uguali e che di conseguenza anche i loro hashCode sono uguali. <br />
     *                  4.  Si svuota la mappa di partenza e si verifica che l'hashCode di una mappa vuota e' 0. <br />
     *                  5.  Si confronta l'hashCode della mappa vuota con la mappa di partenza e si verifica che non siano uguali, modifiche alla mappa comporta modifiche all'hashcode.
     *      
     * @p.recond   La mappa e' stata popolata con 4 elementi.
     * 
     * @p.ostcond  La mappa e' stata svuotata.
     * 
     * @r.esult    Il metodo {@code hashCode()} restituisce i risultati attesi nei vari punti.
     * 
     */
    @Test
    public void test_Map_HashCode()
    {
        //creo una seconda mappa ausiliaria
        MapAdapter map2 = new MapAdapter(testMap);
        //verifico che l'hashcode della mappa sia l'hashcode del set delle Entry della mappa
        HSet e = testMap.entrySet();
        assertEquals(e.hashCode(), testMap.hashCode());

        //controllo che le due mappe siano uguali
        assertEquals(testMap, map2);
        //controllo che l'hashCode delle due collezioni sia uguale
        assertEquals((long)testMap.hashCode(),(long)map2.hashCode());

        testMap.clear();
        //hashCode collezione vuota
        assertTrue(testMap.hashCode() == 0);

        //controllo che l'hashCode di testMap sia cambiato
        assertFalse(testMap.hashCode() == map2.hashCode());

    }


}
