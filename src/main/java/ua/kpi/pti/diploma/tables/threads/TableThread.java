package ua.kpi.pti.diploma.tables.threads;

public abstract class TableThread extends Thread {
    protected int[] sbox;
    protected  int[][] table;
    protected  int startAlpha;
    protected  int endAlpha;

    public TableThread(int[][] table, int startAlpha, int endAlpha,  int[]sBox) {
        this.table = table;
        this.startAlpha = startAlpha;
        this.endAlpha = endAlpha;
        this.sbox = sBox;
    }


}
