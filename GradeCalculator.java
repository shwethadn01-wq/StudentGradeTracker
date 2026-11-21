import java.util.*;
public class GradeCalculator {
    public static final int PASS = 40;
    public static String grade(int m) {
        if (m >= 90) return "O";
        if (m >= 80) return "A+";
        if (m >= 70) return "A";
        if (m >= 60) return "B+";
        if (m >= 50) return "B";
        if (m >= 40) return "C";
        return "F";
    }
    public static int point(int m) {
        if (m >= 90) return 10;
        if (m >= 80) return 9;
        if (m >= 70) return 8;
        if (m >= 60) return 7;
        if (m >= 50) return 6;
        if (m >= 40) return 5;
        return 0;
    }
    public static double sgpa(HashMap<String, int[]> subs) {
        double num = 0, den = 0;
        for (int[] v : subs.values()) { num += v[1] * point(v[0]); den += v[1]; }
        return den == 0 ? 0 : num / den;
    }
}