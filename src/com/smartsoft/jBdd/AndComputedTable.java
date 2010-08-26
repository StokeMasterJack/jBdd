package com.smartsoft.jBdd;

public class AndComputedTable {

    /**
     * The default initial capacity - MUST be a power of two.
     */
    static final int DEFAULT_INITIAL_CAPACITY = 16;

    /**
     * The maximum capacity, used if a higher value is implicitly specified
     * by either of the constructors with arguments.
     * MUST be a power of two <= 1<<30.
     */
    static final int MAXIMUM_CAPACITY = 1 << 30;

    /**
     * The load factor used when none specified in constructor.
     */
    static final float DEFAULT_LOAD_FACTOR = 0.75f;

    /**
     * The table, resized as necessary. Length MUST Always be a power of two.
     */
    transient AndComputation[] table;

    /**
     * The number of key-value mappings contained in this map.
     */
    transient int size;

    /**
     * The next size value at which to resize (capacity * load factor).
     * @serial
     */
    int threshold;

    /**
     * The load factor for the hash table.
     *
     * @serial
     */
    final float loadFactor;

    /**
     * The number of times this HashMap has been structurally modified
     * Structural modifications are those that change the number of mappings in
     * the HashMap or otherwise modify its internal structure (e.g.,
     * rehash).  This field is used to make iterators on Collection-views of
     * the HashMap fail-fast.  (See ConcurrentModificationException).
     */
    transient volatile int modCount;

    /**
     * Constructs an empty <tt>HashMap</tt> with the specified initial
     * capacity and load factor.
     *
     * @param  initialCapacity the initial capacity
     * @param  loadFactor      the load factor
     * @throws IllegalArgumentException if the initial capacity is negative
     *         or the load factor is nonpositive
     */
    public AndComputedTable(int initialCapacity, float loadFactor) {

        this.loadFactor = loadFactor;
        threshold = (int) (initialCapacity * loadFactor);
        table = new AndComputation[initialCapacity];
    }

    /**
     * Constructs an empty <tt>HashMap</tt> with the specified initial
     * capacity and the default load factor (0.75).
     *
     * @param  initialCapacity the initial capacity.
     * @throws IllegalArgumentException if the initial capacity is negative.
     */
    public AndComputedTable(int initialCapacity) {
        this(initialCapacity, DEFAULT_LOAD_FACTOR);
    }

    


    /**
     * Returns index for hash code h.
     */
    static int indexFor(int hash, int arrayLength) {
        return hash & (arrayLength - 1);
    }

    public int size() {
        return size;
    }


    public Node get(int hash, int id1, int id2) {
        int bucketIndex = indexFor(hash, table.length);
        AndComputation e = table[bucketIndex];
        if (e == null) {
            return null;
        } else if (e.hash == hash && e.id1 == id1 && e.id2 == id2) {
            return e.computedValue;
        } else {
            return null;
        }
    }


    /*
   H(k1,k2) = ((k1p1 + k2) p2)/2^w-n

   k are the hashkeys
   ps are sufficiently large primes
   w is the number of bits in an interger
   2^n is the size of the hashtable

    */


    public void put(int hash, int id1, int id2, Node computedValue) {
        int bucketIndex = indexFor(hash, table.length);
        table[bucketIndex] = new AndComputation(hash, id1, id2, computedValue); //lossy
        if (size++ >= threshold) resize(2 * table.length);
    }


    /**
     * Rehashes the contents of this map into a new array with a
     * larger capacity.  This method is called automatically when the
     * number of keys in this map reaches its threshold.
     *
     * If current capacity is MAXIMUM_CAPACITY, this method does not
     * resize the map, but sets threshold to Integer.MAX_VALUE.
     * This has the effect of preventing future calls.
     *
     * @param newCapacity the new capacity, MUST be a power of two;
     *        must be greater than current capacity unless current
     *        capacity is MAXIMUM_CAPACITY (in which case value
     *        is irrelevant).
     */
    void resize(int newCapacity) {
        AndComputation[] oldTable = table;
        int oldCapacity = oldTable.length;
        if (oldCapacity == MAXIMUM_CAPACITY) {
            threshold = Integer.MAX_VALUE;
            return;
        }

        AndComputation[] newTable = new AndComputation[newCapacity];
        transfer(newTable);
        table = newTable;
        threshold = (int) (newCapacity * loadFactor);
    }

    /**
     * Transfers all entries from current table to newTable.
     */
    void transfer(AndComputation[] newTable) {
        AndComputation[] oldTable = table;
        int newCapacity = newTable.length;
        for (int j = 0; j < oldTable.length; j++) {
            AndComputation e = oldTable[j];
            if (e != null) {
                oldTable[j] = null;
                int i = indexFor(e.hash, newCapacity);
                newTable[i] = e;
            }
        }
    }


    /**
     * Removes all of the mappings from this map.
     * The map will be empty after this call returns.
     */
    public void clear() {
        modCount++;
        AndComputation[] tab = table;
        for (int i = 0; i < tab.length; i++)
            tab[i] = null;
        size = 0;
    }


    static class AndComputation {

        final int id1;  //node id of operand #1
        final int id2;  //node id of operand #2
        final int hash; //hash of id1 and id2

        Node computedValue;

        AndComputation(int hash, int id1, int id2, Node computedValue) {
            this.id1 = id1;
            this.id2 = id2;
            this.hash = hash;
            this.computedValue = computedValue;
            assert id1 <= id2;
        }

    }


    int capacity() { return table.length; }

    float loadFactor() { return loadFactor; }



}