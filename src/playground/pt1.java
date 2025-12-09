package playground;

import fileReader.fileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class pt1 {

    public static class Mutable {
        private MInt ref;

        public Mutable(MInt startRef) {
            ref = startRef;
        }

        public Mutable() {
            ref = new MInt();
        }

        public static class MInt {
            private int value = 1;

            public int get() {return value;};
            public void setValue(int newVal) {value = newVal;};
            public void incValue(int inc) {value += inc;};
        }

        public int get() {return ref.get();};
        public void setValue(int newVal) {ref.setValue(newVal);};
        public void incValue(int inc) {ref.incValue(inc);};
        public void incValue() {ref.incValue(1);};
        public void changeRef(MInt newRef) {ref = newRef;};
        public MInt getRef() {return ref;};

        @Override
        public String toString() {
            return String.valueOf(ref.get());
        }
    }

    public static void main(String[] args) {
        ArrayList<String> boxes = fileReader.readFile("src/playground/boxes.txt");
        double[][] allDistances = new double[boxes.size()][boxes.size()];
        ArrayList<Double> sizes = new ArrayList<>();
        double smallest = -1;
        Integer[] smallestBoxes = new Integer[2];
        Map<Integer, Mutable> totals = new HashMap<>();

        for (int i = 0; i < boxes.size(); i++) {
            for (int j = 0; j < boxes.size(); j++) {
                allDistances[i][j] = distance(boxes.get(i), boxes.get(j));
            }
        }
        while (sizes.size() < 1000) {
            for (int i = 0; i < boxes.size(); i++) {
                for (int j = 0; j < boxes.size(); j++) {
                    double curSmallest = smallest;
                    if (smallest <= 0 && !sizes.contains(allDistances[i][j])) {
                        smallest = allDistances[i][j];
                        if (curSmallest != smallest) {
                            smallestBoxes = new Integer[]{i, j};
                        }
                    } else {
                        if (allDistances[i][j] != 0 && !sizes.contains(allDistances[i][j])) {
                            smallest = Math.min(smallest, allDistances[i][j]);
                            if (curSmallest != smallest) {
                                smallestBoxes = new Integer[]{i, j};
                            }
                        }
                    }
                }
            }
            sizes.add(smallest);
            if ((totals.containsKey(smallestBoxes[0]) && totals.containsKey(smallestBoxes[1]) && totals.get(smallestBoxes[0]).getRef() != totals.get(smallestBoxes[1]).getRef() || !totals.containsKey(smallestBoxes[0])) || !totals.containsKey(smallestBoxes[1])) {
                boolean doubled = !totals.containsKey(smallestBoxes[0]) || !totals.containsKey(smallestBoxes[1]);
                if (!totals.containsKey(smallestBoxes[0])) {
                    totals.put(smallestBoxes[0], new Mutable());
                }
                if (!totals.containsKey(smallestBoxes[1])) {
                    if (doubled) {totals.get(smallestBoxes[0]).incValue();}
                    totals.put(smallestBoxes[1], new Mutable(totals.get(smallestBoxes[0]).getRef()));
                } 
                if (totals.get(smallestBoxes[0]).getRef() != totals.get(smallestBoxes[1]).getRef()) {
                    totals.get(smallestBoxes[1]).incValue(totals.get(smallestBoxes[0]).get());
                    for (Integer key : totals.keySet()) {
                        if (totals.get(key).getRef() == totals.get(smallestBoxes[0]).getRef() && !Objects.equals(key, smallestBoxes[0])) {
                            totals.get(key).changeRef(totals.get(smallestBoxes[1]).getRef());                            
                        }
                    }
                    totals.get(smallestBoxes[0]).changeRef(totals.get(smallestBoxes[1]).getRef());
                }
            }
            smallest = -1;
            System.out.println(sizes.size());
        }
        System.out.println(totals.toString());
        int total = 1;
        ArrayList<Integer> impossible = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            int curMax = totals.values().stream().mapToInt(Mutable::get).filter(v -> !impossible.contains(v)).max().getAsInt();
            for (Integer key : totals.keySet()) {
                if (totals.get(key).get() == curMax) {
                    impossible.add(key);
                    break;
                }
            }
            total *= curMax;
        }
        System.out.println(total);
    }

    public static double distance(String boxes1, String boxes2) {
        String[] b1 = boxes1.split(",");
        String[] b2 = boxes2.split(",");
        return Math.abs(Math.sqrt(Math.pow(Integer.parseInt(b1[0]) - Integer.parseInt(b2[0]), 2) + Math.pow(Integer.parseInt(b1[1]) - Integer.parseInt(b2[1]), 2) + Math.pow(Integer.parseInt(b1[2]) - Integer.parseInt(b2[2]), 2)));
    }
}