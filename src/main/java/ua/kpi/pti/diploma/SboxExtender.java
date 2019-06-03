package ua.kpi.pti.diploma;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.math.IntMath.gcd;
import static ua.kpi.pti.diploma.utils.Constants.Q;

public class SboxExtender {
    public static List<Integer> getExtentionParam() {
        List<Integer> aList = new ArrayList<>();
        for (int a = 0; a < Q ; a++) {
            if(gcd(a,Q)==1 && gcd(a,Q-1)!=1) {
                aList.add(a);
            }
        }
        return aList;
    }
}
