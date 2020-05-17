package beau.pihp.com.part_1;

import java.util.ArrayList;

public class StudentDB {
    private static final StudentDB ourInstance = new StudentDB();

    protected ArrayList<Student> students;

    static public StudentDB getInstance() { return ourInstance; }
    public StudentDB() {}
    public ArrayList<Student> getStudents() { return students; }
    public void setStudents(ArrayList<Student> students) { this.students = students; }
    public void addStudent(Student student) { this.students.add(student); }
}
