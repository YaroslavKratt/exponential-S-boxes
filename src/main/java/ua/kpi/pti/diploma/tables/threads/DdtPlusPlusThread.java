package ua.kpi.pti.diploma.tables.threads;

import static ua.kpi.pti.diploma.utils.Constants.Q;

public class DdtPlusPlusThread extends TableThread {

    public DdtPlusPlusThread(int[][] ddt, int startAlpha, int endAlpha,int[]sboxTable) {
        super(ddt, startAlpha, endAlpha, sboxTable);
 }


    @Override
    public void run() {
        for (int alpha = startAlpha; alpha < endAlpha; alpha++) {
            for (int x = 0; x < Q; x++) {
                int out = (sbox[(x+ alpha + Q) % Q] - sbox[x] + Q) % Q;
                table[alpha][out] = (table[alpha][out] + 1) % Q;
            }
        }
    }
}
