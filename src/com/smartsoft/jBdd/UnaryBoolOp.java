package com.smartsoft.jBdd;


import javax.annotation.Nonnull;

public abstract class UnaryBoolOp extends BoolOp<Node> {

    protected UnaryBoolOp(@Nonnull BddSpace space, int initialSizeOfComputedTable) {
        super(space, initialSizeOfComputedTable);
    }

    protected UnaryBoolOp(@Nonnull BddSpace space) {
        super(space);
    }

    @Nonnull
    abstract public Node apply(Node f);


    public int getComputedTableSize() {
        return computedTable.size();
    }

    public boolean isAllLeaf(Node f){
        return f.isLeaf();
    }


}