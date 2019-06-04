package ua.kpi.pti.diploma.tables.threads;

import static ua.kpi.pti.diploma.utils.Constants.Q;

public class DdtXorXorThread extends TableThread {


    public DdtXorXorThread(int[][] table, int startAlpha, int endAlpha, int[] sboxTable) {
        super(table, startAlpha, endAlpha,sboxTable);

    }

    @Override
    public void run() {
        for (int alpha = startAlpha; alpha < endAlpha; alpha++) {
            for (int x = 0; x < Q; x++) {
                int out = sbox[x ^ alpha] ^ sbox[x];
                table[alpha][out] = (table[alpha][out] + 1) &0xFF;
            }
        }
    }
}
