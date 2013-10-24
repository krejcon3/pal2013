package pal;

public class BinomialHeap {
    private Node root = null;

    public void add(int content) {
        if (root == null) {
            root = new Node(content);
        } else {
            Node old = root;
            root = new Node(content);
            root.setPartner(old);
            this.plus(root);
        }
    }

    public int getDiff() {
        Node actual = root;
        int sumDiff = 0;
        while (actual != null) {
            sumDiff += actual.getMax() - actual.getContent();
            actual = actual.getPartner();
        }
        return sumDiff;
    }

    private boolean compare(Node a, Node b) {
        return a.getContent() < b.getContent();
    }

    private void plus(Node start) {
        Node actual = start;
        while (actual.getPartner() != null && actual.getLevel() == actual.getPartner().getLevel()) {
            Node temp;
            if (this.compare(actual, actual.getPartner())) {
                temp = actual.getPartner();
                actual.setPartner(temp.getPartner());
            } else {
                temp = actual;
                actual = temp.getPartner();
            }
            temp.setPartner(null);
            if (actual.getMax() < temp.getMax()) {
                actual.setMax(temp.getMax());
            }
            actual.addChild(temp);
            actual.setLevel(actual.getLevel() + 1);
            root = actual;
        }
    }
}
