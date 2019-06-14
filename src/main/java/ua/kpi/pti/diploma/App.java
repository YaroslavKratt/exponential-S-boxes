package ua.kpi.pti.diploma;

import ua.kpi.pti.diploma.charts.BarChartForTables;
import ua.kpi.pti.diploma.charts.CustomBarChart;
import ua.kpi.pti.diploma.tables.DdtPlusPlus;
import ua.kpi.pti.diploma.tables.DdtXorPlus;
import ua.kpi.pti.diploma.tables.DdtXorXor;
import ua.kpi.pti.diploma.tables.ElTable;
import ua.kpi.pti.diploma.tables.LAT;
import ua.kpi.pti.diploma.tables.LambdaTable;
import ua.kpi.pti.diploma.tables.TableProvider;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        AgafonovCriteriaFilter agafonovCriteriaFilter = new AgafonovCriteriaFilter();
        List<TableProvider> tableProvidersPool = new ArrayList<>();
        Type type = Type.USUAL;
        List<Integer> alpas = agafonovCriteriaFilter
                .filterByOptimalDifferentialCharacteristics(AgafonovCriteriaFilter.findAllPrimitiveElementsOfField());
        alpas = agafonovCriteriaFilter.filterByMaximumAlgebraicDegree(alpas);


        tableProvidersPool.add(new DdtXorXor());
        tableProvidersPool.add(new DdtXorPlus());
        tableProvidersPool.add(new DdtPlusPlus());
        tableProvidersPool.add(new LAT());
        tableProvidersPool.add(new ElTable());
        tableProvidersPool.add(new LambdaTable());

        List<Integer> finalAlpas = alpas;
        tableProvidersPool.forEach(tableProvider -> {
            CustomBarChart chart = new BarChartForTables(tableProvider.getTableName());
            chart.printChart(tableProvider.calculateStatistics(finalAlpas, type)).saveChart(type.toString());
        });
    }
}

