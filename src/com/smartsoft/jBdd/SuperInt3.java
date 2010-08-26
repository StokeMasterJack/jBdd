package com.smartsoft.jBdd;

/**
 * Integer.MAX: 2147483647
 * getNodeCount() = [54657]
 */
public class SuperInt3 {

    int x;
    public static final int C = 10000;

    public final int getA() {
        return x / C;
    }

    public final int getB() {
        return x % C;
    }

    public final void setAB(int a, int b) {
        x = a * C + b;
    }

    public void print() {
        System.out.println("A = [" + getA() + "]");
        System.out.println("B = [" + getB() + "]");
        System.out.println();
    }

}