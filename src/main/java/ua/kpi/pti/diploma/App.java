package ua.kpi.pti.diploma;

import ua.kpi.pti.diploma.charts.BarChartForTables;
import ua.kpi.pti.diploma.tables.*;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        AgafonovCriteriaFilter agafonovCriteriaFilter = new AgafonovCriteriaFilter();
        List<TableProvider> tableProvidersPool = new ArrayList<>();

        List<Integer> alpas = agafonovCriteriaFilter
                .filterByOptimalDifferentialCharacteristics(AgafonovCriteriaFilter.findAllPrimitiveElementsOfField());
        alpas = agafonovCriteriaFilter.filterByMaximumAlgebraicDegree(alpas);


        tableProvidersPool.add(new DdtXorXor());
        tableProvidersPool.add(new DdtXorPlus());
        tableProvidersPool.add(new DdtPlusPlus());
        tableProvidersPool.add(new LAT());
        tableProvidersPool.add(new ElTable());

        List<Integer> finalAlpas = alpas;
        tableProvidersPool.forEach(tableProvider ->
                new BarChartForTables()
                        .printChart(tableProvider.calculateStatistics(finalAlpas),tableProvider.getTableName()));
    }


}

