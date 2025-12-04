package printingDepartment;

import fileReader.fileReader;
import java.util.ArrayList;

public class pt2 {
    public static void main(String[] args) {
        int total = 0;
        boolean removed = true;
        ArrayList<String> temp = fileReader.readFile("src/printingDepartment/paper.txt");
        String[][] paper = new String[temp.size()][temp.get(0).length()];

        int index = 0;
        for (String line : temp) {
            paper[index] = line.split("");
            index++;
        }

        while (removed) {
            removed = false;
            for (int i = 0; i < paper.length; i++) {
                for (int j = 0; j < paper[0].length; j++) {
                    if (paper[i][j].equals("@")) {
                        int surround = 0;
                        for (int n = -1; n <= 1; n++) {
                            for (int m = -1; m <= 1; m++) {
                                if (i + n >= 0 && i + n < paper.length && j + m >= 0 && j + m < paper[0].length && !(n == 0 && m == 0)) {
                                    if (paper[i + n][j + m].equals("@")) {
                                        surround++;
                                    }
                                }
                            }
                        }
                        if (surround < 4) {
                            total++;
                            paper[i][j] = ".";
                            removed = true;
                        }
                    }
                }
            }
        }
        System.out.println(total);
    }
}
