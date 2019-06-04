package ua.kpi.pti.diploma.tables;

import ua.kpi.pti.diploma.Type;
import ua.kpi.pti.diploma.tables.threads.LatThread;
import ua.kpi.pti.diploma.tables.threads.TableThread;
import ua.kpi.pti.diploma.utils.MatrixToCSVPrinter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ua.kpi.pti.diploma.extender.SboxExtender.aList;
import static ua.kpi.pti.diploma.extender.SboxExtender.extendedSBox;
import static ua.kpi.pti.diploma.utils.Constants.*;

public class LAT extends TableProvider {
    String tableName = "LAT";


    @Override
    public Map<Integer, Integer> calculateStatistics(List<Integer> basises, Type type) {
        Map<Integer, Integer> result = new HashMap<>();
        for (Integer basis : basises) {

            int[][] ddt = getTable(basis, type);
         /*   try {
                MatrixToCSVPrinter.printMatrixToCSV(ddt, PATH_TO_LAT + Integer.toHexString(basis) + "__LAT.csv");
            } catch (IOException e) {
                e.printStackTrace();
            }*/
            int max = maxInTable(ddt);


            result.putIfAbsent(max, 0);
            result.put(max, result.get(max) + 1);
        }
        return result;
    }

    public String getTableName() {
        return this.tableName;
    }

    @Override
    public List<TableThread> getThreadPool(int[][] table, int[] sbox) {
        threadPool = new ArrayList<>();

        for (int i = 0; i < CORES; i++) {
            threadPool.add(new LatThread(table, i * Q / CORES, (i + 1) * Q / CORES, sbox));
        }

        return this.threadPool;
    }

}
