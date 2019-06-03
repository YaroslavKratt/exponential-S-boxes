package ua.kpi.pti.diploma.tables;

import ua.kpi.pti.diploma.Type;
import ua.kpi.pti.diploma.tables.threads.extended_threads.LambdaThreadExtended;
import ua.kpi.pti.diploma.tables.threads.ususal_threads.LambdaThread;
import ua.kpi.pti.diploma.tables.threads.ususal_threads.TableThread;
import ua.kpi.pti.diploma.utils.MatrixToCSVPrinter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ua.kpi.pti.diploma.utils.Constants.*;

public class LambdaTable extends TableProvider {
    String tableName = "LAMBDA TABLE";


    @Override
    public Map<Integer, Integer> calculateStatistics(List<Integer> basises, Type type) {
        Map<Integer, Integer> result = new HashMap<>();
        double counter = 1;
        for (Integer basis : basises) {
            System.out.println((counter / basises.size()) * 100 + "%");
            int[][] ddt = getTable(basis,type);
            try {
                MatrixToCSVPrinter.printMatrixToCSV(ddt, PATH_TO_LAMBDA_TABLE + Integer.toHexString(basis) + "__LAMBDA_TABLE.csv");
            } catch (IOException e) {
                e.printStackTrace();
            }
            int max = maxInTable(ddt);


            result.putIfAbsent(max, 0);
            result.put(max, result.get(max) + 1);
            counter++;
        }
        return result;
    }


    @Override
    public List<TableThread> getThreadPool(int[][] table, int basis, Type type) {
        threadPool = new ArrayList<>();
        if (type == Type.USUAL) {
            for (int i = 0; i < CORES; i++) {
                threadPool.add(new LambdaThread(table, i, (i + 1) * Q / CORES, basis));
            }
        } else {
            for (int i = 0; i < CORES; i++) {
                threadPool.add(new LambdaThreadExtended(table, i, (i + 1) * Q / CORES, basis));
            }
        }

        return this.threadPool;
    }

    public String getTableName() {
        return this.tableName;
    }
}