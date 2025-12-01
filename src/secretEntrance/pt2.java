package secretEntrance;

import fileReader.fileReader;
import java.util.ArrayList;

public class pt2 {
    private static int dial = 50;
    private static int zero1 = 0;
    private static int zero2 = 0;
    public static void main(String[] args) {
        // Other file
        ArrayList<String> instructions = fileReader.readFile("src/secretEntrance/instructions.txt");
        for (String instruction : instructions) {
            String dir = instruction.substring(0, 1);
            int num = Integer.parseInt(instruction.substring(1));
            if (dir.equals("L")) {
                num *= -1;
            }

            dial += num;

            while (dial < 0) {
                dial += 100;
            }
            while (dial > 99) {
                dial -= 100;
            }

            // New - tests if it is passing through zero (different function depending on direction)
            switch (dir) {
                case "L" -> zero2 += Math.floor((dial - num - 1) / 100);
                case "R" -> {
                    zero2 += Math.floor((num - dial + 99) / 100);
                    if (dial == 0) {
                        zero2--;
                    }
                }
            }
            
            // Other file
            if (dial == 0) {
                zero1++;
            }
        }
        System.out.println(zero1);
        System.out.println(zero2);
        System.out.println(zero1 + zero2);
    }
}
