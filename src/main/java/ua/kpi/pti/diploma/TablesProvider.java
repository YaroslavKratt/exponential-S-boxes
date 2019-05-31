package ua.kpi.pti.diploma;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ua.kpi.pti.diploma.PreCalculationsAndConstants.*;

public class TablesProvider {


    public int[][] provideDdtXorXor(Integer basis) {
        int[][] ddt = new int[Q][Q];

        for (int alpha = 0; alpha < Q; alpha++) {
            for (int x = 0; x < Q; x++) {
                int out = allExponents.get(basis).get(x ^ alpha) ^ allExponents.get(basis).get(x);
                ddt[alpha][out] = (ddt[alpha][out] + 1) % Q;
            }
        }
        return ddt;
    }


    public int[][] provideDdtXorPlus(Integer basis) {
        int[][] ddt = new int[Q][Q];

        for (int alpha = 0; alpha < Q; alpha++) {
            for (int x = 0; x < Q; x++) {
                int out = (allExponents.get(basis).get(x ^ alpha) - allExponents.get(basis).get(x) + Q) % Q;
                ddt[alpha][out] = (ddt[alpha][out] + 1) % Q;
            }
        }
        return ddt;
    }

    public int[][] provideDdtPlusPlus(Integer basis) {
        int[][] ddt = new int[Q][Q];

        for (int alpha = 0; alpha < Q; alpha++) {
            for (int x = 0; x < Q; x++) {
                int out = (allExponents.get(basis).get((x + alpha + Q) % Q) - allExponents.get(basis).get(x) + Q)%Q;
                ddt[alpha][out] = (ddt[alpha][out] + 1) % Q;
            }
        }
        return ddt;
    }


    private int maxInTable(int[][] matrix) {
        int max = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
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
            int[][] ddt = provideDdtXorXor(basis);

            try {
                MatrixToCSVPrinter.printMatrixToCSV(ddt, PATH_TO_XOR_XOR_FOLDER + Integer.toHexString(basis) + "__XOR_XOR.csv");
            } catch (IOException e) {
                e.printStackTrace();
            }
            int max = maxInTable(ddt);


            result.putIfAbsent(max, 0);
            result.put(max, result.get(max) + 1);
        }
        return result;
    }


}

