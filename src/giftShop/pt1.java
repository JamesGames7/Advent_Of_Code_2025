package giftShop;

import fileReader.fileReader;
import java.util.ArrayList;

public class pt1 {
    static Long invalid = Long.valueOf(0);
    public static void main(String[] args) {
        ArrayList<String> range = fileReader.readFile(("src/giftShop/idRanges.txt"));
        String[] allRanges = range.get(0).split(",");
        for (String curRange : allRanges) {
            Long startVal = Long.valueOf(curRange.split("-")[0]);
            Long endVal = Long.valueOf(curRange.split("-")[1]);
            
            for (Long i = startVal; i <= endVal; i++) {
                String curVal = String.valueOf(i);

                int length = curVal.length();
                if (length % 2 == 0 && curVal.substring(0, length / 2).equals(curVal.substring(length / 2))) {
                    invalid += i;
                }
            }
        }
        System.out.println(invalid);
    }
}