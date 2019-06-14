package ua.kpi.pti.diploma.tables;

import ua.kpi.pti.diploma.Type;
import ua.kpi.pti.diploma.extender.SboxGenerator;
import ua.kpi.pti.diploma.tables.threads.TableThread;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static ua.kpi.pti.diploma.extender.SboxGenerator.aList;
import static ua.kpi.pti.diploma.utils.Constants.*;

public abstract class TableProvider {
    protected String tableName;
    protected List<TableThread> threadPool;
    private SboxGenerator ex = new SboxGenerator();

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

    private void multithread(Map<Integer, Integer> ststistics, int[] sbox) {
        int ddt[][] = new int[Q][Q];
        calculate(ddt, sbox);
        int max = maxInTable(ddt);
        ststistics.putIfAbsent(max, 0);
        ststistics.put(max, ststistics.get(max) + 1);
    }

    public Map<Integer, Integer> calculateStatistics(List<Integer> basises, Type type) {
        Map<Integer, Integer> statistics = new ConcurrentHashMap<>();
        List<Thread> threadPool = new ArrayList<>();
        ex.getExtendedSBox(type);
        if (type == Type.USUAL) {

            for (int i = 0; i < basises.size(); i = i + 8) {
                for (int j = 0; j < 8; j++) {
                    int finalI = i;
                    int finalJ = j;
                    threadPool.add(new Thread(() -> multithread(statistics, sBoxUsusal[basises.get(finalI) + finalJ])));
                }
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
        if (type == Type.AFFINE_ON_ENTER || type == Type.AFFINE_ON_EXIT) {
            for (int b = 0; b < Q; b++) {
                for (int a : aList) {
                    for (int i = 0; i < basises.size(); i++) {
                        int finalI = i;
                        int finalB = b;
                        threadPool.add(new Thread(() -> multithread(statistics, ex.getExtendedSBox(type)[finalB][a][basises.get(finalI)])));

                    }
                }
            }
            //needs refactor
            for (int j = 0; j < threadPool.size(); j++) {
                System.out.println((double) j / threadPool.size() * 100);
                List<Thread> pool = new ArrayList<>();
                threadPool.get(j).start();
                pool.add(threadPool.get(j));
                if ((j + 1) % CORES == 0) {
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


    public abstract List<TableThread> getThreadPool(int[][] table, int[] sbox);

    public String getTableName() {
        return tableName;
    }


}

