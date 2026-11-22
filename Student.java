import java.util.*;
public class Student {
    private int id, year, semester;
    private String name, dept;
    private HashMap<Integer, HashMap<String, int[]>> semesters = new HashMap<>();
    public Student(int id, String name, String dept, int year, int sem) {

    if (id <= 0) {
        throw new IllegalArgumentException("Student ID must be positive");
    }

    if (name == null || name.trim().isEmpty()) {
        throw new IllegalArgumentException("Student name cannot be empty");
    }

    if (dept == null || dept.trim().isEmpty()) {
        throw new IllegalArgumentException("Department cannot be empty");
    }

    if (year <= 0) {
        throw new IllegalArgumentException("Year must be positive");
    }

    if (sem <= 0) {
        throw new IllegalArgumentException("Semester must be positive");
    }

    this.id = id;
    this.name = name.trim();
    this.dept = dept.trim();
    this.year = year;
    this.semester = sem;
}
    public int getId() { return id; }
    public String getName() { return name; }
    public String getDept() { return dept; }
    public int getYear() { return year; }
    public int getSemester() { return semester; }
    public void setDept(String d) { dept = d; }
    public void setName(String n) { name = n; }
    public HashMap<Integer, HashMap<String, int[]>> getSemesters() { return semesters; }
}