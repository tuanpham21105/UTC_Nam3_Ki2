package model;

public class PriorityQueueNode implements Comparable<PriorityQueueNode> {
    public String nodeName;
    public int f;

    public PriorityQueueNode(String nodeName, int f) {
        this.nodeName = nodeName;
        this.f = f;
    }

    @Override
    public int compareTo(PriorityQueueNode arg0) {
        return Integer.compare(this.f, arg0.f);
    }
}
