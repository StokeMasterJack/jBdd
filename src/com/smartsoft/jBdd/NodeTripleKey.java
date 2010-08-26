package com.smartsoft.jBdd;

public final class NodeTripleKey implements NodePairKey {

    private final int idF;
    private final int idG;
    private final int idH;

    public NodeTripleKey(Node f, Node g, Node h) {
        this.idF = f.id;
        this.idG = g.id;
        this.idH = h.id;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NodeTripleKey triple = (NodeTripleKey) o;

        if (idF != triple.idF) return false;
        if (idG != triple.idG) return false;
        if (idH != triple.idH) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idF;
        result = 31 * result + idG;
        result = 31 * result + idH;
        return result;
    }
}