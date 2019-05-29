package ua.kpi.pti.diploma;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
import static ua.kpi.pti.diploma.Constants.Q;

public class TablesProviderTest {
    private final int EXPECTED_SUM = Q;
    TablesProvider provider = new TablesProvider();


    @Test
    public void rowsSumMustBeAsExpected() throws Exception {
        AgafonovCriteriaFilter agafonovCriteriaFilter = new AgafonovCriteriaFilter();
        List<Integer> alpas = agafonovCriteriaFilter.filterByOptimalDifferentialCharacteristics(AgafonovCriteriaFilter.findAllPrimitiveElementsOfField());
        alpas = agafonovCriteriaFilter.filterByMaximumAlgebraicDegree(alpas);
        int[][] matrix = provider.provideDDT(alpas);
        for (int j = 0; j < matrix[0].length; j++) {
            int sum = 0;
            for (int i = 0; i < matrix[0].length; i++) {
                sum += matrix[j][i];
            }
            if(sum!=EXPECTED_SUM){
                throw new Exception();
            }
        }

    }

    @Test
    public void columnsSumMustBeAsExpected() throws Exception {
        AgafonovCriteriaFilter agafonovCriteriaFilter = new AgafonovCriteriaFilter();
        List<Integer> alpas = agafonovCriteriaFilter.filterByOptimalDifferentialCharacteristics(AgafonovCriteriaFilter.findAllPrimitiveElementsOfField());
        alpas = agafonovCriteriaFilter.filterByMaximumAlgebraicDegree(alpas);
        int[][] matrix = provider.provideDDT(alpas);
        for (int j = 0; j < matrix[0].length; j++) {
            int sum = 0;
            for (int i = 0; i < matrix[0].length; i++) {
                sum += matrix[i][j];
            }
            System.out.print(sum+" ");

            if(sum!=EXPECTED_SUM){
                throw new Exception();
            }
        }

    }



}