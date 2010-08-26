package com.smartsoft.jBdd;

import javax.annotation.Nonnull;

public class Nand extends BoolOpSet {

    public Nand(BddSpace space) {
        super(space);
    }

    @Override public Node apply(@Nonnull final Node f, @Nonnull final Node g) {
        assert f != null;
        assert g != null;
        if (f == ZERO || g == ZERO) {
            return ONE;
        }
        else if(f == ONE && g == ONE){
            return ZERO;
        }
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