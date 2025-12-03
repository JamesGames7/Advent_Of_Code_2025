package lobby;

import fileReader.fileReader;

public class pt1 {
    public static void main(String[] args) {
        int total = 0;
        for (String bank : fileReader.readFile("src/lobby/joltage.txt")) {
            int high = 0;
            int low = 0;
            int length = bank.length();
            for (String battery : bank.split("")) {
                int b = Integer.parseInt(battery);

                if (high < b && length - 1 > bank.indexOf(battery)) {
                    high = b;
                    low = 0;
                } else if (low < b) {
                    low = b;
                }
            }
            total += high * 10 + low;
        }
        System.out.println(total);
    }
}
