package pal;

import java.io.*;

public class Main {
    public static int [] nm = null;
    public static String[] start = null;
    public static String[] end = null;
    public static Permutation permutation;
    public static BinomialHeap heap;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = br.readLine()) != null) {
            if (nm == null) {
                nm = new int[2];
                String[] l = line.split("\\s");
                nm[0] = Integer.parseInt(l[0]);
                nm[1] = Integer.parseInt(l[1]);
            } else {
                if (start == null) {
                    start = line.split("\\s");
                } else {
                    end = line.split("\\s");
                }
            }
        }
        permutation = new Permutation(start, end, nm[1]);
        int[] p;
        int maxDiff = 0;
        int tDiff;
        int[] maxPerm = new int[0];
        while ((p = permutation.getNextExtendedPermutation(nm[0])) != null) {
            heap = new BinomialHeap();
            for(int n : p) {
                heap.add(n);
            }
            tDiff = heap.getDiff();
            if (tDiff > maxDiff) {
                maxDiff = tDiff;
                maxPerm = p;
            }
        }
        System.out.println(maxDiff);
        for (int i = 0; i < nm[1]; i++) {
            System.out.print(maxPerm[i] + " ");
        }
        System.out.println();
    }
}


