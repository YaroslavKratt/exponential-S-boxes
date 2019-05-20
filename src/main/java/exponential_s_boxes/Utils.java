package exponential_s_boxes;

import org.bouncycastle.pqc.math.linearalgebra.GF2mField;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static exponential_s_boxes.Constants.M;
import static exponential_s_boxes.Constants.Q;

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


    public static List<Integer> findAllPrimitiveElementsOfField() {
        GF2mField gf2mField = new GF2mField(M);
        ArrayList<Integer> primitiveElements = new ArrayList<Integer>();

        for (int i = 1; i < Q - 1; i++) {
            int current = i;
            boolean isPrimitive = decomposeNumberToPrimeMultipliers(Q - 1).stream()
                    .allMatch(elem -> gf2mField.exp(current, (Q - 1) / elem) != 1);
            if (isPrimitive) {
                primitiveElements.add(i);
            }
        }
        return primitiveElements;
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

}
