package finit_volume.test;


import finit_volume.development.*;

public interface TestFunction {
    Vectorizable getRO(Solver f, De de);
}
