package pal;

import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

    public static HashMap<Integer, double[]> edges = new HashMap<Integer, double[]>();
    public static int vertexCount = -1;
    public static int edgeCount = 0;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("file.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        StringTokenizer st;
        if ((line = br.readLine()) != null) {
            st = new StringTokenizer(line);
            vertexCount = Integer.parseInt(st.nextToken());
        }
        int start, end;
        double[] edge;
        while (!(line = br.readLine()).equals("0 0")) {
            st = new StringTokenizer(line);
            edge = new double[vertexCount - 1];
            start = Integer.parseInt(st.nextToken());
            end = Integer.parseInt(st.nextToken());
            if (start < edge.length) {
                edge[start] = 1;
            }
            if (end < edge.length) {
                edge[end] = -1;
            }
            edges.put(edgeCount++, edge);
        }
        calc();
    }

    public static void calc() {
        int sum = 0;
        char[] ary;
        double[][] m = new double[vertexCount - 1][];
        int counter;
        int from = 0;
        int to = (int) Math.pow(2, edgeCount);
        for (int i = from; i < to; i++) {
            if (Integer.bitCount(i) == vertexCount - 1) {
                counter = 0;
                ary = Integer.toBinaryString(i).toCharArray();
                for (int j = 0; j < ary.length; j++) {
                    if (ary[j] == '1') {
                        m[counter++] = edges.get(((edgeCount) - ary.length) + j);
                    }
                }
                if (isTree(m)) {
                    sum++;
                }
            }
        }
        System.out.println(sum);
    }

    public static boolean isTree(double[][] mat) {
        return ((int)Math.round(determinant(mat))) != 0;
    }

    public static double determinant(double[][] mat) {
        double result = 0;
        if(mat.length == 1) {
            result = mat[0][0];
            return result;
        }
        if(mat.length == 2) {
            result = mat[0][0] * mat[1][1] - mat[0][1] * mat[1][0];
            return result;
        }
        for(int i = 0; i < mat[0].length; i++) {
            double temp[][] = new double[mat.length - 1][mat[0].length - 1];
            for(int j = 1; j < mat.length; j++) {
                System.arraycopy(mat[j], 0, temp[j-1], 0, i);
                System.arraycopy(mat[j], i+1, temp[j-1], i, mat[0].length-i-1);
            }
            result += mat[0][i] * Math.pow(-1, i) * determinant(temp);
        }
        return result;
    }
}
