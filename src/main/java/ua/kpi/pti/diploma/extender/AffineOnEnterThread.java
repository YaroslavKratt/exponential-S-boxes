package ua.kpi.pti.diploma.extender;

import static ua.kpi.pti.diploma.extender.SboxGenerator.aList;
import static ua.kpi.pti.diploma.utils.Constants.FIELD;
import static ua.kpi.pti.diploma.utils.Constants.Q;

/**
 * This thread will generate all  possible S-boxes like: x belongs to F_256 sBox=s(ax +b)
 * s(x) - is usual exponential s-box
 */
public class AffineOnEnterThread extends Thread {
    private int[][][][] extendedSBox;
    private int start;
    private int end;

    AffineOnEnterThread(int start1, int end1, int[][][][] extendedSBox) {

        this.start = start1;
        this.end = end1;
        this.extendedSBox = extendedSBox;
    }

    @Override
    public void run() {
        for (int b = start; b < end; b++) {
            for (int a:aList) {
                for (int base = 0; base < Q; base++) {
                    for (int power = 0; power < Q; power++) {
                        int x = (a * power + b) & 0xFF;
                        extendedSBox[b][a][base][power] = FIELD.exp(base, x);
                    }
                }
            }
        }
    }
}
