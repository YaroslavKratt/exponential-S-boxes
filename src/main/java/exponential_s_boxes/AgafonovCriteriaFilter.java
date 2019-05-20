package exponential_s_boxes;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static exponential_s_boxes.Constants.*;
import static java.lang.Math.pow;

public class AgafonovCriteriaFilter {
    private final int k = (int) pow(P, M - 1);

    List<Integer> filterByOptimalDifferentialCharacteristics(List<Integer> primitiveBases) { //means characteristic that described by Agievich and Afonenko
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

    List<Integer> filterByMaximumAlgebrianDegree(List<Integer> alphas) {
        List<Integer> filteredAlphas = new ArrayList<>();
        for (Integer alpha : alphas) {
            int alphaForBasis = FIELD.mult(alpha, FIELD.inverse(FIELD.add(1, alpha)));
            List<Integer> vectors = new ArrayList<>();
            for (int i = 0; i < M; i++) {
                vectors.add(FIELD.exp(alphaForBasis, (int) pow(2, i)));
                if (Utils.rankIsNotZero(vectors)) {
                    filteredAlphas.add(alpha);
                }
            }
        }
        return filteredAlphas;
    }
}
