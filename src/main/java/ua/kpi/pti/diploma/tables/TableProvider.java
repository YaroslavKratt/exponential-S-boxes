package ua.kpi.pti.diploma.tables;

import ua.kpi.pti.diploma.Type;
import ua.kpi.pti.diploma.extender.ExtenderThread;
import ua.kpi.pti.diploma.extender.SboxExtender;
import ua.kpi.pti.diploma.tables.threads.DdtXorXorThread;
import ua.kpi.pti.diploma.tables.threads.LambdaThread;
import ua.kpi.pti.diploma.tables.threads.TableThread;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static ua.kpi.pti.diploma.extender.SboxExtender.aList;
import static ua.kpi.pti.diploma.utils.Constants.*;
import static ua.kpi.pti.diploma.utils.Constants.CORES;

public abstract class TableProvider {
    protected String tableName;
    protected List<TableThread> threadPool;
    private SboxExtender ex = new SboxExtender();

    public int[][] getTable(int[] sbox) {
        int[][] table = new int[Q][Q];
        calculateWithMultiThreads(getThreadPool(table, sbox));
        return table;

    }

    protected abstract void calculate(int[][] table, int sbox[]);

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

    void calculateWithMultiThreads(List<TableThread> threadPool) {
        threadPool.forEach(TableThread::start);

        for (TableThread thread : threadPool) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private void multithread(Map<Integer, Integer> ststistics, int[] sbox) {
        int ddt[][] = new int[Q][Q];
        calculate(ddt, sbox);
        int max = maxInTable(ddt);
        ststistics.putIfAbsent(max, 0);
        ststistics.put(max, ststistics.get(max) + 1);
    }

    public Map<Integer, Integer> calculateStatistics(List<Integer> basises, Type type)  {
        Map<Integer, Integer> statistics = new ConcurrentHashMap<>();
        List<Thread> threadPool = new ArrayList<>();
        ex.getExtendedSBox();
        if (type == Type.USUAL) {

            for (int i = 0; i < basises.size(); i = i + 8) {

                int finalI = i;
                threadPool.add(new Thread(() -> multithread(statistics, sBoxUsusal[basises.get(finalI)])));
                threadPool.add(new Thread(() -> multithread(statistics, sBoxUsusal[basises.get(finalI + 1)])));
                threadPool.add(new Thread(() -> multithread(statistics, sBoxUsusal[basises.get(finalI + 2)])));
                threadPool.add(new Thread(() -> multithread(statistics, sBoxUsusal[basises.get(finalI + 3)])));
                threadPool.add(new Thread(() -> multithread(statistics, sBoxUsusal[basises.get(finalI + 4)])));
                threadPool.add(new Thread(() -> multithread(statistics, sBoxUsusal[basises.get(finalI + 5)])));
                threadPool.add(new Thread(() -> multithread(statistics, sBoxUsusal[basises.get(finalI + 6)])));
                threadPool.add(new Thread(() -> multithread(statistics, sBoxUsusal[basises.get(finalI + 7)])));


            }
            threadPool.forEach(Thread::start);
            for (Thread t : threadPool) {
                try {
                    t.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
        threadPool = new ArrayList<>();

        if (type == Type.EXTENDED) {
            for (int b = 0; b < Q; b++) {
                for (int a : aList) {
                    for (int i = 0; i < basises.size(); i++) {
                        int finalI = i;
                        int finalB = b;
                        threadPool.add(new Thread(() -> multithread(statistics, ex.getExtendedSBox()[finalB][a][basises.get(finalI)])));

                    }}}
                        for (int j = 0; j <= threadPool.size(); j++) {
                            List<Thread> pool = new ArrayList<>();
                            threadPool.get(j).start();
                            pool.add(threadPool.get(j));
                            if ((j+1) % 7 == 0) {
                                for (Thread p : pool) {
                                    try {
                                        p.join();
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }

                    }




        return statistics;
}


    public String getTableName() {
        return tableName;
    }


    protected abstract List<TableThread> getThreadPool(int[][] table, int[] sbox);


}

