package com.smartsoft.jBdd;

import javax.annotation.Nonnull;

abstract public class BoolOpTuple extends BinaryBoolOp<NodePairKeyTuple> {

    protected BoolOpTuple(@Nonnull BddSpace space, int initialSizeOfComputedTable) {
        super(space, initialSizeOfComputedTable);
    }

    protected BoolOpTuple(@Nonnull BddSpace space) {
        super(space, 1000);
    }

    @Override protected NodePairKeyTuple createNodePairKey(@Nonnull Node n1, @Nonnull Node n2) {
        return new NodePairKeyTuple(n1, n2);
    }
    
}
