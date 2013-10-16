package pal;

import java.io.*;
import java.util.HashMap;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    public static HashMap<Integer,Stack<Integer>> edges = new HashMap<Integer, Stack<Integer>>();
    public static Stack<Integer> stack = new Stack<Integer>();
    public static int[] mcbp;
    public static int crossings = -1;
    public static int edgeCount = 0;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("file.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        StringTokenizer st;
        if ((line = br.readLine()) != null) {
            st = new StringTokenizer(line);
            crossings = Integer.parseInt(st.nextToken());
        }
        if (crossings == 1) {
            PrintWriter print = new PrintWriter(new OutputStreamWriter(System.out, "UTF-8"), false);
            print.write(crossings + "\n0\n0 0\n");
            print.flush();
            System.exit(0);
        }
        mcbp = new int[crossings];
        int start;
        int end;
        while (!(line = br.readLine()).equals("0 0")) {
            st = new StringTokenizer(line);
            start = Integer.parseInt(st.nextToken());
            end = Integer.parseInt(st.nextToken());
            if (!edges.containsKey(start)) {
                edges.put(start, new Stack<Integer>());
            }
            edges.get(start).add(end);
            mcbp[start]--;
            mcbp[end]++;
            edgeCount++;
        }
        int startFrom = -1;
        int endTo = -1;
        for (int i = 0; i < crossings; i++) {
            if (mcbp[i] != 0) {
                if (mcbp[i] == -1 && startFrom == -1) {
                    startFrom = i;
                    continue;
                }
                if (mcbp[i] == 1 && endTo == -1) {
                    endTo = i;
                    continue;
                }
                System.out.println(-1);
                System.exit(0);
            }
        }
        if (startFrom == -1) {
            startFrom = 0;
        }
        stack = new Stack<Integer>();
        euler(startFrom);
        stack.push(startFrom);
        print();
    }

    public static void print() throws IOException {
        PrintWriter print = new PrintWriter(new OutputStreamWriter(System.out, "UTF-8"), false);
        print.write(crossings + "\n");
        int round = 0;
        int start = 0;
        if (!stack.empty()) {
            start = stack.pop();
        }
        while(!stack.empty()) {
            round++;
            print.write(start + " " + stack.peek() + "\n");
            start = stack.pop();
            if (round % 5000 == 0) {
                print.flush();
            }
        }
        if (round == 0) {
            print.write(start + "\n");
        }
        print.write("0 0\n");
        print.flush();
    }

    public static void euler(int start) {
        Stack<Integer> s = edges.get(start);
        if (s != null && !s.empty()) {
            int end = s.pop();
            euler(end);
            stack.push(end);
        }

    }
}
