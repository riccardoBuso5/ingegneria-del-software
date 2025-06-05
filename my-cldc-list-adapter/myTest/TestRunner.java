package myTest;

/**
 * Classe di utility per l'esecuzione dei test delle classi {@link TestListAdapter}, {@link TestAdapterIterator}
 * e {@link TestAdapterListIterator}.
 * <p>
 * Questo runner richiama i metodi statici {@code run()} delle classi di test per eseguire tutti i test
 * definiti nel progetto e mostrare i risultati a console.
 * </p>
 *
 * <b>Uso:</b><br>
 * Eseguire questa classe per lanciare in sequenza tutti i test principali del progetto.
 *
 * @author Riccardo Buso
 */
public class TestRunner {
    public static void main(String[] args) {
        TestListAdapter.run();
        TestAdapterIterator.run(); 
        TestAdapterListIterator.run();
    }
}