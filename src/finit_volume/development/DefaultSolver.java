package finit_volume.development;

import cern.colt.matrix.DoubleMatrix2D;
import cern.colt.matrix.impl.DenseDoubleMatrix2D;
import cern.colt.matrix.impl.SparseDoubleMatrix2D;
import cern.colt.matrix.linalg.Algebra;
import javafx.util.Pair;
import utilities.Functions;

public class DefaultSolver implements Solver {
    @Override
    public Vectorizable solve(double alpha, double beta, int n, Function f) throws FiniteVolumeException {
        if (n>0) {
            Pair<double[], double[]> p = Functions.getVolume(n);

            double[] w = p.getKey();
            double[] x = p.getValue();


            DoubleMatrix2D M = new SparseDoubleMatrix2D(n,n);
            DoubleMatrix2D y = new DenseDoubleMatrix2D(n,1);

            for (int i = 1; i<=n; i++) {
                for (int j = 1; j<=n; j++) {
                    double d = 0;

                    if (i == j)
                        d = 1/(x[i+1]-x[i]) + 1/(x[i]-x[i-1]);
                    else if (j == i+1)
                        d = -1/(x[i+1]-x[i]);
                    else if (j == i-1)
                        d = -1/(x[i]-x[i-1]);

                    M.setQuick(i-1,j-1, d);
                }

                if (i == 1)
                    y.setQuick(i-1, 0,this.integrate(f,w[i-1],w[i]) + alpha/(x[i]-x[i-1]) );
                else if (i == n)
                    y.setQuick(i-1, 0, this.integrate(f,w[i-1],w[i]) + beta/(x[i+1]-x[i]) );
                else
                    y.setQuick(i-1, 0, this.integrate(f,w[i-1],w[i]));
            }

            DoubleMatrix2D result =  Algebra.DEFAULT.solve(M,y);

            Vector v = new Vector(n);
            for (int i = 0; i<n; i++) {
                v.set(i, result.getQuick(i,0));
            }

            return v;
        }
        else
            throw new FiniteVolumeException("Le nombre n est négatif ou nul");
    }


    private double integrate(Function f, double a, double b) {
        double p = (b-a)/2;
        double q = (b+a)/2;
        double s = p;
        s *= (f.calcul(-p*Math.sqrt(1./3.) + q) + f.calcul(p*Math.sqrt(1./3.) + q));
        return s;
    }
}
