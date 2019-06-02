package ua.kpi.pti.diploma.tables;

import ua.kpi.pti.diploma.MatrixToCSVPrinter;
import ua.kpi.pti.diploma.tables.threads.LatThread;
import ua.kpi.pti.diploma.tables.threads.TableThread;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ua.kpi.pti.diploma.Constants.*;

public class LAT extends TableProvider {
    String tableName = "LAT";


    @Override
    public int[][] getTable(int basis) {
        int[][] lat = new int[Q][Q];

        calculateWithMultiThreads(getThreadPool(lat,basis));
        return lat;
    }

    @Override
    public Map<Integer, Integer> calculateStatistics(List<Integer> basises) {
        Map<Integer, Integer> result = new HashMap<>();
        for (Integer basis : basises) {

            int[][] ddt = getTable(basis);
            try {
                MatrixToCSVPrinter.printMatrixToCSV(ddt, PATH_TO_LAT + Integer.toHexString(basis) + "__LAT.csv");
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
    protected List<TableThread> getThreadPool(int[][] table,int basis) {
        threadPool = new ArrayList<>();
        threadPool.add(new LatThread(table, 0, Q / CORES, basis));
        threadPool.add(new LatThread(table, Q / CORES, 2 * Q / CORES, basis));
        threadPool.add(new LatThread(table, 2 * Q / CORES, 3 * Q / CORES, basis));
        threadPool.add(new LatThread(table, 3 * Q / CORES, 4 * Q / CORES, basis));
        threadPool.add(new LatThread(table, 4 * Q / CORES, Q, basis));

        return this.threadPool;
    }
}
