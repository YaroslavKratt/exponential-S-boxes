package ua.kpi.pti.diploma.charts;

import org.knowm.xchart.internal.chartpart.Chart;

import java.util.Map;

public interface CustomBarChart<C extends Chart<?, ?>> {


   void printChart(Map<Integer, Integer> maxInTables,String title);
}
