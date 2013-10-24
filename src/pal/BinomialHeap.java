package pal;

public class BinomialHeap {
    private Node root = null;

    public void add(int content) {
        if (root == null) {
            root = new Node(content);
        } else {
            Node old = root;
            root = new Node(content);
            root.partner = old;
            this.plus(root);
        }
    }

    public int getDiff() {
        Node actual = root;
        int sumDiff = 0;
        while (actual != null) {
            sumDiff += actual.max - actual.content;
            actual = actual.partner;
        }
        return sumDiff;
    }

    private boolean compare(Node a, Node b) {
        return a.content < b.content;
    }

    private void plus(Node start) {
        Node actual = start;
        while (actual.partner != null && actual.level == actual.partner.level) {
            Node temp;
            if (this.compare(actual, actual.partner)) {
                temp = actual.partner;
                actual.partner = temp.partner;
            } else {
                temp = actual;
                actual = temp.partner;
            }
            temp.partner = null;
            if (actual.max < temp.max) {
                actual.max = temp.max;
            }
            actual.childs.add(temp);
            actual.level++;
            root = actual;
        }
    }
}
