package pal;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int round = 0, max = 0;
        double length = 0;
        int x,y;
        int[] coords = new int[4];
        String[] splited;
        try {
            String line = br.readLine();
            if (line != null) {
                splited = line.split("\\s");
                round = Integer.parseInt(splited[0]);
                max = round;
            }
            while ((line = br.readLine()) != null) {
                splited = line.split("\\s");
                x = Integer.parseInt(splited[0]) * 5;
                y = Integer.parseInt(splited[1]) * 5;
                if (max == round) {
                    coords[0] = x;
                    coords[1] = y;
                    coords[2] = x;
                    coords[3] = y;
                    round--;
                    continue;
                }
                length += pythagoras(x, y, coords[0], coords[1]);
                coords[0] = x;
                coords[1] = y;
                round--;
                if (round <= 0) {
                    length += pythagoras(coords[0], coords[1], coords[2], coords[3]);
                    break;
                }

            }
        } catch(IOException e) {
        }
        System.out.println((int)Math.ceil(length));
    }

    private static double pythagoras(int x1, int y1, int x2, int y2) {
        if (x1 == x2) return Math.abs(y1-y2);
        if (y1 == y2) return Math.abs(x1-x2);
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }
}
