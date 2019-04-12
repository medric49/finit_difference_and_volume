package finit_difference.development;

import finit_difference.development.Function;
import finit_difference.development.Solver;
import finit_difference.development.Vector;

import static java.lang.Math.sqrt;

public class DefaultSolver implements Solver
{
    private Vector b;
    private DefaultMatrix matrice;

    @Override
    public Vectorizable solve(double alpha, double beta, int n, Function f)
    {
        double cond;
        Vector prod=new Vector(n-1);
        double s;
        Vector y;
        Vector solution=new Vector(n-1);
        double tol=1e-15;
        final int MAX_ITER = 100;
        int iter=0;
        double[] x =new double[n-1];
        double h=1/n;

        //calcul des xi
        for(int j=0;j<n-1;j++){
            if(j==0){
                x[0]=alpha+h;
            }else {
                x[j]=x[j-1]+h;
            }
        }

        //initialisation de b
        b=new Vector(n-1);
        b.set(0,f.calcul(x[0])+alpha);
        b.set(n-2,f.calcul(x[n-2])+beta);
        for(int i=1;i<n-2;i++)
        {
            b.set(i,f.calcul(x[i]));
        }

        //initialisation de la matrice
        matrice = new DefaultMatrix(n-1,h);

        //debut de la resolution avec l'algorithme de gauss siedel
        try {
            prod=produit(matrice,solution);
        } catch (MatrixOutOfSizeException e) {
            e.printStackTrace();
        }
        cond=norme(prod,b);
        while((cond>tol)&&(iter<MAX_ITER))
        {
            iter++;
            y=solution;
            for(int i=0;i<n;i++)
            {
                try{
                    s=0;
                    for(int j=0;j<i;j++)
                    {
                        s=s+matrice.get(i,j)*solution.get(j);
                    }
                    for(int j=i+1;j<n;j++)
                    {
                        s=s+matrice.get(i,j)*y.get(j);
                    }
                    solution.set(i, (b.get(i)-s)/matrice.get(i,i));
                }catch (MatrixOutOfSizeException e){
                    e.printStackTrace();
                }
            }
            try {
                prod=produit(matrice,solution);
            } catch (MatrixOutOfSizeException e) {
                e.printStackTrace();
            }
            cond=norme(prod,b);
        }

        return solution;
    }
    //pour calculer Ax
    public Vector produit(DefaultMatrix M,Vector V) throws MatrixOutOfSizeException {
        int n=V.size();
        Vector U=new Vector(n);
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                U.set(i, U.get(i)+M.get(i,j)*V.get(j));
            }
        }
        return U;
    }
    //pour la norme de ||Ax-b|| dans l'algorithme de gauss siedel
    public double norme(Vector V1,Vector V2)
    {
        int m=V1.size();
        double s=0;
        for(int i=0;i<m;i++)
        {
            s=s+(V1.get(i)-V2.get(i))*(V1.get(i)-V2.get(i));
        }
        s=sqrt(s);
        return s;
    }

}
