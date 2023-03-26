package by.teachmeskills.homeworks.hw_31032023.generics.arrayListImplementation;


import java.util.List;
import java.util.Collection;
import java.util.Arrays;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.AbstractList;

public class ArrayListImplementation<T> extends AbstractList<T> {

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
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(array, size);
    }

    @Override
    public boolean add(T o) {
        int previousSize = size;
        this.add(size, o);
        return size > previousSize;
    }

    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < size; ++i) {
            if (o.equals(array[i])) {
                this.remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean addAll(Collection c) {
        Object[] arrayCopy = Arrays.copyOf(array, size);
        for (Object element : c) {
            if (!this.add((T) element)) {
                array = arrayCopy;
                size = array.length;
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(int index, Collection c) {
        Object[] arrayCopy = Arrays.copyOf(array, size);
        int i = index;
        for (Object element : c) {
            this.add(i, (T) element);
            ++i;
        }
        if (array.length != c.size() + arrayCopy.length) {
            array = arrayCopy;
            size = array.length;
            return false;
        }
        return true;
    }

    @Override
    public Object set(int index, Object element) {
        Object previousElement = this.get(index);
        if (previousElement != null) {
            array[index] = element;
        }
        return previousElement;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; ++i) {
            if (array[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; --i) {
            if (array[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean retainAll(Collection c) {
        int previousSize = size;
        int i = 0;
        while (i < size) {
            if (!c.contains(array[i])) {
                this.remove(array[i]);
            }
            else {
                ++i;
            }
        }
        return previousSize != size;
    }

    @Override
    public boolean removeAll(Collection c) {
        int previousSize = size;
        int i = 0;
        while (i < size) {
            if (c.contains(array[i])) {
                this.remove(array[i]);
            }
            else {
                ++i;
            }
        }
        return previousSize != size;
    }

    @Override
    public boolean containsAll(Collection c) {
        for (Object element : c) {
            if (!this.contains(element)) {
                return false;
            }
        }
        return true;
    }
}
