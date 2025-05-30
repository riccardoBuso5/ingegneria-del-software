package myAdapter;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class ListAdapterTest {

    @Test
    public void testListAdapter() {
        List list = new ArrayList();
        list.add("A");
        list.add("B");
        list.add("C");

        HList hList = new ListAdapter(list);
        assertEquals(3, hList.size());
        assertTrue(hList.contains("A"));
    }

    @Test
    public void testListAdapterIterator() {
        List list = new ArrayList();
        list.add("A");
        list.add("B");

        HIterator iterator = new ListAdapterIterator(list.iterator());
        assertTrue(iterator.hasNext());
        assertEquals("A", iterator.next());
    }

    @Test
    public void testListAdapterListIterator() {
        List list = new ArrayList();
        list.add("A");
        list.add("B");

        HListIterator listIterator = new ListAdapterListIterator(list.listIterator());
        assertTrue(listIterator.hasNext());
        assertEquals("A", listIterator.next());
        listIterator.add("C");
        assertEquals(3, list.size());
    }
}
