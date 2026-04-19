package com.star.template.util.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ObjectArrayIterator<T> implements Iterator<T> {

    private final T[] array;

    private final int length;

    private int index;

    public ObjectArrayIterator(T[] array) {
        this.array = array;
        this.length = array == null ? 0 : array.length;
    }

    public T[] getArray() {
        return array;
    }

    public boolean hasNext() {
        return index < length;
    }

    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return array[index++];
    }

    public void remove() {
        throw new UnsupportedOperationException("remove() method is not supported");
    }
}