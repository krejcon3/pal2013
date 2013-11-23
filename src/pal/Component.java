package pal;

import java.util.ArrayList;

public class Component {
    public ArrayList<Node> nodes;
    public ArrayList<Node> inputs;
    public ArrayList<Node> outputs;
    public ArrayList<Node> towns;
    public int count;

    public Component() {
        this.nodes = new ArrayList<Node>();
        this.inputs = new ArrayList<Node>();
        this.outputs  = new ArrayList<Node>();
        this.towns = new ArrayList<Node>();
        this.count = 0;
    }

    public void addNode(Node node) {
        this.nodes.add(node);
        this.count++;
    }

    public void findInterestingPoints() {
        for (Node node : this.nodes) {
            for (Node child : node.childs) {
                if (node.componentIndex != child.componentIndex) {
                    if (!node.isOutput) {
                        node.isOutput = true;
                        this.outputs.add(node);
                    }
                    Component fc = Main.tarjan.getComponent(child.componentIndex);
                    if (!child.isInput) {
                        child.isInput = true;
                        fc.inputs.add(child);
                    }
                }
            }
            if (Main.costs[node.name - 1].length > 1 && Main.costs[node.name - 1][1] > 0 && !node.isTown) {
                node.isTown = true;
                this.towns.add(node);

            }
        }
    }
}
