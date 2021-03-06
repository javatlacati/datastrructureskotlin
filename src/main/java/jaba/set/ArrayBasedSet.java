package jaba.set;

import jaba.bags.ArrayBasedBag;

public class ArrayBasedSet<Item> extends ArrayBasedBag<Item> implements jaba.set.Set<Item> {

    public ArrayBasedSet(int capacity) {
        super(capacity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean contains(Item item) {
        for (Item element : super.internalArray) {
            if (element.equals(item)) {
                return true;
            }
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(Item item) {
        if (!contains(item)) {
            super.add(item);
        }
    }
}
