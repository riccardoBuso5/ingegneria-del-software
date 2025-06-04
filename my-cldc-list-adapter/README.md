# My CLDC List Adapter

This project provides an adapter for the Java 1.4.2 List interface, implemented using the Vector class from the CLDC 1.1 environment. The adapter allows for the use of Java Collections Framework features in a constrained environment, ensuring compatibility with the specified Java version.

## Project Structure

- **myAdapter/**: Contains the adapter implementation and interfaces.
  - `HCollection.java`: Defines methods from the Collection interface.
  - `HIterator.java`: Defines methods from the Iterator interface.
  - `HList.java`: Extends HCollection and defines methods from the List interface.
  - `HListIterator.java`: Extends HIterator and defines methods from the ListIterator interface.
  - `ListAdapter.java`: Implements HList and HCollection, adapting Vector functionality.
  - `package-info.java`: Package documentation.

- **myTest/**: Contains unit tests for the adapter.
  - `ListAdapterTest.java`: Unit tests for ListAdapter.
  - `HIteratorTest.java`: Unit tests for HIterator.
  - `HListIteratorTest.java`: Unit tests for HListIterator.
  - `TestRunner.java`: Executes all tests and reports results.
  - `package-info.java`: Package documentation.

- **JUnit/**: Contains the JUnit library required for testing.
  - `junit-3.8.1.jar`: JUnit library.

- **javadoc/**: Contains generated documentation in Javadoc and PDF formats.

## Usage

To compile and run the tests, ensure that the JUnit library is included in your classpath. You can execute the `TestRunner` class from the command line ,
with :
"javac -d FileClass -cp "JUnit/junit-4.13.2.jar;JUnit/hamcrest-core-1.3.jar" myAdapter/*.java myTest/*.java"
,to run all tests and view the results.


## Documentation

Detailed documentation for the adapter and its components can be found in the `doc/` directory, generated using Javadoc.
