package beau.pihp.com.part_1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import beau.pihp.com.part_1.StudentDB;

//public class StudentSummaryAdapter extends BaseAdapter {
//    @Override
//    public int getcount() {
//        return 0;
//    }
//    @Override
//    public Object getItem(int i) {
//        return StudentDb.getInstance().getStudents.get(i);
//    }
//}
public class StudentSummaryAdapter extends BaseAdapter {
    @Override
    public int getCount() {
        return 0;
    }
    @Override
    public Object getItem(int i) {
        return StudentDB.getInstance().getStudents().get(i);
    }
    @Override
    public long getItemId(int i) { // must return long and accept one int argument for superclass definition
        return 0;
    }

    public View getView(int i, View v, ViewGroup vg) {
        View row_view;
        LayoutInflater layoutInflater = LayoutInflater.from(vg.getContext());

        if (v == null) {
            row_view = layoutInflater.inflate(R.layout.student_row, vg, false);
        } else row_view = v;

        Student s = StudentDB.getInstance().getStudents().get(i);
        TextView firstNameView = (TextView) row_view.findViewById(R.id.first_name);
        TextView lastNameView = (TextView) row_view.findViewById(R.id.last_name);
        firstNameView.setText(s.getFirstName());
        lastNameView.setText(s.getLastName());

        return row_view;


    }

}
