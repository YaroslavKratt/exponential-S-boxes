package ua.kpi.pti.diploma;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public static Integer scalarMultiplication(int number1, int number2) {
        int sum =0;
        for (int i = 7; i >= 0; i--) {
            sum^=((number1>>i)&1)& ((number2>>i)&1);
        }
        return sum;
    }




}
