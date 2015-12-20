package es.elka.koda.generator;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.statistics.HistogramDataset;

import java.io.File;
import java.io.IOException;

/***
 * Klasa odpowiedzialna za zapis pliku z histogramem
 */
public class HistogramDrawer {

    /***
     * Zapis histogramu
     *
     * @param values   tablica wartości
     * @param fileName Nazwa pliku do zapisania
     */
    public void draw(double[] values, String fileName) {

        HistogramDataset dataset = new HistogramDataset();

        dataset.addSeries("H1", values, 255);

        JFreeChart chart = ChartFactory.createHistogram(fileName, "", "", dataset, PlotOrientation.VERTICAL, false, false, false);

        int width = 1000;
        int height = 300;

        try {
            ChartUtilities.saveChartAsPNG(new File(fileName + ".png"), chart, width, height);
        } catch (IOException e) {
            System.err.println("\tSaving error : " + e.getMessage());
        }
    }

    /***
     * Zapis histogramu
     *
     * @param values tablica wartości
     */
    public void draw(double[] values) {
        this.draw(values, "histogram");
    }
}
