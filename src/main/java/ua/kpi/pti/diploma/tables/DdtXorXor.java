package ua.kpi.pti.diploma.tables;

import ua.kpi.pti.diploma.utils.MatrixToCSVPrinter;
import ua.kpi.pti.diploma.tables.threads.ususal_threads.DdtXorXorThread;
import ua.kpi.pti.diploma.tables.threads.ususal_threads.TableThread;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ua.kpi.pti.diploma.utils.Constants.*;

public class DdtXorXor extends TableProvider {
    String tableName = "DDT XOR XOR";




    @Override
    public Map<Integer, Integer> calculateStatistics(List<Integer> basises) {
        Map<Integer, Integer> result = new HashMap<>();
        for (Integer basis : basises) {
            int[][] ddt = getTable(basis);

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

    @Override
    public List<TableThread> getThreadPool(int[][] table, int basis) {
        threadPool = new ArrayList<>();
        for (int i = 0; i < CORES; i++) {
            threadPool.add(new DdtXorXorThread(table, i, (i + 1) * Q / CORES, basis));
        }
        return this.threadPool;
    }

    @Override
    public String getTableName() {
        return this.tableName;
    }
}
