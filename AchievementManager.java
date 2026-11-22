import java.util.*;

public class AchievementManager {

    private HashMap<Integer, ArrayList<AchievementRecord>> map = new HashMap<>();

    public void add(int id, AchievementRecord a) {

        if (id <= 0) {
            System.out.println("Invalid student ID!");
            return;
        }

        if (a == null) {
            System.out.println("Achievement details cannot be empty!");
            return;
        }

        map.putIfAbsent(id, new ArrayList<>());
        map.get(id).add(a);

        System.out.println("Achievement added successfully!");
    }

    public void viewStudent(int id, String name) {

        System.out.println("===== ACHIEVEMENT SUMMARY =====");
        System.out.println("Student: " + name + " (" + id + ")");

        if (!map.containsKey(id) || map.get(id).isEmpty()) {
            System.out.println("No achievements available.");
            return;
        }

        int acad = 0, hack = 0, work = 0, cert = 0, extra = 0;

        for (AchievementRecord a : map.get(id)) {

            a.displayDetails();

            if (a.type.equalsIgnoreCase("Academic"))
                acad++;
            else if (a.type.equalsIgnoreCase("Hackathon"))
                hack++;
            else if (a.type.equalsIgnoreCase("Workshop"))
                work++;
            else if (a.type.equalsIgnoreCase("Certification"))
                cert++;
            else if (a.type.equalsIgnoreCase("Extracurricular"))
                extra++;
        }

        System.out.println("\n===== ACHIEVEMENT STATISTICS =====");
        System.out.println("Academic: " + acad);
        System.out.println("Hackathons: " + hack);
        System.out.println("Workshops: " + work);
        System.out.println("Certifications: " + cert);
        System.out.println("Extracurricular: " + extra);

        System.out.println("Total Achievements: " + map.get(id).size());
    }

    public void viewByType(int id, String type) {

        if (!map.containsKey(id)) {
            System.out.println("No achievements found for this student.");
            return;
        }

        boolean found = false;

        for (AchievementRecord a : map.get(id)) {

            if (a.type.equalsIgnoreCase(type)) {
                a.displayDetails();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No achievements found for this type.");
        }
    }
}