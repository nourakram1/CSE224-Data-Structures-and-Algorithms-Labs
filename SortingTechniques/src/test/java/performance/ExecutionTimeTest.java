package performance;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import sort.Sort;
import sort.SortFactory;
import sort.SortType;
import sort.comparison.ComparisonSortType;
import sort.noncomparison.NonComparisonSortType;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExecutionTimeTest {

    private static final int[] ARRAY_SIZES = IntStream.iterate(25, n -> n <= 2_500, n -> n + 25).toArray();
    private static final int REPEATS = 40;
    private static final Map<SortType, List<Long>> executionTimes = new ConcurrentHashMap<>();
    private static final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

    @BeforeAll
    public static void setup() {
        for (ComparisonSortType comparisonSortType : ComparisonSortType.values()) {
            if (comparisonSortType == ComparisonSortType.BOGOSORT) continue;
            executionTimes.put(comparisonSortType, new ArrayList<>());
        }
        for (NonComparisonSortType nonComparisonSortType : NonComparisonSortType.values()) {
            executionTimes.put(nonComparisonSortType, new ArrayList<>());
        }
    }

    @Test
    public void testSortingAlgorithms() {
        for (SortType algorithm : executionTimes.keySet()) {
            System.out.println("\n——— Testing " + algorithm.getName() + " ———");
            for (int size : ARRAY_SIZES) {
                Integer[] randomArray = generateRandomArray(size);
                measureSortingTime(algorithm, randomArray.clone(), size);
            }
        }

        showChartAndWait();  // Show the graph and wait for user to close it
    }

    private void measureSortingTime(SortType algorithm, Integer[] array, int size) {
        long totalDurationNs = 0;
        Sort<Integer> sort;
        for (int repeat = 0; repeat < REPEATS; repeat++) {
            Integer[] arrayCopy = array.clone();
            if (algorithm instanceof ComparisonSortType) {
                sort = SortFactory.getComparisonSort((ComparisonSortType) algorithm, false);
            }
            else {
                sort = SortFactory.getNonComparisonSort(0, (NonComparisonSortType) algorithm, false);
            }
            long startTime = System.nanoTime();
            sort.sort(arrayCopy);
            long endTime = System.nanoTime();

            totalDurationNs += (endTime - startTime);

            assertTrue(isSorted(arrayCopy), algorithm.getName() + " did not sort correctly");
        }

        long avgDurationUs = (totalDurationNs / REPEATS) / 1_000;
        executionTimes.get(algorithm).add(avgDurationUs);
        dataset.addValue(avgDurationUs, algorithm.getName(), String.valueOf(size));

        System.out.printf("%s took %d µs (average over %d runs)%n", algorithm.getName(), avgDurationUs, REPEATS);
    }

    private boolean isSorted(Integer[] array) {
        for (int i = 1; i < array.length; i++) {
            if (array[i - 1] > array[i]) {
                return false;
            }
        }
        return true;
    }

    private Integer[] generateRandomArray(int size) {
        Random rand = new Random();
        return rand.ints(size, 1, 1_000_000).boxed().toArray(Integer[]::new);
    }

    private void showChartAndWait() {
        JFreeChart chart = ChartFactory.createLineChart(
                "Sorting Algorithm Execution Time",
                "Array Size",
                "Time (µs)",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false
        );

        JFrame frame = new JFrame("Sorting Performance");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  // Allow test to continue after closing
        frame.setSize(1000, 800);
        frame.setLayout(new BorderLayout());

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(1000, 800));
        frame.add(chartPanel, BorderLayout.CENTER);

        frame.pack();
        frame.setVisible(true);

        // Block execution until the user closes the window
        synchronized (frame) {
            try {
                frame.wait();  // Waits indefinitely until notified
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
