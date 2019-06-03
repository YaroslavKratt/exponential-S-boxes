package ua.kpi.pti.diploma.tables.threads.extended_threads;

import ua.kpi.pti.diploma.SboxExtender;
import ua.kpi.pti.diploma.tables.threads.ususal_threads.TableThread;

import static ua.kpi.pti.diploma.utils.Constants.Q;
import static ua.kpi.pti.diploma.utils.Constants.allExponents;

public class DdtPlusPlusThreadExtended extends TableThread {

    public DdtPlusPlusThreadExtended(int[][] ddt, int startAlpha, int endAlpha, int basis) {
        super(ddt, startAlpha, endAlpha, basis);

    }


    @Override
    public void run() {
        for (int alpha = startAlpha; alpha < endAlpha; alpha++) {
            for (Integer a : SboxExtender.getExtentionParam()) {

                for (int b = 0; b < Q; b++) {

                    for (int x = 0; x < Q; x++) {
                        int out = (allExponents[basis][((a * x + b) & 0xFF + alpha + Q) % Q] - allExponents[basis][(a * x + b) & 0xFF] + Q) % Q;
                        table[alpha][out] = (table[alpha][out] + 1) % Q;
                    }
                }
            }
        }

    }
}
