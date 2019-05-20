package ua.kpi.pti.diploma;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
import static ua.kpi.pti.diploma.Constants.Q;

public class TablesProviderTest {
    private final int EXPECTED_SUM = Q;
    TablesProvider provider = new TablesProvider();


    @Test
    public void rowsSumMustBeAsExpected() {
        AgafonovCriteriaFilter agafonovCriteriaFilter = new AgafonovCriteriaFilter();
        List<Integer> alpas = agafonovCriteriaFilter.filterByOptimalDifferentialCharacteristics(AgafonovCriteriaFilter.findAllPrimitiveElementsOfField());
        alpas = agafonovCriteriaFilter.filterByMaximumAlgebraicDegree(alpas);
        int[][] matrix = provider.provideDDT(alpas);
        int sum = 0;

    assertEquals(EXPECTED_SUM,sum);
    }
}