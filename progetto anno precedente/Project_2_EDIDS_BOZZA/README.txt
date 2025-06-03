BOZZA GIORGIO N. 2066691
    Comandi per compilare, eseguire i test, generare javadoc.


Per compilare tutti i file .java

    javac -d FileClass -cp :./JUnit/junit-4.13.2.jar:./JUnit/hamcrest-core-1.3.jar ./myAdapter/*.java myTest/*.java

i file .class vengono salvati in una cartella a parte per maggiore ordine.

*****************************************************

per lanciare il main:

    java -cp :./JUnit/junit-4.13.2.jar:./JUnit/hamcrest-core-1.3.jar:./FileClass  myTest/TestRunner

****************************************************
dopo comando precedente si puo' aggiungere il nome di una classe e verranno lanciati solo i test della classi cosi' specificata, per esempio:

    java -cp :./JUnit/junit-4.13.2.jar:./JUnit/hamcrest-core-1.3.jar:./FileClass myTest/TestRunner myTest.TestEntrySet

al posto di myTest.TestEntrySet si puo' usare una delle altre classi di test

****************************************************

Comando che per generare il mio javadoc:

    javadoc -tag d.esign:cm:"Progetto:" -tag s.ummary:cm:"Sommario:" -tag d.escription:cm:"Descrizione:" -tag p.recond:cm:"Condizione iniziale:" -tag p.ostcond:cm:"Condizione finale:" -tag r.esult:cm:"Risultato:" -private -d Javadoc/ -cp ./JUnit/junit-4.13.2.jar: ./myTest/*.java ./myAdapter/*.java
