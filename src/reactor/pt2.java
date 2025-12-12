package reactor;

import fileReader.fileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class pt2 {
    public static HashMap<String, List<Object>> memoized = new HashMap<>();

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
                String keyContains = (nextPorts.contains("fft") ? "1" : "0") + (nextPorts.contains("dac") ? "1" : "0");
                if (memoized.containsKey(connection + "_" + keyContains)) {
                    total += (Long) memoized.get(connection + "_" + keyContains).get(0);
                } else {
                    total += getConnections(server, connection, nextPorts);
                }
            } else {
                nextPorts.add(curKey);

                if (nextPorts.contains("fft") && nextPorts.contains("dac")) {
                    total++;
                }
            }
        }

        List<String> tempPorts = ports;
        tempPorts.add(curKey);

        String keyContains = (tempPorts.contains("fft") ? "1" : "0") + (tempPorts.contains("dac") ? "1" : "0");

        memoized.put(curKey + "_" + keyContains, Arrays.asList(total));

        // System.out.println(memoized.toString());

        // System.out.println(memoized.toString());

        return total;
    }
}
