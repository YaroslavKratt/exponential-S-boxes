package ua.kpi.pti.diploma.ddt;

import ua.kpi.pti.diploma.MatrixToCSVPrinter;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ua.kpi.pti.diploma.Constants.PATH_TO_PLUS_PLUS_FOLDER;
import static ua.kpi.pti.diploma.Constants.Q;
import static ua.kpi.pti.diploma.Constants.allExponents;

public class DdtPlusPlus implements DdtProvider {
    @Override
    public int[][] getDdt(int basis) {
        int[][] ddt = new int[Q][Q];

        for (int alpha = 0; alpha < Q; alpha++) {
            for (int x = 0; x < Q; x++) {
                int out = (allExponents.get(basis).get((x + alpha + Q) % Q) - allExponents.get(basis).get(x) + Q)%Q;
                ddt[alpha][out] = (ddt[alpha][out] + 1) % Q;
            }
        }
        return ddt;
    }

    @Override
    public Map<Integer, Integer> calculateStatistics(List<Integer> basises) {
        Map<Integer, Integer> result = new HashMap<>();
        for (Integer basis : basises) {
            int[][] ddt = getDdt(basis);

            try {
                MatrixToCSVPrinter.printMatrixToCSV(ddt, PATH_TO_PLUS_PLUS_FOLDER + Integer.toHexString(basis) + "__PLUS_PLUS.csv");
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
