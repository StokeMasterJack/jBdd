package com.smartsoft.jBdd;

public class ZDiff extends BoolOpTuple {

    public ZDiff(BddSpace space) {
        super(space);
    }

    public Node apply(Node l, Node r) {
        System.out.println("ZDiff.apply(..)");
        if (l.isZero() || l == r)
            return ZERO;
        if (r.isZero())
            return l;
        if (l.var > r.var)
            return apply(l, r.lo);


        NodePairKeyTuple key = new NodePairKeyTuple(l, r);
        Node ret = null;
        ret = getComputedResult(key);
        if (ret != null) return ret;

        if (l.var == r.var) {
            Node e = apply(l.lo, r.lo);
            Node t = apply(l.hi, r.hi);
            ret = getNode(l.var, t, e);
        } else {
            Node e = apply(l.lo, r);
            ret = getNode(l.var, l.hi, e);
        }

        putComputedResult(key, ret);

        return ret;

    }

}