package ua.kpi.pti.diploma.tables.threads;

import static ua.kpi.pti.diploma.utils.Constants.Q;
import static ua.kpi.pti.diploma.utils.Utils.scalarMultiplication;

public class LatThread extends TableThread {


    public LatThread(int[][] lat, int startAlpha, int endAlpha, int[]sBox) {
        super(lat, startAlpha, endAlpha, sBox);
    }

    @Override
    public void run() {
        for (int alpha = startAlpha; alpha < endAlpha; alpha++) {
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
}
