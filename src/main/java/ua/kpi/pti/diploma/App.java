package ua.kpi.pti.diploma;

import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.SwingWrapper;
import ua.kpi.pti.diploma.charts.BarChartForDDT;
import ua.kpi.pti.diploma.charts.CustomBarChart;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws IOException {
        AgafonovCriteriaFilter agafonovCriteriaFilter = new AgafonovCriteriaFilter();
        List<Integer> alpas = agafonovCriteriaFilter.filterByOptimalDifferentialCharacteristics(AgafonovCriteriaFilter.findAllPrimitiveElementsOfField());
        System.out.println("primitive:" + alpas.size());
        for (int r : alpas) {
            System.out.print(Integer.toHexString(r) + "; ");

        }
        System.out.println();
        alpas = agafonovCriteriaFilter.filterByMaximumAlgebraicDegree(alpas);
        System.out.println("alpas:" + alpas.size());
        for (int r : alpas) {
            System.out.print(Integer.toHexString(r) + "; ");

        }

        TablesProvider provider = new TablesProvider();
        Map<Integer,Integer> maxXorXor = provider.calculateStatistics(alpas);

        System.out.println("\n"+maxXorXor.keySet());
        System.out.println( maxXorXor.values());

        CustomBarChart<CategoryChart> xorXor = new BarChartForDDT();
        CategoryChart xorXorChart = xorXor.getChart(maxXorXor.keySet().toArray(new Integer[0]), maxXorXor.values().toArray(new Integer[0]));
        new SwingWrapper(xorXorChart).displayChart();



    }
}

