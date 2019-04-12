package finit_volume.test;

import finit_volume.development.*;
import javafx.util.Pair;
import utilities.Functions;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.mdalsoft.test.DefaultTestLogger.logTest;

public class TestManager {

    public static void main(String[] args) {
        ArrayList<Integer> results = new ArrayList<>();
        ArrayList<String> scenarii = new ArrayList<>();
        ArrayList<Double> times = new ArrayList<>();

        ArrayList<Pair<Character, Character>> testClasses = Functions.getTestClasses();
        File logFile = Functions.createLogFile(Functions.FINITE_VOLUME_LOG_FOLDER);


        Date start = new Date();
        try {
            String classeATester = "finit_volume.development.DefaultSolver";
            String methodToTest = "DefaultSolver.solve";
            String testFileLogger = "com.mdalsoft.test.FileTestLogger";
            for (int i = 0; i < testClasses.size(); i++) {
                Pair<Character, Character> classe = testClasses.get(i);

                String scenario = null;
                Function f = null;
                Function u = null;
                double alpha = 0;
                double beta = 0;
                double tol = 0;
                int n = 0;
                TestFunction g = null;
                Vector ra = null;
                Mesure mesure = null;


                g = new TestFunction() {
                    @Override
                    public Vectorizable getRO(Solver f, De de) {
                        if (de.getN() > 0) {
                            Vectorizable ro = null;
                            try {
                                return f.solve(de.getAlpha(), de.getBeta(), de.getN(), de.getF());
                            } catch (Exception e) {
                                return null;
                            }
                        } else {
                            try {
                                Vectorizable ro = f.solve(de.getAlpha(), de.getBeta(), de.getN(), de.getF());
                                return new Vector(1);
                            } catch (FiniteVolumeException e) {
                                return null;
                            } catch (Exception e) {
                                return new Vector(1);
                            }
                        }
                    }
                };


                switch (classe.getValue()) {
                    case 'a':
                        n = -3;
                        mesure = new Mesure() {
                            @Override
                            public double getError(Vectorizable v1, Vectorizable v2) {
                                if (v1 == v2)
                                    return 0;
                                return 10;
                            }
                        };
                        tol = 10e-10;
                        break;

                    case 'b':
                        n = 0;
                        mesure = new Mesure() {
                            @Override
                            public double getError(Vectorizable v1, Vectorizable v2) {
                                if (v1 == v2)
                                    return 0;
                                return 10;
                            }
                        };
                        tol = 10e-10;
                        break;

                    case 'c':
                        n = 1;
                        mesure = new Mesure() {
                            @Override
                            public double getError(Vectorizable v1, Vectorizable v2) {
                                if (v1 != null) {
                                    if (v1.size() == v2.size()) {
                                        double s = 0;
                                        double s0 = 0;
                                        double tmp = 0;
                                        for (int i = 0; i < v1.size(); i++) {
                                            s0 += v2.get(i)*v2.get(i);
                                            tmp = v1.get(i) - v2.get(i);
                                            s += tmp * tmp;
                                        }

                                        s0 = Math.sqrt(s0);
                                        if (s0 == 0)
                                            return Math.sqrt(s);
                                        else
                                            return Math.sqrt(s) / s0;
                                    }
                                }
                                return 10;
                            }
                        };
                        tol = 10e-8;
                        break;

                    case 'd':
                        n = 2;
                        mesure = new Mesure() {
                            @Override
                            public double getError(Vectorizable v1, Vectorizable v2) {
                                if (v1 != null) {
                                    if (v1.size() == v2.size()) {
                                        double s = 0;
                                        double s0 = 0;
                                        double tmp = 0;
                                        for (int i = 0; i < v1.size(); i++) {
                                            s0 += v2.get(i)*v2.get(i);
                                            tmp = v1.get(i) - v2.get(i);
                                            s += tmp * tmp;
                                        }

                                        s0 = Math.sqrt(s0);
                                        if (s0 == 0)
                                            return Math.sqrt(s);
                                        else
                                            return Math.sqrt(s) / s0;
                                    }
                                }
                                return 10;
                            }
                        };
                        tol = 10e-8;
                        break;

                    case 'e':
                        n = 10000;
                        mesure = new Mesure() {
                            @Override
                            public double getError(Vectorizable v1, Vectorizable v2) {
                                if (v1 != null) {
                                    if (v1.size() == v2.size()) {
                                        double s = 0;
                                        double s0 = 0;
                                        double tmp = 0;
                                        for (int i = 0; i < v1.size(); i++) {
                                            s0 += v2.get(i)*v2.get(i);
                                            tmp = v1.get(i) - v2.get(i);
                                            s += tmp * tmp;
                                        }

                                        s0 = Math.sqrt(s0);
                                        if (s0 == 0)
                                            return Math.sqrt(s);
                                        else
                                            return Math.sqrt(s) / s0;
                                    }
                                }
                                return 10;
                            }
                        };
                        tol = 10e-8;
                        break;
                }

                switch (classe.getKey()) {
                    case 'a':
                        scenario = "Fonction nulle";

                        f = new Function() {
                            @Override
                            public double calcul(double x) {
                                return 0;
                            }
                        };
                        u = new Function() {
                            @Override
                            public double calcul(double x) {
                                return 0;
                            }
                        };
                        alpha = 0;
                        beta = 0;

                        if (n > 0) {
                            ra = new Vector(n);

                            Pair<double[], double[]> pair = Functions.getVolume(n);
                            double[] x = pair.getValue();

                            for (int j = 1; j <= n; j++) {
                                ra.set(j-1, u.calcul(x[j]));
                            }
                        }
                        break;

                    case 'b':
                        scenario = "Fonction constante non nulle";

                        f = new Function() {
                            @Override
                            public double calcul(double x) {
                                return 0;
                            }
                        };
                        u = new Function() {
                            @Override
                            public double calcul(double x) {
                                return 2;
                            }
                        };
                        alpha = 2;
                        beta = 2;

                        if (n > 0) {
                            ra = new Vector(n);

                            Pair<double[], double[]> pair = Functions.getVolume(n);
                            double[] x = pair.getValue();

                            for (int j = 1; j <= n; j++) {
                                ra.set(j-1, u.calcul(x[j]));
                            }
                        }

                        break;

                    case 'c':
                        scenario = "Fonction linéaire";
                        f = new Function() {
                            @Override
                            public double calcul(double x) {
                                return 0;
                            }
                        };
                        u = new Function() {
                            @Override
                            public double calcul(double x) {
                                return x;
                            }
                        };
                        alpha = 0;
                        beta = 1;

                        if (n > 0) {
                            ra = new Vector(n);
                            Pair<double[], double[]> pair = Functions.getVolume(n);
                            double[] x = pair.getValue();

                            for (int j = 1; j <= n; j++) {
                                ra.set(j-1, u.calcul(x[j]));
                            }
                        }


                        break;

                    case 'd':
                        scenario = "Fonction polynomiale de dégré 2";

                        f = new Function() {
                            @Override
                            public double calcul(double x) {
                                return -1;
                            }
                        };
                        u = new Function() {
                            @Override
                            public double calcul(double x) {
                                return (1./2)*x*x;
                            }
                        };
                        alpha = 0;
                        beta = 1. / 2;

                        if (n > 0) {
                            ra = new Vector(n);

                            Pair<double[], double[]> pair = Functions.getVolume(n);
                            double[] x = pair.getValue();

                            for (int j = 1; j <= n; j++) {
                                ra.set(j-1, u.calcul(x[j]));
                            }
                        }


                        break;

                    case 'e':
                        scenario = "Fonction polynomiale de degré 3";

                        f = new Function() {
                            @Override
                            public double calcul(double x) {
                                return -3. * x;
                            }
                        };
                        u = new Function() {
                            @Override
                            public double calcul(double x) {
                                return  (1./2)*x*x*x;
                            }
                        };
                        alpha = 0;
                        beta = 1. / 2.;

                        if (n > 0) {
                            ra = new Vector(n);

                            Pair<double[], double[]> pair = Functions.getVolume(n);
                            double[] x = pair.getValue();

                            for (int j = 1; j <= n; j++) {
                                ra.set(j-1, u.calcul(x[j]));
                            }
                        }


                        break;

                    case 'f':
                        scenario = "Fonction exponentielle";

                        f = new Function() {
                            @Override
                            public double calcul(double x) {
                                return -16 * Math.exp(4. * x);
                            }
                        };
                        u = new Function() {
                            @Override
                            public double calcul(double x) {
                                return Math.exp(4*x);
                            }
                        };
                        alpha = 1;
                        beta = Math.exp(4);


                        if (n > 0) {
                            ra = new Vector(n);

                            Pair<double[], double[]> pair = Functions.getVolume(n);
                            double[] x = pair.getValue();

                            for (int j = 1; j <= n; j++) {
                                ra.set(j-1, u.calcul(x[j]));
                            }
                        }

                        break;

                    case 'g':
                        scenario = "Fonction polynomiale de degré elevé";

                        f = new Function() {
                            @Override
                            public double calcul(double x) {
                                return -9900. * Math.pow(x - 0.5, 98);
                            }
                        };
                        u = new Function() {
                            @Override
                            public double calcul(double x) {
                                return Math.pow(x-0.5, 100);
                            }
                        };
                        alpha = Math.pow(0.5, 100);
                        beta = Math.pow(0.5, 100);

                        if (n > 0) {
                            ra = new Vector(n);


                            Pair<double[], double[]> pair = Functions.getVolume(n);
                            double[] x = pair.getValue();

                            for (int j = 1; j <= n; j++) {
                                ra.set(j-1, u.calcul(x[j]));
                            }
                        }


                        break;

                    case 'h':
                        scenario = "Fonction logarithmique";

                        f = new Function() {
                            @Override
                            public double calcul(double x) {
                                return 1. / ((x + .5) * (x + .5));
                            }
                        };
                        u = new Function() {
                            @Override
                            public double calcul(double x) {
                                return Math.log(x+.5);
                            }
                        };
                        alpha = -Math.log(2);
                        beta = Math.log(3. / 2.);


                        if (n > 0) {
                            ra = new Vector(n);


                            Pair<double[], double[]> pair = Functions.getVolume(n);
                            double[] x = pair.getValue();

                            for (int j = 1; j <= n; j++) {
                                ra.set(j-1, u.calcul(x[j]));
                            }
                        }


                        break;

                    case 'i':
                        scenario = "Fonction sinusoidale";

                        f = new Function() {
                            @Override
                            public double calcul(double x) {
                                return (4 * Math.PI * Math.PI) * Math.sin(2 * Math.PI * x);
                            }
                        };
                        u = new Function() {
                            @Override
                            public double calcul(double x) {
                                return Math.sin(2*Math.PI*x);
                            }
                        };
                        alpha = 0;
                        beta = 0;


                        if (n > 0) {
                            ra = new Vector(n);


                            Pair<double[], double[]> pair = Functions.getVolume(n);
                            double[] x = pair.getValue();

                            for (int j = 1; j <= n; j++) {
                                ra.set(j-1, u.calcul(x[j]));
                            }
                        }

                        break;

                    case 'j':
                        scenario = "Fonction chainette";

                        f = new Function() {
                            @Override
                            public double calcul(double x) {
                                return -16 * Math.sinh(4 * x);
                            }
                        };
                        u = new Function() {
                            @Override
                            public double calcul(double x) {
                                return Math.sinh(4*x);
                            }
                        };
                        alpha = 0;
                        beta = Math.sinh(4);

                        if (n > 0) {
                            ra = new Vector(n);

                            Pair<double[], double[]> pair = Functions.getVolume(n);
                            double[] x = pair.getValue();

                            for (int j = 1; j <= n; j++) {
                                ra.set(j-1, u.calcul(x[j]));
                            }
                        }

                        break;
                }


                if (n < 0)
                    scenario += " avec n négatif";
                else if (n == 0)
                    scenario += " avec n nul";
                else if (n == 1)
                    scenario += " avec n valant 1";
                else if (n == 2)
                    scenario += " avec n valant 2";
                else
                    scenario += " avec n très grand";

                TestData testData = new TestData(
                        new DefaultSolver(), scenario, new De(alpha, beta, n, f), g, ra, mesure, tol
                );

                Map parTest = new HashMap();
                parTest.put("file",Functions.FINITE_DIFFERENCE_LOG_FOLDER+"/finite_difference.log");
                Date d1 = new Date();

                parTest.put("classtotest", classeATester);
                parTest.put("testreference", methodToTest);
                parTest.put("teststarttime", "" + d1.getTime());
                parTest.put("testLogger0", testFileLogger);

                logTest(parTest, "start", false);
                parTest.put("testcase", scenario);

                boolean result = testData.result();
                parTest.put("testresult", result);

                Date d2 = new Date();

                parTest.put("testendtime", "" + d2.getTime());
                logTest(parTest, "end", false);

                results.add(result ? 1 : 0);
                scenarii.add(scenario);
                times.add((d2.getTime() - d1.getTime()) / 1000.);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        Date end = new Date();

        try {

            double s = 0;

            for (int i = 0; i < results.size(); i++) {
                s += results.get(i);
            }
            s /= results.size();
            System.out.println("Taux de reussite : " + (s * 100)+"%");


            FileOutputStream io = new FileOutputStream(logFile);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(io));

            writer.write("==========   Résultat du test de la librairie des différences finies   ==========\n\n");
            writer.newLine();

            writer.write("Date de début : " + Functions.FORMATTER.format(start));
            writer.newLine();
            writer.write("Date de fin : " + Functions.FORMATTER.format(end));
            writer.newLine();
            writer.write("Durée du test : " + ((end.getTime() - start.getTime()) / 1000.) + " secondes");
            writer.newLine();
            writer.write("Résultat du test : " + (int) (s * results.size()) + "/" + results.size());
            writer.newLine();
            writer.write("Pourcentage de réussite : " + (s * 100) + " %");
            writer.write("\n");
            writer.newLine();

            writer.write("****** Détails ******\n");
            writer.newLine();
            for (int i = 0; i < results.size(); i++) {
                writer.write("Scénario du cas de test : " + scenarii.get(i));
                writer.newLine();
                writer.write("Résultat du cas de test : " + (results.get(i) == 1 ? "Succès" : "Echec"));
                writer.newLine();
                writer.write("Durée d'exécution du cas de test : " + times.get(i) + " secondes\n");
                writer.newLine();
            }


            writer.close();
            io.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
