package myTest;
import myAdapter.ListAdapter;
import org.junit.*; 
import static org.junit.Assert.*;
import org.junit.runner.Result;
import org.junit.runner.JUnitCore;
import org.junit.runner.notification.Failure;




public class TestRunner {
    public static void main(String[] args) {
        ListAdapterTest.run();
    }
}