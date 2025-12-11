package reactor;

import fileReader.fileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class pt2 {
    public static HashMap<String, Long> memoized = new HashMap<>();

    public static void main(String[] args) {
        ArrayList<String> server = fileReader.readFile("src/reactor/serverRack.txt");
        HashMap<String, List<String>> inOut = new HashMap<>();

        for (String s : server) {
            inOut.put(s.split(":")[0], Arrays.asList(s.substring(s.indexOf(":") + 2).split(" ")));
        }
        
        System.out.println(getConnections(inOut, "svr", new ArrayList<>()));
    }

    public static Long getConnections(HashMap<String, List<String>> server, String curKey, List<String> ports) {
        Long total = Long.valueOf(0);

        List<String> connections = server.get(curKey);

        for (String connection : connections) {
            List<String> nextPorts = new ArrayList<>();
            for (String port : ports) {
                nextPorts.add(port);
            }
            if (!connection.equals("out")) {
                nextPorts.add(curKey);
                if (memoized.containsKey(connection)) {
                    total += memoized.get(connection);
                } else {
                    total += getConnections(server, connection, nextPorts);
                }
            } else {
                nextPorts.add(curKey);

                // if (nextPorts.contains("fft") && nextPorts.contains("dac")) {
                    total++;
                // }
            }
        }

        memoized.put(curKey, total);

        // System.out.println(memoized.toString());

        return total;
    }
}
