package finit_difference.test;

import finit_difference.development.Function;

public class De {
    private double alpha;
    private double beta;
    private Function f;
    private long n;

    public De(double alpha, double beta, long n, Function f) {
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

    public long getN() {
        return n;
    }

    public void setN(long n) {
        this.n = n;
    }
}
