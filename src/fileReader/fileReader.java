package fileReader;

import java.io.*;
import java.util.*;

public class fileReader {
    public static ArrayList<String> readFile(String fileName) {
        File textFile = new File(fileName);
        FileReader in;
        BufferedReader readFile;
        String line;
        ArrayList<String> fileContents = new ArrayList<>();

        try {
            in = new FileReader(textFile);
            readFile = new BufferedReader(in);
            while ((line = readFile.readLine()) != null) {
                fileContents.add(line);
            }
            readFile.close();
            in.close();
        } catch (IOException e) {
            System.err.println("IOExceptiion: " + e.getMessage());
        }
        return fileContents;
    }
}
