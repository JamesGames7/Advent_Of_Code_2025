package reactor;

import fileReader.fileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class pt1 {
    public static void main(String[] args) {
        ArrayList<String> server = fileReader.readFile("src/reactor/serverRack.txt");
        HashMap<String, List<String>> inOut = new HashMap<>();

        for (String s : server) {
            inOut.put(s.split(":")[0], Arrays.asList(s.substring(s.indexOf(":") + 2).split(" ")));
        }
        
        System.out.println(getConnections(inOut, "you"));
    }

    public static int getConnections(HashMap<String, List<String>> server, String curKey) {
        int total = 0;

        List<String> connections = server.get(curKey);

        for (String connection : connections) {
            if (!connection.equals("out")) {
                total += getConnections(server, connection);
            } else {
                total++;
            }
        }
        
        return total;
    }
}
