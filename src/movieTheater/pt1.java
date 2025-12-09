package movieTheater;

import fileReader.fileReader;
import java.util.ArrayList;
import java.util.Collections;

public class pt1 {
    public static void main(String[] args) {
        ArrayList<String> tiles = fileReader.readFile("src/movieTheater/tiles.txt");
        ArrayList<Long> sizes = new ArrayList<>();

        for (int i = 0; i < tiles.size(); i++) {
            for (int j = i + 1; j < tiles.size(); j++) {
                String[] tile1 = tiles.get(i).split(",");
                String[] tile2 = tiles.get(j).split(",");
                
                sizes.add(Math.abs((Long.valueOf(tile1[0]) - Long.valueOf(tile2[0]) + 1) * (Long.valueOf(tile1[1]) - Long.valueOf(tile2[1]) + 1)));
            }
        }

        System.out.println(Collections.max(sizes));
    }
}
