package ua.kpi.pti.diploma.tables;

import ua.kpi.pti.diploma.MatrixToCSVPrinter;
import ua.kpi.pti.diploma.tables.threads.DdtPlusPlusThread;
import ua.kpi.pti.diploma.tables.threads.TableThread;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ua.kpi.pti.diploma.Constants.*;

public class DdtPlusPlus extends TableProvider {
    String tableName = "DDT PLUS PLUS";

    @Override
    public int[][] getTable(int basis) {
        int[][] ddt = new int[Q][Q];

        calculateWithMultiThreads(getThreadPool(ddt, basis));
        return ddt;
    }

    @Override
    public Map<Integer, Integer> calculateStatistics(List<Integer> basises) {
        Map<Integer, Integer> result = new HashMap<>();
        for (Integer basis : basises) {
            int[][] ddt = getTable(basis);

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

    public String getTableName() {
        return tableName;
    }

    @Override
    public List<TableThread> getThreadPool(int[][] table, int basis) {
        threadPool = new ArrayList<>();

        threadPool.add(new DdtPlusPlusThread(table, 0, Q / CORES, basis));
        threadPool.add(new DdtPlusPlusThread(table, Q / CORES, 2 * Q / CORES, basis));
        threadPool.add(new DdtPlusPlusThread(table, 2 * Q / CORES, 3 * Q / CORES, basis));
        threadPool.add(new DdtPlusPlusThread(table, 3 * Q / CORES, 4 * Q / CORES, basis));
        threadPool.add(new DdtPlusPlusThread(table, 4 * Q / CORES, Q, basis));

        return this.threadPool;
    }
}
