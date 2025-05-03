package Plotting.SpacePlotting;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import Plotting.ChartPlotter;
import Plotting.Dummy;
import table.Hashtable;
import table.HierarchicalHashtable;

public class SpacePlottingMain {
    private static final int MAX_N = 2000;
    private static final int STEP = 100;
    private static final int BINLEN = 32;

    public static void main(String[] args) throws IOException {
        // Prepare N values
        List<Integer> ns = IntStream.iterate(STEP, i -> i + STEP)
                .limit(MAX_N / STEP)
                .boxed()
                .collect(Collectors.toList());

        // Measure sizes
        DeepSizeMeasurer measurer = new DeepSizeMeasurer();
        List<Double> perfect = measurer.measure(ns, n -> {
            Hashtable<Dummy> ht = new Hashtable<>(n);
            List<Dummy> dummies = Dummy.createDummies(n, BINLEN);
            ht.insertAll(dummies);
            return ht;
        });
        List<Double> hierarchical = measurer.measure(ns, n -> {
            HierarchicalHashtable<Dummy> hht = new HierarchicalHashtable<>(n);
            List<Dummy> dummies = Dummy.createDummies(n, BINLEN);
            hht.insertAll(dummies);
            return hht;
        });

        // Plot results
        ChartPlotter.plotAndSave(
                ns, perfect, hierarchical,
                "O(n^2) Space Perfect HashTable", "O(n) Space Hierarchical HashTable",
                "Memory Usage (MB)",
                "Number of Elements Inserted (n)", "Size (MB)",
                "hashTables_space_comparison.png");
    }
}
