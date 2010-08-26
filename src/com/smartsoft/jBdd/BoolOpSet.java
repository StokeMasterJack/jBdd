package com.smartsoft.jBdd;

import javax.annotation.Nonnull;

abstract public class BoolOpSet extends BinaryBoolOp<NodePairKeySet> {


    protected BoolOpSet(@Nonnull BddSpace space, int initialSizeOfComputedTable) {
        super(space, initialSizeOfComputedTable);
    }

    protected BoolOpSet(@Nonnull BddSpace space) {
        super(space, 1000);
    }

    @Nonnull
    protected NodePairKeySet createNodePairKey(Node n1, @Nonnull Node n2) {
        return new NodePairKeySet(n1, n2);
    }

    


}
