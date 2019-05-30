package ua.kpi.pti.diploma;

import org.bouncycastle.pqc.math.linearalgebra.GF2mField;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Constants {
    public static final int P = 2;//characteristic of field
    public static final int M = 8; //extension
    public static final int Q = (int) Math.pow(P, M);
    public static final GF2mField FIELD = new GF2mField(M);
    public static final String FILE_NAME = "E:\\my projects\\exponential s-boxes\\table.csv";
    public static  Map<Integer, List<Integer>> allExponents = new HashMap<>();
    static{
        List<Integer> exponentsForCurrentBase;
        for (int base = 0; base < Q; base++) {
            exponentsForCurrentBase = new ArrayList<>();
            exponentsForCurrentBase.add(0); //S(0) = 0
            for (int power = 1; power < 256; power++) {
                exponentsForCurrentBase.add(FIELD.exp(base, power));
            }
            allExponents.put(base, exponentsForCurrentBase);
        }
    }
}
