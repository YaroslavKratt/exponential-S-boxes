package ua.kpi.pti.diploma;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static ua.kpi.pti.diploma.Constants.*;

public class Utils {

    public static Set<Integer> decomposeNumberToPrimeMultipliers(int number) {
        Set<Integer> result = new HashSet<Integer>();
        int div = 2;

        while (number > 1) {
            while (number % div == 0) {
                result.add(div);
                number = number / div;
            }
            div++;
        }
        return result;
    }


    public static boolean rankIsNotZero(List<Integer> vectors) {
        int s = vectors.get(0);
        for (int j = 0; j < vectors.size(); j++) {
            if (j != 0) {
                s = s ^ vectors.get(j);
            }
        }
        return s != 0;
    }

    public static int[] toBinaryArray(Integer number) {
        String binary = Integer.toBinaryString(number);
        String[] binaryStringArray = binary.split("");
        binaryStringArray = addZeroes(binaryStringArray);
        int[] binaryArray = new int[binaryStringArray.length];

        for (int i = 0; i < binaryStringArray.length; i++) {
            binaryArray[i] = Integer.parseInt(binaryStringArray[i]);
        }
        return binaryArray;

    }

    private static String[] addZeroes(String[] binaryArr) {
        List<String> binList = Arrays.asList(binaryArr);

        while (true) {
            if (binaryArr.length < M) {
                binList.add(0, "0");
            } else {
                break;
            }
        }

        return binList.toArray(new String[0]);
    }

    Integer scalarMultiplication(int[] vector1, int[] vector2) {
        int s = 0;

        for (int i = 0; i < M; i++) {
            s += vector1[i] * vector2[i];
        }
        return s;
    }


}
