package beau.pihp.com.assignment3_myversion;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class AddStudentMenu extends AppCompatActivity {
    protected LayoutInflater layoutInflater;
    protected LinearLayout root;
    protected EditText mEditText;
    protected final String TAG = "AddStudentMenu Screen";
    DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Test", "onCreate() called");
        setContentView(R.layout.add_student);

        myDB = new DatabaseHelper(this);

        layoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        root = findViewById(R.id.add_student_layout);

        Button add_course_btn = findViewById(R.id.add_course);
        add_course_btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        View row_view = layoutInflater.inflate(R.layout.add_course, root, false);
                        root.addView(row_view);
                        root.invalidate();
                    }
                }
        );
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_student, menu);
        Log.d(TAG, "Added Done");
        return super.onCreateOptionsMenu(menu);
    }
    // Provide functionality to options
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Log.d(TAG, "Entered onOptionsItemSelected");
        if (item.getItemId() == R.id.action_done) {
            Log.d(TAG, "Passed if-statement");
            mEditText = (EditText) findViewById(R.id.new_student_first_name);
            String newStudentFirstName = mEditText.getText().toString();
            mEditText = (EditText) findViewById(R.id.new_student_last_name);
            String newStudentLastNAme = mEditText.getText().toString();
            mEditText = (EditText) findViewById(R.id.new_student_cwid);
            String newStudentCWIDString = mEditText.getText().toString();
            int newStudentCWIDInt = Integer.parseInt(newStudentCWIDString);
            mEditText = (EditText) findViewById(R.id.new_student_course_id);
            String newStudentCourseID = mEditText.getText().toString();
            mEditText = (EditText) findViewById(R.id.new_student_grade);
            String newStudentGrade = mEditText.getText().toString();

            Student newStudent = new Student(newStudentFirstName, newStudentLastNAme, newStudentCWIDInt);
            myDB.insertStudent(newStudentFirstName, newStudentLastNAme, newStudentCWIDInt);
            ArrayList<CourseEnrollment> newCourses = new ArrayList<CourseEnrollment>();
            newCourses.add(new CourseEnrollment(newStudentCourseID, newStudentGrade));
            newStudent.setCourses(newCourses);

            StudentDB.getInstance().addStudent(newStudent);
            ArrayList<Student> studentList = StudentDB.getInstance().getStudents();
            Log.d(TAG, "Student size:" + studentList.size());

            setResult(Activity.RESULT_OK);

            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
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
