package ua.kpi.pti.diploma.tables;

import ua.kpi.pti.diploma.MatrixToCSVPrinter;
import ua.kpi.pti.diploma.tables.threads.LambdaThread;
import ua.kpi.pti.diploma.tables.threads.TableThread;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ua.kpi.pti.diploma.Constants.*;

public class LambdaTable extends TableProvider {
    String tableName = "LAMBDA TABLE";
    @Override
    int[][] getTable(int basis) {
        int[][] llambdaTablet = new int[Q][Q];

        calculateWithMultiThreads(getThreadPool(llambdaTablet,basis));
        return llambdaTablet;
    }

    @Override
    public Map<Integer, Integer> calculateStatistics(List<Integer> basises) {
        Map<Integer, Integer> result = new HashMap<>();
        double  counter=1;
        for (Integer basis : basises) {
            System.out.println((counter/basises.size())*100 + "%");
            int[][] ddt = getTable(basis);
            try {
                MatrixToCSVPrinter.printMatrixToCSV(ddt, PATH_TO_LAMBDA_TABLE + Integer.toHexString(basis) + "__LAMBDA_TABLE.csv");
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

        threadPool.add(new LambdaThread(table, 0, Q / CORES, basis));
        threadPool.add(new LambdaThread(table, Q / CORES, 2 * Q / CORES, basis));
        threadPool.add(new LambdaThread(table, 2 * Q / CORES, 3 * Q / CORES, basis));
        threadPool.add(new LambdaThread(table, 3 * Q / CORES, 4 * Q / CORES, basis));
        threadPool.add(new LambdaThread(table, 4 * Q / CORES, Q, basis));

        return this.threadPool;
    }

    public String getTableName() {
        return tableName;
    }
}