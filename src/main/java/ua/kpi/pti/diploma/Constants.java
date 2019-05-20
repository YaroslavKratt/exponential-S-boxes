package ua.kpi.pti.diploma;

import org.bouncycastle.pqc.math.linearalgebra.GF2mField;

public interface Constants {
    public static final int P = 2;//characteristic of field
    public static final int M = 8; //extension
    public static final int Q = (int) Math.pow(P, M);
    public static final GF2mField FIELD = new GF2mField(M);
    public static final String FILE_NAME = "E:\\my projects\\exponential s-boxes\\table.csv";

}
