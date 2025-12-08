package laboratories;

import fileReader.fileReader;
import java.util.ArrayList;

public class pt1 {
    public static void main(String[] args) {
        int total = 0;
        ArrayList<String> beams = fileReader.readFile("src/laboratories/beam.txt");

        boolean[][] hasBeam = new boolean[beams.size()][beams.get(0).length()];
        
        for (int i = 0; i < beams.size(); i++) {
            for (int j = 0; j < beams.get(i).length(); j++) {
                if (i > 0) {
                    if (beams.get(i).charAt(j) == '^') {
                        if (hasBeam[i - 1][j]) {
                            hasBeam[i][j - 1] = true;
                            hasBeam[i][j + 1] = true;
                            total++;
                        }
                        hasBeam[i][j] = false;
                    } else {
                        hasBeam[i][j] = hasBeam[i - 1][j] || hasBeam[i][j];
                    }
                } else {
                    hasBeam[i][j] = beams.get(i).charAt(j) == 'S';
                }
            }
        }
        System.out.println(total);
    }
}
