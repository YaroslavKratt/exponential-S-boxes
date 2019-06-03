package ua.kpi.pti.diploma.tables.threads.extended_threads;

import ua.kpi.pti.diploma.SboxExtender;
import ua.kpi.pti.diploma.tables.threads.ususal_threads.TableThread;

import static ua.kpi.pti.diploma.utils.Constants.Q;
import static ua.kpi.pti.diploma.utils.Constants.allExponents;

public class DdtXorXorThreadExtended extends TableThread {

    public DdtXorXorThreadExtended(int[][] lat, int startAlpha, int endAlpha, int basis) {
        super(lat, startAlpha, endAlpha, basis);
    }

    @Override
    public void run() {
        for (int alpha = startAlpha; alpha < endAlpha; alpha++) {
            for (Integer a : SboxExtender.getExtentionParam()) {

                for (int b = 0; b < Q; b++) {
                    for (int x = 0; x < Q; x++) {
                        int out = allExponents[basis][((a * x + b) & 0xFF) ^ alpha] ^ allExponents[basis][(a * x + b) & 0xFF];
                        table[alpha][out] = (table[alpha][out] + 1) & 0xFF;
                    }
                }
            }
        }
    }
}
