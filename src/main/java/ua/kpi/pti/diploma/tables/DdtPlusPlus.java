package ua.kpi.pti.diploma.tables;

import ua.kpi.pti.diploma.utils.MatrixToCSVPrinter;
import ua.kpi.pti.diploma.tables.threads.ususal_threads.DdtPlusPlusThread;
import ua.kpi.pti.diploma.tables.threads.ususal_threads.TableThread;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ua.kpi.pti.diploma.utils.Constants.*;

public class DdtPlusPlus extends TableProvider {
    String tableName = "DDT PLUS PLUS";


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
        for (int i = 0; i < CORES; i++) {
            threadPool.add(new DdtPlusPlusThread(table, i, (i + 1) * Q / CORES, basis));
        }
        return this.threadPool;
    }

}
