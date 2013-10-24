package pal;

import java.io.*;

public class Main {
    public static int [] nm = null;
    public static String[] start = null;
    public static String[] end = null;
    public static Permutation permutation;

    public static void main(String[] args) throws IOException {
//        System.setIn(new FileInputStream("file.txt"));
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
        permutation.printDiffFinal(nm);
    }
}


