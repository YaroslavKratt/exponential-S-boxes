package ua.kpi.pti.diploma.tables.threads.extended_threads;

import ua.kpi.pti.diploma.SboxExtender;
import ua.kpi.pti.diploma.tables.threads.ususal_threads.TableThread;

import static ua.kpi.pti.diploma.utils.Constants.Q;
import static ua.kpi.pti.diploma.utils.Constants.allExponents;
import static ua.kpi.pti.diploma.utils.Utils.scalarMultiplication;

public class ElThreadExtended extends TableThread {


    public ElThreadExtended(int[][] lat, int startAlpha, int endAlpha, int basis) {
        super(lat, startAlpha, endAlpha, basis);
    }

    @Override
    public void run() {
        for (int alpha = startAlpha; alpha < endAlpha; alpha++) {
            for (Integer a : SboxExtender.getExtentionParam()) {

                for (int b = 0; b < Q; b++) {
                    for (int beta = 0; beta < Q; beta++) {
                        int sum1 = 0;

                        for (int k = 0; k < Q; k++) {
                            int sum2 = 0;

                            for (int x = 0; x < Q; x++) {
                                int sBoxOut = allExponents[basis][((a * x + b) & 0xFF + k) & 0xFF];
                                if ((scalarMultiplication(alpha, (a * x + b) & 0xFF) ^ scalarMultiplication(beta, sBoxOut)) == 1) {
                                    sum2++;
                                }
                            }
                            sum1 += (Q - 2 * (sum2)) * (Q - 2 * (sum2));
                        }
                        table[alpha][beta] = sum1;
                    }
                }
            }
        }
    }
}
