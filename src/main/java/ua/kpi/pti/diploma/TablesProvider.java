package ua.kpi.pti.diploma;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static ua.kpi.pti.diploma.Constants.FIELD;
import static ua.kpi.pti.diploma.Constants.Q;

public class TablesProvider {
    Map<Integer, Integer> maximumsForEachAlpha = new HashMap<>();
    private int[][] ddt = new int[Q][Q];


    public int[][] provideDDT(List<Integer> alphas) {

        for (int alpha : alphas) {
            for (int x = 0; x < Q; x++) {
                int out = FIELD.exp(alpha, x ^ alpha) ^ FIELD.exp(alpha, x);
                ddt[alpha][out]++;
            }
        }
        return ddt;
    }

    private Map<Integer, Integer> countMaximumForEachAlpha(int[][] matrix) {
        Map<Integer, Integer> maximums = new HashMap<>();
        for (int i = 0; i < matrix.length; i++) {
            int max = 0;

            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] > max) {
                    max = matrix[i][j];
                }
            }
            maximums.put(i, max);
        }
        return maximums;
    }

    public Map<String, Integer[]> countAlphasforEachMaximum(int[][] matrix) {
        Map<Integer, Integer> alphasCount = new HashMap<>();
        Map<Integer, Integer> maximumsForEachAlpha = countMaximumForEachAlpha(matrix);
        Map<String, Integer[]> maximumsAndAlphas = new HashMap<>();

        for (int i = 0; i < Q; i++) {
            alphasCount.putIfAbsent(maximumsForEachAlpha.get(i), 0);
            alphasCount.put(maximumsForEachAlpha.get(i), alphasCount.get(maximumsForEachAlpha.get(i)) + 1);
        }
        Integer[] max = alphasCount.keySet().toArray(new Integer[0]);
        maximumsAndAlphas.put("max", alphasCount.keySet().toArray(new Integer[0]));
        maximumsAndAlphas.put("amountOfAlphas", alphasCount.values().toArray(new Integer[0]));

        return maximumsAndAlphas;
    }
}

