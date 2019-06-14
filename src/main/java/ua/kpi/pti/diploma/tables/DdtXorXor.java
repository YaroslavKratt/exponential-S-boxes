package ua.kpi.pti.diploma.tables;

import ua.kpi.pti.diploma.tables.threads.DdtXorXorThread;
import ua.kpi.pti.diploma.tables.threads.TableThread;

import java.util.ArrayList;
import java.util.List;

import static ua.kpi.pti.diploma.utils.Constants.CORES;
import static ua.kpi.pti.diploma.utils.Constants.Q;

public class DdtXorXor extends TableProvider {
    String tableName = "DDT_XOR_XOR";


    @Override
    protected void calculate(int[][] table, int[] sbox) {
        for (int alpha = 0; alpha < Q; alpha++) {
            for (int x = 0; x < Q; x++) {
                int out = sbox[x ^ alpha] ^ sbox[x];
                table[alpha][out] = (table[alpha][out] + 1) &0xFF;
            }
        }
    }



    @Override
    public List<TableThread> getThreadPool(int[][] table, int[] sbox) {
        threadPool = new ArrayList<>();

        for (int i = 0; i < CORES; i++) {
            threadPool.add(new DdtXorXorThread(table, i * Q / CORES, (i + 1) * Q / CORES, sbox));
        }

        return this.threadPool;
    }

    @Override
    public String getTableName() {
        return this.tableName;
    }
}
