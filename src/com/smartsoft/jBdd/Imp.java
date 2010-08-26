package com.smartsoft.jBdd;

import javax.annotation.Nonnull;

public class Imp extends BoolOpTuple {

    public Imp(BddSpace space) {
        super(space);
    }

    @Nonnull public Node apply(@Nonnull final Node l, @Nonnull final Node r) {
        //terminal conditions
        if (l == ZERO) {
            return ONE;
        } else if (l == ONE) {
            return r;
        } else if (r == ONE) {
            return ONE;
        } else {
            NodePairKeyTuple key = new NodePairKeyTuple(l, r);
            Node result = getComputedResult(key);
            if (result == null) {
                result = computeNode(l, r);
                putComputedResult(key, result);
            }
            return result;
        }

    }


}