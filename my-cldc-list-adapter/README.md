# My CLDC List Adapter

Questo progetto fornisce un adattatore per l’interfaccia `List` di Java 1.4.2, implementato tramite la classe `Vector` dell’ambiente CLDC 1.1. L’adattatore permette di utilizzare le funzionalità del Java Collections Framework anche in ambienti con risorse limitate, garantendo compatibilità con la versione specificata di Java.

---

## Struttura del Progetto

- **myAdapter/**  
  Contiene le interfacce e le implementazioni principali:
  - `HCollection.java`: Interfaccia ispirata a `Collection`.
  - `HIterator.java`: Interfaccia ispirata a `Iterator`.
  - `HList.java`: Estende `HCollection` e aggiunge i metodi di `List`.
  - `HListIterator.java`: Estende `HIterator` e aggiunge i metodi di `ListIterator`.
  - `ListAdapter.java`: Implementazione di `HList` e `HCollection` tramite `Vector`.
  - `ListAdapterIterator.java`, `AdapterListIterator.java`: Implementazioni degli iteratori.
  - `package-info.java`: Documentazione del package.

- **myTest/**  
  Contiene i test unitari e il runner:
  - `ListAdapterTest.java`: Test per `ListAdapter`.
  - `HIteratorTest.java`: Test per `HIterator`.
  - `HListIteratorTest.java`: Test per `HListIterator`.
  - `TestRunner.java`: Esegue tutti i test e mostra i risultati.
  - `package-info.java`: Documentazione del package.

- **lib/**  
  Contiene le librerie JUnit necessarie per il testing:
  - `junit-4.13.2.jar`
  - `hamcrest-core-1.3.jar`

- **fileclass/**  
  Cartella di output per i file `.class` compilati.

- **doc/**  
  Documentazione generata tramite Javadoc.

---

## Compilazione ed Esecuzione dei Test

Assicurati di avere Java (1.4.2 o superiore) e JUnit 4 nella cartella `lib/`.

1. **Compila tutto il progetto**:

   ```sh
   javac -cp ".;lib/junit-4.13.2.jar;lib/hamcrest-core-1.3.jar" -d fileclass myAdapter/*.java myTest/*.java
   ```

2. **Esegui i test**:

   ```sh
   java -cp "fileclass;lib/junit-4.13.2.jar;lib/hamcrest-core-1.3.jar" myTest.TestRunner
   ```

   > Su Linux/macOS usa `:` invece di `;` come separatore nel classpath.

---

## Generazione della Documentazione

Per generare la documentazione Javadoc:

```sh
javadoc -d doc -cp ".;lib/junit-4.13.2.jar;lib/hamcrest-core-1.3.jar" myAdapter/*.java myTest/*.java
```

La documentazione sarà disponibile nella cartella `doc/` (`doc/index.html`).

---

## Note

- Progettato per ambienti embedded o con risorse limitate.
- Compatibile con Java 1.4.2 e CLDC 1.1.
- Per dettagli sulle eccezioni e sulle funzionalità, consulta la documentazione Javadoc generata.

---

**Autore:**  
Riccardo Buso
