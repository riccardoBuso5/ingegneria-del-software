package myAdapter;

/**
 * Una coppia chiave-valore di una mappa. <br />
 * L'unico modo di ottenere una reference ad una coppia di una mappa e' tramite l'iteratore della vista delle coppie chiave-valore di una {@link HMap}.
 */
public interface HEntry 
{
    /**
     * Metodo per ottenere la chiave di una coppia
     * 
     * @return la chiave della coppia
     */
    public Object getKey();

    /**
     * Metodo per ottenere il valore di una coppia. <br />
     * Se la mappatura e' stata rimossa dalla mappa, i risultati sono indefiniti.
     * 
     * @return il valore della coppia
     */
    public Object getValue();

    /**
     * Metodo per sostituire il valore associato ad una chiave. <br />
     * Modifica la mappa di cui rappresenta una coppia. <br />
     * 
     * @param v nuovo valore da inserire nella coppia
     * @return la vecchia variabile contenuta nella coppia
     * @throws NullPointerException se il valore nuovo e' null, non accettabile nella mappa
     */
    public Object setValue(Object v) throws NullPointerException;

    /**
     * Comparo gli oggetti per uguaglianza. <br />
     * Ritorna vero se l'oggetto comparato e' esso stesso una coppia chiave-valore e le due rappresentano la stessa mappatura.
     * 
     * @param o l'oggetto da comparare con questa coppia chiave-valore
     * @return true se l'oggetto e' uguale a questo ingresso, usando l'hashcode
     */
    public boolean equals(Object o);

    /**
     * Metodo che ritorna l'hash code per questa coppia
     * 
     * @return l'hash code per questa coppia chiave-valore di mappa
     */
    public int hashCode();

}
