package ua.kpi.pti.diploma;

import java.io.FileWriter;
import java.io.IOException;

public class MatrixToCSVPrinter {

    public static void printMatrixInHTML(int[][] matrix) throws IOException {
        FileWriter writer =new FileWriter(PreCalculationsAndConstants.FILE_NAME);

        for (int i = 0; i < matrix.length; i++) {

            // Loop through all elements of current row
            for (int j = 0; j < matrix[i].length; j++) {
                writer.append(  matrix[i][j] + ";");
            }
            writer.append("\n");
        }
        writer.flush();
        writer.close();

    }



}
