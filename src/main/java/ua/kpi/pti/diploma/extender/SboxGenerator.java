package ua.kpi.pti.diploma.extender;

import ua.kpi.pti.diploma.Type;

import java.util.ArrayList;
import java.util.List;


import static org.apache.commons.math3.util.ArithmeticUtils.gcd;
import static ua.kpi.pti.diploma.utils.Constants.*;
import static ua.kpi.pti.diploma.utils.Constants.CORES;
/**
 * This class will generate  exponential S-boxes with required type
 */
public class SboxGenerator {
    public static List<Integer> aList;

    static {
        aList = new ArrayList<>();
        for (int a = 1; a < Q; a++) {
            if (gcd(a, Q) == 1 && gcd(a, Q - 1) != 1) {
                aList.add(a);
            }
        }
    }

    private static int[][][][] extendedSBox;

    public int[][][][] getExtendedSBox(Type type) {
        ArrayList<Thread> pool = new ArrayList<>();

        if (extendedSBox == null) {
            extendedSBox = new int[Q][Q][Q][Q];
            if (type == Type.AFFINE_ON_ENTER) {
                for (int i = 0; i < CORES; i++) {
                    pool.add(new AffineOnEnterThread(i * Q / CORES, (i + 1) * Q / CORES, extendedSBox));
                }
            }
            if (type == Type.AFFINE_ON_EXIT) {
                for (int i = 0; i < CORES; i++) {
                    pool.add(new AfineOnExitThread(i * Q / CORES, (i + 1) * Q / CORES, extendedSBox));
                }
            }
            threadWork(pool);
        }
        return extendedSBox;
    }

    private void threadWork(ArrayList<Thread> pool) {
        pool.forEach(Thread::start);

        for (Thread t : pool) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("FINISHED INIT");
    }
}
