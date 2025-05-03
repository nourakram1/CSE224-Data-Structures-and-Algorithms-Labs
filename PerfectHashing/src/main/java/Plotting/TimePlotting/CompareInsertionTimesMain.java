package Plotting.TimePlotting;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import Plotting.ChartPlotter;
import Plotting.Dummy;
import table.Hashtable;
import table.HierarchicalHashtable;
import table.Table;

public class CompareInsertionTimesMain {
    private static final int MAX_N = 1500;
    private static final int STEP = 50;
    private static final int BINLEN = 32;

    public static void main(String[] args) throws IOException {
        List<Integer> ns = IntStream.iterate(STEP, i -> i + STEP)
                .limit(MAX_N / STEP)
                .boxed()
                .collect(Collectors.toList());

        List<Double> insertTimesHash = new ArrayList<>();
        List<Double> insertTimesHierarchical = new ArrayList<>();

        List<Double> searchTimesHash = new ArrayList<>();
        List<Double> searchTimesHierarchical = new ArrayList<>();

        for (int n : ns) {
            List<Dummy> data = Dummy.createDummies(n, BINLEN);

            // Hashtable
            Hashtable<Dummy> hashTable = new Hashtable<>(n);
            insertTimesHash.add(measureInsertionTime(hashTable, data));
            searchTimesHash.add(measureSearchTime(hashTable, data));

            // HierarchicalHashtable
            HierarchicalHashtable<Dummy> hierarchicalTable = new HierarchicalHashtable<>(n);
            insertTimesHierarchical.add(measureInsertionTime(hierarchicalTable, data));
            searchTimesHierarchical.add(measureSearchTime(hierarchicalTable, data));
        }

        // Plot insertion time comparison
        ChartPlotter.plotAndSave(
                ns,
                insertTimesHash,
                insertTimesHierarchical,
                "Hashtable",
                "HierarchicalHashtable",
                "Mean Insertion Time Comparison",
                "Number of Elements",
                "Time per Insert (ms)",
                "compare_insertion_time.png"
        );

        // Plot search time comparison
        ChartPlotter.plotAndSave(
                ns,
                searchTimesHash,
                searchTimesHierarchical,
                "Hashtable",
                "HierarchicalHashtable",
                "Mean Search Time Comparison",
                "Number of Elements",
                "Time per Search (ms)",
                "compare_search_time.png"
        );
    }

    private static double measureInsertionTime(Table<Dummy> table, List<Dummy> data) {
        long start = System.nanoTime();
        table.insertAll(data);
        long end = System.nanoTime();

        for (Dummy d : data) {
            if (!table.contains(d)) {
                throw new RuntimeException("Insertion error: " + d.getBinaryRepresentation());
            }
        }

        return (end - start) / 1_000_000.0 / data.size();
    }

    private static double measureSearchTime(Table<Dummy> table, List<Dummy> data) {
        long start = System.nanoTime();
        for (Dummy d : data) {
            if (!table.contains(d)) {
                throw new RuntimeException("Search error: " + d.getBinaryRepresentation());
            }
        }
        long end = System.nanoTime();
        return (end - start) / 1_000_000.0 / data.size();
    }
}
