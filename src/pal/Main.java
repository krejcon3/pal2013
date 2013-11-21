package pal;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static int[] NMQ = new int[3];
    public static int[] BCTUVW = new int[6];



    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("file.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        StringTokenizer st;
        int charCounter = 0;
        if ((line = br.readLine()) != null) {
            st = new StringTokenizer(line);
            while (st.hasMoreTokens()) {
                NMQ[charCounter++] = Integer.parseInt(st.nextToken());
            }
        }
        int lineCounter = 0;
        while ((line = br.readLine()) != null) {
            charCounter = 0;
            st = new StringTokenizer(line);
            if (lineCounter < NMQ[0]) {
                while (st.hasMoreTokens()) {
                    NMQ[charCounter++] = Integer.parseInt(st.nextToken());
                }
            }
        }
        System.out.println("OUTPUT");
    }
}
