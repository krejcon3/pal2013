package pal;

import java.util.HashMap;

public class Node {

    public int index;
    public int count;
    public HashMap<Integer, int[]> childs;
    public String maxW = "";
    public String minW = "";

    public Node(int index, int count) {
        this.index = index;
        this.count = count;
        this.childs = new HashMap<Integer, int[]>(count*2);
    }

    public void addChilds(int index, int[] childs) {
        this.childs.put(index, childs);
    }

    public void addMaxW(String w) {
        if (this.maxW.length() < w.length()) {
            this.maxW = w;
        }
    }

    public void addMinW(String w) {
        if (this.minW.length() > w.length()) {
            this.minW = w;
        }
    }
}
