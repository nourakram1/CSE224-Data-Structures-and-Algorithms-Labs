package Plotting;

import TreeSet.AVLSet;
import TreeSet.BSTSet;
import TreeSet.RedBlackSet;

import java.io.IOException;
import java.util.*;
import java.util.function.Supplier;

public class HeightBenchmark {
    static final int START = 2, END = 1_000_000, FACTOR = 2;

    public static void main(String[] args) throws IOException {
        List<Integer> ns = new ArrayList<>();
        List<Double> avlHeights = new ArrayList<>();
        List<Double> rbHeights = new ArrayList<>();
        List<Double> idealHeights = new ArrayList<>();

        Random rand = new Random();

        for (int n = START, idealHeight = 0; n <= END; n *= FACTOR, idealHeight++) {
            int items = n - 1;
            ns.add(items);

            List<Integer> keys = new ArrayList<>(rand.ints().distinct().limit(items).boxed().toList());

            double avlHeight = measureHeight(AVLSet::new, keys);
            double rbHeight = measureHeight(RedBlackSet::new, keys);

            avlHeights.add(avlHeight);
            rbHeights.add(rbHeight);
            idealHeights.add((double)idealHeight);

            System.out.printf("n = %d → AVL height: %.1f, RB height: %.1f%n", items, avlHeight, rbHeight);
        }

        ChartPlotter.plotAndSave(
                ns,
                new List[]{avlHeights, rbHeights, idealHeights},
                new String[]{"AVL Tree", "Red-Black Tree", "Ideal Height"},
                "Tree Height vs. Number of Elements",
                "Number of Elements (n)",
                "Height",
                "height_chart.png"
        );
    }

    private static double measureHeight(Supplier<BSTSet<Integer>> setSupplier, List<Integer> keys) {
        BSTSet<Integer> set = setSupplier.get();

        for (int key : keys) {
            set.insert(key);
        }

        return (double) set.height();
    }
}
