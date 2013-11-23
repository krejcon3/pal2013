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

    public void getBestWay() {
        int earnings = 0;
        for (Node first : this.towns) {
            for (Node second : this.towns) {
                if (first.name == second.name) {
                    earnings = Main.costs[first.name - 1][1] - Main.costs[first.name - 1][0];
                } else {
                    earnings = Main.costs[first.name - 1][1] + Main.costs[second.name - 1][1] - Main.costs[first.name - 1][0] - first.getCostOfWayToNode(second.name);
                }
                Main.maxEarnings = Math.max(earnings, Main.maxEarnings);
                for (Node output : this.outputs) {
                    int outputEarnings = earnings;
                    if (output.name != second.name) {
                        outputEarnings -= second.getCostOfWayToNode(output.name);
                    }
                    output.maxEarnings = Math.max(outputEarnings, output.maxEarnings);
                }
            }
        }
        int tEarnings = 0;
        for (Node input : this.inputs) {
            if (input.bonusEarnings > Main.costs[input.name - 1][0]) {
                for (Node first : this.towns) {
                    if (input.name == first.name) {
                        tEarnings = Main.costs[first.name - 1][1] - Main.costs[first.name - 1][0] + input.bonusEarnings;
                    } else {
                        tEarnings = Main.costs[first.name - 1][1] - Main.costs[input.name - 1][0] - input.getCostOfWayToNode(first.name) + input.bonusEarnings;
                    }
                    for (Node second : this.towns) {
                        earnings = tEarnings;
                        if (first.name != second.name) {
                            earnings += Main.costs[second.name - 1][1] - first.getCostOfWayToNode(second.name);
                        }
                        Main.maxEarnings = Math.max(earnings, Main.maxEarnings);
                        for (Node output : this.outputs) {
                            if (second.name != output.name) {
                                output.maxEarnings = Math.max(output.maxEarnings, earnings - second.getCostOfWayToNode(output.name));
                            } else {
                                output.maxEarnings = Math.max(output.maxEarnings, earnings);
                            }
                        }
                    }
                }
            }
            for (Node output : this.outputs) {
                if (input.name == output.name) {
                    earnings = input.bonusEarnings - Main.costs[input.name - 1][0];
                } else {
                    earnings = input.bonusEarnings - input.getCostOfWayToNode(output.name) - Main.costs[input.name - 1][0];
                }
                output.maxEarnings = Math.max(output.maxEarnings, earnings);
            }
        }
        for (Node output : this.outputs) {
            if (output.maxEarnings > 0) {
                for (Node child : output.childs) {
                    if (child.componentIndex != output.componentIndex) {
                        child.bonusEarnings = Math.max(output.maxEarnings, child.bonusEarnings);
                    }
                }
            }
        }
    }
}
