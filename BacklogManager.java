import java.util.*;
public class BacklogManager {
    private HashMap<Integer, ArrayList<BacklogRecord>> map = new HashMap<>();
    public void checkAndAdd(int id, String sub, int sem, int marks) {
        if (marks < GradeCalculator.PASS) {
            map.putIfAbsent(id, new ArrayList<>());
            map.get(id).add(new BacklogRecord(sub, sem, marks));
        }
    }
    public BacklogRecord find(int id, String sub) {
        if (!map.containsKey(id)) return null;
        for (BacklogRecord b : map.get(id))
            if (b.subject.equalsIgnoreCase(sub)) return b;
        return null;
    }
    public void reExam(int id, String sub, int marks) throws GradeTrackerException {
        if (marks < 0 || marks > 100) throw new GradeTrackerException("Invalid re-exam marks");
        BacklogRecord b = find(id, sub);
        if (b == null) throw new GradeTrackerException("Backlog not found");
        if (b.cleared) throw new GradeTrackerException("Already cleared");
        b.reExam(marks);
        System.out.println(marks >= 40 ? "Backlog cleared! Attempts: " + b.attempts : "Still active. Attempts: " + b.attempts);
    }
    public void viewStudent(int id, String name) {
        System.out.println("Student: " + name + " (" + id + ")");
        int active = 0, cleared = 0;
        if (!map.containsKey(id)) { System.out.println("Status: No Backlogs"); return; }
        for (BacklogRecord b : map.get(id)) {
            b.displayDetails();
            if (b.cleared) cleared++; else active++;
        }
        System.out.println("Active: " + active + " | Cleared: " + cleared +
            " | Status: " + (active == 0 ? "No Backlogs" : "Backlog Pending"));
    }
    public void viewAllWithActive() {
        for (int id : map.keySet()) {
            boolean has = false;
            for (BacklogRecord b : map.get(id)) if (!b.cleared) has = true;
            if (has) System.out.println("Student ID " + id + " has active backlog");
        }
    }
}