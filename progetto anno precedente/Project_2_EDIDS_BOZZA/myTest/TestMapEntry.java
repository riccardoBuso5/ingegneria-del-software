package myTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import org.junit.Before;
import org.junit.Test;
import myAdapter.*;

/**
 * Test suite per la classe {@link myAdapter.MapAdapter.Entry}.
 * <p>
 * Summary: Verifica il corretto funzionamento dei metodi della classe {@code MapAdapter.Entry} che implementa l'interfaccia {@link myAdapter.HEntry}
 * <p>
 * Design:  Si utilizzano JUnit 4.13.2, hamcrest-core-1.3 <br />
 *          La test suite include test cases i metodi di accesso, modifica e interrogazione di una {@link myAdapter.HEntry} <br />
 *          tramite la classe {@link myAdapter.MapAdapter.Entry}, di cui viene fornito un set reinizializzato prima di ogni test case. <br />
 *          Si suppongono funzionanti i metodi di {@link myAdapter.MapAdapter}, {@link myAdapter.MapAdapter.EntrySet} e {@link myAdapter.MapAdapter.EntrySet.EntrySetIterator}. <br />
 *          Prima di ogni test case si popola la mappa per ottenere la vista delle entry di tipo Entry. Per accedere agli elementi del set uso un iteratore al set. <br />
 *          Nella descrizoine dei diversi testCase, e' implicita l'esecuzione della funzione setUp annotata con @Before e che suppongono testati e funzionanti i metodi <br />
 *          {@link myAdapter.MapAdapter#put put}, usata per la funzione setUp.
 */
public class TestMapEntry 
{

    private MapAdapter map = new MapAdapter();
    private HSet testEntry = map.entrySet();

    //METODO PER POPOLARE UNA MAPPA

    /**
     * Configuara l'ambiente dei test cases popolando la mappa la cui vista verra' usata per ottenere oggetti di tipo Entry.
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

    //-------TEST DEL METODO getKey() ----------

    /**
     * Test del metodo {@link myAdapter.MapAdapter.Entry#getKey() getKey} su un oggetto di tipo Entry.
     * 
     * @s.ummary Verifica che il metodo {@code getKey()} ritorna correttamente il valore della chiave della Entry.
     * 
     * @d.esign    Si vuole verificare che {@code getKey()} ritorni la corretta chiave associata alla Entry analizzata senza provocare errori <br />
     *              o modificare la mappa.
     * 
     * @d.escription   Si suppongono testati e funzionanti il metodo {@link myAdapter.MapAdapter#get get}, {@link myAdapter.MapAdapter#containsKey containsKey}, <br />
     *                  {@link myAdapter.MapAdapter#size() size}, {@link myAdapter.MapAdapter.EntrySet.EntrySetIterator#next() next()}, {@link myAdapter.MapAdapter.EntrySet#iterator() iterator} <br />
     *                  1.  Creo una mappa nuova e ci aggiungo una coppia chiave-valore conosciuta. <br />
     *                  2.  Uso l'iteratore della vista delle Entry della mappa per ottenere la Entry. <br />
     *                  3.  Controllo che la chiave della Entry corrisponda alla chiave della mappa. <br />
     *                  4.  Controllo che la mappa abbia la stessa dimensione di partenza e lo stesso valore associato alla chiave.
     * 
     * @p.recond   La vista delle Entry della mappa che contiene oggetti di tipo Entry e' stata popolata.
     * 
     * @p.ostcond  La vista testEntry contiene la coppia conosciuta, la mappa che contiene la Entry non e' stata modificata.
     * 
     * @r.esult    {@code getKey()} deve restuire la chiave associata alla Entry senza modificare la mappa
     * 
     */
    @Test
    public void test_Entry_getKey()
    {
        //ci aggiungo un elemento conosciuto
        MapAdapter map = new MapAdapter();
        map.put(4, "");
        testEntry = map.entrySet();
        //uso l'iteratore per ottenere l'oggetto di tipo Entry
        HIterator it = testEntry.iterator();
        HEntry e = (HEntry)it.next();
        //controllo che la chiave della Entry sia il valore aspettato 4
        assertTrue(e.getKey().equals(4));

        //controllo che la dimensione della mappa sia ancora 1
        assertTrue(map.size() == 1);
        //controllo che map contenga ancora la coppia [4, ""]
        assertTrue(map.containsKey(4) && map.get(4).equals(""));
    }
    
    //-------FINE DEL METODO getKey() ----------
    //-------TEST DEL METODO getValue() ----------
    
    /**
     * Test del metodo {@link myAdapter.MapAdapter.Entry#getValue() getValue} su un oggetto di tipo Entry.
     * 
     * @s.ummary Verifica che il metodo {@code getValue()} ritorna correttamente il valore del valore della Entry.
     * 
     * @d.esign    Si vuole verificare che il metodo {@code getValue()} si appoggi correttamente al metodo {@link myAdapter.MapAdapter#get get} per il ritorno del valore associato alla Entry analizzata, senza provocare errori <br />
     *              o modificare la mappa. 
     * 
     * @d.escription   Si suppongono testati e funzionanti il metodo {@link myAdapter.MapAdapter#get get}, {@link myAdapter.MapAdapter#containsKey containsKey}, <br />
     *                  {@link myAdapter.MapAdapter#size() size}, {@link myAdapter.MapAdapter.EntrySet.EntrySetIterator#next() next()}, {@link myAdapter.MapAdapter.EntrySet#iterator() iterator} <br />
     *                  1.  Creo una mappa nuova e ci aggiungo una coppia chiave-valore conosciuta. <br />
     *                  2.  Uso l'iteratore della vista delle Entry della mappa per ottenere la Entry. <br />
     *                  3.  Controllo che il valore della Entry corrisponda al valore dell'unica coppia della mappa. <br />
     *                  4.  Controllo che la mappa abbia la stessa dimensione di partenza e la stessa coppia chiave-valore.
     * 
     * @p.recond   La vista delle Entry della mappa che contiene oggetti di tipo Entry e' stata popolata.
     * 
     * @p.ostcond  La vista testEntry contiene la coppia conosciuta, la mappa che contiene la Entry non e' stata modificata.
     * 
     * @r.esult    {@code getValue()} deve restuire il valore associato alla Entry senza modificare la mappa
     * 
     */
    @Test
    public void test_Entry_getValue()
    {
        //Creo una nuova mappa e ci aggiungo un elemento
        map = new MapAdapter();
        map.put(4, "pinguino");
        testEntry = map.entrySet();
        //uso l'iteratore per ottenere l'oggetto di tipo Entry
        HIterator it = testEntry.iterator();
        HEntry e = (HEntry)it.next();
        
        //controllo che il valore della Entry sia il valore aspettato "pinguino"
        assertTrue(e.getValue().equals("pinguino"));

        //controllo che la dimensione della mappa sia ancora 1
        assertTrue(map.size() == 1);
        //controllo che map contenga ancora la coppia [4, "pinguino"]
        assertTrue(map.containsKey(4) && map.get(4).equals("pinguino"));
    }

    /**
     * Test del metodo {@link myAdapter.MapAdapter.Entry#getValue() getValue} dopo la rimozione della Entry dal set di Entry.
     * 
     * @s.ummary Verifica che il metodo {@code getValue()} ritorna {@code null} dopo la sua rimozione dal setEntry.
     * 
     * @d.esign    Si vuole verificare che il metodo {@code getValue()} si appoggi correttamente al metodo {@link myAdapter.MapAdapter#get get} per il ritorno del valore associato alla Entry analizzata, senza provocare errori <br />
     *              o modificare la mappa. 
     * 
     * @d.escription   Si suppongono testati e funzionanti il metodo {@link myAdapter.MapAdapter.EntrySet#remove remove}, <br />
     *                  {@link myAdapter.MapAdapter.EntrySet.EntrySetIterator#next() next()}, {@link myAdapter.MapAdapter.EntrySet#iterator() iterator} <br />
     *                  1.  Creo una mappa nuova e ci aggiungo una coppia chiave-valore conosciuta. <br />
     *                  2.  Uso l'iteratore della vista delle Entry della mappa per ottenere la Entry. <br />
     *                  3.  Si rimuove tramite l'iteratore la Entry dal set. <br />
     *                  4.  Si verifica che chiamando il metodo getValue() per la Entry ottenuta al punto 2, il valore restituito e' {@code null}.
     * 
     * @p.recond   Creo un nuovo EntrySet con una sola Entry
     * 
     * @p.ostcond  La Entry e' stata rimossa dall'EntrySet.
     * 
     * @r.esult    {@code getValue()} deve restuire null se chiamato dopo la rimozione della Entry dal set
     * 
     */
    @Test
    public void test_Entry_getValue_NoMoreMapping()
    {
        //Creo una nuova mappa e ci aggiungo un elemento
        map = new MapAdapter();
        map.put(4, "pinguino");
        testEntry = map.entrySet();
        //uso l'iteratore per ottenere l'oggetto di tipo Entry
        HIterator it = testEntry.iterator();
        HEntry e = (HEntry)it.next();
        
        //rimuovo tramite iteratore la mappatura
        it.remove();
        //controllo che ora il metodo getValue() della Entry e sia null
        assertTrue(e.getValue() == null);
    }

    //-------FINE DEL METODO getValue() ----------
    //-------TEST DEL METODO setValue() ----------
    
    /**
     * Test del metodo {@link myAdapter.MapAdapter.Entry#setValue setValue} quando si cerca di impostare un valore null su un oggetto di tipo Entry.
     * 
     * @s.ummary Verifica che il metodo {@code setValue()} lancia correttamente {@link NullPointerException} quando si cerca di impostare il valore di una Entry a null.
     * 
     * @d.esign    Si vuole analizzare il comportamento di {@code setValue()} quando si prova a impostare un valore null alla Entry.
     * 
     * @d.escription   Si suppongono testati e funzionanti il metodo {@link myAdapter.MapAdapter.EntrySet.EntrySetIterator#next() next()}, {@link myAdapter.MapAdapter.EntrySet#iterator() iterator} <br />
     *                  1.  Uso un iteratore per ottenere un oggetto di tipo Entry. <br />
     *                  2.  Si chiama setValue(null) per verificare il lancio di NullPointerException quando si cerca di impostare un valore null su un oggetto di tipo Entry. <br />
     * 
     * @p.recond   La vista delle Entry della mappa che contiene oggetti di tipo Entry e' stata popolata.
     * 
     * @p.ostcond  La vista testEntry non e' stata modificata.
     * 
     * @r.esult    {@code setValue()} deve lanciare {@link NullPointerException} quando si cerca di impostare un valore null su un oggetto di tipo Entry.
     * 
     */
    @Test(expected = NullPointerException.class)
    public void test_Entry_setValue_Null()
    {
        HIterator it= testEntry.iterator();
        HEntry e = (HEntry)it.next();
        e.setValue(null);
    }

    /**
     * Test del metodo {@link myAdapter.MapAdapter.Entry#setValue setValue} su una Entry che conteneva gia' un valore.
     * 
     * @s.ummary   Verifica che il metodo {@code setValue()} ritorna correttamente il vecchio valore contenuto nella Entry e modifica sia <br />
     *              sia la mappa che la Entry.
     * 
     * @d.esign    Si vuole verificare che il metodo {@code setValue()} su una Entry si appoggi in modo adeguato al metodo {@link myAdapter.MapAdapter#put put} per il cambiamento del valore associato ad una chiave. <br />
     *              Verifico che restituisce il vecchio valore associato alla Entry e che modifica la Entry e con backing la Mappa.
     * 
     * @d.escription   Si suppongono testati e funzionanti il metodo {@link myAdapter.MapAdapter#get get}, {@link myAdapter.MapAdapter#containsKey containsKey}, <br />
     *                  {@code getValue()}, {@link myAdapter.MapAdapter.EntrySet.EntrySetIterator#next() next()}, {@link myAdapter.MapAdapter.EntrySet#iterator() iterator} <br />
     *                  1.  Uso un iteratore per ottenere un oggetto di tipo Entry. <br />
     *                  2.  Controllo che il valore di Entry sia quello aspettato. <br />
     *                  3.  Modifico il valore della Entry analizzata e controllo che il valore sia cambiato. <br />
     *                  4.  Controllo che la mappa sia stata modificata tramite la chiamata a questo metodo.
     * 
     * @p.recond   La vista delle Entry della mappa che contiene oggetti di tipo Entry e' stata popolata.
     * 
     * @p.ostcond  La vista testEntry e' stata modificata e pure la mappa map con il nuovo valore dell Entry.
     * 
     * @r.esult    {@code setValue()} deve sostituire correttamente il valore associato ad una Entry e restituire il vecchio valore ad essa associata.
     * 
     */
    @Test
    public void test_Entry_setValue()
    {
        //Creo una nuova mappa e ci aggiungo un elemento
        map = new MapAdapter();
        map.put(4, "pinguino");
        testEntry = map.entrySet();
        //uso l'iteratore per ottenere l'oggetto di tipo Entry
        HIterator it = testEntry.iterator();
        HEntry e = (HEntry)it.next();
        
        //controllo che il valore della Entry sia il valore aspettato "pinguino"
        assertTrue(e.getValue().equals("pinguino"));

        //Cambio valore alla entry
        assertEquals("pinguino", e.setValue("economy+"));

        //Controllo che ora la Entry contenga il valore "economy+"
        assertTrue(e.getValue().equals("economy+"));

        //Controllo che anche la mappa e' stata modificata dal metodo
        assertTrue(map.containsKey(4) && map.get(4).equals("economy+"));
    }
    
    //-------FINE DEL METODO setValue() ----------
    //-------TEST DEL METODO equals() ----------
    
    /**
     * Test del metodo {@link myAdapter.MapAdapter.Entry#equals equals} tra due HEntry.
     * 
     * @s.ummary   Verifica che il metodo {@code equals()} funzioni correttamente sia fornendo una HEntry uguale, sia fornendone una non uguale.
     * 
     * @d.esign    Si vuole verificare che {@code equals()} restituisca correttamente {@code true} se la HEntry fornita come argomento ha <br />
     *              la stessa chiave e lo stesso valore della HEntry chiamante, {@code false} quando anche una di queste condizioni viene a mancare.
     * 
     * @d.escription   Si suppongono testati e funzionanti il metodo {@code getKey()}, {@code getValue()}, {@link myAdapter.MapAdapter.EntrySet.EntrySetIterator#next() next()}, {@link myAdapter.MapAdapter.EntrySet#iterator() iterator} <br />
     *                  1.  Si crea una seconda vista delle entry della mappa a partire da una mappa di supporto.  <br />
     *                  2.  Si usano due iteratori per ottenere due oggetti di tipo HEntry a partire dalle due viste di Entry. <br />
     *                  3.  Si controlla che le due HEntry abbiano la stessa chiave e lo stesso valore. <br />
     *                  4.  Si chiama il metodo equals() fornendo la prima HEntry come argomento e verificare che restituisce true. <br />
     *                  5.  Si cambia il valore della prima HEntry analizzata. <br />
     *                  6.  Si controlla che la nuova HEntry non sia piu' uguale alla HEntry precedentemente e verificare che equals() che restituisca false. <br />
     *                  7.  Si cambia chiave e valore della prima HEntry e si verifica che equals() restituisca false. <br />
     *                  8.  Si cambia valore alla prima HEntry in modo che sia uguale al valore della seconda HEntry, verificare che equals() restituisca false.
     *                  
     * @p.recond   La vista delle Entry e' stata popolata e si crea un secondo set con gli stessi elementi.
     * 
     * @p.ostcond  La vista delle Entry non e' stata modificata.
     * 
     * @r.esult    Il metodo {@code equals()} deve restituire {@code false} quando due HEntry non rappresentano la stessa mappatura, {@code true} altrimenti.
     * 
     */
    @Test
    public void test_Entry_equals_Entry()
    {
        //creo una nuova mappa inizialmente uguale a map e la sua EntrySet
        MapAdapter map2 = new MapAdapter(map);
        HSet toCmp = map2.entrySet();
        //creo due iteratori a due viste uguali
        HIterator itToCmp = toCmp.iterator();
        HIterator itEntry = testEntry.iterator();
        //ottengo le due entry che voglio comparare
        HEntry e1 = (HEntry)itToCmp.next();
        HEntry e2 = (HEntry)itEntry.next(); 

        //Comparo le due Entry ottenute
        assertEquals(e1.getKey(), e2.getKey());
        assertEquals(e1.getValue(), e2.getValue());
        assertTrue(e1.equals(e2));

        //cambio il valore di e1 in modo che sia diverso dal valore di e2
        e1.setValue(999);
        //comparo ora e1 e e2, hanno la stessa chiave ma valore diverso, mi aspetto risulti false
        assertEquals(e1.getKey(), e2.getKey());
        assertNotEquals(e1.getValue(), e2.getValue());
        assertFalse(e1.equals(e2));

        //faccio puntare e1 alla prossima Entry, cosi' cambio la chiave della Entry
        e1 = (HEntry)itToCmp.next();

        //comparo ora e1 e e2, hanno chiavi diverse e valori diversi, mi aspetto risulti false
        assertNotEquals(e1.getKey(), e2.getKey());
        assertNotEquals(e1.getValue(), e2.getValue());
        assertFalse(e1.equals(e2));

        //cambio il valore di e1 in modo che sia uguale a quello di e2
        e1.setValue(e2.getValue());
        //comparo ora e1 e e2, hanno lo stesso valore ma chiave differente mi aspetto risulti false
        assertNotEquals(e1.getKey(), e2.getKey());
        assertEquals(e1.getValue(), e2.getValue());
        assertFalse(e1.equals(e2));
    }

    /**
     * Test del metodo {@link myAdapter.MapAdapter.Entry#equals equals} tra una HEntry e un oggetto non HEntry.
     * 
     * @s.ummary   Verifica che il metodo {@code equals()} restituisca sempre {@code false} se l'argomento di equals() non e' un oggetto di tipo Entry.
     * 
     * @d.esign    Si vuole verificare che {@code equals()} restituisca correttamente {@code false} se l'oggetto fornito come argomento non <br />
     *              e' di tipo entry.
     * 
     * @d.escription   Si suppongono testati e funzionanti il metodo  {@link myAdapter.MapAdapter.EntrySet.EntrySetIterator#next() next()}, {@link myAdapter.MapAdapter.EntrySet#iterator() iterator} <br />
     *                  1.  Si usa l'iteratore della vista delle Entry della mappa per ottenere la HEntry. <br />
     *                  2.  Si chiama il metodo equals() usando come argomento la mappa, che non e' di tipo HEntry, verifico che restituisca false.
     *      
     * @p.recond   La vista delle Entry e' stata popolata e contiene oggetti di tipo Entry.
     * 
     * @p.ostcond  La vista delle Entry non e' stata modificata.
     * 
     * @r.esult    Il metodo {@code equals()} deve restituire {@code false} quando si compara una HEntry con un oggetto non di tipo HEntry.
     * 
     */
    @Test
    public void test_Entry_equals_NotEntry()
    {
        HIterator it = testEntry.iterator();
        HEntry e = (HEntry)it.next();
        //Comparo la Entry con la mappa, mi aspetto risulti false
        assertFalse(e.equals(map));
    }

    //-------FINE DEL METODO equals() ----------
    //-------TEST DEL METODO hashCode() ----------

    /**
     * Test del metodo {@link myAdapter.MapAdapter.Entry#hashCode() hashCode}.
     * 
     * @s.ummary   Verifica che il metodo {@code hashCode()} generi correttamente l'hashCode della Entry che chiama il metodo.
     * 
     * @d.esign    Si vuole verificare che il metodo {@code hashCode()} preveda il corretto aggiornamento dell'hashCode della Entry al variare <br />
     *              del valore della Entry. Se due Entry hanno lo stesso hashCode allora si dimostra che se {@code e1.equals(e2)}, allora {@code e1.hashCode()} == {@code e2.hashCode()}.
     * 
     * 
     * @d.escription   Si suppongono testati e funzionanti il metodo {@code getKey()}, {@code getValue()}, {@code setValue()}, <br />
     *                  {@link myAdapter.MapAdapter.EntrySet.EntrySetIterator#next() next()}, {@link myAdapter.MapAdapter.EntrySet#iterator() iterator} <br />
     *                  1.  Si usa l'iteratore della vista delle Entry della mappa per ottenere la Entry. <br />
     *                  2.  Si controlla che l'hashCode della Entry ottenuta sia l'OR esclusivo della mappatura della Entry <br />
     *                  3.  Si salva il valore corrente dell'hashCode della Entry e si modifica la entry con l'operazione setValue() <br />
     *                  4.  Si controlla che il nuovo valore dell'hashCode sia cambiato
     *                  5.  Si controlla che due Entry uguali abbiano anche lo stesso hashCode
     *      
     * @p.recond   La vista delle Entry e' stata popolata e contiene oggetti di tipo Entry.
     * 
     * @p.ostcond  La vista testEntry e' stata modifica.
     * 
     * @r.esult    Il metodo {@code hashCode()} deve restituire i risultati attesi nei vari punti.
     * 
     */
    @Test
    public void test_Entry_hashCode()
    {
        //ottengo l'oggetto di tipo Entry tramite iteratore della vista delle Entry
        HIterator it = testEntry.iterator();
        HEntry e = (HEntry)it.next();
        //Controllo che l'hashCode della Entry ottenuta sia l'OR esclusivo della mappatura della Entry
        int hk = e.getKey().hashCode();
        int hv = e.getValue().hashCode();
        assertEquals((long) hv ^ hk, (long)e.hashCode());

        //salvo l'attuale valore dell'hashCode e poi modifico la Entry
        int hc = e.hashCode();
        e.setValue("Africa");
        
        //controllo che l'hashCode sia cambiato
        assertNotEquals((long)hc,(long) e.hashCode());

        //controllo che due Entry uguali abbiano lo stesso hashCode
        HEntry e1 = (HEntry)testEntry.toArray()[0];
        assertEquals(e1, e);
        assertEquals((long) e1.hashCode(), (long) e.hashCode());
    }




}
