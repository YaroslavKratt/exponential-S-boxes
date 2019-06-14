package ua.kpi.pti.diploma.tables;

import ua.kpi.pti.diploma.tables.threads.ElThread;
import ua.kpi.pti.diploma.tables.threads.TableThread;

import java.util.ArrayList;
import java.util.List;

import static ua.kpi.pti.diploma.utils.Constants.CORES;
import static ua.kpi.pti.diploma.utils.Constants.Q;
import static ua.kpi.pti.diploma.utils.Utils.scalarMultiplication;

public class ElTable extends TableProvider {
    String tableName = "El_Table";


    @Override
    protected void calculate(int[][] table, int[] sbox) {
        for (int alpha = 0; alpha < Q; alpha++) {
            for (int beta = 0; beta < Q; beta++) {
                int sum1 = 0;

                for (int k = 0; k < Q; k++) {
                    int sum2 = 0;

                    for (int x = 0; x < Q; x++) {
                        int sBoxOut = sbox[x];
                        if((scalarMultiplication(alpha, x) ^ scalarMultiplication(beta, sBoxOut))==1) {
                            sum2 ++;
                        }
                    }
                    sum1 += (Q - 2*(sum2))*(Q - 2*(sum2));
                }
                table[alpha][beta] = sum1;
            }
        }
    }



    @Override
    public List<TableThread> getThreadPool(int[][] table, int[] sbox) {
        threadPool = new ArrayList<>();

        for (int i = 0; i < CORES; i++) {
            threadPool.add(new ElThread(table, i * Q / CORES, (i + 1) * Q / CORES, sbox));
        }

        return this.threadPool;
    }
    public String getTableName() {
        return this.tableName;
    }
}

