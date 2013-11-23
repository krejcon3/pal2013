package pal;

import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

    public static int pointCount = 0;
    public static int roadCount = 0;
    public static int[][] costs;
    public static int[][] edges;
    public static HashMap<Integer, Node> nodes;
    public static Tarjan tarjan;
    public static Dijsktra dijsktra;
    public static int maxEarnings = 0;


    public static void main(String[] args) throws IOException {
        read();
        tarjan = new Tarjan();
        tarjan.run();
        dijsktra = new Dijsktra();
        for (Component component : tarjan.components) {
            dijsktra.run(component);
        }
        getBestWay();
        System.out.println(maxEarnings);
    }

    public static void read() throws IOException {
//        System.setIn(new FileInputStream("file3.in"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        StringTokenizer st;
        if ((line = br.readLine()) != null) {
            st = new StringTokenizer(line);
            if (st.hasMoreTokens()) {
                pointCount = Integer.parseInt(st.nextToken());
                costs = new int[pointCount][];
                nodes = new HashMap<Integer, Node>(pointCount);
            } else {
                System.out.println(-1);
                System.exit(0);
            }
            if (st.hasMoreTokens()) {
                roadCount = Integer.parseInt(st.nextToken());
                edges = new int[roadCount][2];
            } else {
                System.out.println(-1);
                System.exit(0);
            }
        }
        int counter = 0;
        int first = 0;
        int second = 0;
        int[] ar;
        while ((line = br.readLine()) != null && counter < costs.length + edges.length) {
            st = new StringTokenizer(line);
            if (st.hasMoreTokens()) {
                first = Integer.parseInt(st.nextToken());
            } else {
                System.out.println(-1);
                System.exit(0);
            }
            if (st.hasMoreTokens()) {
                second = Integer.parseInt(st.nextToken());
                ar = new int[2];
                ar[0] = first;
                ar[1] = second;
            } else {
                ar = new int[1];
                ar[0] = first;
            }
            if (counter < costs.length) {
                costs[counter] = ar;
                nodes.put(counter + 1, new Node(counter + 1));
            }
            if (counter >= costs.length && counter < edges.length + costs.length) {
                edges[counter - costs.length] = ar;
                nodes.get(ar[0]).addChild(nodes.get(ar[1]));
            }
            counter++;
        }
    }

    public static void getBestWay() {
        for (int i = tarjan.countComponent() - 1; i >= 0; i--) {
            tarjan.getComponent(i).getBestWay();
        }
    }
}
