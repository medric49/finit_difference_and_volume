import finit_difference.development.DefaultSolver;
import finit_difference.development.Function;
import finit_difference.development.Vectorizable;
import finit_difference.test.TestManager;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<Integer> results = TestManager.run();

        double s = 0;

        for (int i=0; i<results.size(); i++) {
            s += results.get(i);
        }
        s /= results.size();

        System.out.println("Résultat du test : "+ (s*100)+" %");

        Vectorizable v = (new DefaultSolver().solve(0, 0, 3, new Function() {
            @Override
            public double calcul(double x) {
                return 0;
            }
        }));
        for (int i = 0; i<v.size(); i++) {
            System.out.println(v.get(i));
        }
    }
}
