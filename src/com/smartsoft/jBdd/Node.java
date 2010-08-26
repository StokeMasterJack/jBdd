package com.smartsoft.jBdd;

import java.util.Map;

public final class Node {

    public final int id;
    public final int var;
    public final Node lo;
    public final Node hi;

    public final int hash;  //hash of pair(lo.id,hi.id)

    public Node next;

    public Node(int id, int var, Node lo, Node hi,int hash) {
        this.id = id;
        this.var = var;
        this.lo = lo;
        this.hi = hi;
        this.hash = hash;
    }

    

    public boolean isVar() {
        if (lo == null) return false;
        if (hi == null) return false;
        return lo.isZero() && hi.isOne();
    }

    public boolean isOne() {
        return id == 1;
    }

    public boolean isZero() {
        return id == 0;
    }


    public String getType() {
        if (id == 0) return "ZERO";
        if (id == 1) return "ONE";
        if (isVar()) return "VAR";
        return "INTERNAL";
    }

    public boolean isInternal() {
        return lo != null;
    }

    public boolean isLeaf() {
        return id == 0 || id == 1;
    }

    public int getNodeCount() {
        if (isLeaf()) return 1;
        else return 1 + lo.getNodeCount() + hi.getNodeCount();
    }


    public String toString() {
        return toString(null);
    }

    public String toString(Map<Integer, String> varNames) {
        String retVal = "N[" + id + "]";
        if (id == 0) {
            retVal += ":ZERO";
        } else if (id == 1) {
            retVal += ":ONE";
        } else {
            if (isVar()) {
                retVal += ":V";
            } else {
                retVal += ":I";
            }
            String varName = varNames == null ? "" : ":" + varNames.get(var);
            retVal += "  var[" + var + varName + "]";

            String sLo;
            if (this.lo == null) {
                sLo = "";
            } else {
                sLo = "  lo:" + lo.id;
            }

            String sHi;
            if (this.hi == null) {
                sHi = "";
            } else {
                sHi = "  hi:" + hi.id;
            }

            retVal += sLo + sHi;
        }
        return retVal;
    }


    public static String getIndent(int depth) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < depth; i++) {
            sb.append("\t");
        }
        return sb.toString();
    }



    @Override public int hashCode() {
        return id;
    }

    @Override public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public static int calcHash(int nodeId1, int nodeId2) {
        int hashDave = nodeId1 * 71 + nodeId2;

        // This function ensures that hashCodes that differ only by
        // constant multiples at each bit position have a bounded
        // number of collisions (approximately 8 at default load factor).
        hashDave ^= (hashDave >>> 20) ^ (hashDave >>> 12);
        return hashDave ^ (hashDave >>> 7) ^ (hashDave >>> 4);
    }

    /**
     * equals and hashCode Intentionally not overridden
     */
}
