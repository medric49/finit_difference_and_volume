package finit_difference.development;

public class DefaultMatrix implements Matrix{

    private long size;// taille de la matrice
    private double hcarr; // le carre du pas h du maillage

    private DefaultMatrix(){
        this.size = 0;
        this.hcarr = 0.;
    }
    public DefaultMatrix(long n,double h){
        this.size = n;
        this.hcarr = h*h;
    }
    public double get(long i,long j) throws MatrixOutOfSizeException{
        if((i<=size) && (j<=size)){
            if(i==j){
                return 2/hcarr;
            }else if((i-j==1)||(j-i==1)){
                return -1/hcarr;
            } else {
                return 0.;
            }
        }else throw new MatrixOutOfSizeException(i,j,this.size);
    }
    public void set(long i,long j,double x){

    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }
}