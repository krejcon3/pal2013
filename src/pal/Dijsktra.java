package pal;

import java.util.PriorityQueue;

public class Dijsktra {
    private Component component;
    private PriorityQueue<Node> priorityQueue = new PriorityQueue<Node>();

    public void run(Component component) {
        this.component = component;
        for (Node input : this.component.inputs) {
            this.dijsktra(input);
        }
        for (Node town : this.component.towns) {
            this.dijsktra(town);
        }
    }

    private void dijsktra(Node start) {
        for (Node node : this.component.nodes) {
            node.resetDijsktra();
        }
        priorityQueue = new PriorityQueue<Node>();
        start.dijsktra_distance = 0;
        this.priorityQueue.add(start);
        start.addWayCost(start.name, start.dijsktra_distance);
        while (!this.priorityQueue.isEmpty()) {
            Node node = this.priorityQueue.poll();
            node.isDijsktra_visited = true;
            for (Node child : node.childs) {
                if (child.componentIndex == start.componentIndex) {
                    int alt = node.dijsktra_distance + Main.costs[child.name - 1][0];
                    if (alt < child.dijsktra_distance && !child.isDijsktra_visited) {
                        child.dijsktra_distance = alt;
                        this.priorityQueue.add(child);
                        if (child.isTown || child.isOutput) {
                            start.addWayCost(child.name, child.dijsktra_distance);
                        }
                    }
                }
            }
        }

    }
}
