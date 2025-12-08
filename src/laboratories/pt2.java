package laboratories;

import fileReader.fileReader;
import java.util.ArrayList;

public class pt2 {
    public static void main(String[] args) {
        Long total = Long.valueOf(0);
        ArrayList<String> beams = fileReader.readFile("src/laboratories/beam.txt");

        boolean[][] hasBeam = new boolean[beams.size()][beams.get(0).length()];
        Long[][] merged = new Long[beams.size()][beams.get(0).length()];

        for (Long[] merged1 : merged) {
            for (int n = 0; n < merged1.length; n++) {
                merged1[n] = Long.valueOf(0);
            }
        }
        
        for (int i = 0; i < beams.size(); i++) {
            for (int j = 0; j < beams.get(i).length(); j++) {
                if (i > 0) {
                    if (beams.get(i).charAt(j) == '^') {
                        if (hasBeam[i - 1][j]) {
                            hasBeam[i][j - 1] = true;
                            merged[i][j - 1] += merged[i - 1][j];
                            hasBeam[i][j + 1] = true;
                            merged[i][j + 1] += merged[i - 1][j];
                        }
                        hasBeam[i][j] = false;
                    } else {
                        hasBeam[i][j] = hasBeam[i - 1][j] || hasBeam[i][j];
                        merged[i][j] += merged[i - 1][j];
                    }
                } else {
                    hasBeam[i][j] = beams.get(i).charAt(j) == 'S';
                    merged[i][j] = Long.valueOf(beams.get(i).charAt(j) == 'S' ? 1 : 0);
                }
            }
        }
        for (Long val : merged[merged.length - 1]) {
            total += val;
        }
        System.out.println(total);
    }
}
