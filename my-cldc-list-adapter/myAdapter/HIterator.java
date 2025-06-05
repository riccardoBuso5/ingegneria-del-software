package myAdapter;

/**
 * Interfaccia che rappresenta un iteratore su una collezione di oggetti, come specificato da java.util.Iterator (Java 1.4.2).
 */
public interface HIterator {
    /**
     * Restituisce true se la collezione ha altri elementi da iterare.
     *
     * @return true se ci sono altri elementi da iterare
     * @implNote Questo metodo non lancia mai eccezioni.
     */
    boolean hasNext();

    /**
     * Restituisce il prossimo elemento nella collezione.
     *
     * @return il prossimo elemento nella collezione
     * @throws java.util.NoSuchElementException se non ci sono altri elementi
     */
    Object next();

    /**
     * Rimuove l'ultimo elemento restituito da questo iteratore dalla collezione sottostante.
     * Può essere chiamato solo una volta per ogni chiamata a next().
     *
     * @throws UnsupportedOperationException se l'operazione non è supportata
     * @throws IllegalStateException se il metodo next() non è stato ancora chiamato,
     *         oppure remove() è già stato chiamato dopo l'ultima chiamata a next()
     */
    void remove();
}