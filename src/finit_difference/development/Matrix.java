package finit_difference.development;

public interface Matrix {
    double get(long i, long j) throws MatrixOutOfSizeException;
    void set(long i,long j,double x) throws MatrixOutOfSizeException;
}
