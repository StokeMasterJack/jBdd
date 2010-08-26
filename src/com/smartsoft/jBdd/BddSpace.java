package com.smartsoft.jBdd;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class BddSpace {

    private final UniqueTable uniqueTable;

    private final Node ONE;
    private final Node ZERO;

    public final And and;
    public final Iff iff;
    public final Imp imp;
    public final Nand nand;
    public final Or or;
    public final Ite ite;
    public final ZDiff zDiff;
    public final Not not;

    public BddSpace(int varCount) {
        this.uniqueTable = new UniqueTable(varCount);
        this.ONE = uniqueTable.getOne();
        this.ZERO = uniqueTable.getZero();

        this.and = new And(this);
        this.iff = new Iff(this);
        this.imp = new Imp(this);
        this.nand = new Nand(this);
        this.or = new Or(this);
        this.ite = new Ite(this);
        this.zDiff = new ZDiff(this);
        this.not = new Not(this);
    }

    public Node getOne() {
        return ONE;
    }

    public Node getZero() {
        return ZERO;
    }


    public UniqueTable getUniqueTable() {
        return uniqueTable;
    }

    public Map<Integer, Boolean> anySat(Node u, int depth) {
        depth++;
//        System.out.println("anySatCall[ Depth[" + depth + "] u[" + u + "]  ]");
        if (u.isZero()) {
            throw new IllegalArgumentException();
        } else if (u.isOne()) {
            return new HashMap<Integer, Boolean>();
        } else if (u.lo.isZero()) {
            final Map<Integer, Boolean> map = anySat(u.hi, depth);
            map.put(u.var, true);
            return map;
        } else {
            final Map<Integer, Boolean> map = anySat(u.lo, depth);
            map.put(u.var, false);
            return map;
        }
    }

    public Map<Integer, Boolean> anySat(Node u) {
        return anySat(u, 0);
    }

    public long satCount(Node u) {
        assert u != null;
        return (long) Math.pow(2, u.var - 1) * count(u);
    }

    private long count(Node u) {
        if (u.isZero()) {
            return 0;
        } else if (u.isOne()) {
            return 1;
        } else {
            long x1 = (long) (Math.pow(2.0, (double) (u.lo.var - u.var - 1)));
            long x2 = count(u.lo);
            long x3 = (long) (Math.pow(2.0, (double) (u.hi.var - u.var - 1)));
            long x4 = count(u.hi);
            return x1 * x2 + x3 * x4;
        }
    }

    public Node ithVar(int i) {
        return uniqueTable.getOrCreateNode(i, ZERO, ONE);
    }


    public Set<Map<Integer, Boolean>> allSat(Node u) {
        if (u.isZero()) {
            return new HashSet<Map<Integer, Boolean>>();
        } else if (u.isOne()) {
            final HashSet<Map<Integer, Boolean>> set = new HashSet<Map<Integer, Boolean>>();
            set.add(new HashMap<Integer, Boolean>());
            return set;
        } else {
            final Set<Map<Integer, Boolean>> mapSetLo = allSat(u.lo);
            for (Map<Integer, Boolean> map : mapSetLo) {
                map.put(u.var, false);
            }
            final Set<Map<Integer, Boolean>> mapSetHi = allSat(u.hi);
            for (Map<Integer, Boolean> map : mapSetHi) {
                map.put(u.var, true);
            }
            mapSetLo.addAll(mapSetHi);
            return mapSetLo;
        }

    }

    public int getVarCount() {
        return uniqueTable.getVarCount();
    }

    public int getNodeCount() {
        return uniqueTable.getNodeCount();
    }

    public void printComputedTablesSize() {
        System.out.println("and: " + and.getComputedTableSize());
        System.out.println("or: " + or.getComputedTableSize());
        System.out.println("nand: " + nand.getComputedTableSize());
        System.out.println("iff: " + iff.getComputedTableSize());
        System.out.println("imp: " + imp.getComputedTableSize());
        System.out.println();
    }

    public void printUniqueTableSizes() throws Exception {
        uniqueTable.printTableSizes();
    }

    public void print() {
        uniqueTable.print();
    }


    public boolean isCompliment(Node f, Node g) {
        return not.apply(f) == g;
    }

    public Bdd varBdd(int varIndex) {
        return new Bdd(this, this.ithVar(varIndex));
    }


}
