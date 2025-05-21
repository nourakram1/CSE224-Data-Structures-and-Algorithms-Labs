package plot;

import tree.set.AVLSet;
import tree.set.RedBlackSet;
import tree.set.BSTSet;

import java.io.IOException;
import java.util.*;
import java.util.function.Supplier;

public class DeletionBenchmark {
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

            double timeAVL = measureDeletionTime(AVLSet::new, keys);
            double timeRB = measureDeletionTime(RedBlackSet::new, keys);

            avlTimes.add(timeAVL);
            rbTimes.add(timeRB);

            System.out.printf("n = %d → AVL Delete: %.3f µs, RB Delete: %.3f µs%n", n, timeAVL, timeRB);
        }

        ChartPlotter.plotAndSave(
                ns,
                Arrays.asList(avlTimes, rbTimes),
                new String[]{"AVL Tree", "Red-Black Tree"},
                "Deletion Time vs. Number of Elements",
                "Number of Elements (n)",
                "Time (µs)",
                "target/test-output/plots/delete_time_chart.png"
        );
    }

    private static double measureDeletionTime(Supplier<BSTSet<Integer>> setSupplier, List<Integer> keys) {
        final int trials = 5;
        double totalTime = 0;

        for (int t = 0; t < trials; t++) {
            BSTSet<Integer> set = setSupplier.get();

            for (int key : keys) {
                set.insert(key);
            }

            List<Integer> shuffledKeys = new ArrayList<>(keys);
            Collections.shuffle(shuffledKeys);

            long start = System.nanoTime();
            for (int key : shuffledKeys) {
                set.delete(key);
            }
            long end = System.nanoTime();

            totalTime += (end - start);
        }

        return totalTime / trials / 1e3; // return µs
    }
}
