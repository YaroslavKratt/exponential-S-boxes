package ua.kpi.pti.diploma.tables;

import ua.kpi.pti.diploma.MatrixToCSVPrinter;
import ua.kpi.pti.diploma.tables.threads.ElThread;
import ua.kpi.pti.diploma.tables.threads.TableThread;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ua.kpi.pti.diploma.Constants.*;

public class ElTable extends TableProvider {
    String tableName = "El Table";

    @Override
    int[][] getTable(int basis) {
        int[][] elTable = new int[Q][Q];
        calculateWithMultiThreads(getThreadPool(elTable, basis));
        return elTable;
    }

    @Override
    public Map<Integer, Integer> calculateStatistics(List<Integer> basises) {
        Map<Integer, Integer> result = new HashMap<>();
        for (Integer basis : basises) {
            double  counter=1;
            System.out.println((counter/basises.size())*100 + "%");
            int[][] ddt = getTable(basis);
            try {
                MatrixToCSVPrinter.printMatrixToCSV(ddt, PATH_TO_EL_TABLE + Integer.toHexString(basis) + "__EL_TABLE.csv");
            } catch (IOException e) {
                e.printStackTrace();
            }
            int max = maxInTable(ddt);


            result.putIfAbsent(max, 0);
            result.put(max, result.get(max) + 1);
        counter++;}
        return result;
    }


    @Override
    protected List<TableThread> getThreadPool(int[][] table, int basis) {
        threadPool = new ArrayList<>();
        threadPool.add(new ElThread(table, 0, Q / CORES, basis));
        threadPool.add(new ElThread(table, Q / CORES, 2 * Q / CORES, basis));
        threadPool.add(new ElThread(table, 2 * Q / CORES, 3 * Q / CORES, basis));
        threadPool.add(new ElThread(table, 3 * Q / CORES, 4 * Q / CORES, basis));
        threadPool.add(new ElThread(table, 4 * Q / CORES, Q, basis));

        return this.threadPool;
    }

    public String getTableName() {
        return tableName;
    }
}

