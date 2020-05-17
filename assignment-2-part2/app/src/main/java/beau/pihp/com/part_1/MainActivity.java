package beau.pihp.com.part_1;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    protected LinearLayout root;
    protected LayoutInflater layoutInflater;
    protected final String TAG = "MainActivity Screen";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate() called");
        super.onCreate(savedInstanceState);
        createStudents();
        setContentView(R.layout.student_summary);

        root = findViewById(R.id.student_summary);
        layoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE); // = LayoutInflater.from(this); is equivalent

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

//        Button add_student_btn = new Button(this);
//        add_student_btn.setText("Add student");
//        add_student_btn.setTextSize(24);
//        add_student_btn.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Intent intent = new Intent(view.getContext(), AddStudentMenu.class);
////                        intent.putExtra("Button Tag", ((Integer)view.getTag()).intValue()); // Put extra info about the intent. In this case, it adds the tag (i.e. reference) to the button
//                        view.getContext().startActivity(intent);
//                    }
//                }
//        );
//        root.addView(add_student_btn);
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

    // Create an options menu
    // Note: simply inflating a layout and invoking the superclass
    //       the argument 'menu' is provided by superclass?
    //       the layout MUST be under a 'menu' android resource directory (i.e. r-click 'res direction, new, android resource directory, menu)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_summary_screen, menu);
        return super.onCreateOptionsMenu(menu);
    }
    // Provide functionality to options
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_add) {
            Log.d(TAG, "Pressed Add");
            Intent intent = new Intent(MainActivity.this, AddStudentMenu.class);
//                        intent.putExtra("Button Tag", ((Integer)view.getTag()).intValue()); // Put extra info about the intent. In this case, it adds the tag (i.e. reference) to the button
            startActivityForResult(intent, 1); // 1 is arbitrary
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult");
        if ((requestCode == 1) && (resultCode == Activity.RESULT_OK)) {
            ArrayList<Student> studentList = StudentDB.getInstance().getStudents();
            root = findViewById(R.id.student_summary);
            if (root.getChildCount() < studentList.size()) {
                Log.d(TAG, "root.getChildCount() = " + root.getChildCount());
                Log.d(TAG, "studentList.size() = " + studentList.size());
                for (int i = root.getChildCount(); i < studentList.size(); i++) {
                    Log.d(TAG, "Inside loop, i = " + i);
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
//                    course2View.setText(s.getCourseByIndex(1).getCourseID() + ": " + s.getCourseByIndex(1).getGrade());

                    root.addView(row_view);
                }
                root.invalidate();
            }
//            View row_view = layoutInflater.inflate(R.layout.student_row, root, false);
//            TextView firstNameView = (TextView) row_view.findViewById(R.id.first_name);
//            TextView lastNameView = (TextView) row_view.findViewById(R.id.last_name);
//            TextView course1View = (TextView) row_view.findViewById(R.id.course1);
//            TextView course2View = (TextView) row_view.findViewById(R.id.course2);
//            firstNameView.setText("TestFN");
//            lastNameView.setText("TestLN");
//            course1View.setText("TestCourse");
//            course2View.setText("TestCourse2");


//            root.addView(row_view);
//            root.invalidate();
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
        root = findViewById(R.id.student_summary);
        Log.d(TAG, "Number of children:" + root.getChildCount());
//        ArrayList<Student> studentList = StudentDB.getInstance().getStudents();
//        if (root.getChildCount() < studentList.size()) {
//            Log.d(TAG, "Inside loop");
//            for (int i = root.getChildCount() - 1; i < studentList.size(); i++) {
//                Student s = studentList.get(i); // ArrayList does not use bracket notation
//
//                // 2nd parameter of .inflate() tells the View object (that we're inflating) who its parent is
//                //  Important because without it, match_parent would not work
//                View row_view = layoutInflater.inflate(R.layout.student_row, root, false);
//                TextView firstNameView = (TextView) row_view.findViewById(R.id.first_name);
//                TextView lastNameView = (TextView) row_view.findViewById(R.id.last_name);
//                TextView course1View = (TextView) row_view.findViewById(R.id.course1);
//                TextView course2View = (TextView) row_view.findViewById(R.id.course2);
//                firstNameView.setText(s.getFirstName());
//                lastNameView.setText(s.getLastName());
//                course1View.setText(s.getCourseByIndex(0).getCourseID() + ": " +  s.getCourseByIndex(0).getGrade());
//                course2View.setText(s.getCourseByIndex(1).getCourseID() + ": " + s.getCourseByIndex(1).getGrade());
//
//                root.addView(row_view);
//            }
//        }

//        View row_view = layoutInflater.inflate(R.layout.student_row, root, false);
//        TextView firstNameView = (TextView) row_view.findViewById(R.id.first_name);
//        TextView lastNameView = (TextView) row_view.findViewById(R.id.last_name);
//        TextView course1View = (TextView) row_view.findViewById(R.id.course1);
//        TextView course2View = (TextView) row_view.findViewById(R.id.course2);
//        firstNameView.setText("TestFN");
//        lastNameView.setText("TestLN");
//        course1View.setText("TestCourse");
//        course2View.setText("TestCourse2");
//
//        root = findViewById(R.id.student_summary);
//        root.addView(row_view);
//        root.invalidate();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
//        root = findViewById(R.id.add_student_layout);
//        root.invalidate();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }
}