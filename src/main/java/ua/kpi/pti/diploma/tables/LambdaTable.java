package ua.kpi.pti.diploma.tables;

import ua.kpi.pti.diploma.tables.threads.LambdaThread;
import ua.kpi.pti.diploma.tables.threads.TableThread;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;
import static ua.kpi.pti.diploma.utils.Constants.Q;
import static ua.kpi.pti.diploma.utils.Utils.scalarMultiplication;

public class LambdaTable extends TableProvider {
    String tableName = "LAMBDA_TABLE";


    @Override
    protected void calculate(int[][] table, int[] sbox) {
        int n1, n2;
        int sum, sumK;
        for (int alpha = 0; alpha < Q; alpha++) {
            for (int beta = 0; beta < Q; beta++) {
                sumK = 0;
                for (int k = 0; k < Q; k++) {
                    n1 = 0;
                    n2 = 0;
                    for (int x = 0; x < Q - k; x++) {
                        n1 = n1 + (scalarMultiplication(alpha, x) ^ scalarMultiplication(beta, sbox[(x + k) & 0xFF]));
                    }
                    for (int x = Q - k; x < Q; x++) {
                        n2 = n2 + (scalarMultiplication(alpha, x) ^ scalarMultiplication(beta, sbox[(x+ k) & 0xFF]));
                    }
                    sum = abs(Q - k - 2 * n1) + abs(k - 2 * n2);
                    sumK = sumK + sum * sum;

                }
                table[alpha][beta] = sumK;
            }
        }

    }



    @Override
    public List<TableThread> getThreadPool(int[][] table, int[] sbox) {
        threadPool = new ArrayList<>();

        for (int i = 0; i < 7; i++) {
            threadPool.add(new LambdaThread(table, i * Q / 7, (i + 1) * Q / 7, sbox));
        }

        return this.threadPool;
    }

    public String getTableName() {
        return this.tableName;
    }
}