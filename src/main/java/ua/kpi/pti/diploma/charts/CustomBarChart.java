package ua.kpi.pti.diploma.charts;

import org.knowm.xchart.internal.chartpart.Chart;

import java.util.Map;

public interface CustomBarChart<C extends Chart<?, ?>> {


   BarChartForTables printChart(Map<String, String> maxInTables);
}
