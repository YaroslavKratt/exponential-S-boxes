package ua.kpi.pti.diploma.charts;

import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.style.Styler;

import java.util.Arrays;

public class BarChartForDDT implements CustomBarChart<CategoryChart> {

    @Override
    public CategoryChart getChart(Integer[] maximums, Integer[] amountOfAlphaForEachMaximum) {
        CategoryChart chart = new CategoryChartBuilder().width(800).height(600).title("DDT").xAxisTitle("Maximum").yAxisTitle("Number of alpha").build();

        // Customize Chart
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNW);
        chart.getStyler().setHasAnnotations(true);

        // Series
        chart.addSeries("exponential_s_boxes.exponential_s_boxes.test 1", Arrays.asList(maximums), Arrays.asList(amountOfAlphaForEachMaximum));

        return chart;
    }
}
