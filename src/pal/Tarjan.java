package pal;

import java.util.ArrayList;

public class Tarjan {
    private Node stackTop;
    private int count;
    private int index;
    public ArrayList<Component> components = new ArrayList<Component>(5000);

    public Tarjan() {
        this.stackTop = null;
        this.count = 0;
        this.index = 0;
    }

    public void run() {
        for (int i = 1; i <= Main.pointCount; i++) {
            this.stackTop = null;
            this.index = 0;
            Node no = Main.nodes.get(i);
            if (no.index == 0) {
                this.find_scc(no);
            }
        }
        this.findInterestingPoints();
    }

    private void push(Node n) {
        n.previous = this.stackTop;
        n.instack = true;
        this.stackTop = n;
    }

    private Node pop(Node n) {
        this.stackTop = n.previous;
        n.previous = null;
        n.instack = false;
        return n;
    }

    public Component getComponent(int index) {
        return this.components.get(index);
    }

    public int countComponent() {
        return this.count;
    }

    private void findInterestingPoints() {
        for (Component component: this.components) {
            component.findInterestingPoints();
        }
    }

    private void find_scc(Node n) {
        n.index = ++this.index;
        n.lowlink = n.index;
        this.push(n);
        for (Node child : n.childs) {
            if (child.index == 0) {
                this.find_scc(child);
                n.lowlink = Math.min(n.lowlink, child.lowlink);
            } else if(child.instack) {
                n.lowlink = Math.min(n.lowlink, child.index);
            }
        }
        if (n.lowlink == n.index) {
            Component component = new Component();
            Node x;
            while (this.stackTop != null) {
                x = this.pop(this.stackTop);
                x.componentIndex = this.count;
                component.addNode(x);
                if (x.index == x.lowlink) {
                    break;
                }
            }
            this.components.add(component);
            this.count++;
        }
    }
}
