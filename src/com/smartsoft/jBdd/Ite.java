package com.smartsoft.jBdd;

import javax.annotation.Nonnull;

public class Ite extends TernaryBoolOp {

    public Ite(@Nonnull BddSpace space, int initialSizeOfComputedTable) {
        super(space, initialSizeOfComputedTable);
    }

    public Ite(@Nonnull BddSpace space) {
        super(space);
    }


    @Override protected Node apply(Node f, Node g, Node h) {
        return iteRecurse(f, g, h);
    }

    protected Node iteRecurse(Node f, Node g, Node h) {
        assert f!=null;
        assert g!=null;
        assert h!=null;

        if (f.isOne()) return g;
        if (f.isZero()) return h;
        if (g == h) return g;

        if (g.isOne() && h.isZero()) return f;
        if (g.isZero() && h.isOne())
            return space.not.apply(f);

        int v = Math.min(g.var, h.var);
        if (f.var < v) {
            return iteRecurse(f.lo, g, h);
        }

        Node r = getComputedResult(f, g, h);

        if (r != null) return r;


        if (f.var == g.var) {
            if (f.var == h.var) {
                Node e = iteRecurse(f.lo, g.lo, h.lo);
                Node t = iteRecurse(f.hi, g.hi, h.hi);
                r = getNode(f.var, t, e);
            } else if (f.var < h.var) {
                Node e = iteRecurse(f.lo, g.lo, h);
                Node t = iteRecurse(f.hi, g.hi, ZERO);
                r = getNode(f.var, t, e);
            } else /* f > h */ {
                Node t = iteRecurse(f, g, h.lo);
                r = getNode(h.var, t, h.hi);
            }
        } else if (f.var < g.var) {
            if (f.var == h.var) {
                Node e = iteRecurse(f.lo, g, h.lo);
                Node t = iteRecurse(f.hi, ZERO, h.hi);
                r = getNode(f.var, t, e);
            } else if (f.var < h.var) {
                r = iteRecurse(f.lo, g, h);
            } else /* f > h */ {
                Node e = iteRecurse(f, g, h.lo);
                r = getNode(h.var, e, h.hi);
            }
        } else /* f > g */ {
            if (g.var == h.var) {
                Node t = iteRecurse(f, g.lo, h.lo);
                r = getNode(g.var, t, h.hi);
            } else if (g.var < h.var) {
                Node t = iteRecurse(f, g.lo, h);
                r = getNode(g.var, t, ZERO);
            } else /* g > h */ {
                Node t = iteRecurse(f, g, h.lo);
                r = getNode(h.var, t, h.hi);
            }
        }

        putComputedResult(new NodeTripleKey(f, g, h), r);

        return r;


    }

}