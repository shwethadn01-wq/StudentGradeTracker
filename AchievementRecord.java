public class AchievementRecord extends AcademicRecord {

    String type, title, organizer, date;
    String level;

    public AchievementRecord(String type, String title,
            String organizer, String date, String level) {

        if (type == null || type.trim().isEmpty()) {
            throw new IllegalArgumentException("Achievement type cannot be empty");
        }

        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Achievement title cannot be empty");
        }

        if (organizer == null || organizer.trim().isEmpty()) {
            throw new IllegalArgumentException("Organizer cannot be empty");
        }

        if (date == null || date.trim().isEmpty()) {
            throw new IllegalArgumentException("Date cannot be empty");
        }

        if (level == null || level.trim().isEmpty()) {
            throw new IllegalArgumentException("Achievement level cannot be empty");
        }

        if (!type.equalsIgnoreCase("Academic")
                && !type.equalsIgnoreCase("Hackathon")
                && !type.equalsIgnoreCase("Workshop")
                && !type.equalsIgnoreCase("Certification")
                && !type.equalsIgnoreCase("Extracurricular")) {

            throw new IllegalArgumentException("Invalid achievement type");
        }

        this.type = type.trim();
        this.title = title.trim();
        this.organizer = organizer.trim();
        this.date = date.trim();
        this.level = level.trim();
    }

    @Override
    public void displayDetails() {
        System.out.println("[" + type + "] " + title +
                " | By: " + organizer +
                " | " + date +
                " | Level: " + level);
    }

    @Override
    public int getTotalMarks() {
        return 0;
    }
}