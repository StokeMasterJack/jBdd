package com.smartsoft.jBdd;


import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public abstract class TernaryBoolOp extends BoolOp<NodeTripleKey> {

    protected TernaryBoolOp(@Nonnull BddSpace space, int initialSizeOfComputedTable) {
        super(space, initialSizeOfComputedTable);
    }

    protected TernaryBoolOp(@Nonnull BddSpace space) {
        super(space);
    }

    @Nullable
    protected Node getComputedResult(Node f, Node g, Node h) {
        NodeTripleKey key = new NodeTripleKey(f, g, h);
        return getComputedResult(key);
    }

    protected void putComputedResult(@Nonnull Node f, @Nonnull Node g, @Nonnull Node h, @Nonnull Node computedResult) {
        NodeTripleKey key = new NodeTripleKey(f, g, h);
        putComputedResult(key, computedResult);
    }

    @Nonnull
    abstract protected Node apply(Node f, Node g, Node h);

    protected int getTopVar(Node f, Node g, Node h) {
        return getTopNode(f, g, h).var;
    }

    protected Node getTopNode(Node f, Node g, Node h) {
        if (f.var <= g.var && f.var <= h.var) return f;
        if (g.var <= f.var && g.var <= h.var) return g;
        if (h.var <= f.var && h.var <= g.var) return h;
        throw new IllegalStateException();
    }

    public boolean isAllLeaf(Node f, Node g,Node h){
        return f.isLeaf() && g.isLeaf() && h.isLeaf();
    }


}