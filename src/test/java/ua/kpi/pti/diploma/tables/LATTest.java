package ua.kpi.pti.diploma.tables;


import org.junit.Test;
import ua.kpi.pti.diploma.utils.Constants;

import static java.lang.Math.pow;
import static ua.kpi.pti.diploma.utils.Constants.Q;

public class LATTest {


    @Test
    public void checkSumForRows() throws Exception {
        int[][] lat = new LAT().getTable(6);
        for (int i = 1; i < Q; i++) {
            int sum = 0;
            for (int j = 1; j < Q; j++) {
                sum += lat[i][j];
            }
            System.out.println(sum);
            if (sum != (int) pow(4, Constants.M)) {
                throw new Exception("sum=" + sum + " expected=" + (int) pow(4, Constants.M));
            }

        }
    }

    @Test
    public void checkSumForColumns() throws Exception {

        int[][] lat = new LAT().getTable(6);

        for (int j = 1; j < Q; j++) {
            int sum = 0;
            for (int i = 1; i < Q; i++) {

                sum += lat[i][j];
            }
            System.out.println(sum);
            if (sum != (int) pow(4, Constants.M)) {
                throw new Exception("sum=" + sum + " expected=" + (int) pow(4, Constants.M));
            }

        }
    }
}

