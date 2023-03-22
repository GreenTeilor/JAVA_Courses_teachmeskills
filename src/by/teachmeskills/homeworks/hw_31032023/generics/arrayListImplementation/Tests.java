package by.teachmeskills.homeworks.hw_31032023.generics.arrayListImplementation;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class Tests {

    public <T> void commonTest(ArrayListImplementation<T> list) {
        list.clear();
        assertEquals("clear() doesn't work", "[]", list.toString());
        assertEquals("size() doesn't work", 0, list.size());
        assertTrue("isEmpty() doesn't work", list.isEmpty());
        list.add((T)"hello");
        list.add((T)"world");
        assertEquals("Element added wrong", "[ hello  world ]", list.toString());
        assertEquals("toArray() doesn't work", "[hello, world]", Arrays.toString(list.toArray()));
        list.addAll(Arrays.asList("and", "sun"));
        assertEquals("addAll() doesn't work", "[ hello  world  and  sun ]", list.toString());
        list.addAll(1, Arrays.asList( "fascinating", "beautiful"));
        assertEquals("addAll() by index doesn't work", "[ hello  fascinating  beautiful  world  and  sun ]", list.toString());
        assertNull("set()doesn't work", list.set(10, "someInfo"));
        list.set(0, "Hi");
        assertEquals("set() doesn't work", "[ Hi  fascinating  beautiful  world  and  sun ]", list.toString());
        assertEquals("indexOf() doesn't work", -1, list.indexOf("someInfo"));
        assertEquals("indexOf() doesn't work", 3, list.indexOf("world"));
        list.add((T)new String("world"));
        assertEquals("lastIndexOf() doesn't work", 6, list.lastIndexOf("world"));
        list.retainAll(Arrays.asList("Hi", "world", "someInfo", "sun"));
        assertEquals("retainAll() doesn't work", "[ Hi  world  sun  world ]", list.toString());
        list.removeAll(Arrays.asList("world", "sun"));
        assertEquals("removeAll() doesn't work", "[ Hi ]", list.toString());
        list.add((T)"somebody");
        assertTrue("containsAll() doesn't work", list.containsAll(Arrays.asList("Hi", "somebody")));
        assertTrue("containsAll() doesn't work", list.containsAll(Arrays.asList("Hi")));
        assertFalse("containsAll() doesn't work", list.containsAll(Arrays.asList("Hi", "somebody", "someInfo")));
    }

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
        commonTest(list);
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
        commonTest(list);
    }

    //Initialized with collection
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
        commonTest(list);
    }

}
