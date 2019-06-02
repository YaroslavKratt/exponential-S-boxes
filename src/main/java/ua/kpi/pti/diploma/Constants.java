package ua.kpi.pti.diploma;

import org.bouncycastle.pqc.math.linearalgebra.GF2mField;

public abstract class Constants {
    public static final int P = 2;//characteristic of field
    public static final int M = 8; //extension
    public static final int Q = (int) Math.pow(P, M);
    public static final GF2mField FIELD = new GF2mField(M);
    public static final String PATH_TO_XOR_XOR_FOLDER = ".\\DDT_XOR_XOR\\";
    public static final String PATH_TO_XOR_PLUS_FOLDER = ".\\DDT_XOR_PLUS\\";
    public static final String PATH_TO_PLUS_PLUS_FOLDER = ".\\DDT_PLUS_PLUS\\";
    public static final String PATH_TO_LAT = ".\\LAT\\";
    public static final String PATH_TO_EL_TABLE = ".\\EL_TABLE\\";
    public static final int CORES = Runtime.getRuntime().availableProcessors();

    public static int[][] allExponents = new int[Q][Q];
    static {
        for (int base = 0; base < Q; base++) {

           allExponents[base][0]=0; //S(0) = 0
            for (int power = 1; power < Q; power++) {
                allExponents[base][power]=FIELD.exp(base, power);
            }

        }
    }

    public static int[] AR_WEIGHT = {
            0, 1, 1, 2, 1, 2, 2, 3, 1, 2, 2, 3, 2, 3, 3, 4,
            1, 2, 2, 3, 2, 3, 3, 4, 2, 3, 3, 4, 3, 4, 4, 5,
            1, 2, 2, 3, 2, 3, 3, 4, 2, 3, 3, 4, 3, 4, 4, 5,
            2, 3, 3, 4, 3, 4, 4, 5, 3, 4, 4, 5, 4, 5, 5, 6,
            1, 2, 2, 3, 2, 3, 3, 4, 2, 3, 3, 4, 3, 4, 4, 5,
            2, 3, 3, 4, 3, 4, 4, 5, 3, 4, 4, 5, 4, 5, 5, 6,
            2, 3, 3, 4, 3, 4, 4, 5, 3, 4, 4, 5, 4, 5, 5, 6,
            3, 4, 4, 5, 4, 5, 5, 6, 4, 5, 5, 6, 5, 6, 6, 7,
            1, 2, 2, 3, 2, 3, 3, 4, 2, 3, 3, 4, 3, 4, 4, 5,
            2, 3, 3, 4, 3, 4, 4, 5, 3, 4, 4, 5, 4, 5, 5, 6,
            2, 3, 3, 4, 3, 4, 4, 5, 3, 4, 4, 5, 4, 5, 5, 6,
            3, 4, 4, 5, 4, 5, 5, 6, 4, 5, 5, 6, 5, 6, 6, 7,
            2, 3, 3, 4, 3, 4, 4, 5, 3, 4, 4, 5, 4, 5, 5, 6,
            3, 4, 4, 5, 4, 5, 5, 6, 4, 5, 5, 6, 5, 6, 6, 7,
            3, 4, 4, 5, 4, 5, 5, 6, 4, 5, 5, 6, 5, 6, 6, 7,
            4, 5, 5, 6, 5, 6, 6, 7, 5, 6, 6, 7, 6, 7, 7, 8};
}
