package pal;

import java.util.ArrayList;
import java.util.HashMap;

public class Node {
    public Node previous = null;
    public boolean instack = false;
    public int name = -1;
    public int lowlink = 0;
    public int index = 0;
    public int componentIndex = -1;
    public boolean isInput = false;
    public boolean isOutput = false;
    public boolean isTown = false;
    public ArrayList<Node> childs = new ArrayList<Node>();
    public HashMap<Integer, Integer> interestingWaysCosts = new HashMap<Integer, Integer>();

    public Node(int nodeIndex) {
        this.name = nodeIndex;
        this.index = 0;
        this.lowlink = 0;
    }

    public void addChild(Node node) {
        this.childs.add(node);
    }

    public void addWayCost(int nodeName, int cost) {
        this.interestingWaysCosts.put(nodeName, cost);
    }

    public int getCostOfWayToNode(int nodeName) {
        return this.interestingWaysCosts.get(nodeName);
    }
}
