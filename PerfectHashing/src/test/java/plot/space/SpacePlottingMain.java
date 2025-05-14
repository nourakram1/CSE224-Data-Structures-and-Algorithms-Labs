package plot.space;

import plot.ChartPlotter;
import factory.StubFactory;
import stub.Stub;
import table.Hashtable;
import table.HierarchicalHashtable;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SpacePlottingMain {

    private static final int MAX_N = 2000;
    private static final int STEP = 100;
    private static final StubFactory stubFactory = new StubFactory();
    
    public static void main(String[] args) throws IOException {
        // Prepare N values
        List<Integer> ns = IntStream.iterate(STEP, i -> i + STEP)
                .limit(MAX_N / STEP)
                .boxed()
                .collect(Collectors.toList());

        // Measure sizes
        DeepSizeMeasurer measurer = new DeepSizeMeasurer();
        List<Double> perfect = measurer.measure(ns, n -> {
            Hashtable<Stub> ht = new Hashtable<>(n);
            List<Stub> stubs = stubFactory.createInstances(n);
            ht.insertAll(stubs);
            return ht;
        });
        List<Double> hierarchical = measurer.measure(ns, n -> {
            HierarchicalHashtable<Stub> hht = new HierarchicalHashtable<>(n);
            List<Stub> dummies = stubFactory.createInstances(n);
            hht.insertAll(dummies);
            return hht;
        });

        // Plot results
        ChartPlotter.plotAndSave(
                ns, perfect, hierarchical,
                "O(n^2) Space Perfect HashTable", "O(n) Space Hierarchical HashTable",
                "Memory Usage (MB)",
                "Number of Elements Inserted (n)", "Size (MB)",
                "src/test/resources/plots/space/hashtable_space_comparison.png");
    }
}