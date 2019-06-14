package ua.kpi.pti.diploma.extender;

import static ua.kpi.pti.diploma.extender.SboxGenerator.aList;
import static ua.kpi.pti.diploma.utils.Constants.FIELD;
import static ua.kpi.pti.diploma.utils.Constants.Q;
/**
 * This thread will generate all  possible S-boxes like: x belongs to F_256 sBox=a*s(x) ^ b
 * s(x) - is usual exponential s-box
 */
public class AfineOnExitThread extends Thread{
    private int[][][][] extendedSBox;
    private int start;
    private int end;

     public AfineOnExitThread(int start1, int end1, int[][][][] extendedSBox) {

        this.start = start1;
        this.end = end1;
        this.extendedSBox = extendedSBox;
    }

    @Override
    public void run() {
        for (int b = start; b < end; b++) {
            for (int a : aList) {
                for (int base = 0; base < Q; base++) {
                    for (int power = 0; power < Q; power++) {
                        extendedSBox[b][a][base][power] = FIELD.mult(a,FIELD.exp(base, power))^b;
                    }
                }
            }
        }
    }
}
