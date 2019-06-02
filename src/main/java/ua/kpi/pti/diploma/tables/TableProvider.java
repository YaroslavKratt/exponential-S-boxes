package ua.kpi.pti.diploma.tables;

import ua.kpi.pti.diploma.tables.threads.TableThread;

import java.util.List;
import java.util.Map;

public abstract class TableProvider {
    protected String tableName;
    protected List<TableThread> threadPool;


    abstract int[][] getTable(int basis);

    int maxInTable(int[][] matrix) {
        int max = 0;
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[i].length; j++) {
                if (matrix[i][j] > max) {
                    max = matrix[i][j];
                }
            }
        }
        return max;
    }

    void calculateWithMultiThreads( List<TableThread> threadPool) {
        threadPool.forEach(TableThread::start);

        for (TableThread thread : threadPool) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public abstract Map<Integer, Integer> calculateStatistics(List<Integer> basises);


    public String getTableName() {
        return tableName;
    }


    protected abstract List<TableThread> getThreadPool(int[][] table, int basis);
}
