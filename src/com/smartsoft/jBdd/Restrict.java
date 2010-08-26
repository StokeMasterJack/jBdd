package com.smartsoft.jBdd;

import javax.annotation.Nonnull;

public final class Restrict extends BoolOpSet {

    private static final int initialCapacity = 131072;      //2^17
    private AndComputedTable andComputedTable = new AndComputedTable(initialCapacity);

    public Restrict(BddSpace space) {
        super(space, -1);
    }

    @Nonnull public Node apply(@Nonnull final Node l, @Nonnull final Node r) {
        if (l == ZERO) {
            return ZERO;
        } else if (r == ONE) {
            return l;
        } else if (r == ZERO) {
            return ZERO;
        } else if (l == r) {
            return l;
        } else if (l == ONE) {
            return r;
        } else {
            Node result;

            int id1 = l.id;
            int id2 = r.id;

            if (id1 > id2) {
                id1 = r.id;
                id2 = l.id;
            }

            int hash = Node.calcHash(id1, id2);
            result = andComputedTable.get(hash, id1, id2);

            if (result != null) return result;

            if (l.var < r.var) {
                Node n1 = apply(l.lo, r);
                Node n2 = apply(l.hi, r);
                result = getNode(l.var, n1, n2);
            } else if (l.var == r.var) {
                Node nLo = apply(l.lo, r.lo);
                Node nHi = apply(l.hi, r.hi);
                result = getNode(l.var, nLo, nHi);
            } else {
                Node n1 = apply(l, r.lo);
                Node n2 = apply(l, r.hi);
                result = getNode(r.var, n1, n2);
            }

            andComputedTable.put(hash, id1, id2, result);

            return result;
        }

    }


    public int getComputedTableSize() {
        return andComputedTable.size();
    }

    public void print() {
        System.out.println("DF:computedTable.size() = [" + getComputedTableSize() + "]");
    }


}