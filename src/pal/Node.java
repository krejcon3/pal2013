package pal;

import java.util.ArrayList;

public class Node {
    private ArrayList<Node> childs = new ArrayList<Node>();
    private Node partner;
    private int content;
    private int level;
    private int max;

    public Node(int content) {
        this.content = content;
        this.level = 0;
        this.max = content;
    }

    public void addChild(Node child) {
        this.childs.add(child);
    }

    public ArrayList<Node> getChilds() {
        return this.childs;
    }

    public int getContent() {
        return this.content;
    }

    public void setContent(int content) {
        this.content = content;
    }

    public void setPartner(Node partner) {
        this.partner = partner;
    }

    public Node getPartner() {
        return this.partner;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return this.level;
    }

    public int getMax() {
        return this.max;
    }

    public void setMax(int max) {
        this.max = max;
    }
}
