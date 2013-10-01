package pal;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

public class Main {

    public static ArrayList<char[]> list = new ArrayList<char[]>(40000);
    public static HashSet<char[]> visited = new HashSet<char[]>(20000);
    public static HashSet<char[]> opened = new HashSet<char[]>(20000);
    public static Stack<char[]> stack = new Stack<char[]>();
    public static HashMap<String, char[]> map = new HashMap<String, char[]>(20000);
    public static char[] head = null;

    public static String[] getString(char[] array) {
        return new String(array).split("\\s");
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        String pom;
        char[] chars;
        while ((line = br.readLine()) != null) {
            chars = line.toCharArray();
            list.add(chars);
            if (line.startsWith("\t")) {
                continue;
            }
            if (head == null) {
                head = chars;
            }
            map.put(getString(chars)[0], chars);
        }
        if (!findCycles()) {
            print();
        } else {
            System.out.println("ERROR");
        }
    }

    public static boolean findCycles() {
        char[] chars = head;
        char[] act;
        String[] pom;
        String str;
        stack.push(chars);
        int index;
        while (!stack.isEmpty()) {
            index = 1;
            chars = stack.peek();
            visited.add(chars);
            opened.add(chars);
            pom = getString(chars);
            if (pom.length == 1) {
                stack.pop();
                opened.remove(chars);
                continue;
            }
            while (index < pom.length) {
                str = pom[index++];
                act = map.get(str + ":");
                if (opened.contains(act)) {
                    return true;
                }
                if (!visited.contains(act)) {
                    stack.push(act);
                    break;
                }
                if (index >= pom.length) {
                    stack.pop();
                    opened.remove(chars);
                }
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
