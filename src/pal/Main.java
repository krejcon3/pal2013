package pal;

import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

    public static HashMap<Integer, int[]> edges = new HashMap<Integer, int[]>();
    public static int vertexCount = -1;
    public static int edgeCount = 0;
    public static int[] kombination;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("file.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        StringTokenizer st;
        if ((line = br.readLine()) != null) {
            st = new StringTokenizer(line);
            vertexCount = Integer.parseInt(st.nextToken());
            kombination = new int[vertexCount - 1];
            for (int i = 0; i < vertexCount - 1; i++) {
                kombination[i] = i;
            }
        }
        int[] edge;
        while (!(line = br.readLine()).equals("0 0")) {
            edge = new int[2];
            st = new StringTokenizer(line);
            edge[0] = Integer.parseInt(st.nextToken());
            edge[1] = Integer.parseInt(st.nextToken());
            edges.put(edgeCount++, edge);
        }
        while (kombination != null) {
            print(kombination);
            kombination = KSubsetLexSuccessor(kombination, edgeCount, vertexCount - 1);
        }
    }

    public static int[] KSubsetLexSuccessor(int[] T, int n, int k) {
        int[] U = T.clone();
        int i = k - 1;
        while (i >= 0 && T[i] >= n - k + i) {
            i--;
        }
        if (i < 0) {
            return null;
        } else {
            for (int j = i; j < k; j++) {
                U[j] = T[i] + 1 + j - i;
            }
            return U;
        }
    }

    public static void print(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}
