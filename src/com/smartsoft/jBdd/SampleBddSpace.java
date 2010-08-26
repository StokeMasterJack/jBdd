package com.smartsoft.jBdd;

public class SampleBddSpace extends BddSpace {

    private Node masterNode;

    public SampleBddSpace() {
        super(5);
        Node root = ithVar(1);

        Node se = ithVar(2);
        Node le = ithVar(3);
        Node v6 = ithVar(4);
        Node v8 = ithVar(5);

        Node r1 = imp.apply(se, root);
        Node r2 = imp.apply(le, root);
        Node r3 = imp.apply(v6, root);
        Node r4 = imp.apply(v8, root);


        Node c1 = nand.apply(se, le);
        Node c2 = or.apply(se, le);
        Node c3 = nand.apply(v6, v8);
        Node c4 = or.apply(v6, v8);


        masterNode = imp.apply(getOne(), root);

        masterNode = and.apply(masterNode, r1);
        masterNode = and.apply(masterNode, r2);
        masterNode = and.apply(masterNode, r3);
        masterNode = and.apply(masterNode, r4);

        masterNode = and.apply(masterNode, c1);
        masterNode = and.apply(masterNode, c2);
        masterNode = and.apply(masterNode, c3);
        masterNode = and.apply(masterNode, c4);

    }

    public Node getMasterNode() {
        return masterNode;
    }
}
