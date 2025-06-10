package myTest;

/**
 * Classe di utility per l'esecuzione dei test definiti nel package {@code myTest}.
 * Questa classe contiene il metodo {@code main} che esegue in sequenza i test definiti nelle classi
 * <p>
 * <b>Test Case Name:</b> TestRunner<br>
 * <b>Summary:</b> Classe di utility che esegue in sequenza tutti i test definiti nel package {@code myTest},
 * richiamando i metodi statici {@code run()} delle classi di test e mostrando i risultati a console.<br>
 * <b>Test Case Design:</b> Permette di eseguire tutti i test da linea di comando e visualizzare il numero totale di test,
 * quelli riusciti, quelli falliti e il tempo di esecuzione complessivo.<br>
 * <b>Pre-condizione:</b> Tutte le classi di test devono essere correttamente compilate e presenti nel classpath.<br>
 * <b>Post-condizione:</b> Vengono mostrati a console i risultati aggregati dei test.<br>
 * <b>Test Tools:</b> JUnit 4.13.2<br>
 * <b>Author:</b> Riccardo Buso
 * </p>
 */
public class TestRunner {
    public static void main(String[] args) {
        TestListAdapter.run();
        TestAdapterIterator.run();
        TestAdapterListIterator.run();
    }
}