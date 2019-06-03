package ua.kpi.pti.diploma.tables;

import ua.kpi.pti.diploma.Type;
import ua.kpi.pti.diploma.tables.threads.extended_threads.DdtXorPlusThreadExtended;
import ua.kpi.pti.diploma.tables.threads.ususal_threads.DdtXorPlusThread;
import ua.kpi.pti.diploma.tables.threads.ususal_threads.TableThread;
import ua.kpi.pti.diploma.utils.MatrixToCSVPrinter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ua.kpi.pti.diploma.utils.Constants.*;

public class DdtXorPlus extends TableProvider {
    String tableName = "DDT XOR PLUS";



    @Override
    public Map<Integer, Integer> calculateStatistics(List<Integer> basises, Type type) {
        Map<Integer, Integer> result = new HashMap<>();
        for (Integer basis : basises) {
            int[][] ddt = getTable(basis, type);

            try {
                MatrixToCSVPrinter.printMatrixToCSV(ddt, PATH_TO_XOR_PLUS_FOLDER + Integer.toHexString(basis) + "__XOR_PLUS.csv");
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
    public List<TableThread> getThreadPool(int[][] table, int basis, Type type) {
        threadPool = new ArrayList<>();
        if (type == Type.USUAL) {
            for (int i = 0; i < CORES; i++) {
                threadPool.add(new DdtXorPlusThread(table, i, (i + 1) * Q / CORES, basis));
            }
        } else {
            for (int i = 0; i < CORES; i++) {
                threadPool.add(new DdtXorPlusThreadExtended(table, i, (i + 1) * Q / CORES, basis));
            }
        }

        return this.threadPool;
    }
}
