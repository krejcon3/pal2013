package pal;

import java.io.*;
import java.util.HashSet;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static int[] NMQ = new int[3];
    public static int[] BCTUVW = new int[6];
    public static Node[] nodes;
    public static int[] END;

    public static char[] toAlhabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    public static Stack<Node> stack = new Stack<Node>();
    public static Stack<String> word = new Stack<String>();
    public static HashSet<Node> visited = new HashSet<Node>(20000);
    public static HashSet<Node> opened = new HashSet<Node>(20000);

    public static HashSet<Integer> control = new HashSet<Integer>();

    public static boolean isFinite = true;

    public static void main(String[] args) throws IOException {
        read();
        Printer.printNodeArray(nodes);
        calc();
        if (isFinite) {
            System.out.println("FINITE " + nodes[0].maxW);
        } else {
            System.out.println("FINITE " + nodes[0].minW);
        }
    }

    public static void calc() {
        boolean breakable = false;
        Node node = nodes[0];
        stack.push(node);
        while (!stack.empty()) {
            node = stack.peek();
            visited.add(node);
            opened.add(node);
            for (int h = 0; h < node.count; h++) {
                for (int i = 0; i < node.childs.get(h).length; i++) {
                    System.out.println(nodes[node.childs.get(h)[i]].index);
                    if (opened.contains(nodes[node.childs.get(h)[i]])) {
                        continue;
                    }
                    if (!visited.contains(nodes[i])) {
                        stack.push(nodes[i]);
                        breakable = true;
                        break;
                    }
//                    node.addMaxW(toAlhabet[h] + node.maxW);
//                    node.addMinW(toAlhabet[h] + node.minW);
                }
                if (breakable) {
                    breakable = false;
                    break;
                }
            }
        }
    }

    public static void read() throws IOException {
        System.setIn(new FileInputStream("file.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        StringTokenizer st;
        if ((line = br.readLine()) != null) {
            st = new StringTokenizer(line);
            for (int i = 0; i < 3; i++) {
                NMQ[i] = Integer.parseInt(st.nextToken());
            }
            System.out.println();
        }
        nodes = new Node[NMQ[0]];
        if (NMQ[2] == 1) {
            Node node;
            int index;
            for (int i = 0; i < nodes.length; i++) {
                if ((line = br.readLine()) != null) {
                    st = new StringTokenizer(line);
                    index = Integer.parseInt(st.nextToken());
                    node = new Node(index, NMQ[1]);
                    for (int j = 0; j < NMQ[1]; j++) {
                        int[] array = new int[Integer.parseInt(st.nextToken())];
                        for (int k = 0; k < array.length; k++) {
                            array[k] = Integer.parseInt(st.nextToken());
                        }
                        node.addChilds(j, array);
                    }
                    nodes[index] = node;
                }
            }
        } else if(NMQ[2] == 2) {
            if ((line = br.readLine()) != null) {
                st = new StringTokenizer(line);
                for (int i = 0; i < BCTUVW.length; i++) {
                    BCTUVW[i] = Integer.parseInt(st.nextToken());
                }
                calcNodes();
            }
        }
        if ((line = br.readLine()) != null) {
            st = new StringTokenizer(line);
            int count = Integer.parseInt(st.nextToken());
            END = new int[count];
            for (int i = 0; i < END.length; i++) {
                END[i] = Integer.parseInt(st.nextToken());
            }
        }
    }

    public static void calcNodes() {
        Node node;
        int r;
        for (int j = 0; j <nodes.length; j++) {
            node = new Node(j, NMQ[1]);
            for (int h = 0; h < NMQ[1]; h++) {
                int[] array = new int[H(j, h)];
                int counter = 0;
                control = new HashSet<Integer>();
                for (int k = 1; k <= array.length; k++) {
                    r = sigma(j, h, k);
                    if (!control.contains(r)) {
                        array[counter++] = r;
                        if (counter < array.length) {
                            control.add(r);
                            array[counter] = -1;
                        }
                    }
                }
                int[] ret = new int[counter];
                System.arraycopy(array, 0, ret, 0, ret.length);
                node.addChilds(h, ret);
            }
            nodes[j] = node;
        }
    }

    public static int sigma(int stateIndex, int charIndex, int k) {
        if (stateIndex < Math.floor(NMQ[0]/BCTUVW[0])) {
            return 1 + stateIndex + ((BCTUVW[0] * stateIndex * k + BCTUVW[1] * charIndex) % (NMQ[0] - (int)Math.floor(NMQ[0]/BCTUVW[0]) - 1));
        } else {
            return ((int)Math.floor(NMQ[0]/BCTUVW[0])) + ((BCTUVW[0] * stateIndex * k + BCTUVW[1] * charIndex) % (NMQ[0] - (int)Math.floor(NMQ[0]/BCTUVW[0])));
        }
    }

    public static int H(int stateIndex, int charIndex) {
        return BCTUVW[2] + ((BCTUVW[3] * stateIndex + BCTUVW[4] * charIndex) % BCTUVW[5]);
    }
}
