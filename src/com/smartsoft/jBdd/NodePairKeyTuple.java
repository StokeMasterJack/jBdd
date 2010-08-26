package com.smartsoft.jBdd;

public final class NodePairKeyTuple implements NodePairKey {

    private final int id1;
    private final int id2;
    private final int hash;

    public NodePairKeyTuple(Node n1, Node n2) {
        this.id1 = n1.id;
        this.id2 = n2.id;
        this.hash = id1 * 71 + id2;
    }

    @Override
    public boolean equals(Object o) {
        NodePairKeyTuple that = (NodePairKeyTuple) o;
        return this.id1 == that.id1 && this.id2 == that.id2;
    }

    @Override
    public int hashCode() {
        return hash;
    }
}