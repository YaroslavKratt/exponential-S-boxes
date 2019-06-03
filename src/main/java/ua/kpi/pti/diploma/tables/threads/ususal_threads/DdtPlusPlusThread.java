package ua.kpi.pti.diploma.tables.threads.ususal_threads;

import static ua.kpi.pti.diploma.utils.Constants.Q;
import static ua.kpi.pti.diploma.utils.Constants.allExponents;

public class DdtPlusPlusThread extends TableThread {

    public DdtPlusPlusThread(int[][] ddt, int startAlpha, int endAlpha, int basis) {
        super(ddt, startAlpha, endAlpha, basis);
 }


    @Override
    public void run() {
        for (int alpha = startAlpha; alpha < endAlpha; alpha++) {
            for (int x = 0; x < Q; x++) {
                int out = (allExponents[basis][(x+ alpha + Q) % Q] - allExponents[basis][x] + Q) % Q;
                table[alpha][out] = (table[alpha][out] + 1) % Q;
            }
        }
    }
}
