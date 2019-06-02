package ua.kpi.pti.diploma.tables.threads;

import static ua.kpi.pti.diploma.Constants.Q;
import static ua.kpi.pti.diploma.Constants.allExponents;

public class DdtPlusPlusThread extends TableThread {
    public DdtPlusPlusThread(int[][] ddt, int startAlpha, int endAlpha, int basis) {
        super(ddt, startAlpha, endAlpha, basis);
    }


    @Override
    public void run() {
        for (int alpha = startAlpha; alpha < endAlpha; alpha++) {
            for (int x = 0; x < Q; x++) {
                int out = (allExponents.get(basis).get((x + alpha + Q) % Q) - allExponents.get(basis).get(x) + Q) % Q;
                table[alpha][out] = (table[alpha][out] + 1) % Q;
            }
        }
    }
}
