package finit_volume.test;

import finit_volume.development.*;

public class De {
    private double alpha;
    private double beta;
    private Function f;
    private int n;

    public De(double alpha, double beta, int n, Function f) {
        this.alpha = alpha;
        this.beta = beta;
        this.f = f;
        this.n = n;
    }

    public double getAlpha() {
        return alpha;
    }

    public void setAlpha(double alpha) {
        this.alpha = alpha;
    }

    public double getBeta() {
        return beta;
    }

    public void setBeta(double beta) {
        this.beta = beta;
    }

    public Function getF() {
        return f;
    }

    public void setF(Function f) {
        this.f = f;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }
}
