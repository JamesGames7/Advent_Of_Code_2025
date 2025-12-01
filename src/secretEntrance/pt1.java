package secretEntrance;

import fileReader.fileReader;
import java.util.ArrayList;

public class pt1 {
    private static int dial = 50;
    private static int zero = 0;
    public static void main(String[] args) {
        ArrayList<String> instructions = fileReader.readFile("src/secretEntrance/instructions.txt");

        // Iterate through file
        for (String instruction : instructions) {
            // Split into direction / number
            String dir = instruction.substring(0, 1);
            int num = Integer.parseInt(instruction.substring(1));
            if (dir.equals("L")) {
                num *= -1;
            }

            dial += num;

            // Recenter based on dial limit (100)
            while (dial < 0) {
                dial += 100;
            }
            while (dial > 99) {
                dial -= 100;
            }
            
            // Increase number
            if (dial == 0) {
                zero++;
            }
        }
        System.out.println(zero);
    }
}
