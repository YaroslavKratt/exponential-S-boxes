package ua.kpi.pti.diploma.tables.threads.ususal_threads;

public abstract class TableThread extends Thread {
    protected  int[][] table;
    protected  int startAlpha;
    protected  int endAlpha;
    protected int basis;

    public TableThread(int[][] lat, int startAlpha, int endAlpha, int basis) {
        this.table = lat;
        this.startAlpha = startAlpha;
        this.endAlpha = endAlpha;
        this.basis = basis;
    }


}
