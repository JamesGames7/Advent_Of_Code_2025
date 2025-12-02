package giftShop;

import fileReader.fileReader;
import java.util.ArrayList;

public class pt2 {
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
                boolean complete = false;
                for (int n = 1; n <= length / 2; n++) {
                    boolean equal = true;
                    if (length % n == 0 && !complete) {
                        String repeatVal = curVal.substring(0, n);
                        for (int m = 1; (m + 1) * n <= length; m++) {
                            if (equal && !curVal.substring(n * m, n * (m + 1)).equals(repeatVal)) {
                                equal = false;
                            }
                        }
                        if (equal) {
                            invalid += i;
                            complete = true;
                        }
                    }
                }
            }
        }
        System.out.println(invalid);
    }
}