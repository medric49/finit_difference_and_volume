package finit_difference.test;

import finit_difference.development.*;

public abstract class TestManager {
    public static void run() {

        (new TestData(

                new DefaultSolver(),

                "sdsd",

                new De(0, 0, 10, new Function() {
                    @Override
                    public double calcul(double x) {
                        return Math.sin(x);
                    }
                }),

                new TestFunction() {
                    @Override
                    public Vectorizable getRO(Solver f, De de) {
                        Vectorizable ro = f.solve(de.getAlpha(), de.getBeta(), de.getN(), de.getF());
                        return ro;
                    }
                },

                new Vector(),

                new Mesure() {
                    @Override
                    public double getError(Vectorizable v1, Vectorizable v2) {
                        return 0;
                    }
                },

                10e-9
        )).result();

    }
}
