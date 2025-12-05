package cafeteria;

import fileReader.fileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class pt2 {
    public static void main(String[] args) {
        Long total = Long.valueOf(0);
        int i = 0;
        ArrayList<String> file = fileReader.readFile("src/cafeteria/ingredients.txt");
        List<List<Long>> allRanges = new ArrayList<>();

        while (!file.get(i).equals("")) {
            String[] curRange = file.get(i).split("-");
            Long min = Long.valueOf(curRange[0]);
            Long max = Long.valueOf(curRange[1]);

            for (int j = 0; j < i; j++) {
                List<Long> testRange = allRanges.get(j);

                if (min < testRange.get(0)) {
                    if (testRange.get(0) <= max && max <= testRange.get(1)) {
                        max = testRange.get(0) - 1;
                    } else if (testRange.get(1) < max) {
                        total -= (testRange.get(1) - testRange.get(0) + 1);
                    }
                } else if (min <= testRange.get(1)) {
                    min = testRange.get(1) + 1;
                    if (min > max) {
                        max = Long.valueOf(-5);
                        min = Long.valueOf(-5);
                        total--;
                        break;
                    }
                }
            }

            total += max - min + 1;

            allRanges.add(Arrays.asList(min, max));
            
            i++;
        }

        System.out.println(total);
    }
}
