import java.util.*;

public class BacklogManager {

    private HashMap<Integer, ArrayList<BacklogRecord>> map = new HashMap<>();

    // Add backlog only when marks are below passing marks
    public void checkAndAdd(int id, String sub, int sem, int marks) {

        if (id <= 0 || sub == null || sub.trim().isEmpty() || sem <= 0) {
            return;
        }

        if (marks < 0 || marks > 100) {
            return;
        }

        if (marks < GradeCalculator.PASS) {
            map.putIfAbsent(id, new ArrayList<>());

            if (find(id, sub) == null) {
                map.get(id).add(new BacklogRecord(sub, sem, marks));
            }
        }
    }

    // Find backlog using student ID and subject
    public BacklogRecord find(int id, String sub) {

        if (sub == null || !map.containsKey(id)) {
            return null;
        }

        for (BacklogRecord b : map.get(id)) {
            if (b.subject.equalsIgnoreCase(sub.trim())) {
                return b;
            }
        }

        return null;
    }

    // Handle re-examination
    public void reExam(int id, String sub, int marks)
            throws GradeTrackerException {

        if (id <= 0) {
            throw new GradeTrackerException("Invalid student ID");
        }

        if (sub == null || sub.trim().isEmpty()) {
            throw new GradeTrackerException("Subject cannot be empty");
        }

        if (marks < 0 || marks > 100) {
            throw new GradeTrackerException("Invalid re-exam marks");
        }

        BacklogRecord b = find(id, sub);

        if (b == null) {
            throw new GradeTrackerException("Backlog not found");
        }

        if (b.cleared) {
            throw new GradeTrackerException("Backlog already cleared");
        }

        b.reExam(marks);

        System.out.println(
            marks >= GradeCalculator.PASS
            ? "Backlog cleared! Attempts: " + b.attempts
            : "Still active. Attempts: " + b.attempts
        );
    }

    // Display individual student backlog details
    public void viewStudent(int id, String name) {

        System.out.println("Student: " + name + " (" + id + ")");

        int active = 0;
        int cleared = 0;

        if (!map.containsKey(id)) {
            System.out.println("Status: No Backlogs");
            return;
        }

        for (BacklogRecord b : map.get(id)) {

            b.displayDetails();

            if (b.cleared) {
                cleared++;
            } else {
                active++;
            }
        }

        System.out.println("Active: " + active +
                " | Cleared: " + cleared +
                " | Status: " +
                (active == 0 ? "No Backlogs" : "Backlog Pending"));
    }

    // Display all students having active backlogs
    public void viewAllWithActive() {

        boolean found = false;

        for (int id : map.keySet()) {

            boolean has = false;

            for (BacklogRecord b : map.get(id)) {
                if (!b.cleared) {
                    has = true;
                    break;
                }
            }

            if (has) {
                System.out.println("Student ID " + id +
                        " has active backlog");
                found = true;
            }
        }

        if (!found) {
            System.out.println("No active backlogs found.");
        }
    }
}