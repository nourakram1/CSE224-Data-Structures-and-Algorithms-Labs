package plot;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.List;


public class ChartPlotter {

    public static void plotAndSave(
            List<Integer> ns,
            List<Double> series1,
            List<Double> series2,
            String label1,
            String label2,
            String title,
            String xAxis,
            String yAxis,
            String outputFile) throws IOException {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i = 0; i < ns.size(); i++) {
            dataset.addValue(series1.get(i), label1, ns.get(i));
            dataset.addValue(series2.get(i), label2, ns.get(i));
        }
        JFreeChart chart = ChartFactory.createLineChart(
                title, xAxis, yAxis, dataset,
                PlotOrientation.VERTICAL, true, true, false);
        ChartUtils.saveChartAsPNG(new File(outputFile), chart, 900, 600);
        SwingUtilities.invokeLater(() -> {
            JFrame f = new JFrame(title);
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.add(new ChartPanel(chart));
            f.pack();
            f.setVisible(true);
        });
    }
}