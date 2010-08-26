package com.smartsoft.jBdd;

public final class NodePairKeySet implements NodePairKey {

    private final int id1;
    private final int id2;

    public NodePairKeySet(Node n1, Node n2) {
        if (n1.id <= n2.id) {
            this.id1 = n1.id;
            this.id2 = n2.id;
        } else {
            this.id1 = n2.id;
            this.id2 = n1.id;
        }
    }

    @Override
    public boolean equals(Object o) {
        NodePairKeySet that = (NodePairKeySet) o;
        return this.id1 == that.id1 && this.id2 == that.id2;
    }

    @Override
    public int hashCode() {
        return id1 * 71 + id2;
    }

    /**
    H(k1,k2) = ((k1p1 + k2) p2)/2^w-n

    k are the hashkeys
    ps are sufficiently large primes
    w is the number of bits in an interger
    2^n is the size of the hashtable

 */
}