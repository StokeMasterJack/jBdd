package com.smartsoft.jBdd;

import javax.annotation.Nonnull;

public final class Iff extends BoolOpSet {

    public Iff(BddSpace space) {
        super(space);
    }

    @Nonnull public Node apply(@Nonnull final Node f, @Nonnull final Node g) {
        //terminal conditions
        if (f.isOne() && g.isOne()) return ONE;
        else if (f.isOne() && g.isZero()) return ZERO;
        else if (f.isZero() && g.isOne()) return ZERO;
        else if (f.isZero() && g.isZero()) return ONE;
        else {
            NodePairKeySet key = new NodePairKeySet(f, g);
            Node r = getComputedResult(key);
            if (r == null) {
                r = computeNode(f, g);
                putComputedResult(key, r);
            }
            return r;
        }

    }


}