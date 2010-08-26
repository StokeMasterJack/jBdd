package com.smartsoft.jBdd;


import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public abstract class BinaryBoolOp<KeyType extends NodePairKey> extends BoolOp<KeyType> {

    protected BinaryBoolOp(@Nonnull BddSpace space, int initSize) {
        super(space, initSize);
    }

    @Nullable
    protected Node getComputedResult(Node n1, Node n2) {
        KeyType key = createNodePairKey(n1, n2);
        return getComputedResult(key);
    }

    protected void putComputedResult(@Nonnull Node n1, @Nonnull Node n2, @Nonnull Node computedResult) {
        KeyType key = createNodePairKey(n1, n2);
        putComputedResult(key, computedResult);
    }

    @Nonnull
    abstract protected KeyType createNodePairKey(@Nonnull Node n1, @Nonnull Node n2);


    @Nonnull
    abstract protected Node apply(Node f, Node g);


    public Node applyRecurse(Node p, Node q) {

        int x = getTopVar(p, q);

        Node pt = cofactor1(p, x);
        Node pe = cofactor0(p, x);

        Node qt = cofactor1(q, x);
        Node qe = cofactor0(q, x);

        Node rt = apply(pt, qt);
        Node re = apply(pe, qe);

        Node retVal = getNode(x, rt, re);

        return retVal;


    }

    public Node computeNode(Node l, Node r) {
        int topVar;
        Node t;
        Node e;
        if (l.var == r.var) {
            topVar = l.var;
            e = apply(l.lo, r.lo);
            t = apply(l.hi, r.hi);

        } else if (l.var < r.var) {
            topVar = l.var;
            e = apply(l.lo, r);
            t = apply(l.hi, r);
        } else /* f.var > g.var */ {
            topVar = r.var;
            e = apply(l, r.lo);
            t = apply(l, r.hi);
        }

        return uniqueTable.getOrCreateNode(topVar, e, t);
    }

    public Node cofactor0(Node p, int var) {
        if (var < p.var) return p;
        else if (var == p.var) {
            return p.lo;
        } else {
            throw new IllegalStateException();
        }
    }

    public Node cofactor1(Node p, int var) {
        if (var < p.var) return p;
        else if (var == p.var) {
            return p.hi;
        } else {
            throw new IllegalStateException();
        }
    }

    protected int getTopVar(Node g, Node h) {
        return getTopNode(g, h).var;
    }

    protected Node getTopNode(Node p, Node q) {
        if (p.var <= q.var) return p;
        else return q;
    }

    public boolean isAllLeaf(Node f, Node g) {
        return f.isLeaf() && g.isLeaf();
    }


}