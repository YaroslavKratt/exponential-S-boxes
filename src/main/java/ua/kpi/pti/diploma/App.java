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
        Map<String,Integer[]> maxAndAlphasXorXor = provider.countAlphasforEachMaximum(provider.provideDdtXorXor(alpas));
        Map<String,Integer[]> maxAndAlphasXorPlus = provider.countAlphasforEachMaximum(provider.provideDdtXorPlus(alpas));
        Map<String,Integer[]> maxAndAlphasPlusPlus = provider.countAlphasforEachMaximum(provider.provideDdtPlusPlus(alpas));


        CustomBarChart<CategoryChart> xorXor = new BarChartForDDT();
        CategoryChart xorXorChart = xorXor.getChart(maxAndAlphasXorXor.get("max"),maxAndAlphasXorXor.get("amountOfAlphas"));
        new SwingWrapper<CategoryChart>(xorXorChart).displayChart();

        CustomBarChart<CategoryChart> xorPlus = new BarChartForDDT();
        CategoryChart xorPlusChart = xorPlus.getChart(maxAndAlphasXorPlus.get("max"),maxAndAlphasXorPlus.get("amountOfAlphas"));
        xorPlusChart.setTitle("DDT XOR plus");
        new SwingWrapper<CategoryChart>(xorPlusChart).displayChart();

        CustomBarChart<CategoryChart> plusPlus = new BarChartForDDT();
        CategoryChart plusPlusChart = plusPlus.getChart(maxAndAlphasPlusPlus.get("max"),maxAndAlphasPlusPlus.get("amountOfAlphas"));
        plusPlusChart.setTitle("DDT + +");
        new SwingWrapper<CategoryChart>(plusPlusChart).displayChart();


    }
}

