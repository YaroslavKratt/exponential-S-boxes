package ua.kpi.pti.diploma.ddt;

import java.util.List;
import java.util.Map;

public interface DdtProvider {
    int[][] getDdt(int basis);

     default int maxInTable(int[][] matrix) {
        int max = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] > max) {
                    max = matrix[i][j];
                }
            }
        }
        return max;
    }

     Map<Integer, Integer> calculateStatistics(List<Integer> basises);


    }
