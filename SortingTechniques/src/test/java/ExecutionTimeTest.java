import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.junit.jupiter.api.*;
import sort.Sort;
import sort.SortFactory;
import sort.SortType;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ExecutionTimeTest {

    private static final int[] ARRAY_SIZES = {100, 150, 300, 450, 600, 750, 1_000, 2_000, 3_000, 4_000, 5_000, 7_500, 10_000,12_500,15_000, 17_500,20_000, 22_500, 25_000,30_000, 35_000, 40_000, 45_000, 50_000, 75_000, 100_000,250_000,500_000,750_000, 1_000_000};
    private final Map<SortType, List<Long>> executionTimes = new HashMap<>();

    @Test
    void testSortingExecutionTimes() {
        for (SortType algorithm : SortType.values()) {
            if (algorithm != SortType.COUNTING_SORT && algorithm != SortType.RADIX_SORT) continue;
            executionTimes.put(algorithm, new ArrayList<>());
        }

        for (int size : ARRAY_SIZES) {
            Integer[] randomArray = generateRandomArray(size);
            System.out.println("\n=== Sorting " + size + " elements ===");

            for (SortType algorithm : SortType.values()) {
                if (algorithm != SortType.COUNTING_SORT && algorithm != SortType.RADIX_SORT) continue;

                Integer[] arrayCopy = randomArray.clone();
                long startTime = System.nanoTime();
                Sort<Integer> sorter = SortFactory.getSort(algorithm, false);
                sorter.sort(arrayCopy);
                long endTime = System.nanoTime();

                long durationMs = (endTime - startTime) / 1_000_000;
                executionTimes.get(algorithm).add(durationMs);

                System.out.printf("%s took %d ms%n", algorithm.getName(), durationMs);
                Assertions.assertTrue(isSorted(arrayCopy), algorithm.getName() + " did not sort correctly.");
            }
        }

        // Ensure the GUI runs on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(this::createChart);

        // Prevent JUnit from terminating immediately (debugging purpose)
        try {
            Thread.sleep(9999999);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private Integer[] generateRandomArray(int size) {
        Random rand = new Random();
        Integer[] array = new Integer[size];
        for (int i = 0; i < size; i++) {
            array[i] = rand.nextInt(10000);
        }
        return array;
    }

    private boolean isSorted(Integer[] array) {
        for (int i = 1; i < array.length; i++) {
            if (array[i - 1] > array[i]) return false;
        }
        return true;
    }

    private void createChart() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (SortType algorithm : executionTimes.keySet()) {
            List<Long> times = executionTimes.get(algorithm);
            for (int i = 0; i < ARRAY_SIZES.length; i++) {
                dataset.addValue(times.get(i), algorithm.getName(), String.valueOf(ARRAY_SIZES[i]));
            }
        }

        JFreeChart chart = ChartFactory.createLineChart(
                "Sorting Execution Time",
                "Array Size",
                "Time (ms)",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Sorting Performance");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(10_000, 10_0000);

            ChartPanel chartPanel = new ChartPanel(chart);
            chartPanel.setPreferredSize(new Dimension(10_000, 10_000));
            frame.add(chartPanel);
            frame.pack();
            frame.setVisible(true);
        });
    }
}
