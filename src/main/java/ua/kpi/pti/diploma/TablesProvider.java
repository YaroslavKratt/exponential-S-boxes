package ua.kpi.pti.diploma;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ua.kpi.pti.diploma.PreCalculationsAndConstants.*;

public class TablesProvider {
    Map<Integer, Integer> maximumsForEachAlpha = new HashMap<>();
    private int[][] ddt = new int[Q][Q];


    public int[][] provideDdtXorXor(Integer basis) {
        for (int alpha = 0; alpha < Q; alpha++) {
            for (int x = 0; x < Q; x++) {
                int out = allExponents.get(basis).get(x ^ alpha) ^ allExponents.get(basis).get(x) % Q;
                ddt[alpha][out]++;
            }
        }
        return ddt;
    }


    public int[][] provideDdtXorPlus(Integer basis) {
        for (int alpha = 0; alpha < Q; alpha++) {
            for (int x = 0; x < Q; x++) {
                int out = (allExponents.get(basis).get(x ^ alpha) - allExponents.get(basis).get(x) + Q) % Q;
                ddt[alpha][out]++;
            }
        }
        return ddt;
    }

    public int[][] provideDdtPlusPlus(Integer basis) {

        for (int alpha = 0; alpha < Q; alpha++) {
            for (int x = 0; x < Q; x++) {
                int out = (allExponents.get(basis).get((x + alpha + Q) % Q) - allExponents.get(basis).get(x) + Q) % Q;
                ddt[alpha][out]++;
            }
        }
        return ddt;
    }


    private int maxInTable(int[][] matrix) {
        int max = 0;
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[i].length; j++) {
                if (matrix[i][j] > max) {
                    max = matrix[i][j];
                }
            }
        }
        return max;
    }

    public Map<Integer, Integer> calculateStatistics(List<Integer> basises) {
        Map<Integer, Integer> result = new HashMap<>();
        for (Integer basis : basises) {
            int max = maxInTable(provideDdtXorXor(basis));
            result.putIfAbsent(max, 0);
            result.put(max, result.get(max) + 1);
        }
        return result;
    }


}

