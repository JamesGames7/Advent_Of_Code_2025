package trashCompactor;

import fileReader.fileReader;
import java.util.ArrayList;
import java.util.List;

public class pt2 {
    public static void main(String[] args) {
        Long total = Long.valueOf(0);
        ArrayList<String> file = fileReader.readFile("src/trashCompactor/homework.txt");

        List<List<String>> homework = new ArrayList<>();

        for (String line : file) {
            int index = 0;
            List<String> temp = new ArrayList<>();
            while (index < line.length()) {
                String lastLine = file.get(4).substring(index + 1);
                int length = Math.min(lastLine.indexOf('+') > -1 ? lastLine.indexOf('+') : lastLine.length() + 1, lastLine.indexOf('*') > -1 ? lastLine.indexOf('*') : lastLine.length() + 1);

                temp.add(line.substring(index, index + length));
                index += length + 1;
            }
            homework.add(temp);
        }

        for (int j = 0; j < homework.get(0).size(); j++) {
            List<List<Long>> homeworkEquation = new ArrayList<>();
            for (int n = 0; n < homework.get(4).get(j).length(); n++) {
                List<Long> temp = new ArrayList<>();
                for (int i = 0; i < homework.size() - 1; i++) {
                    Integer curVal = homework.get(i).get(j).charAt(n) - '0';
                    if (curVal >= 0) {
                        temp.add(Long.valueOf(curVal));
                    }
                }
                homeworkEquation.add(temp);
            }
            
            String curSign = homework.get(4).get(j).trim();
            Long curTotal = Long.valueOf(curSign.equals("+") ? 0 : 1);
            for (List<Long> vals : homeworkEquation) {
                double tinyTotal = 0;
                for (int i = vals.size() - 1; i >= 0; i--) {
                    tinyTotal += vals.get(vals.size() - 1 - i) * Math.pow(10, i);
                }
                switch (curSign) {
                    case "+" -> curTotal += (long) tinyTotal;
                    case "*" -> curTotal *= (long) tinyTotal;
                }
            }

            total += curTotal;
        }
        
        System.out.println(total);
    }
}
