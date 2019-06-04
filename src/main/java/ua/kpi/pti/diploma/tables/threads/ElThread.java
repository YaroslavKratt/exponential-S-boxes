package ua.kpi.pti.diploma.tables.threads;

import static ua.kpi.pti.diploma.utils.Constants.Q;
import static ua.kpi.pti.diploma.utils.Utils.scalarMultiplication;

public class ElThread extends TableThread {


    public ElThread(int[][] lat, int startAlpha, int endAlpha, int[] sboxTable) {
        super(lat, startAlpha, endAlpha, sboxTable);

    }

    @Override
    public void run() {
        for (int alpha = startAlpha; alpha < endAlpha; alpha++) {
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
}
