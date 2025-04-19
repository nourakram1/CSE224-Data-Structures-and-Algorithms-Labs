import graphDataStructure.DirectedEdge;
import graphDataStructure.Graph;
import graphDataStructure.WeightedDigraph;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import shortestPathAlgorithms.BellmanFord;
import shortestPathAlgorithms.Dijkstra;
import shortestPathAlgorithms.FloydWarshall;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class ShortestPathBenchmarkPlotter {
    static Random rand = new Random();

    public static void main(String[] args) {
        int[] sizes = {50, 100, 200, 300, 400, 500, 600, 700, 800};
        double[] densities = {0.1};
        int trials = 3;

        Map<Double, DefaultCategoryDataset> singleSourceDatasets = new HashMap<>();
        Map<Double, DefaultCategoryDataset> allPairsDatasets = new HashMap<>();

        for (double density : densities) {
            singleSourceDatasets.put(density, new DefaultCategoryDataset());
            allPairsDatasets.put(density, new DefaultCategoryDataset());
        }

        for (int size : sizes) {
            for (double density : densities) {
                DefaultCategoryDataset ssDataset = singleSourceDatasets.get(density);
                DefaultCategoryDataset apDataset = allPairsDatasets.get(density);

                double bfSSAvg = 0, bfAPAvg = 0, djSSAvg = 0, djAPAvg = 0, fwAvg = 0;

                for (int t = 0; t < trials; t++) {
                    Graph g = generateRandomGraph(size, density);
                    int source = 0;

                    // Bellman-Ford single-source
                    long t1 = System.nanoTime();
                    new BellmanFord(g, source);
                    long t2 = System.nanoTime();
                    bfSSAvg += (t2 - t1) / 1e6;

                    // Bellman-Ford all-pairs
                    t1 = System.nanoTime();
                    for (int i = 0; i < size; i++) new BellmanFord(g, i);
                    t2 = System.nanoTime();
                    bfAPAvg += (t2 - t1) / 1e6;

                    // Dijkstra single-source
                    t1 = System.nanoTime();
                    new Dijkstra(g, source);
                    t2 = System.nanoTime();
                    djSSAvg += (t2 - t1) / 1e6;

                    // Dijkstra all-pairs
                    t1 = System.nanoTime();
                    for (int i = 0; i < size; i++) new Dijkstra(g, i);
                    t2 = System.nanoTime();
                    djAPAvg += (t2 - t1) / 1e6;

                    // Floyd-Warshall all-pairs only
                    t1 = System.nanoTime();
                    new FloydWarshall(g);
                    t2 = System.nanoTime();
                    fwAvg += (t2 - t1) / 1e6;
                }

                // Take the average
                bfSSAvg /= trials;
                bfAPAvg /= trials;
                djSSAvg /= trials;
                djAPAvg /= trials;
                fwAvg /= trials;

                String sizeStr = size + "";
                ssDataset.addValue(bfSSAvg, "Bellman-Ford", sizeStr);
                ssDataset.addValue(djSSAvg, "Dijkstra", sizeStr);

                apDataset.addValue(bfAPAvg, "Bellman-Ford", sizeStr);
                apDataset.addValue(djAPAvg, "Dijkstra", sizeStr);
                apDataset.addValue(fwAvg, "Floyd-Warshall", sizeStr);
            }
        }

        // Show charts per density and save as PNG
        SwingUtilities.invokeLater(() -> {
            for (double density : densities) {
                DefaultCategoryDataset ssDataset = singleSourceDatasets.get(density);
                DefaultCategoryDataset apDataset = allPairsDatasets.get(density);

                // Show and save charts as PNG
                showAndSaveChart(ssDataset,
                        "Single-Source Shortest Path (Density = " + density + ")",
                        "Graph Size", "Time (ms)", "single_source_" + density + ".png");

                showAndSaveChart(apDataset,
                        "All-Pairs Shortest Path (Density = " + density + ")",
                        "Graph Size", "Time (ms)", "all_pairs_" + density + ".png");
            }
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

    private static void showAndSaveChart(DefaultCategoryDataset dataset, String title, String xLabel, String yLabel, String fileName) {
        JFreeChart chart = ChartFactory.createLineChart(
                title, xLabel, yLabel, dataset,
                PlotOrientation.VERTICAL, true, true, false
        );

        // Show the chart in a JFrame
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new ChartPanel(chart));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // Save the chart as a PNG file
        try {
            File outputFile = new File(fileName);
            org.jfree.chart.ChartUtils.saveChartAsPNG(outputFile, chart, 800, 600);
            System.out.println("Chart saved as " + outputFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
