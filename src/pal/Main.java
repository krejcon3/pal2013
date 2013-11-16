package pal;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

    public static int[] vertices;
    public static int verticesCount = 0;
    public static int graphCount = 0;
    public static HashMap<Integer, ArrayList<int[]>> combinations;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("file.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        if ((line = br.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(line);
            int num = 0;
            int[] verts;
            if (st.hasMoreTokens()) {
                num = Integer.parseInt(st.nextToken());
                verticesCount = num;
                int maxNum = 0;
                if (num == 0) {
                    System.out.println(0);
                    System.exit(0);
                }
                verts = new int[num];
                int i = 0;
                while (st.hasMoreTokens()) {
                    num = Integer.parseInt(st.nextToken());
                    if (maxNum == 0) {
                        maxNum = num;
                    }
                    if (num == 0) {
                        break;
                    }
                    verts[i++] = num;
                }
                if (i == 0) {
                    System.out.println(0);
                    System.exit(0);
                }
                vertices = new int[i];
                combinations = new HashMap<Integer, ArrayList<int[]>>(3 * maxNum);
                combinations();
                System.arraycopy(verts, 0, vertices, 0, i);
            } else {
                System.out.println(0);
                System.exit(0);
            }
        }
        calc();
    }

    public static void calc() {
        generateGraph();
        System.out.println(graphCount);
    }

    public static void combinations() {
        for (int j: vertices) {
            combinations.put(j, new ArrayList<int[]>());
        }
        int[] pom;
        char[] ar;
        for (int i = 1; i < Math.pow(2, verticesCount - 1) - 1; i++) {
            for (int j: vertices) {
                if (Integer.bitCount(i) == j) {
                    
                }
            }
        }
    }

    public static int[][] generateGraph() {
        int [][] graph = new int[vertices.length][vertices.length];
        for (int i = 0; i < graph.length; i++) {
            for (int j = i; j < graph.length; j++) {
                if (i == j) {
                    graph[i][j] = vertices[i];
                } else {

                    graph[i][j] = 0;
                }
            }
        }
        printMatrix(graph);
        return null;
    }

    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            printArray(matrix[i]);
        }
    }

    public static void printArray(int[] array) {
        for (int i : array) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
