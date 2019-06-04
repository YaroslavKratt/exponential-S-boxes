package ua.kpi.pti.diploma.extender;

import java.util.ArrayList;
import java.util.List;


import static org.apache.commons.math3.util.ArithmeticUtils.gcd;
import static ua.kpi.pti.diploma.utils.Constants.*;
import static ua.kpi.pti.diploma.utils.Constants.CORES;

public class SboxExtender {
    public static List<Integer> aList;

    static {
        aList = new ArrayList<>();
        for (int a = 1; a < Q; a++) {
            if (gcd(a, Q) == 1 && gcd(a, Q - 1) != 1) {
                aList.add(a);
            }
        }
    }

    public static int[][][][] extendedSBox = new int[Q][Q][Q][Q];


    static {
        System.out.println("Started");
        List<ExtenderThread> pool = new ArrayList<>();
        for (int i = 0; i < CORES; i++) {
            pool.add(new ExtenderThread(i * Q / CORES, (i + 1) * Q / CORES, extendedSBox, aList));
        }
        for (Thread t : pool) {
            t.start();
        }
        for (Thread t : pool) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Finished");

    }

}
