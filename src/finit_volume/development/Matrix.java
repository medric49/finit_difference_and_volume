package finit_volume.development;

import finit_difference.development.MatrixOutOfSizeException;

interface Matrix{
    double get(long i, long j) throws MatrixOutOfSizeException;
    void set(long i, long j, double x);

}