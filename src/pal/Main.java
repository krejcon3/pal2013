package pal;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("file.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
            while ((line = br.readLine()) != null) {

            }
        System.out.println("OUTPUT");
    }
}
