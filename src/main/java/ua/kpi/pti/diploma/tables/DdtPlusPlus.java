package ua.kpi.pti.diploma.tables;

import ua.kpi.pti.diploma.Type;
import ua.kpi.pti.diploma.tables.threads.DdtPlusPlusThread;
import ua.kpi.pti.diploma.tables.threads.TableThread;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ua.kpi.pti.diploma.extender.SboxExtender.aList;
import static ua.kpi.pti.diploma.utils.Constants.*;

public class DdtPlusPlus extends TableProvider {
    String tableName = "DDT_PLUS_PLUS";


    @Override
    protected void calculate(int[][] table, int[] sbox) {
        for (int alpha = 0; alpha < Q; alpha++) {
            for (int x = 0; x < Q; x++) {
                int out = (sbox[(x+ alpha + Q) % Q] - sbox[x] + Q) % Q;
                table[alpha][out] = (table[alpha][out] + 1) % Q;
            }
        }
    }


    public String getTableName() {
        return tableName;
    }

    @Override
    public List<TableThread> getThreadPool(int[][] table, int[] sbox) {
        threadPool = new ArrayList<>();

        for (int i = 0; i < CORES; i++) {
            threadPool.add(new DdtPlusPlusThread(table, i * Q / CORES, (i + 1) * Q / CORES, sbox));
        }
        return this.threadPool;
    }
}


