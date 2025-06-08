package myAdapter;
//le seguenti eccezioni sono state create per essere compatibili con le eccezioni di Java SE


/**
 * Segnala che una richiesta è stata fatta su un oggetto e che l'oggetto non è nello stato appropriato.
 * (Equivalente a java.lang.IllegalStateException di Java SE)
 */
class IllegalStateException extends RuntimeException {
    public IllegalStateException() { super(); }
    public IllegalStateException(String message) { super(message); }
}

/**
 * Segnala che la richiesta di un elemento non esistente è stata fatta su una collezione o iteratore.
 * (Equivalente a java.util.NoSuchElementException di Java SE)
 */
class NoSuchElementException extends RuntimeException {
    public NoSuchElementException() { super(); }
    public NoSuchElementException(String message) { super(message); }
}