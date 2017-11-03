package com.stackhunter.util.objectiterator;

import java.lang.reflect.Array;

public class ArrayIterator implements IObjectIterator {

    private final String name;
    private final Object array;
    private final int length;
    private int nextIndex = -1;
    private Object currentElement;

    public ArrayIterator(String name, Object array) {
        this.name = name;
        this.array = array;
        this.length = Array.getLength(array);
    }

    @Override
    public boolean next() {
        if (nextIndex + 1 >= length) {
            return false;
        }

        nextIndex++;
        currentElement = Array.get(array, nextIndex);
        return true;
    }

    @Override
    public String getName() {
        return name + "[" + nextIndex + "]";
    }

    @Override
    public Object getValue() {
        return currentElement;
    }

}
