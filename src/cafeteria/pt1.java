package cafeteria;

import fileReader.fileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class pt1 {
    public static void main(String[] args) {
        int i = 0;
        int total = 0;
        ArrayList<String> file = fileReader.readFile("src/cafeteria/ingredients.txt");
        List<List<Long>> ranges = new ArrayList<>();

        while (!file.get(i).equals("")) {
            String[] curRange = file.get(i).split("-");
            ranges.add(Arrays.asList(Long.valueOf(curRange[0]), Long.valueOf(curRange[1])));
            i++;
        }
        
        i++;
        while (i < file.size()) {
            boolean fresh = false;
            Long curVal = Long.valueOf(file.get(i));
            for (List<Long> range : ranges) {
                if (range.get(0) <= curVal && range.get(1) >= curVal) {
                    fresh = true;
                    break;
                }
            }
            if (fresh) {
                total += 1;
            }
            i++;
        }
        System.out.println(total);
    }
}
