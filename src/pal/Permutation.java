package pal;

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

    public boolean more(int[] first, int[] second) {
        for (int i = 0; i < first.length; i++) {
            if (first[i] != second[i]) {
                return first[i] > second[i];
            }
        }
        return true;
    }

    public int[] getNextPermutation() {
        if (this.cycle == 0) {
            this.cycle++;
            return this.startPermutation;
        }
        if (this.more(this.startPermutation, this.endPermutation)) {
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

    public void printDiffFinal(int[] nm) {
        int[] p;
        int maxDiff = 0;
        int tDiff;
        int[] maxPerm = new int[0];
        BinomialHeap heap;
        while ((p = this.getNextPermutation()) != null) {
            heap = new BinomialHeap();
            for(int i = 0; i < nm[0]; i++) {
                heap.add(p[i % p.length] + (i / p.length * p.length));
            }
            tDiff = heap.getDiff();

            if (tDiff > maxDiff) {
                maxDiff = tDiff;
                maxPerm = p.clone();
            }
        }
        System.out.println(maxDiff);
        for (int i = 0; i < nm[1]; i++) {
            System.out.print(maxPerm[i] + " ");
        }
        System.out.println();
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
