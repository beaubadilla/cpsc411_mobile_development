package beau.pihp.com.part_1;

import java.util.ArrayList;

public class Student {
    protected String firstName;
    protected String lastName;
    protected int CWID;
    protected ArrayList<CourseEnrollment> courses;


    public Student(String firstName, String lastName, int CWID) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.CWID = CWID;
    }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public int getCWID() { return CWID; }
    public void setCWID(int CWID) { this.CWID = CWID; }
    public ArrayList<CourseEnrollment> getCourses() { return courses; }
    public void setCourses(ArrayList<CourseEnrollment> courses) { this.courses = courses; }
    public CourseEnrollment getCourseByIndex(int index) { return courses.get(index);}
}

