package pal;

public class WordGenerator {
    int b, c, d, e;
    public static char[] translate = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};


    public WordGenerator(int b, int c, int d, int e) {
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
    }

    public char[] getWord(int l, int a) {
        char[] word = new char[l];
        long prev = a;
        word[0] = translate[(int)(prev % this.e)];
        for (int i = 1; i < l; i++) {
            prev = this.generateChar(prev);
            word[i] = translate[(int)(prev % this.e)];
        }
        return word;
    }

    private long generateChar(long prev) {
        return ((((prev + 1) * this.b) + this.c) % this.d);
    }
}
