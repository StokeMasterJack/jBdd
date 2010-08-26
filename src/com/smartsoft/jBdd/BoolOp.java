package com.smartsoft.jBdd;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashMap;

import static com.smartsoft.jBdd.util.lang.Strings.getSimpleName;

public abstract class BoolOp<NodeKeyType> {

    @Nonnull protected final BddSpace space;
    @Nonnull protected final Node ONE;
    @Nonnull protected final Node ZERO;
    @Nonnull protected final UniqueTable uniqueTable;

    protected final HashMap<NodeKeyType, Node> computedTable;

    protected BoolOp(@Nonnull BddSpace space, int initialSizeOfComputedTable) {
        assert space != null;

        this.space = space;
        this.ZERO = space.getZero();
        this.ONE = space.getOne();
        this.uniqueTable = space.getUniqueTable();

        assert ZERO != null;
        assert ONE != null;

        if (initialSizeOfComputedTable != -1) {
            this.computedTable = new HashMap<NodeKeyType, Node>(initialSizeOfComputedTable);
        } else {
            this.computedTable = null;
        }


    }

    protected BoolOp(@Nonnull BddSpace space) {
        this(space, 1000);
    }


    @Override public String toString() {
        return " " + getSimpleName(this) + " ";
    }

    @Nullable
    protected Node getComputedResult(@Nonnull NodeKeyType key) {
        return computedTable.get(key);
    }

    protected void putComputedResult(@Nonnull NodeKeyType key, @Nonnull Node computedResult) {
        computedTable.put(key, computedResult);
    }

    public int getComputedTableSize() {
        return computedTable.size();
    }


    protected Node getNode(int var, Node lo, Node hi) {
        return uniqueTable.getOrCreateNode(var, lo, hi);
    }
}
