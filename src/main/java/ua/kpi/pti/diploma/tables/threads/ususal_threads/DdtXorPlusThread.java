package ua.kpi.pti.diploma.tables.threads.ususal_threads;

import static ua.kpi.pti.diploma.utils.Constants.Q;
import static ua.kpi.pti.diploma.utils.Constants.allExponents;

public class DdtXorPlusThread extends TableThread {

    public DdtXorPlusThread(int[][] lat, int startAlpha, int endAlpha, int basis) {
        super(lat, startAlpha, endAlpha, basis);

    }


    @Override
    public void run() {
        for (int alpha = startAlpha; alpha < endAlpha; alpha++) {
            for (int x = 0; x < Q; x++) {
                int out = (allExponents[basis][x ^ alpha] - allExponents[basis][x] + Q) & 0xFF;
                table[alpha][out] = (table[alpha][out] + 1) & 0xFF;
            }
        }
    }
}
