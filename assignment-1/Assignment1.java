package beau.pihp.com.cpsc411_testing_environment;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.constraint.solver.widgets.Rectangle;
import android.view.Gravity;
import android.widget.GridLayout;
import android.widget.HorizontalScrollView;
import android.widget.TextView;


public class Assignment1 extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final int NUM_ROWS = 11;
        final int NUM_COL = 4;

        HorizontalScrollView hsv = new HorizontalScrollView(this);

        GridLayout gl = new GridLayout(this);
        gl.setRowCount(NUM_ROWS);
        gl.setColumnCount(NUM_COL);
        gl.setBackgroundColor(Color.WHITE); // Testing purposes only
        gl.setOrientation(GridLayout.VERTICAL);

        initializeTable(gl);

        hsv.addView(gl);
        setContentView(R.layout.assignment_1);
//        setContentView(hsv);
    }
    private void initializeTable(GridLayout gl) {
        createCell(gl, "Rules void hello1(int hour)", 0,0,1,4,1, 1);
        createCell(gl, "properties", 1,0, 2, 1, 1, 1 );
        createCell(gl, "name", 1, 1, 1, 2, 1, 1);
        createCell(gl, "Day Hour Classification", 1, 3, 1, 1,1,1);
        createCell(gl, "category", 2, 1, 1, 2,1,1);
        createCell(gl, "Day and Time", 2, 3, 1, 1, 1, 1);
        createCell(gl, "Rule", 3, 0, 1, 1, 1, 1);
        createCell(gl, "C1", 3, 1,1,2,1,1);
        createCell(gl, "A1", 3, 3, 1, 1, 1,1);
        createCell(gl, "", 4, 0, 1,1,1,1);
        createCell(gl, "min <= hour && hour <= max", 4, 1,1,2,1,1);
        createCell(gl, "System.out.println(greeting + \", World!", 4, 3, 1,1,1,1);
        createCell(gl, "", 5, 0, 1,1,1,1);   // <blank>
        createCell(gl, "int min", 5, 1, 1,1,1,1);
        createCell(gl, "int max", 5, 2, 1,1,1,1);
        createCell(gl, "String greeting", 5, 3, 1,1,1,1);
        createCell(gl, "Rule", 6, 0, 1,1,1,1);
        createCell(gl, "From", 6, 1, 1, 1, 1, 1);
        createCell(gl, "To", 6, 2, 1, 1, 1,1);
        createCell(gl, "Greeting", 6, 3, 1, 1, 1, 1);
        createCell(gl, "R10", 7, 0, 1, 1, 1, 1);
        createCell(gl, "0", 7, 1, 1, 1, 1,1);
        createCell(gl, "11", 7, 2, 1, 1, 1, 1);
        createCell(gl, "Good Morning", 7, 3, 1, 1, 1, 1);
        createCell(gl, "R20", 8, 0, 1, 1, 1,1);
        createCell(gl, "0", 8, 1, 1, 1, 1,1);
        createCell(gl, "17", 8, 2, 1, 1, 1,1);
        createCell(gl, "Good Afternoon", 8, 3, 1, 1, 1, 1);
        createCell(gl, "R30", 9, 0, 1, 1, 1, 1);
        createCell(gl, "18", 9, 1, 1, 1,1, 1);
        createCell(gl, "21", 9, 2, 1, 1, 1,1);
        createCell(gl, "Good Evening", 9, 3, 1, 1, 1,1);
        createCell(gl, "R40", 10, 0, 1,1,1,1);
        createCell(gl, "22", 10, 1, 1,1,1,1);
        createCell(gl, "23", 10, 2, 1, 1,1,1);
        createCell(gl, "Good Night", 10, 3,1,1,1,1);
    }
    private void styleCell(TextView tv, int row, int col) {
        if (row == 0) {
            tv.setBackgroundColor(Color.BLACK);
            tv.setTextColor(Color.WHITE);
        }
        else if (row == 3 || row == 4 || row == 5) {
            tv.setBackgroundColor(Color.rgb(148, 251, 255)); // teal
            if (row == 3) {
                tv.setTypeface(null, Typeface.BOLD);
            }
        }

        if ((row == 1 || row == 2) && col == 3) {
            tv.setGravity(Gravity.CENTER | Gravity.START);
        }

        if (row == 6 || row == 7 || row == 8 || row == 9 || row == 10) {
            if (row == 6) {
                tv.setTypeface(null, Typeface.BOLD);
            }
            if (col == 1 || col == 2) {
                tv.setBackgroundColor(Color.YELLOW);
                if (row == 7 || row == 8 || row == 9 || row == 10) {
                    tv.setGravity(Gravity.CENTER| Gravity.END);
                }
            }
            else if (col == 3) {
                tv.setBackgroundColor(Color.rgb(255, 180, 148)); // orange
                tv.setGravity(Gravity.CENTER | Gravity.START);
            }
            else if (col == 0) {
                tv.setGravity(Gravity.CENTER | Gravity.START);
            }
        }
    }

    private void createCell(GridLayout gl, String text,
                                 int row, int col,
                                 int rowSize, int colSize,
                                 float rowWeight, float colWeight) {
        GridLayout.Spec rowSpec = GridLayout.spec(row, rowSize, rowWeight);
        GridLayout.Spec colSpec = GridLayout.spec(col, colSize, colWeight);
        GridLayout.LayoutParams params = new GridLayout.LayoutParams(rowSpec, colSpec);

        TextView tv = new TextView(this);
        tv.setText(text);
        tv.setTextSize(16);
        tv.setLayoutParams(params);
        tv.setGravity(Gravity.CENTER);
        styleCell(tv, row, col);

        gl.addView(tv);

    }


}
