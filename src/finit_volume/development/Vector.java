package finit_volume.development;

public class Vector implements Vectorizable {

    private double [] V;

    public Vector(int n){
        V=new double[n];
    }

    @Override
    public double get(int i)
    {
        return V[i];
    }

    @Override
    public void set(int i, double x)
    {
        V[i]=x;
    }

    @Override
    public int size()
    {
        return V.length;
    }
}
