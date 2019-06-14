package ua.kpi.pti.diploma.charts;

import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.SwingWrapper;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

public class BarChartForTables implements CustomBarChart<CategoryChart> {
    private CategoryChart chart;
    private String title;
    private String chartPath;

    public BarChartForTables(String title) {
        this.title = title;
    }

    public BarChartForTables printChart(Map<String, String> maxInTables) {
        Integer[] max = maxInTables.keySet().toArray(new Integer[0]);
        Integer[] amountOfAlpha = maxInTables.values().toArray(new Integer[0]);
        chart = new CategoryChartBuilder()
                .width(800)
                .height(600)
                .title(title)
                .xAxisTitle("Максимуми")
                .yAxisTitle("Кількість експонент")
                .build();

        //chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNW);
        chart.getStyler().setHasAnnotations(true);
        chart.getStyler().setYAxisDecimalPattern("#,###,###,###,###,###,###,###,###,###,###,###,###,###");
        chart.addSeries(title, Arrays.asList(max), Arrays.asList(amountOfAlpha));
        new SwingWrapper(chart).displayChart();
        return this;
    }

    public BarChartForTables saveChart(String type) {
        try {
            chartPath = "./" + type + "__" + title;
            BitmapEncoder.saveBitmap(chart, chartPath, BitmapEncoder.BitmapFormat.JPG);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;

    }
}
