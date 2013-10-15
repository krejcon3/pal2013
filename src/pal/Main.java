package pal;

import java.io.*;
import java.util.*;

public class Main {

    public static int nodeCount = -1;
    public static int minLength = -1;
    public static int[] set;
    public static int setCount = -1;
    public static int spineCount = -1;
    public static int edgeCounter = 0;

    public static int[] map;

    public static ArrayList<int[]> linearMatrix = new ArrayList<int[]>();
    public static HashMap<Integer, Node> kruskalMap;
    public static ArrayList<char[]> combinations = new ArrayList<char[]>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        int row = 0;
        int val;
        StringTokenizer st;
        while ((line = br.readLine()) != null) {
            st = new StringTokenizer(line);
            if (nodeCount == -1 && st.hasMoreTokens()) {
                nodeCount = Integer.parseInt(st.nextToken());
                continue;
            }
            if (row < nodeCount) {
                    for (int i = 0; i < row; i++) {
                        if (st.hasMoreTokens()) {
                            val = Integer.parseInt(st.nextToken());
                            if (val != 0) {
                                linearMatrix.add(new int[]{i, row, val});
                            }
                        }
                    }
                row++;
                continue;
            }
            if (set == null) {
                if (st.hasMoreTokens()) {
                    setCount = Integer.parseInt(st.nextToken());
                    set = new int[setCount];
                    map = new int[nodeCount];
                }
                int i = 0;
                while (st.hasMoreTokens()) {
                    set[i] = Integer.parseInt(st.nextToken()) - 1;
                    map[set[i]] = i+1;
                    i++;
                }
                continue;
            }
            if (spineCount == -1 && st.hasMoreTokens()) {
                spineCount = Integer.parseInt(st.nextToken());
                continue;
            }
        }
        Collections.sort(linearMatrix, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[2] > o2[2]) {
                    return 1;
                }
                if (o1[2] == o2[2]) {
                    return 0;
                }
                return -1;
            }
        });
        if (nodeCount == setCount && spineCount == 2) {
            minLength = kruskal(new char[0]);
        } else {
            combinations();
            int midResult = -1;
            for (char[] comb : combinations) {
                midResult = kruskal(comb);
                if (minLength == -1) {
                    minLength = midResult;
                }
                if (minLength > midResult && midResult != -1) {
                    minLength = midResult;
                }
            }
        }
        System.out.println(minLength);
    }

    public static void combinations() {
        char[] ar;
        int from = (int)Math.pow(2, spineCount - 1);
        int to = (int)Math.pow(2, set.length);
        for (int i = from; i < to; i++) {
            if (Integer.bitCount(i) == spineCount) {
                char[] combination = new char[set.length + 1];
                ar = Integer.toBinaryString(i).toCharArray();
                System.arraycopy(ar, 0, combination, combination.length - ar.length, ar.length);
                combinations.add(combination);
            }
        }
    }

    public static int kruskal(char[] spines) {
        HashSet<Integer> cw = new HashSet<Integer>();
        edgeCounter = 0;
        kruskalMap = new HashMap<Integer, Node>();
        int length = 0;
        for (int[] coords : linearMatrix) {
            if (spines.length > 0) {
                if (spines[map[coords[0]]] == '1' || spines[map[coords[1]]] == '1') {
                    if ((spines[map[coords[0]]] == '1' && spines[map[coords[1]]] == '1')) {
                        continue;
                    }
                    if (cw.contains(coords[0]) || cw.contains(coords[1])) {
                        continue;
                    }

                    if (union(coords[0], coords[1])) {
                        length += coords[2];
                        if (minLength < length && minLength != -1) {
                            return minLength;
                        }
                        if (edgeCounter >= nodeCount - 1) {
                            return length;
                        }
                        if (spines[map[coords[0]]] == '1') {
                            cw.add(coords[0]);
                        }
                        if (spines[map[coords[1]]] == '1') {
                            cw.add(coords[1]);
                        }
                    }
                    continue;
                }
            }
            if (union(coords[0], coords[1])) {
                length += coords[2];
                if (minLength < length && minLength != -1) {
                    return minLength;
                }
                if (edgeCounter >= nodeCount - 1) {
                    return length;
                }
            }
        }
        if (edgeCounter != nodeCount - 1) {
            return -1;
        }
        return length;
    }

    public static Node find(int i) {
        Node n;
        if (kruskalMap.containsKey(i)) {
            n = kruskalMap.get(i);
            while (n.parent != null) {
                n = n.parent;
            }
        } else {
            n = new Node(i);
            kruskalMap.put(i, n);
        }
        return n;
    }

    public static boolean union(int a, int b) {
        Node rA = find(a);
        Node rB = find(b);
        if (rA.id != rB.id) {
            rA.parent = rB;
            edgeCounter++;
            return true;
        }
        return false;
    }
}

class Node {
    public Node parent;
    public int id;

    public Node(int id) {
        this.id = id;
    }
}

class Edge {
    public int x;
    public int y;

    public Edge(int x, int y) {
        this.x = x;
        this.y = y;
    }
}