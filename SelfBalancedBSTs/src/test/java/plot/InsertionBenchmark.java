package plot;

import tree.set.AVLSet;
import tree.set.RedBlackSet;
import tree.set.BSTSet;

import java.io.IOException;
import java.util.*;
import java.util.function.Supplier;

public class InsertionBenchmark {
    static final int START = 50_000, END = 1_000_000, STEP = 50_000;

    public static void main(String[] args) throws IOException {
        List<Integer> ns = new ArrayList<>();
        List<Double> avlTimes = new ArrayList<>();
        List<Double> rbTimes = new ArrayList<>();

        Random rand = new Random();
        for (int n = START; n <= END; n += STEP) {
            ns.add(n);

            List<Integer> keys = rand.ints()
                    .distinct()
                    .limit(n)
                    .boxed()
                    .toList();

            double timeAVL = measureInsertionTime(AVLSet::new, keys);
            double timeRB = measureInsertionTime(RedBlackSet::new, keys);

            avlTimes.add(timeAVL);
            rbTimes.add(timeRB);

            System.out.printf("n = %d → AVL: %.3f ms, RB: %.3f ms%n", n, timeAVL, timeRB);
        }

        ChartPlotter.plotAndSave(
                ns,
                Arrays.asList(avlTimes, rbTimes),
                new String[]{"AVL Tree", "Red-Black Tree"},
                "Insertion Time vs. Number of Elements",
                "Number of Elements (n)",
                "Time (µs)",
                "target/test-output/plots/insertion_time_chart.png"
        );

    }

    private static double measureInsertionTime(Supplier<BSTSet<Integer>> setSupplier, List<Integer> keys) {
        BSTSet<Integer> set = setSupplier.get();
        long sum = 0;
        for (int key : keys) {
            long start = System.nanoTime();
            set.insert(key);
            long end = System.nanoTime();
            sum += end - start;
        }
        return (double) sum / keys.size() / 1e3;
    }
}
