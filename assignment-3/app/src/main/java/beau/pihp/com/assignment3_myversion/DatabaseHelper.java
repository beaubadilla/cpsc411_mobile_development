package beau.pihp.com.assignment3_myversion;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String TAG = "DatabaseHelper";

    public static final String DATABASE_NAME = "Students.db";

    public static final String TABLE_NAME_1 = "Student";
    public static final String STUDENT_COL_1 = "FirstName";
    public static final String STUDENT_COL_2 = "LastName";
    public static final String STUDENT_COL_3 = "CWID";


    public static final String TABLE_NAME_2 = "CourseEnrollment";
    public static final String CE_COL_1 = "CourseID";
    public static final String CE_COL_2 = "Grade";

    StudentDB sDB;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "onCreate()");
        db.execSQL("CREATE TABLE " + TABLE_NAME_1 + " (FirstName TEXT, LastName TEXT, CWID INTEGER)");
        db.execSQL("CREATE TABLE " + TABLE_NAME_2 + " (CourseID TEXT, Grade TEXT)");
//        this.insertStudent("Jake", "Day", 0);
//        this.insertStudent("Jennie", "Day", 1);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Student");
        db.execSQL("DROP TABLE IF EXISTS CourseEnrollment");
        onCreate(db);
    }

    public boolean insertStudent(String fName, String lName, int cwid) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(STUDENT_COL_1, fName);
        cv.put(STUDENT_COL_2, lName);
        cv.put(STUDENT_COL_3, cwid);
        long result = db.insert(TABLE_NAME_1, null, cv);
        if (result == -1) { return false; }
        else { return true; }
    }

    public boolean insertCourse(String courseid, String grade) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(CE_COL_1, courseid);
        cv.put(CE_COL_2, grade);
        long result = db.insert(TABLE_NAME_2, null, cv);
        if (result == -1) { return false; }
        else { return true; }
    }

    public void putPersistentIntoLocal() {
        sDB = StudentDB.getInstance();
        Cursor results = this.getAllStudents();
//        if (results.getCount() == 0) { return; }
        while (results != null && results.moveToNext()) {
            Log.d(TAG, results.getString(0));
            Log.d(TAG, results.getString(1));
            Log.d(TAG, results.getString(2));

            Student s = new Student(results.getString(0), results.getString(1), results.getInt(2));
            sDB.addStudent(s);
        }
    }

    public Cursor getAllStudents() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor results = db.rawQuery("SELECT * from " + TABLE_NAME_1, null);
        return results;
    }

    public Cursor getAllCourseEnrollments() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor results = db.rawQuery("SELECT * from " + TABLE_NAME_2, null);
        return results;
    }
}
