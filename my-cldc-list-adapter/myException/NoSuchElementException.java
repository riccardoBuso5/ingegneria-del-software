package myException;


/**
 * Segnala che la richiesta di un elemento non esistente è stata fatta su una collezione o iteratore.
 * (Equivalente a java.util.NoSuchElementException di Java SE)
 */
public class NoSuchElementException extends RuntimeException {
    public NoSuchElementException() { super(); }
    public NoSuchElementException(String message) { super(message); }
}