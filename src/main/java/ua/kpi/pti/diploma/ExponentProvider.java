package ua.kpi.pti.diploma;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ua.kpi.pti.diploma.PreCalculationsAndConstants.FIELD;

public class ExponentProvider {

   public static Map<Integer, List<Integer>> getAllSboxesForBases(List<Integer> bases) {
        Map<Integer, List<Integer>> result = new HashMap<>();
        List<Integer> exponentsForCurrentBase;
        for (Integer base : bases) {
            exponentsForCurrentBase = new ArrayList<>();
            exponentsForCurrentBase.add(0); //S(0) = 0
            for (int power = 1; power < 256; power++) {
                exponentsForCurrentBase.add(FIELD.exp(base, power));
            }
            result.put(base, exponentsForCurrentBase);
        }
        return result;
    }
}
