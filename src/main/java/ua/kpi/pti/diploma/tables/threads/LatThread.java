package ua.kpi.pti.diploma.tables.threads;

import static ua.kpi.pti.diploma.Constants.Q;
import static ua.kpi.pti.diploma.Constants.allExponents;
import static ua.kpi.pti.diploma.Utils.scalarMultiplication;

public class LatThread extends TableThread {


    public LatThread(int[][] lat, int startAlpha, int endAlpha, int basis) {
        super(lat, startAlpha, endAlpha, basis);
    }

    @Override
    public void run() {
        for (int alpha = startAlpha; alpha < endAlpha; alpha++) {
            for (int beta = 0; beta < Q; beta++) {
                int sum = 0;
                for (int x = 0; x < Q; x++) {
                    int sBoxOut = allExponents.get(basis).get(x);
                    sum += Math.pow(-1, scalarMultiplication(alpha, x) ^ scalarMultiplication(beta, sBoxOut));
                }
                table[alpha][beta] = sum * sum;
            }
        }


    }
}
