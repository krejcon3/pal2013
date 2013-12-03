package pal;

public class Printer {


    public static void printNode(Node node) {
        System.out.print(node.index + "  ");
        for (int i = 0; i < node.count; i++) {
            int[] ar = node.childs.get(i);
            System.out.print(ar.length + " {");
            for (int p : ar) {
                System.out.print(p + ",");
            }
            System.out.print("}\t\t\t");
        }
        System.out.println();
    }

    public static void printNodeArray(Node[] nodes) {
        for (Node node : nodes) {
            printNode(node);
        }
    }
}
