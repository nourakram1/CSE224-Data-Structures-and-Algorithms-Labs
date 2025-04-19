import graphDataStructure.DirectedEdge;
import graphDataStructure.Graph;
import graphDataStructure.WeightedDigraph;
import shortestPathAlgorithms.BellmanFord;
import shortestPathAlgorithms.Dijkstra;
import shortestPathAlgorithms.FloydWarshall;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.ChartUtils;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.*;

public class ShortestPathBenchmarkPlotter {
    static Random rand = new Random();

    public static void main(String[] args) {
        int[] sizes = {50, 100, 200, 300, 400, 500, 600, 700, 800};
        double[] densities = {0.2, 0.5, 0.8, 1};
        int trials = 3;

        Map<String, DefaultCategoryDataset> datasets = new HashMap<>();
        datasets.put("single-source", new DefaultCategoryDataset());
        datasets.put("all-pairs", new DefaultCategoryDataset());

        for (int size : sizes) {
            for (double density : densities) {
                Graph g = generateRandomGraph(size, density);
                int source = 0;

                // --- Bellman-Ford ---
                long bfSingle = averageTime(trials, () -> new BellmanFord(g, source));
                datasets.get("single-source").addValue(bfSingle, "BellmanFord (d=" + density + ")", size + "");

                long bfAll = averageTime(trials, () -> {
                    for (int i = 0; i < size; i++) new BellmanFord(g, i);
                });
                datasets.get("all-pairs").addValue(bfAll, "BellmanFord (d=" + density + ")", size + "");

                // --- Dijkstra ---
                long dijSingle = averageTime(trials, () -> new Dijkstra(g, source));
                datasets.get("single-source").addValue(dijSingle, "Dijkstra (d=" + density + ")", size + "");

                long dijAll = averageTime(trials, () -> {
                    for (int i = 0; i < size; i++) new Dijkstra(g, i);
                });
                datasets.get("all-pairs").addValue(dijAll, "Dijkstra (d=" + density + ")", size + "");

                // --- Floyd-Warshall (only small graphs) ---
                if (size <= 200) {
                    long fwAll = averageTime(trials, () -> new FloydWarshall(g));
                    datasets.get("all-pairs").addValue(fwAll, "FloydWarshall (d=" + density + ")", size + "");
                }
            }
        }

        SwingUtilities.invokeLater(() -> {
            showChart(datasets.get("single-source"), "Single-Source Shortest Path Timing", "Graph Size", "Time (ms)");
            showChart(datasets.get("all-pairs"), "All-Pairs Shortest Path Timing", "Graph Size", "Time (ms)");
        });
    }

    private static Graph generateRandomGraph(int nodes, double density) {
        Graph g = new WeightedDigraph(nodes);
        int maxEdges = nodes * (nodes - 1);
        int edgeCount = (int) (density * maxEdges);

        Set<String> existing = new HashSet<>();
        while (edgeCount-- > 0) {
            int u = rand.nextInt(nodes);
            int v = rand.nextInt(nodes);
            if (u != v && existing.add(u + "->" + v)) {
                double weight = 1 + rand.nextInt(10);
                g.addEdge(new DirectedEdge(u, v, weight));
            }
        }
        return g;
    }

    private static long averageTime(int trials, Runnable algorithm) {
        long totalTime = 0;
        for (int i = 0; i < trials; i++) {
            long t1 = System.nanoTime();
            algorithm.run();
            long t2 = System.nanoTime();
            totalTime += (t2 - t1);
        }
        return totalTime / trials / 1_000_000; // return in milliseconds
    }

    private static void showChart(DefaultCategoryDataset dataset, String title, String xLabel, String yLabel) {
        JFreeChart chart = ChartFactory.createLineChart(
                title,
                xLabel,
                yLabel,
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false
        );

        // Enhance plot appearance
        chart.setBackgroundPaint(Color.WHITE);
        chart.getTitle().setFont(new Font("SansSerif", Font.BOLD, 18));
        chart.getLegend().setItemFont(new Font("SansSerif", Font.PLAIN, 14));

        CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(new Color(240, 240, 240));
        plot.setRangeGridlinePaint(Color.GRAY);
        plot.setOutlineVisible(false);

        CategoryItemRenderer renderer = plot.getRenderer();
        for (int i = 0; i < dataset.getRowCount(); i++) {
            renderer.setSeriesStroke(i, new BasicStroke(2.0f));
        }

        // Display chart in window
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new ChartPanel(chart));
        frame.setSize(1000, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // Optionally save chart as PNG (uncomment if desired)
        try {
            ChartUtils.saveChartAsPNG(new File(title.replaceAll("\\s+", "_") + ".png"), chart, 1000, 600);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
