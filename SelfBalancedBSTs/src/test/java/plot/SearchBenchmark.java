package plot;

import tree.set.AVLSet;
import tree.set.BSTSet;
import tree.set.RedBlackSet;

import java.io.IOException;
import java.util.*;
import java.util.function.Supplier;

public class SearchBenchmark {
    static final int START = 50_000, END = 1_000_000, STEP = 50_000;

    public static void main(String[] args) throws IOException {
        List<Integer> ns = new ArrayList<>();
        List<Double> avlTimes = new ArrayList<>();
        List<Double> rbTimes = new ArrayList<>();

        Random rand = new Random();

        for (int n = START; n <= END; n += STEP) {
            ns.add(n);

            List<Integer> keys = new ArrayList<>(rand.ints().distinct().limit(n).boxed().toList());

            double timeAVL = measureSearchTime(AVLSet::new, keys);
            double timeRB = measureSearchTime(RedBlackSet::new, keys);

            avlTimes.add(timeAVL);
            rbTimes.add(timeRB);

            System.out.printf("n = %d → AVL Search: %.3f ms, RB Search: %.3f ms%n", n, timeAVL, timeRB);
        }

        ChartPlotter.plotAndSave(
                ns,
                Arrays.asList(avlTimes, rbTimes),
                new String[]{"AVL Tree", "Red-Black Tree"},
                "Search Time vs. Number of Elements",
                "Number of Elements (n)",
                "Time (µs)",
                "target/test-output/plots/search_time_chart.png"
        );

    }

    private static double measureSearchTime(Supplier<BSTSet<Integer>> setSupplier, List<Integer> keys) {
        final int trials = 3;
        double total = 0;

        for (int i = 0; i < trials; i++) {
            BSTSet<Integer> set = setSupplier.get();
            for (int key : keys) {
                set.insert(key);
            }

            List<Integer> shuffledKeys = new ArrayList<>(keys);
            Collections.shuffle(shuffledKeys);

            long start = System.nanoTime();
            for (int key : shuffledKeys) {
                set.search(key);
            }
            long end = System.nanoTime();

            total +=  (end - start) ;
        }

        return total / trials/ 1e3;
    }

}
