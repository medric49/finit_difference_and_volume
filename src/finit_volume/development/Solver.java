package finit_volume.development;

public interface Solver {
    Vectorizable solve(double alpha, double beta, int n, Function f) throws FiniteVolumeException;
    // alpha = u(0); beta = u(1)
}
