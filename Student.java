import java.util.*;
public class Student {
    private int id, year, semester;
    private String name, dept;
    private HashMap<Integer, HashMap<String, int[]>> semesters = new HashMap<>();
    public Student(int id, String name, String dept, int year, int sem) {
        this.id = id; this.name = name; this.dept = dept;
        this.year = year; this.semester = sem;
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