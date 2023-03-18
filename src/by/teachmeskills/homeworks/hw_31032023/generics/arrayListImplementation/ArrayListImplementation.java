package by.teachmeskills.homeworks.hw_31032023.generics.arrayListImplementation;


import java.util.List;
import java.util.Collection;
import java.util.Arrays;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.AbstractList;

public class ArrayListImplementation<T> extends AbstractList<T>{

    private Object[] array;
    private int size;

    public ArrayListImplementation() {
        array = new Object[0];
        size = 0;
    }

    public ArrayListImplementation(int capacity) {
        array = new Object[capacity];
        size = 0;
    }

    public ArrayListImplementation(Collection<? extends T> collection) {
        Object[] objects = collection.toArray();
        if (collection.getClass() == ArrayListImplementation.class) {
            array = objects;
        } else {
            array = Arrays.copyOf(objects, objects.length, Object[].class);
        }
        size = objects.length;
    }

    @Override
    public void add(int index, T element) {
        try {
            Object[] updatedArray = new Object[size + 1];
            System.arraycopy(array, 0, updatedArray, 0, index);
            System.arraycopy(array, index, updatedArray, index + 1, size - index);
            updatedArray[index] = element;
            array = updatedArray;
            ++size;
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public T remove(int index) {
        try {
            T element = (T) array[index];
            Object[] result = new Object[size - 1];
            System.arraycopy(array, 0, result, 0, index);
            System.arraycopy(array, index + 1, result, index, size - index - 1);
            array = result;
            --size;
            return element;
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public T get(int index) {
        try {
            return (T) array[index];
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public boolean contains(Object o) {
        if (o == null) {
            for (Object element : array) {
                if (element == null) {
                    return true;
                }
            }
        } else {
            for (Object element : array) {
                if (o.equals(element)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[");
        for (Object element : array) {
            result.append(" ").append(element.toString()).append(" ");
        }
        result.append("]");
        return result.toString();
    }

    @Override
    public void clear() {
        array = new Object[0];
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public boolean add(Object o) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean addAll(Collection c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection c) {
        return false;
    }

    @Override
    public Object set(int index, Object element) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator listIterator() {
        return null;
    }

    @Override
    public ListIterator listIterator(int index) {
        return null;
    }

    @Override
    public List subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public boolean retainAll(Collection c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection c) {
        return false;
    }

    @Override
    public boolean containsAll(Collection c) {
        return false;
    }

    @Override
    public Object[] toArray(Object[] a) {
        return new Object[0];
    }
}
