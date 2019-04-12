package finit_difference.development;

public class MatrixOutOfSizeException extends Exception{

    public MatrixOutOfSizeException(long i, long j, long n){
        super();
        System.out.println("i = "+i+" j = "+j+" n = "+n);
    }
}