package finit_difference.test;

import finit_difference.development.Vectorizable;

public interface Mesure {
    double getError(Vectorizable v1, Vectorizable v2);
}
