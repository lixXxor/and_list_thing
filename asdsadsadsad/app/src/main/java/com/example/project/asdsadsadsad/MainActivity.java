package com.example.project.asdsadsadsad;

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


public class MainActivity extends Activity implements ViewSwitcher.ViewFactory {

    private Button btnPicDateTime;
    private TextSwitcher DaySwitcher, MonthSwitcher, YearSwitcher;
    private ImageView dayP, dayM, monthP, monthM, yearP, yearM;

    private int prevMonth;
    private Calendar cal;
    private static int year = 1819710;
    private static int day = 1819710;
    private static int month = 1819710;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_date_picker);

        btnPicDateTime = (Button) findViewById(R.id.btnPicDateTime);
        btnPicDateTime.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                int month = cal.get(Calendar.MONTH) + 1;
                Toast.makeText(
                        getApplicationContext(),
                        cal.get(Calendar.YEAR) + "/" + cal.get(Calendar.MONTH)
                                + "/" + cal.get(Calendar.DAY_OF_MONTH),
                        Toast.LENGTH_LONG).show();                                                     finish();
            }
        });

        DaySwitcher = (TextSwitcher) findViewById(R.id.DaySwitcher);
        DaySwitcher.setFactory(this);

        MonthSwitcher = (TextSwitcher) findViewById(R.id.MonthSwitcher);
        MonthSwitcher.setFactory(this);

        YearSwitcher = (TextSwitcher) findViewById(R.id.YearSwitcher);
        YearSwitcher.setFactory(this);

        setAnimation(DaySwitcher);
        setAnimation(MonthSwitcher);
        setAnimation(YearSwitcher);

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

        dayP = (ImageView) findViewById(R.id.dayP);
        dayM = (ImageView) findViewById(R.id.dayM);

        monthP = (ImageView) findViewById(R.id.monthP);
        monthM = (ImageView) findViewById(R.id.monthM);

        yearP = (ImageView) findViewById(R.id.yearP);
        yearM = (ImageView) findViewById(R.id.yearM);

        getClickEvents(dayP, Calendar.DAY_OF_YEAR, 1);
        getClickEvents(dayM, Calendar.DAY_OF_YEAR, -1);
        getClickEvents(monthP, Calendar.MONTH, 1);
        getClickEvents(monthM, Calendar.MONTH, -1);
        getClickEvents(yearP, Calendar.YEAR, 1);
        getClickEvents(yearM, Calendar.YEAR, -1);

        update();
    }

    private void getClickEvents(ImageView imageView, final int field,
                                final int value) {

        imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                cal.roll(field, value);
                update();
            }
        });
    }

    private void setAnimation(TextSwitcher switcher) {
        Animation in = AnimationUtils.loadAnimation(getApplicationContext(),
                android.R.anim.fade_in);
        Animation out = AnimationUtils.loadAnimation(getApplicationContext(),
                android.R.anim.fade_out);

        switcher.setInAnimation(out);
        switcher.setOutAnimation(in);

    }

    public void update() {

        DaySwitcher.setText(String.valueOf(cal.get(Calendar.DAY_OF_MONTH)));
        MonthSwitcher.setText(sort(cal.get(Calendar.MONTH)));
        YearSwitcher.setText(String.valueOf(cal.get(Calendar.YEAR)));

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
            update();
        }

        return retrn;
    }

    public View makeView() {
        TextView t = new TextView(this);
        t.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL);
        t.setTextAppearance(getApplicationContext(), R.style.AppTheme);

        return t;
    }

}