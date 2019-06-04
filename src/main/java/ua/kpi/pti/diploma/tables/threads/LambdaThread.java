package ua.kpi.pti.diploma.tables.threads;

import static java.lang.Math.abs;
import static ua.kpi.pti.diploma.utils.Constants.Q;
import static ua.kpi.pti.diploma.utils.Utils.scalarMultiplication;

public class LambdaThread extends TableThread {


    public LambdaThread(int[][] lat, int startAlpha, int endAlpha, int[] sboxTable) {
        super(lat, startAlpha, endAlpha, sboxTable);
    }

    @Override
    public void run() {

        int n1, n2;
        int sum, sumK;
        for (int alpha = startAlpha; alpha < endAlpha; alpha++) {
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
}
