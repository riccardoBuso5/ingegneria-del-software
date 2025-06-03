package myAdapter;

import java.util.NoSuchElementException;

/**
 * Un iteratore su una collezione.
 */
public interface HIterator 
{
    /**
     * Metodo che determina se ci sono ancora elementi da percorrere dall'iteratore
     * 
     * @return true se l'iteratore ha ulteriori elementi
     */
    public boolean hasNext();

    /**
     * Metodo che restituisce il prossimo elemento nell'iterazione
     * 
     * @return il prossimo elemento della collezione d'interesse
     * @throws java.util.NoSuchElementException se l'iteratore non ha ulteriori elementi
     */
    public Object next() throws NoSuchElementException;

    /**
     * Metodo che rimuove dalla collezione l'ultimo elemento visto dall'iteratore
     * 
     * @throws IllegalStateException se il metodo next() non e' stato ancora chiamato o il metodo remove() e' stato gia' chiamato dall'ultima chiamata
     */
    public void remove();

}
