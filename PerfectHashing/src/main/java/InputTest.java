import dictionary.Dictionary;
import table.HierarchicalHashtable;
import table.instrument.InstrumentedHashtable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Timer;

public class InputTest {
    public static void main(String[] args) {
        boolean useTwoLevelHashing = true; // Set true if you want to test two-level

        Dictionary dictionary = new Dictionary(useTwoLevelHashing);
        HashSet<String> s = new HashSet<>();
        System.out.println("Benchmarking insertion of 1 million strings using " +
                (useTwoLevelHashing ? "two-level hashing..." : "one-level hashing..."));

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < 1_000_000; i++) {
            s.add("" + i);
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Time taken to insert 1,000,000 words: " + (endTime - startTime) + " ms");

//        if (dictionary.table instanceof InstrumentedHashtable<?> table) {
//            System.out.println("Total collisions: " + table.getCollisionCount());
//            System.out.println("Total grows: " + table.getGrowCount());
//            System.out.println("Total rehashes: " + table.getRehashCount());
//        } else {
//            System.out.println("Instrumentation not available for two-level hashing.");
//        }
    }
}
