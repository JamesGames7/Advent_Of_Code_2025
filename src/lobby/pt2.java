package lobby;

import fileReader.fileReader;

public class pt2 {
    public static void main(String[] args) {
        long total = 0;
        for (String bank : fileReader.readFile("src/lobby/joltage.txt")) {
            int[] nums = new int[12];
            int index = 0;
            int length = bank.length();

            for (String battery : bank.split("")) {
                int b = Integer.parseInt(battery);
                int internalIndex = 0;
                boolean added = false;
                for (int curVal : nums) {
                    if (length - index >= 12 - internalIndex && b > curVal && !added) {
                        nums[internalIndex] = b;
                        int tempIndex = internalIndex + 1;
                        while (tempIndex < 12) {
                            nums[tempIndex] = 0;
                            tempIndex++;
                        }

                        added = true;
                    }
                    
                    internalIndex++;
                }

                index++;
            }
            
            int finalIndex = 0;
            long tempTotal = 0;
            while (finalIndex < 12) {
                tempTotal += nums[finalIndex] * (long)Math.pow(10, (11 - finalIndex));
                finalIndex++;
            }
            total += tempTotal;
        }
        System.out.println(total);
    }
}
