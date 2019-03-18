package finit_difference.test;

import finit_difference.development.Solver;
import finit_difference.development.Vectorizable;

public interface TestFunction {
    Vectorizable getRO(Solver f, De de);
}
