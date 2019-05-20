package ua.kpi.pti.diploma.charts;

import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.internal.chartpart.Chart;

public interface CustomBarChart<C extends Chart<?, ?>> {


    CategoryChart getChart(Integer[] maximums, Integer[] amountOfAlphaForEachMaximum);
}
