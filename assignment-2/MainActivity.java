package beau.pihp.com.part_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    protected LinearLayout root;
    protected LayoutInflater layoutInflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createStudents();
        setContentView(R.layout.student_summary);

        root = findViewById(R.id.student_summary);
        layoutInflater= (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE); // = LayoutInflater.from(this); is equivalent

        ArrayList<Student> studentList = StudentDB.getInstance().getStudents();

        for(int i = 0; i < studentList.size(); i++) {
            Student s = studentList.get(i); // ArrayList does not use bracket notation

            // 2nd parameter of .inflate() tells the View object (that we're inflating) who its parent is
            //  Important because without it, match_parent would not work
            View row_view = layoutInflater.inflate(R.layout.student_row, root, false);
            TextView firstNameView = (TextView) row_view.findViewById(R.id.first_name);
            TextView lastNameView = (TextView) row_view.findViewById(R.id.last_name);
            TextView course1View = (TextView) row_view.findViewById(R.id.course1);
            TextView course2View = (TextView) row_view.findViewById(R.id.course2);
            firstNameView.setText(s.getFirstName());
            lastNameView.setText(s.getLastName());
            course1View.setText(s.getCourseByIndex(0).getCourseID() + ": " +  s.getCourseByIndex(0).getGrade());
            course2View.setText(s.getCourseByIndex(1).getCourseID() + ": " + s.getCourseByIndex(1).getGrade());

            root.addView(row_view);
        }
    }

    protected void createStudents() {
        Student student;
        ArrayList<CourseEnrollment> courses;
        ArrayList<Student> listOfStudents = new ArrayList<Student>();

        // Student #1
        student = new Student("Jake", "Day", 0);
        courses = new ArrayList<CourseEnrollment>();
        courses.add(new CourseEnrollment("CPSC 411", "F"));
        courses.add(new CourseEnrollment("CPSC 440", "D"));
        student.setCourses(courses);
        listOfStudents.add(student);

        // Student #2
        student = new Student("Jennie", "Day", 1);
        courses = new ArrayList<CourseEnrollment>();
        courses.add(new CourseEnrollment("CPSC 449", "A"));
        courses.add(new CourseEnrollment("CPSC 481", "A"));
        student.setCourses(courses);
        listOfStudents.add(student);

        // Add to StudentDB
        StudentDB.getInstance().setStudents(listOfStudents);
    }
}
