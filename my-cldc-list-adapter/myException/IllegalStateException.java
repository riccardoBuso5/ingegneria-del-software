package myException;

/**
 * Segnala che una richiesta è stata fatta su un oggetto e che l'oggetto non è nello stato appropriato.
 * (Equivalente a java.lang.IllegalStateException di Java SE)
 */
public class IllegalStateException extends RuntimeException {
    public IllegalStateException() { super(); }
    public IllegalStateException(String message) { super(message); }
   
}
