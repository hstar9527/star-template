package com.star.template.util.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ByteArrayIterator implements Iterator<Byte> {

    private final byte[] array;

    private final int length;

    private int index;

    public ByteArrayIterator(byte[] array) {
        this.array = array;
        this.length = array == null ? 0 : array.length;
    }

    public Object getArray() {
        return array;
    }

    public boolean hasNext() {
        return index < length;
    }

    public Byte next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return array[index++];
    }

    public void remove() {
        throw new UnsupportedOperationException("remove() method is not supported");
    }
}