package com.smartsoft.jBdd;

import javax.annotation.Nonnull;


public final class UniqueTable {

    private final VarUniqueTable[] uniqueTables;

    private static final int FIRST_VAR_INDEX = 1;

    private final Node ZERO;
    private final Node ONE;

    private final int varCount;

    private int nextNodeId;

    public UniqueTable(int varCount) {
        this.varCount = varCount;
        uniqueTables = new VarUniqueTable[varCount + 1];

        for (int i = FIRST_VAR_INDEX; i <= varCount; i++) {
            uniqueTables[i] = new VarUniqueTable(i);
        }

        ZERO = new Node(0, varCount + 1, null, null, -1);
        ONE = new Node(1, varCount + 1, null, null, -1);
        nextNodeId = 2;

    }

    public Node getZero() {
        return ZERO;
    }

    public Node getOne() {
        return ONE;
    }


    public Node getOrCreateNode(int var, Node lo, Node hi) {
        if (lo == hi) {
            return lo;
        }
        return uniqueTables[var].getOrCreateNode(lo, hi);
    }


    public int getVarCount() {
        return varCount;
    }

    private class VarUniqueTable {

        public final int var;
        private final UniqueTableHashMap map = new UniqueTableHashMap();

        public VarUniqueTable(int var) {
            this.var = var;
        }

        public Node getOrCreateNode(@Nonnull Node lo, @Nonnull Node hi) {
            int id1 = lo.id;
            int id2 = hi.id;

            int hash = Node.calcHash(id1, id2);
            Node node = map.get(hash, id1, id2);
            if (node == null) {
                node = new Node(nextNodeId, var, lo, hi, hash);
                nextNodeId++;
                map.put(node);
            }
            return node;
        }

        public int getNodeCount() {
            return map.size();
        }

    }

    public void printTableSizes() {
        int sumOfTableCounts = 0;
        for (VarUniqueTable table : uniqueTables) {
            if (table == null) continue;
            System.out.println(table.var + ": [" + table.getNodeCount() + "]");
            sumOfTableCounts += table.getNodeCount();
        }
        System.out.println("sumOfTableCounts = [" + sumOfTableCounts + "]");

    }

    public int getNodeCount() {
        return nextNodeId;
    }

    public void print()  {
        System.out.println("getNodeCount() = [" + getNodeCount() + "]");
    }
}
