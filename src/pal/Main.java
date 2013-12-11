package pal;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static int n, m, a1, a2, b, c, d, e;
    public static char[] text, pattern;


    public static void main(String[] args) throws IOException {
        read();
        generateWords();
        Printer.printCharArray(text);
        Printer.printCharArray(pattern);
    }

    public static void read() throws IOException {
        System.setIn(new FileInputStream("file.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        if ((line = br.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(line);
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            a1 = Integer.parseInt(st.nextToken());
            a2 = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());
        }
    }

    public static void generateWords() {
        WordGenerator wg = new WordGenerator(b, c, d, e);
        text = wg.getWord(n, a1);
        pattern = wg.getWord(m, a2);
    }
}
