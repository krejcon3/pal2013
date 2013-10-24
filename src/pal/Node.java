package pal;

import java.util.ArrayList;

public class Node {
    public ArrayList<Node> childs = new ArrayList<Node>();
    public Node partner;
    public int content;
    public int level;
    public int max;

    public Node(int content) {
        this.content = content;
        this.level = 0;
        this.max = content;
    }
}
