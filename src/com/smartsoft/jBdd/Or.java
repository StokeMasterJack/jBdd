package com.smartsoft.jBdd;

import javax.annotation.Nonnull;

public class Or extends BoolOpSet {

    public Or(BddSpace space) {
        super(space);
    }

    @Nonnull public Node apply(@Nonnull final Node f, @Nonnull final Node g) {

        if (f == ONE || g == ONE) {
            return ONE;
        } else if (f == ZERO && g == ZERO) {
            return ZERO;
        } else if (f == g) {
            return f;
        } else if (f == ZERO) {
            return g;
        } else if (g == ZERO) {
            return f;
        } else {
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