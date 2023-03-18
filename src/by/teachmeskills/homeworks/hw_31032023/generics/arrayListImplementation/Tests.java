package by.teachmeskills.homeworks.hw_31032023.generics.arrayListImplementation;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class Tests {

    //Initial capacity - 0
    @Test
    public void test1() {
        ArrayListImplementation<String> list = new ArrayListImplementation<>();
        list.add(0, "Some");
        list.add(1, "new");
        list.add(2, "info");
        assertEquals("Elements added wrong", "[ Some  new  info ]", list.toString());
        list.add(0, "begin");
        assertEquals("Element added wrong", "[ begin  Some  new  info ]", list.toString());
        list.remove(1);
        assertEquals("Element removed wrong", "[ begin  new  info ]", list.toString());
        assertEquals("get() doesn't work", "info", list.get(2));
        assertTrue("contains#1() doesn't work", list.contains("begin"));
        assertTrue("contains#2() doesn't work", list.contains(new String("begin")));
        assertFalse("contains() with null doesn't work", list.contains(null));
        list.add(0, null);
        assertTrue("add() with null doesn't work", list.contains(null));
        list.clear();
        assertEquals("clear() doesn't work", "[]", list.toString());
    }

    ////Initial capacity - 10
    @Test
    public void test2() {
        ArrayListImplementation<String> list = new ArrayListImplementation<>(10);
        list.add(0, "Some");
        list.add(1, "new");
        list.add(2, "info");
        assertEquals("Elements added wrong", "[ Some  new  info ]", list.toString());
        list.add(0, "begin");
        assertEquals("Element added wrong", "[ begin  Some  new  info ]", list.toString());
        list.remove(1);
        assertEquals("Element removed wrong", "[ begin  new  info ]", list.toString());
        assertEquals("get() doesn't work", "new", list.get(1));
        assertTrue("contains#1() doesn't work", list.contains("begin"));
        assertTrue("contains#2() doesn't work", list.contains(new String("begin")));
        assertFalse("contains() with null doesn't work", list.contains(null));
        list.add(0, null);
        assertTrue("add() with null doesn't work", list.contains(null));
        list.clear();
        assertEquals("clear() doesn't work", "[]", list.toString());
    }

    @Test
    public void test3() {
        ArrayListImplementation<String> list = new ArrayListImplementation<>(Arrays.asList("1", "2"));
        list.add(0, "Some");
        list.add(1, "new");
        list.add(2, "info");
        assertEquals("Elements added wrong", "[ Some  new  info  1  2 ]", list.toString());
        list.add(0, "begin");
        assertEquals("Element added wrong", "[ begin  Some  new  info  1  2 ]", list.toString());
        list.remove(1);
        assertEquals("Element removed wrong", "[ begin  new  info  1  2 ]", list.toString());
        assertEquals("get() doesn't work", "info", list.get(2));
        assertTrue("contains#1() doesn't work", list.contains("info"));
        assertTrue("contains#2() doesn't work", list.contains(new String("info")));
        assertFalse("contains() with null doesn't work", list.contains(null));
        list.add(0, null);
        assertTrue("add() with null doesn't work", list.contains(null));
        list.clear();
        assertEquals("clear() doesn't work", "[]", list.toString());
    }

}
