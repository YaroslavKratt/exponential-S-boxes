package ua.kpi.pti.diploma;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ua.kpi.pti.diploma.Constants.*;

public class TablesProvider {
    Map<Integer, Integer> maximumsForEachAlpha = new HashMap<>();
    private int[][] ddt = new int[Q][Q];


    public int[][] provideDdtXorXor(Integer basis, HashMap<Integer,List<Integer>> allExponents) {
        for (int alpha = 0; alpha < Q; alpha++) {
            for (int x = 0; x < Q; x++) {
                int out = allExponents.get(basis).get( x ^ alpha) ^ allExponents.get(basis).get(x) % Q;
                ddt[alpha][out]++;
            }
        }
        return ddt;
    }
//todo
    public int[][] provideDdtXorPlus(Integer basis ) {
        for (int alpha = 0; alpha < Q; alpha++) {
            for (int x = 0; x < Q; x++) {
                int out = allExponents.get(basis).get( x ^ alpha) ^ allExponents.get(basis).get(x) % Q;
                ddt[alpha][out]++;
            }
        }
        return ddt;;
    }

    public int[][] provideDdtPlusPlus(List<Integer> alphas) {

        for (int alpha : alphas) {
            for (int beta : alphas) {

                for (int x = 0; x < Q; x++) {
                    if (FIELD.exp(alpha, FIELD.add(x, alpha)) == FIELD.add(FIELD.exp(alpha, x), beta)) {
                        ddt[alpha][beta] = ddt[alpha][beta] % Q + 1;
                    }
                }

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

