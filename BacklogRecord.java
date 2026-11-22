public class BacklogRecord extends AcademicRecord {

    String subject;
    int sem, originalMarks, attempts = 1, latestMarks;
    boolean cleared = false;

    public BacklogRecord(String s, int sem, int marks) {

        if (s == null || s.trim().isEmpty()) {
            throw new IllegalArgumentException("Subject cannot be empty");
        }

        if (sem <= 0) {
            throw new IllegalArgumentException("Invalid semester");
        }

        if (marks < 0 || marks > 100) {
            throw new IllegalArgumentException("Marks must be between 0 and 100");
        }

        subject = s.trim();
        this.sem = sem;
        originalMarks = marks;
        latestMarks = marks;

        if (marks >= GradeCalculator.PASS) {
            cleared = true;
        }
    }

    public void reExam(int marks) {

        if (marks < 0 || marks > 100) {
            throw new IllegalArgumentException("Invalid re-exam marks");
        }

        attempts++;
        latestMarks = marks;

        if (marks >= GradeCalculator.PASS) {
            cleared = true;
        }
    }

    public void displayDetails() {
        System.out.println(subject + " | Sem " + sem +
            " | Original Marks: " + originalMarks +
            " | Attempts: " + attempts +
            " | Latest Marks: " + latestMarks +
            " | Status: " + (cleared ? "Cleared" : "Active"));
    }

    public int getTotalMarks() {
        return latestMarks;
    }
}