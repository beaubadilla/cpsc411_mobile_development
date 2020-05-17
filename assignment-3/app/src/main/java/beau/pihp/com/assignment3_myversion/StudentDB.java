package beau.pihp.com.assignment3_myversion;

import java.util.ArrayList;

public class StudentDB {
    private static final StudentDB ourInstance = new StudentDB();

    protected ArrayList<Student> students;
    DatabaseHelper myDB;

    static public StudentDB getInstance() { return ourInstance; }
//    public StudentDB() { myDB = new DatabaseHelper(); }
    public ArrayList<Student> getStudents() { return students; }
    public void setStudents(ArrayList<Student> students) { this.students = students; }
    public void addStudent(Student student) { this.students.add(student); }
//    public void getStudentsFromSQLiteDB() {
//        Cursor results = myDB.getAllStudents();
//        StringBuffer buffer = new StringBuffer();
//
//    }

}
