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

    }
}
