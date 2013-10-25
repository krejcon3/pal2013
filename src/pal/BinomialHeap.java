package pal;

public class BinomialHeap {
    private int[] fakeHeap = new int[22];
    private int[] maximas = new int[22];

    public BinomialHeap() {
        for (int i = 0; i < this.fakeHeap.length; i++) {
            this.fakeHeap[i] = -1;
            this.maximas[i] = -1;
        }
    }
    public void add(int content) {
        int it = 0;
        int locMax = content;
        while (this.fakeHeap[it] != -1) {
            if (content > this.fakeHeap[it]) {
                content = this.fakeHeap[it];
            }
            if (locMax < this.maximas[it]) {
                locMax = this.maximas[it];
            }
            this.fakeHeap[it] = -1;
            this.maximas[it] = -1;
            it++;
        }
        this.fakeHeap[it] = content;
        this.maximas[it] = locMax;
    }

    public int getDiff() {
        int sum = 0;
        for (int i = 0; i < this.fakeHeap.length; i++) {
            if (this.fakeHeap[i] != -1) {
                sum += this.maximas[i] - this.fakeHeap[i];
            }
        }
        return sum;
    }
}
