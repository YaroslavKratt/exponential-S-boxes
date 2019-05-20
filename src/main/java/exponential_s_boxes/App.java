package exponential_s_boxes;

import java.util.List;

public class App {
    public static void main(String[] args) {
    AgafonovCriteriaFilter agafonovCriteriaFilter= new AgafonovCriteriaFilter();
     //   System.out.println(Integer.toBinaryString(Constants.FIELD.getPolynomial()));
    List<Integer> res = agafonovCriteriaFilter.filterByOptimalDifferentialCharacteristics(Utils.findAllPrimitiveElementsOfField());
    //    System.out.println("primitive:" + res.size());
        for (int r:res       ) {
       //     System.out.print(Integer.toHexString(r)+"; ");

        }
        System.out.println();
    res = agafonovCriteriaFilter.filterByMaximumAlgebrianDegree(res);
       System.out.println("res:" + res.size());
        for (int r:res       ) {
            System.out.print(Integer.toHexString(r)+"; ");

        }
    }
}

