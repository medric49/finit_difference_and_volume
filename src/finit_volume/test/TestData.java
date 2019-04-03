package finit_volume.test;

import finit_volume.development.*;

public class TestData {
    private Solver f;
    private String sce;
    private De de;
    private TestFunction g;
    private Vectorizable ra;
    private Mesure m;
    private double tol;

    public TestData(Solver f, String sce, De de, TestFunction g, Vectorizable ra, Mesure m, double tol) {
        this.f = f;
        this.sce = sce;
        this.de = de;
        this.g = g;
        this.ra = ra;
        this.m = m;
        this.tol = tol;
    }

    public boolean result() {
        return this.m.getError(this.g.getRO(this.f, this.de), this.ra) < this.tol;
    }


}
