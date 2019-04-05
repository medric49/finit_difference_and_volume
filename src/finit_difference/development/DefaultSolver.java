package finit_difference.development;

import cern.colt.matrix.DoubleMatrix2D;
import cern.colt.matrix.impl.DenseDoubleMatrix2D;
import cern.colt.matrix.impl.SparseDoubleMatrix2D;
import cern.colt.matrix.linalg.Algebra;
import utilities.Functions;

public class DefaultSolver implements Solver{
    @Override
    public Vectorizable solve(double alpha, double beta, int n, Function f) throws FiniteDifferenceException {
        if (n >0) {
            double[] x = Functions.getMaillage(n);
            double h_carre = 1./(n+1);
            h_carre *=h_carre;

            DoubleMatrix2D M = new SparseDoubleMatrix2D(n,n);
            DoubleMatrix2D y = new DenseDoubleMatrix2D(n,1);

            for (int i = 0; i<n; i++) {
                for (int j = 0; j<n;j++) {
                    double d = 0;
                    if (i==j)
                        d = 2;
                    else if (j == i+1 || j == i-1)
                        d = -1;
                    M.set(i,j,d);
                }

                if (i == 0)
                    y.setQuick(i, 0, h_carre*f.calcul(x[i])+ alpha );
                else if (i == n-1)
                    y.setQuick(i, 0, h_carre*f.calcul(x[i]) + beta );
                else
                    y.setQuick(i, 0, h_carre*f.calcul(x[i]));

            }
            DoubleMatrix2D result =  Algebra.DEFAULT.solve(M,y);

            Vector v = new Vector(n);
            for (int i = 0; i<n; i++) {
                v.set(i, result.getQuick(i,0));
            }

            return v;
        }
        else
            throw new FiniteDifferenceException("Le nombre de maille est négatif");
    }
}
