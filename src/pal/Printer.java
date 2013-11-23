package pal;

import java.util.HashMap;

public class Printer {

    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void printNodeChilds(Node n) {
        for (Node no : n.childs) {
            System.out.print(no.name + ",");
        }
        System.out.println();
    }

    public static void printTreeChilds(HashMap<Integer, Node> nodes) {
        for (int i = 1; i < nodes.size() + 1; i++) {
            System.out.print(i + ":");
            printNodeChilds(nodes.get(i));
        }
    }

    public static void printComponent(Component component) {
        for (Node node : component.nodes) {
            System.out.print(node.name + "(" + node.componentIndex + ")" + ",");
        }
        System.out.println();
    }

    public static void printComponentInputs(Component component) {
        for (Node node : component.inputs) {
            System.out.print(node.name + "(" + node.componentIndex + ")" + ",");
        }
        System.out.println();
    }

    public static void printComponentOutputs(Component component) {
        for (Node node : component.outputs) {
            System.out.print(node.name + "(" + node.componentIndex + ")" + ",");
        }
        System.out.println();
    }
    public static void printComponentTowns(Component component) {
        for (Node node : component.towns) {
            System.out.print(node.name + "(" + node.componentIndex + ")" + ",");
        }
        System.out.println();
    }

    public static void printComponentNodesWithCosts(Component component) {
        for (Node node : component.inputs) {
            System.out.println(node.name + ":");
            for (Node town : component.towns) {
                System.out.println(town.name + " (" + node.getCostOfWayToNode(town.name) + ")");
            }
            for (Node output : component.outputs) {
                System.out.println(output.name + " (" + node.getCostOfWayToNode(output.name) + ")");
            }
            System.out.println();
        }
        System.out.println();
        for (Node node : component.towns) {
            System.out.println(node.name + ":");
            for (Node town : component.towns) {
                System.out.println(town.name + " (" + node.getCostOfWayToNode(town.name) + ")");
            }
            for (Node output : component.outputs) {
                System.out.println(output.name + " (" + node.getCostOfWayToNode(output.name) + ")");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void printComponentInputsBonuses(Component component) {
        for (Node node : component.inputs) {
            System.out.print(node.name + "(" + node.bonusEarnings + ")" + ",");
        }
        System.out.println();
    }
}
