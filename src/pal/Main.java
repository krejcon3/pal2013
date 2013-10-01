package pal;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;
import java.util.HashMap;
import java.util.regex.Pattern;

public class Main {

    public static ArrayList<char[]> list = new ArrayList<char[]>(40000);
    public static HashSet<char[]> visited = new HashSet<char[]>(20000);
    public static HashMap<String, Node> map = new HashMap<String, Node>(20000);
    public static Stack<Node> stack = new Stack<Node>();
    public static Pattern pattern = Pattern.compile("\\s");
    public static Node start = null;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        char[] chars;
        Node node;
        while ((line = br.readLine()) != null) {
            chars = line.toCharArray();
            list.add(chars);
            if (line.startsWith("\t")) {
                continue;
            }
            node = new Node(pattern.split(line), chars);
            if (start == null) {
                start = node;
            }
            map.put(node.targets[0], node);
        }
        if (!findCycles()) {
            print();
        } else {
            System.out.println("ERROR");
        }
    }

    public static boolean findCycles() {
        Node node = start;
        stack.push(node);
        while (!stack.isEmpty()) {
            node = stack.peek();
            visited.add(node.line);
            node.visited = true;
            node.opened = true;
            if (node.hasNext()) {
                node = map.get(node.getNext() + ":");
                if (node.opened) {
                    return true;
                }
                if (node.visited) {
                    continue;
                }
                stack.push(node);
            } else {
                stack.pop();
                node.opened = false;
            }
        }
        return false;
    }

    public static void print() throws IOException {
        PrintWriter print = new PrintWriter(new OutputStreamWriter(System.out, "UTF-8"), false);
        boolean hash = false;
        int loop = 0;
        for (char[] line : list) {
            if (line[0] == '\t') {
                if (hash) {
                    print.write('#');
                }
            } else {
                if (!visited.contains(line)) {
                    print.write('#');
                    hash = true;
                } else {
                    hash = false;
                }
            }
            print.write(line);
            print.write('\n');
            if (loop++ >= 6000) {
                print.flush();
                loop = 0;
            }
        }
        print.flush();
    }
}

class Node {
    private int count = 1;
    public String[] targets;
    public char[] line;
    public boolean opened = false;
    public boolean visited = false;

    public Node(String[] targets, char[] line) {
        this.targets = targets;
        this.line = line;
    }

    public boolean hasNext() {
        return this.count < this.targets.length;
    }

    public String getNext() {
        return this.targets[this.count++];
    }
}
