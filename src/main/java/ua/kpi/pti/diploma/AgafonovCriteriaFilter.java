package ua.kpi.pti.diploma;

import org.bouncycastle.pqc.math.linearalgebra.GF2mField;
import ua.kpi.pti.diploma.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static java.lang.Math.pow;
import static ua.kpi.pti.diploma.utils.Constants.*;

public class AgafonovCriteriaFilter {
    private final int k = (int) pow(P, M - 1);


    public static List<Integer> findAllPrimitiveElementsOfField() {
        GF2mField gf2mField = new GF2mField(M);
        ArrayList<Integer> primitiveElements = new ArrayList<>();

        for (int i = 1; i < Q - 1; i++) {
            int current = i;
            boolean isPrimitive = Utils.decomposeNumberToPrimeMultipliers(Q - 1).stream()
                    .allMatch(elem -> gf2mField.exp(current, (Q - 1) / elem) != 1);
            if (isPrimitive) {
                primitiveElements.add(i);
            }
        }
        return primitiveElements;
    }

    public List<Integer> filterByOptimalDifferentialCharacteristics(List<Integer> primitiveBases) { //means characteristic that described by Agievich and Afonenko
        List<Integer> filteredExponents = new ArrayList<>();
        for (Integer alpha : primitiveBases) {

            boolean isAcceptable = IntStream.rangeClosed(1, k - 1)
                    .allMatch(exponent -> FIELD.add(FIELD.add(FIELD.exp(alpha, k), FIELD.exp(alpha, exponent)), 1) != 0);
            if (isAcceptable) {
                filteredExponents.add(alpha);
            }

        }
        return filteredExponents;
    }

    public List<Integer> filterByMaximumAlgebraicDegree(List<Integer> alphas) {
        List<Integer> filteredAlphas = new ArrayList<>();
        for (Integer alpha : alphas) {
            int alphaForBasis = FIELD.mult(alpha, FIELD.inverse(FIELD.add(1, alpha)));
            List<Integer> vectors = new ArrayList<>();
            for (int i = 0; i < M; i++) {
                vectors.add(FIELD.exp(alphaForBasis, (int) pow(2, i)));
            }
            if (Utils.rankIsNotZero(vectors)) {
                filteredAlphas.add(alpha);
            }
        }
        return filteredAlphas;
    }
}
