package ua.kpi.pti.diploma.tables.threads.extended_threads;

import ua.kpi.pti.diploma.SboxExtender;
import ua.kpi.pti.diploma.tables.threads.ususal_threads.TableThread;

import static java.lang.Math.abs;
import static ua.kpi.pti.diploma.utils.Constants.Q;
import static ua.kpi.pti.diploma.utils.Constants.allExponents;
import static ua.kpi.pti.diploma.utils.Utils.scalarMultiplication;

public class LambdaThreadExtended extends TableThread {

    public LambdaThreadExtended(int[][] lat, int startAlpha, int endAlpha, int basis) {
        super(lat, startAlpha, endAlpha, basis);

    }

    @Override
    public void run() {

        int n1, n2;
        int sum, sumK;
        for (int alpha = startAlpha; alpha < endAlpha; alpha++) {
            for (Integer a : SboxExtender.getExtentionParam()) {

                for (int b = 0; b < Q; b++) {
                    for (int beta = 0; beta < Q; beta++) {
                        sumK = 0;
                        for (int k = 0; k < Q; k++) {
                            n1 = 0;
                            n2 = 0;
                            for (int x = 0; x < Q - k; x++) {
                                n1 = n1 + (scalarMultiplication(alpha, (a * x + b) & 0xFF) ^ scalarMultiplication(beta, allExponents[basis][((a * x + b) & 0xFF + k) & 0xFF]));
                            }
                            for (int x = Q - k; x < Q; x++) {
                                n2 = n2 + (scalarMultiplication(alpha, (a * x + b) & 0xFF) ^ scalarMultiplication(beta, allExponents[basis][((a * x + b) & 0xFF + k) & 0xFF]));
                            }
                            sum = abs(Q - k - 2 * n1) + abs(k - 2 * n2);
                            sumK = sumK + sum * sum;

                        }
                        table[alpha][beta] = sumK;
                    }
                }
            }
        }
    }
}
