package trashCompactor;

import fileReader.fileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class pt1 {
    public static void main(String[] args) {
        Long total = Long.valueOf(0);
        ArrayList<String> file = fileReader.readFile("src/trashCompactor/homework.txt");

        List<List<Long>> homework = new ArrayList<>();

        for (String line : file) {
            List<String> temp = new ArrayList<>(Arrays.asList(line.split(" ")));
            while (temp.contains("")) {
                temp.remove("");
            }
            List<Long> tempInt = new ArrayList<>();
            try {
                for (String n : temp) {
                    tempInt.add(Long.valueOf(n));
                }
                homework.add(tempInt);
            } catch (NumberFormatException e) {
                for (int j = 0; j < homework.get(0).size(); j++) {
                    String sign = temp.get(j);
                    Long tempTotal = Long.valueOf(sign.equals("+") ? 0 : 1);
                    for (int i = 0; i < homework.size(); i++) {
                        switch (sign) {
                            case "+" -> tempTotal += homework.get(i).get(j);
                            case "*" -> tempTotal *= homework.get(i).get(j);
                        }
                    }
                    total += tempTotal;
                }
            }
        }
        System.out.println(total);
    }
}
