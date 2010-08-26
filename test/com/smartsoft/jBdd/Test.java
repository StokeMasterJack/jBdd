package com.smartsoft.jBdd;

import junit.framework.TestCase;

import java.util.Map;
import java.util.Set;

public class Test extends TestCase {

    public void testAnd() throws Exception {
        BddSpace m = new BddSpace(4);

        Node T = m.getOne();
        Node F = m.getZero();

        assertEquals(1, T.getNodeCount());
        assertEquals(1, F.getNodeCount());

        assertTrue(m.and.apply(T, T).isOne());
        assertTrue(m.and.apply(F, T).isZero());
        assertTrue(m.and.apply(T, F).isZero());
        assertTrue(m.and.apply(F, F).isZero());

        assertEquals(2, m.getNodeCount());

        Node a = m.ithVar(1);
        Node b = m.ithVar(2);
        Node c = m.ithVar(3);
        Node d = m.ithVar(4);

        Node master = a;

        Node r1 = m.imp.apply(b, a);
        Node r2 = m.imp.apply(c, a);
        Node r3 = m.imp.apply(d, c);

        master = m.and.apply(master, r1);
        master = m.and.apply(master, r2);
        master = m.and.apply(master, r3);

        Node notB = m.not.apply(b);

        master = m.and.apply(master, notB);

        System.out.println(master.isZero());

        System.out.println(m.satCount(master));

        System.out.println(m.allSat(master));

    }

    public void test() throws Exception {
        SampleBddSpace space = new SampleBddSpace();
        Node master = space.getMasterNode();

        assertEquals(4, space.satCount(master));

        System.out.println("AnySat: ");
        Map<Integer, Boolean> product = space.anySat(master);
        System.out.println("\t" + product);

        System.out.println();
        System.out.println("AllSat: ");
        final Set<Map<Integer, Boolean>> products = space.allSat(master);
        for (Map<Integer, Boolean> p : products) {
            System.out.println("\t" + p);
        }

    }

}
