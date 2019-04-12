package finit_difference.development;

import finit_difference.development.Vectorizable;

public class Vector implements Vectorizable
{
    private double [] V;
    public Vector(int n){
        V=new double[n];
    }
    public double get(int i)
    {
        return V[i];
    }

    public void set(int i, double x)
    {
        V[i]=x;
    }
    public int size()
    {
        return V.length;
    }

}
