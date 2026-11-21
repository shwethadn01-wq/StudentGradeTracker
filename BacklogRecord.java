public class BacklogRecord extends AcademicRecord {
    String subject; int sem, originalMarks, attempts = 1, latestMarks;
    boolean cleared = false;
    public BacklogRecord(String s, int sem, int marks) {
        subject = s; this.sem = sem; originalMarks = marks; latestMarks = marks;
    }
    public void reExam(int marks) {
        attempts++; latestMarks = marks;
        if (marks >= GradeCalculator.PASS) cleared = true;
    }
    public void displayDetails() {
        System.out.println(subject + " | Sem " + sem + " | Orig: " + originalMarks +
            " | Attempts: " + attempts + " | " + (cleared ? "Cleared (" + latestMarks + ")" : "Active"));
    }
    public int getTotalMarks() { return latestMarks; }
}