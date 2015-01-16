package com.example.project.project;

/**
 * Created by gzaphf on 16/01/15.
 */

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import java.util.Calendar;


public class CalenderDialog extends Activity implements ViewSwitcher.ViewFactory {


    private ImageView plus_a_year, minus_a_year, plus_a_month, minus_a_month, plus_a_day, minus_a_day;
    private TextSwitcher yearSwitcher, monthSwitcher, daySwitcher;
    private Button btnAddDate;

    private int prevMonth;
    private Calendar cal;
    private static int year = 1819710;
    private static int month = 1819710;
    private static int day = 1819710;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_date_picker);

        //btnAddDate = (Button) findViewById(R.id.btnAddDate);
        btnAddDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // so it starts with 1 instead of 0
                int month = cal.get(Calendar.MONTH) + 1;
                Toast.makeText(
                        getApplicationContext(),
                        cal.get(Calendar.YEAR) + "-" + month
                                + "-" + cal.get(Calendar.DAY_OF_MONTH),
                        Toast.LENGTH_LONG).show();                                                     finish();
            }
        });

        //creating the switcher for each state

        yearSwitcher = (TextSwitcher) findViewById(R.id.YearSwitcher);
        //ViewSwitcher
        yearSwitcher.setFactory(this);

        monthSwitcher = (TextSwitcher) findViewById(R.id.MonthSwitcher);
        //ViewSwitcher
        monthSwitcher.setFactory(this);

        daySwitcher = (TextSwitcher) findViewById(R.id.DaySwitcher);
        //ViewSwitcher
        daySwitcher.setFactory(this);

        setAnimation(yearSwitcher);
        setAnimation(monthSwitcher);
        setAnimation(daySwitcher);


        //getting the images
        plus_a_year = (ImageView) findViewById(R.id.plus_a_year);
        minus_a_year = (ImageView) findViewById(R.id.minus_a_year);

        plus_a_month = (ImageView) findViewById(R.id.plus_a_month);
        minus_a_month = (ImageView) findViewById(R.id.minus_a_month);

        plus_a_day = (ImageView) findViewById(R.id.plus_a_day);
        minus_a_day = (ImageView) findViewById(R.id.minus_a_day);



        cal = Calendar.getInstance();

        if (year != 1819710) {
            cal.set(Calendar.YEAR, year);
        }
        if (month != 1819710) {
            cal.set(Calendar.MONTH, month);
        }
        if (day != 1819710) {
            cal.set(Calendar.DAY_OF_MONTH, day);
        }

        getClickEvents(plus_a_year, Calendar.YEAR, 1);
        getClickEvents(minus_a_year, Calendar.YEAR, -1);
        getClickEvents(plus_a_month, Calendar.MONTH, 1);
        getClickEvents(minus_a_month, Calendar.MONTH, -1);
        getClickEvents(plus_a_day, Calendar.DAY_OF_YEAR, 1);
        getClickEvents(minus_a_day, Calendar.DAY_OF_YEAR, -1);


        updateSwitcher();
    }



    private void setAnimation(TextSwitcher switcher) {
        Animation in = AnimationUtils.loadAnimation(getApplicationContext(),
                android.R.anim.fade_in);
        Animation out = AnimationUtils.loadAnimation(getApplicationContext(),
                android.R.anim.fade_out);

        switcher.setInAnimation(out);
        switcher.setOutAnimation(in);

    }

    //this is for ViewSwitcher
    public View makeView() {
        TextView t = new TextView(this);
        t.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL);
        t.setTextAppearance(getApplicationContext(), R.style.AppTheme);

        return t;
    }

    private void getClickEvents(ImageView imageView, final int field,
                                final int value) {

        imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                cal.roll(field, value);
                updateSwitcher();
            }
        });
    }

    public void updateSwitcher() {
        yearSwitcher.setText(String.valueOf(cal.get(Calendar.YEAR)));
        monthSwitcher.setText(sort(cal.get(Calendar.MONTH)));
        daySwitcher.setText(String.valueOf(cal.get(Calendar.DAY_OF_MONTH)));

    }

    private CharSequence sort(int i) {

        String retrn = null;

        if (i == 0) {
            retrn = "Jan";
            prevMonth = i;
        }
        if (i == 1) {
            retrn = "Feb";
        }
        if (i == 2) {
            retrn = "Mar";
        }
        if (i == 3) {
            retrn = "Apr";
        }
        if (i == 4) {
            retrn = "May";
        }
        if (i == 5) {
            retrn = "Jun";
        }
        if (i == 6) {
            retrn = "Jul";
        }
        if (i == 7) {
            retrn = "Aug";
        }
        if (i == 8) {
            retrn = "Sep";
        }
        if (i == 9) {
            retrn = "Oct";
        }
        if (i == 10) {
            retrn = "Nov";
        }
        if (i == 11) {
            retrn = "Dec";
            prevMonth = i;
        }

        if (i == 12) {
            if (prevMonth == 0) {
                cal.roll(Calendar.MONTH, -1);
            } else {
                cal.roll(Calendar.MONTH, 1);
            }
            updateSwitcher();
        }

        return retrn;
    }



}