package ua.kpi.pti.diploma.tables;

import ua.kpi.pti.diploma.MatrixToCSVPrinter;
import ua.kpi.pti.diploma.tables.threads.DdtXorXorThread;
import ua.kpi.pti.diploma.tables.threads.TableThread;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ua.kpi.pti.diploma.Constants.*;

public class DdtXorXor extends TableProvider {
    String tableName = "DDT XOR XOR";



    @Override
    public int[][] getTable(int basis) {
        int[][] ddt = new int[Q][Q];

        calculateWithMultiThreads(getThreadPool(ddt,basis));
        return ddt;
    }

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
    public String getTableName() {
        return tableName;
    }
    protected List<TableThread> getThreadPool(int[][]table,int basis) {
        threadPool = new ArrayList<>();

        threadPool.add(new DdtXorXorThread(table, 0, Q / CORES, basis));
        threadPool.add(new DdtXorXorThread(table, Q / CORES, 2 * Q / CORES, basis));
        threadPool.add(new DdtXorXorThread(table, 2 * Q / CORES, 3 * Q / CORES, basis));
        threadPool.add(new DdtXorXorThread(table, 3 * Q / CORES, 4 * Q / CORES, basis));
        threadPool.add(new DdtXorXorThread(table, 4 * Q / CORES, Q, basis));

        return this.threadPool;
    }
}
