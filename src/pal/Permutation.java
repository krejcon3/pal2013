package pal;

import java.util.Arrays;

public class Permutation {
    private int[] startPermutation;
    private int[] endPermutation;
    private int length;
    private int cycle = 0;

    public Permutation(String[] start, String[] end, int length) {
        this.length = length;
        this.startPermutation = new int[this.length];
        this.endPermutation = new int[this.length];
        for (int i = 0; i < this.length; i++) {
            this.startPermutation[i] = Integer.parseInt(start[i]);
        }
        for (int i = 0; i < this.length; i++) {
            this.endPermutation[i] = Integer.parseInt(end[i]);
        }
    }

    public int[] getNextPermutation() {
        if (this.cycle == 0) {
            this.cycle++;
            return this.startPermutation;
        }
        if (Arrays.equals(this.startPermutation, this.endPermutation)) {
            return null;
        }
        this.cycle++;
        int it = -1;
        for (int i = this.length - 1; i > 0; i--) {
            if (this.startPermutation[i] > this.startPermutation[i - 1]) {
                it = i - 1;
                break;
            }
        }
        if (it == -1) {
            return null;
        }
        for (int i = this.length - 1; i > it; i--) {
            if (this.startPermutation[i] > this.startPermutation[it]) {
                this.startPermutation = this.swap(it, i, this.startPermutation);
                int start = it + 1;
                int end = this.length - 1;
                while (start < end) {
                    this.startPermutation = swap(start++, end--, this.startPermutation);
                }
                return this.startPermutation;
            }
        }
        return null;
    }

    public int[] getNextExtendedPermutation(int epLength) {
        int[] p = this.getNextPermutation();
        if (p == null) {
            return null;
        }
        int[] ep = new int[epLength];
        for (int i = 0; i < ep.length; i++) {
            ep[i] = p[i % p.length] + (i / p.length * p.length) ;
        }
        return ep;
    }

    public int getNextExtendedPermutationNumber(int epLength) {
        int[] p = this.getNextPermutation();
        if (p == null) {
            return -1;
        }
        int ep = 0;
        for (int i = 0; i < epLength; i++) {
            ep += (p[i % p.length] + (i / p.length * p.length)) * Math.pow(10, epLength - 1 - i);
        }
        return ep;
    }

    private int[] swap(int i, int j, int[] array) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
        return array;
    }
}
