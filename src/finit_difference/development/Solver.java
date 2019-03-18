package finit_difference.development;

public interface Solver {
    Vectorizable solve(double alpha, double beta, long n, Function f) throws FiniteDifferenceException;
    // alpha = u(0); beta = u(1)
}
