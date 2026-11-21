public class AchievementRecord extends AcademicRecord {
    String type, title, organizer, date;
    String level;
    public AchievementRecord(String type, String title, String organizer, String date, String level) {
        this.type = type; this.title = title;
        this.organizer = organizer; this.date = date; this.level = level;
    }
    public void displayDetails() {
        System.out.println("[" + type + "] " + title + " | By: " + organizer +
            " | " + date + " | Level: " + level);
    }
    public int getTotalMarks() { return 0; }
}