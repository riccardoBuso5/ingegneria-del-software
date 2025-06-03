package myTest;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * Classe che esegue tutti i test definiti nelle altre classi. <br />
 * Scrive il risultato dei test, il numero complessivo dei test, il numero di test falliti e il tempo richiesto per l'esecuzione dei test. <br />
 * Si utilizzano JUnit 4-13-2, hamcrest-core-1.3
 */
public class TestRunner 
{
    public static void main(String[] args)
    {
        Class[] testClasses;
        
        if (args.length > 0)
        {
            try
            {
            //Carica la classe di test specificata dall'argomento della linea di comando
            Class testClass = Class.forName(args[0]);
            testClasses = new Class[]{testClass};
            }
            catch(ClassNotFoundException e)
            {
                System.err.println("Classe di test non trovata: " + args[0]);
                return;
            }

        }
        else
        {
            //Specifica tutte le classi di test se args.length == 0
            testClasses = new Class[]
            {
                TestValueCollection.class,
                TestCollectionIterator.class,
                TestEntrySet.class,
                TestEntrySetIterator.class,
                TestMapAdapter.class,
                TestMapEntry.class,
                TestKeySet.class,
                TestSetIterator.class
            };
        }

        //Tengo conto del tempo impiegato
        long start = System.currentTimeMillis();                //inizio
        Result result = JUnitCore.runClasses(testClasses);      //compio i test
        long end = System.currentTimeMillis();                  //fine

        System.out.println("\nNumero totale di test: " + result.getRunCount());

        System.out.println("Numero di tests falliti: " + result.getFailureCount());
        //stampo i fallimenti
        for(Failure failure : result.getFailures())
        {
            System.out.println("\t" + failure.toString());
        }
        //stampo il tempo impiegato
        System.out.println("Tempo impiegato: " + (end - start) + " ms.");
        //stampo il successo o il fallimento dei test
        System.out.println("Tutti i test sono stati eseguiti con successo: " + result.wasSuccessful());
    }


}
