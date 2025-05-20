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
            List<List<Double>> series,
            String[] labels,
            String title,
            String xAxis,
            String yAxis,
            String outputFile) throws IOException {

        if (series.size() != labels.length) {
            throw new IllegalArgumentException("Number of series and labels must be the same");
        }

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i = 0; i < ns.size(); i++) {
            for (int s = 0; s < series.size(); s++) {
                dataset.addValue(series.get(s).get(i), labels[s], ns.get(i));
            }
        }

        JFreeChart chart = ChartFactory.createLineChart(
                title, xAxis, yAxis, dataset,
                PlotOrientation.VERTICAL, true, true, false);

        ChartUtils.saveChartAsPNG(new File(outputFile), chart, 1500, 1000);
        SwingUtilities.invokeLater(() -> {
            JFrame f = new JFrame(title);
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.add(new ChartPanel(chart));
            f.pack();
            f.setVisible(true);
        });
    }
}