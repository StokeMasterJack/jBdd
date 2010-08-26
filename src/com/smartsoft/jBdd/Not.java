package com.smartsoft.jBdd;


import javax.annotation.Nonnull;

public class Not extends UnaryBoolOp {

    public Not(@Nonnull BddSpace space, int initialSizeOfComputedTable) {
        super(space, initialSizeOfComputedTable);
    }

    public Not(@Nonnull BddSpace space) {
        super(space);
    }

    @Override public Node apply(Node r) {
        
        if (r == ZERO) {
            return ONE;
        } else if (r == ONE) {
            return ZERO;
        } else {

            
            Node result;
//            result = getComputedResult(r);
//            if (result != null) return result;


            Node e = apply(r.lo);
            Node t = apply(r.hi);

            result = getNode(r.var,e,t);

//            putComputedResult(r, result);

            return result;


        }

    }

}