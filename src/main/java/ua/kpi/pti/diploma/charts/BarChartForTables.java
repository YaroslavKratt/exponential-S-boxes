package ua.kpi.pti.diploma.charts;

import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

public class BarChartForTables implements CustomBarChart<CategoryChart> {
   private CategoryChart chart;
   private String title;

    public BarChartForTables(String title) {
        this.title = title;
    }

    public BarChartForTables printChart(Map<Integer, Integer> maxInTables) {
        Integer[] max = maxInTables.keySet().toArray(new Integer[0]);
        Integer[] amountOfAlpha = maxInTables.values().toArray(new Integer[0]);

      chart= new CategoryChartBuilder()
                .width(800)
                .height(600)
                .title(title)
                .xAxisTitle("Максимуми")
                .yAxisTitle("Кількість експонент")
                .build();

        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNW);
        chart.getStyler().setHasAnnotations(true);

        chart.addSeries(title, Arrays.asList(max), Arrays.asList(amountOfAlpha));
        new SwingWrapper(chart).displayChart();
        return this;
    }
    public void saveChart(String tybe) {
        try {

            BitmapEncoder.saveBitmap(chart, "./"+tybe+"__"+ title, BitmapEncoder.BitmapFormat.JPG);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
