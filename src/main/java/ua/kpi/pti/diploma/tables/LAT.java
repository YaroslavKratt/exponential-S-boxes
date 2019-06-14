package ua.kpi.pti.diploma.tables;

import ua.kpi.pti.diploma.tables.threads.LatThread;
import ua.kpi.pti.diploma.tables.threads.TableThread;

import java.util.ArrayList;
import java.util.List;

import static ua.kpi.pti.diploma.utils.Constants.CORES;
import static ua.kpi.pti.diploma.utils.Constants.Q;
import static ua.kpi.pti.diploma.utils.Utils.scalarMultiplication;

public class LAT extends TableProvider {
    String tableName = "LAT";


    @Override
    protected void calculate(int[][] table, int[] sbox) {
        for (int alpha = 0; alpha < Q; alpha++) {
            for (int beta = 0; beta < Q; beta++) {
                int sum = 0;
                for (int x = 0; x < Q; x++) {
                    int sBoxOut = sbox[x];
                    if((scalarMultiplication(alpha, x) ^ scalarMultiplication(beta, sBoxOut))==1) {
                        sum ++;
                    }
                }
                table[alpha][beta] = (Q - 2*(sum))*(Q - 2*(sum));
            }
        }


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
