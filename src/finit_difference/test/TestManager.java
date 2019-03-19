package finit_difference.test;

import finit_difference.development.*;
import javafx.util.Pair;
import utilities.Functions;

import java.util.ArrayList;

public abstract class TestManager {
    public static ArrayList<Integer> run() {
        ArrayList<Integer> results = new ArrayList<>();
        ArrayList<Pair<Character, Character>> testClasses = Functions.getTestClasses();

        for (int i=0; i< testClasses.size(); i++) {
            Pair<Character, Character> classe = testClasses.get(i);

            String scenario = null;
            Function f = null;

            double alpha = 0;
            double beta = 0;
            double tol = 0;
            long n = 0;
            TestFunction g = null;
            Vector ra = null;
            Mesure mesure = null;

            g = new TestFunction() {
                @Override
                public Vectorizable getRO(Solver f, De de) {
                    if (de.getN() > 0) {
                        Vectorizable ro = null;
                        try {
                            return f.solve(de.getAlpha(), de.getBeta(), de.getN(),de.getF());
                        } catch (Exception e) {
                            return null;
                        }
                    }

                    else {
                        try {
                            Vectorizable ro = f.solve(de.getAlpha(), de.getBeta(), de.getN(),de.getF());
                            return new Vector();
                        }catch (FiniteDifferenceException e) {
                            return null;
                        }catch (Exception e) {
                            return new Vector();
                        }
                    }
                }
            };



            switch (classe.getValue()) {
                case 'a':
                    n = -3;

                    mesure =new Mesure() {
                        @Override
                        public double getError(Vectorizable v1, Vectorizable v2) {
                            if (v1 == v2)
                                return 0;
                            return 10;
                        }
                    };
                    break;

                case 'b':
                    n = 0;
                    mesure =new Mesure() {
                        @Override
                        public double getError(Vectorizable v1, Vectorizable v2) {
                            if (v1 == v2)
                                return 0;
                            return 10;
                        }
                    };
                    break;

                case 'c':
                    n = 1;
                    mesure =new Mesure() {
                        @Override
                        public double getError(Vectorizable v1, Vectorizable v2) {
                            if (v1 != null)
                            {
                                if (v1.size() == v2.size()) {
                                    double s = 0;
                                    for (int i =0; i<v1.size(); i++) {
                                        s += (v1.get(i)-v2.get(i))*(v1.get(i)-v2.get(i));
                                    }
                                    return Math.sqrt(s);
                                }
                            }
                            return 10;
                        }
                    };

                    break;

                case 'd':
                    n = 2;
                    mesure =new Mesure() {
                        @Override
                        public double getError(Vectorizable v1, Vectorizable v2) {
                            if (v1 != null)
                            {
                                if (v1.size() == v2.size()) {
                                    double s = 0;
                                    for (int i =0; i<v1.size(); i++) {
                                        s += (v1.get(i)-v2.get(i))*(v1.get(i)-v2.get(i));
                                    }
                                    return Math.sqrt(s);
                                }
                            }
                            return 10;
                        }
                    };
                    break;

                case 'e':
                    n = 10000;
                    mesure =new Mesure() {
                        @Override
                        public double getError(Vectorizable v1, Vectorizable v2) {
                            if (v1 != null)
                            {
                                if (v1.size() == v2.size()) {
                                    double s = 0;
                                    for (int i =0; i<v1.size(); i++) {
                                        s += (v1.get(i)-v2.get(i))*(v1.get(i)-v2.get(i));
                                    }
                                    return Math.sqrt(s);
                                }
                            }
                            return 10;
                        }
                    };
                    break;
            }


            switch (classe.getKey()) {
                case 'a':
                    scenario = "Cas de la fonction nulle";

                    f = new Function() {
                        @Override
                        public double calcul(double x) {
                            return 0;
                        }
                    };
                    alpha = 0;
                    beta = 0;


                    ra = new Vector();

                    tol = 10e-8;
                    break;

                case 'b':
                    scenario = "Cas d'une fonction constante non nulle";

                    f = new Function() {
                        @Override
                        public double calcul(double x) {
                            return 0;
                        }
                    };
                    alpha = 2;
                    beta = 2;


                    ra = new Vector();

                    tol = 10e-8;
                    break;

                case 'c':
                    scenario = "Cas d'une fonction linéaire";
                    f = new Function() {
                        @Override
                        public double calcul(double x) {
                            return 0;
                        }
                    };
                    alpha = 0;
                    beta = 1;

                    ra = new Vector();


                    tol = 10e-8;
                    break;

                case 'd':
                    scenario = "Cas d'une fonction polynomiale de dégré 2";

                    f = new Function() {
                        @Override
                        public double calcul(double x) {
                            return 1;
                        }
                    };
                    alpha = 0;
                    beta = 1./2;

                    ra = new Vector();


                    tol = 10e-8;
                    break;

                case 'e':
                    scenario = "Cas d'une fonction polynomiale de degré 3";

                    f = new Function() {
                        @Override
                        public double calcul(double x) {
                            return 3.*x;
                        }
                    };
                    alpha = 0;
                    beta = 1./2.;

                    ra = new Vector();


                    tol = 10e-8;
                    break;

                case 'f':
                    scenario = "Fonction exponentielle";

                    f = new Function() {
                        @Override
                        public double calcul(double x) {
                            return 16*Math.exp(4.*x);
                        }
                    };
                    alpha = 1;
                    beta = Math.exp(4);


                    ra = new Vector();

                    tol = 10e-8;
                    break;

                case 'g':
                    scenario = "Fonction polynomiale de degré elevé";

                    f = new Function() {
                        @Override
                        public double calcul(double x) {
                            return 9900.*Math.pow(x-0.5, 98);
                        }
                    };
                    alpha = Math.pow(0.5, 100);
                    beta = Math.pow(0.5, 100);

                    ra = new Vector();


                    tol = 10e-8;
                    break;

                case 'h':
                    scenario = "Cas d'une fonction logarithmique";

                    f = new Function() {
                        @Override
                        public double calcul(double x) {
                            return -1./((x+.5)*(x+.5));
                        }
                    };
                    alpha = -Math.log(2);
                    beta = Math.log(3./2.);


                    ra = new Vector();

                    tol = 10e-8;

                    break;

                case 'i':
                    scenario = "Cas d'une fonction sinusoidale";

                    f = new Function() {
                        @Override
                        public double calcul(double x) {
                            return -(4*Math.PI*Math.PI)*Math.sin(2*Math.PI*x);
                        }
                    };
                    alpha = 0;
                    beta = 0;


                    ra = new Vector();


                    tol = 10e-8;
                    break;

                case 'j':
                    scenario = "Cas d'une fonction chainette";

                    f = new Function() {
                        @Override
                        public double calcul(double x) {
                            return 16*Math.sinh(4*x);
                        }
                    };
                    alpha = 0;
                    beta = Math.sinh(4);


                    ra = new Vector();

                    tol = 10e-8;
                    break;
            }

            if (n <= 0)
                ra =null;

            results.add((new TestData(
                    new DefaultSolver(), scenario, new De(alpha, beta, n, f), g, ra, mesure, tol
            )).result() ? 1:0);

        }


        return results;
    }
}
