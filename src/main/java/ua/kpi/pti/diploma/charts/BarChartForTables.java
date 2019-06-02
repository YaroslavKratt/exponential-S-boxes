package ua.kpi.pti.diploma.charts;

import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.style.Styler;

import java.util.Arrays;
import java.util.Map;

public class BarChartForTables implements CustomBarChart<CategoryChart> {

    public void printChart(Map<Integer, Integer> maxInTables, String title) {
        Integer[] max = maxInTables.keySet().toArray(new Integer[0]);
        Integer[] amountOfAlpha = maxInTables.values().toArray(new Integer[0]);

        CategoryChart chart = new CategoryChartBuilder()
                .width(800)
                .height(600)
                .title(title)
                .xAxisTitle("Maximum")
                .yAxisTitle("Number of alpha")
                .build();

        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNW);
        chart.getStyler().setHasAnnotations(true);

        chart.addSeries(title, Arrays.asList(max), Arrays.asList(amountOfAlpha));
        new SwingWrapper(chart).displayChart();

    }


}
