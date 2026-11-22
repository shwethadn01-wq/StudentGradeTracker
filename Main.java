import java.util.*;
public class Main {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Student> students = new ArrayList<>();
    static HashSet<Integer> ids = new HashSet<>();
    static BacklogManager bm = new BacklogManager();
    static AchievementManager am = new AchievementManager();
    static Student search(int id) {
        for (Student s : students) if (s.getId() == id) return s;
        return null;
    }
    public static void main(String[] args) {
        while (true) {
            System.out.println("\n1.Register 2.View 3.Search 4.Update 5.Marks 6.Report 7.Backlog 8.Achievement 9.Exit");
            int ch = sc.nextInt(); sc.nextLine();
            try {
                if (ch == 1) register();
                else if (ch == 2) {
    if (students.isEmpty()) {
        System.out.println("No student records available.");
    } else {
        System.out.println("\n========== STUDENT DETAILS ==========");
        for (Student s : students) {
            System.out.println("Student ID   : " + s.getId());
            System.out.println("Name         : " + s.getName());
            System.out.println("Department   : " + s.getDept());
            System.out.println("Year         : " + s.getYear());
            System.out.println("Semester     : " + s.getSemester());
            System.out.println("-------------------------------------");
        }
    }
}
                else if (ch == 3) { System.out.print("ID: "); Student s = search(sc.nextInt()); System.out.println(s == null ? "Not found" : s.getName()); }
                else if (ch == 4) update();
                else if (ch == 5) addMarks();
                else if (ch == 6) report();
                else if (ch == 7) backlogMenu();
                else if (ch == 8) achievementMenu();
                else break;
            } catch (Exception e) { System.out.println("Error: " + e.getMessage()); }
        }
    }
    static void register() throws GradeTrackerException {
    System.out.print("ID Name Dept Year Sem: ");

    int id = sc.nextInt();
    String n = sc.next();
    String d = sc.next();
    int y = sc.nextInt();
    int sem = sc.nextInt();

    if (id <= 0) {
        throw new GradeTrackerException("Student ID must be positive");
    }

    if (ids.contains(id)) {
        throw new GradeTrackerException("Duplicate Student ID");
    }

    if (n.trim().isEmpty() || d.trim().isEmpty()) {
        throw new GradeTrackerException("Name and department cannot be empty");
    }

    if (y <= 0 || sem <= 0) {
        throw new GradeTrackerException("Invalid year or semester");
    }

    Student student = new Student(id, n, d, y, sem);

    students.add(student);
    ids.add(id);

    System.out.println("Student registered successfully!");
}
    static void update() {
        System.out.print("ID: "); Student s = search(sc.nextInt()); sc.nextLine();
        if (s == null) { System.out.println("Not found"); return; }
        System.out.print("New Dept: "); s.setDept(sc.nextLine());
        System.out.println("Updated!");
    }
    static void addMarks() throws GradeTrackerException {
    System.out.print("ID Sem No.of Subjects: ");

    int id = sc.nextInt();
    int sem = sc.nextInt();
    int n = sc.nextInt();
    sc.nextLine();

    Student s = search(id);

    if (s == null) {
        System.out.println("Student not found");
        return;
    }

    if (sem <= 0) {
        throw new GradeTrackerException("Invalid semester number");
    }

    if (n <= 0) {
        throw new GradeTrackerException("Number of subjects must be positive");
    }

    s.getSemesters().putIfAbsent(sem, new HashMap<>());

    for (int i = 0; i < n; i++) {
        System.out.print("Subject Credits Marks: ");

        String sub = sc.next();
        int cr = sc.nextInt();
        int mk = sc.nextInt();
        sc.nextLine();

        if (sub.trim().isEmpty()) {
            throw new GradeTrackerException("Subject name cannot be empty");
        }

        if (mk < 0 || mk > 100 || cr <= 0) {
            throw new GradeTrackerException("Invalid marks or credits");
        }

        s.getSemesters().get(sem).put(sub, new int[]{mk, cr});

        bm.checkAndAdd(id, sub, sem, mk);
    }

    System.out.println("Semester records updated successfully!");
} 
    static void report() {
        System.out.print("ID Sem: "); int id = sc.nextInt(); int sem = sc.nextInt();
        Student s = search(id); if (s == null || !s.getSemesters().containsKey(sem)) { System.out.println("No data"); return; }
        var subs = s.getSemesters().get(sem);
        int total = 0; for (int[] v : subs.values()) total += v[0];
        double perc = total * 100.0 / (subs.size() * 100);
        boolean pass = subs.values().stream().allMatch(v -> v[0] >= 40);
        System.out.println("Total: " + total + " | %: " + perc + " | Grade: " + GradeCalculator.grade((int)perc) + " | SGPA: " + GradeCalculator.sgpa(subs) + " | Result: " + (pass ? "PASS" : "FAIL"));
    }
    static void backlogMenu() throws GradeTrackerException {
        System.out.println("1.Re-exam 2.View Student 3.View All Active");
        int c = sc.nextInt(); sc.nextLine();
        if (c == 1) { System.out.print("ID Subject Marks: "); int id = sc.nextInt(); String sub = sc.next(); int m = sc.nextInt(); bm.reExam(id, sub, m);
            Student s = search(id); if (s != null) for (var semMap : s.getSemesters().values()) if (semMap.containsKey(sub)) semMap.get(sub)[0] = m;
        } else if (c == 2) { System.out.print("ID: "); int id = sc.nextInt(); Student s = search(id); bm.viewStudent(id, s == null ? "Unknown" : s.getName()); }
        else bm.viewAllWithActive();
    }
    static void achievementMenu() {
        System.out.println("1.Add 2.View Summary 3.View By Type");
        int c = sc.nextInt(); sc.nextLine();
        System.out.print("Student ID: ");
        int id = sc.nextInt(); sc.nextLine();
        Student s = search(id);
        if (s == null) { System.out.println("Not found"); return; }
        if (c == 1) {
            System.out.print("Type (Academic/Hackathon/Workshop/Certification/Extracurricular): ");
            String type = sc.nextLine();
            System.out.print("Title: "); String title = sc.nextLine();
            System.out.print("Organizer: "); String org = sc.nextLine();
            System.out.print("Date: "); String date = sc.nextLine();
            System.out.print("Level: "); String level = sc.nextLine();
            System.out.println("==============================");
            System.out.println(" STUDENT GRADE TRACKER SYSTEM ");
            System.out.println("==============================");
            am.add(id, new AchievementRecord(type, title, org, date, level));
        } else if (c == 2) am.viewStudent(id, s.getName());
        else { System.out.print("Enter Type: "); am.viewByType(id, sc.nextLine()); }
    }
}