package utilities;

import javafx.util.Pair;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;

public abstract class Functions {
    private static final String FINITE_DIFFERENCE_LOG_FOLDER = "logs/finite_difference";

    public static String createFiniteDifferenceLogFile() {
        Date date = new Date();
        String fileName = FINITE_DIFFERENCE_LOG_FOLDER+"/"+date.toString()+".log";
        File file = new File(fileName);
        try {
            FileOutputStream io = new FileOutputStream(file);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(io));
            writer.close();
            io.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileName;
    }

    public static ArrayList<Pair<Character, Character>> getTestClasses() {
        ArrayList<Pair<Character, Character>> result = new ArrayList<>();
        String s = null;
        try {
            Process p = Runtime.getRuntime().exec("./jenny.sh 10 5");
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));

            while ((s = stdInput.readLine()) != null) {
                String[] strings = s.split(",");
                result.add(new Pair<Character, Character>(strings[0].charAt(1), strings[1].charAt(1)));
            }
            stdInput.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
            return result;
        }
    }
