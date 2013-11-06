package pal;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    public static ArrayList<int[]> edges;
    public static int[] parentMap;
    public static int vertexCount = -1;
    public static int edgeCount = 0;
    public static int[] combination;
    public static int co = 0;

    public static void main(String[] args) throws IOException {
//        System.setIn(new FileInputStream("file.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        StringTokenizer st;
        if ((line = br.readLine()) != null) {
            st = new StringTokenizer(line);
            vertexCount = Integer.parseInt(st.nextToken());
            edges = new ArrayList<int[]>(vertexCount * 2);
            combination = new int[vertexCount - 1];
            for (int i = 0; i < vertexCount - 1; i++) {
                combination[i] = i;
            }
        }
        int[] edge;
        while (!(line = br.readLine()).equals("0 0")) {
            edge = new int[2];
            st = new StringTokenizer(line);
            edge[0] = Integer.parseInt(st.nextToken());
            edge[1] = Integer.parseInt(st.nextToken());
            edges.add(edge);
            edgeCount++;
        }
        int x;
        parentMap = new int[vertexCount];
        for (x = 0; x < combination.length; x++) {
            edge = edges.get(x);
            if (!union(edge[0], edge[1])) {
                break;
            }
        }
        if (x == combination.length) {
            co++;
        }
        while (combination != null) {
            combination = KSubsetLexSuccessor(combination, edgeCount, vertexCount - 1);
        }
        System.out.println(co);
    }

    public static boolean union(int a, int b) {
        int rA = find(a);
        int rB = find(b);
        if (rA != rB) {
            parentMap[rA] = rB + 1;
            return true;
        }
        return false;
    }

    public static int find(int i) {
        if (parentMap[i] > 0) {
            int n;
            n = parentMap[i] - 1;
            while (parentMap[n] > 0) {
                n = parentMap[n] - 1;
            }
            return n;
        } else {
            return i;
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
            int[] edge;
            boolean cor = true;
            parentMap = new int[vertexCount];
            for (int j = 0; j < i; j++) {
                edge = edges.get(U[j]);
                if (!union(edge[0], edge[1])) {
                    cor = false;
                    break;
                }
            }
            for (int j = i; j < k; j++) {
                if (j >= i) {
                    U[j] = T[i] + 1 + j - i;
                }
                if (cor) {
                    edge = edges.get(U[j]);
                    if (!union(edge[0], edge[1])) {
                        cor = false;
                    }
                }
            }
            if (cor) {
                co++;
            }
            return U;
        }
    }
}
