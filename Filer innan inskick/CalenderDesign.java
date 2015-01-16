package com.example.project.project;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import java.util.Calendar;

public class CalenderDesign extends DialogFragment implements ViewSwitcher.ViewFactory{

    String dateString;

    private ImageView plus_a_year, minus_a_year, plus_a_month, minus_a_month, plus_a_day, minus_a_day;
    private TextSwitcher yearSwitcher, monthSwitcher, daySwitcher;
    private Button fixBtn;

    private int prevMonth;
    private Calendar cal;
    private static int year = 1819710;
    private static int day = 1819710;
    private static int month = 1819710;

    public CalenderDesign() {
        // Empty constructor required for DialogFragment
    }

    public static CalenderDesign newInstance(Button btnDate){
        CalenderDesign fragment = new CalenderDesign();

        //Make button to argument
        Bundle args = new Bundle();
        args.putString("date", btnDate.getText().toString());
        fragment.setArguments(args);

        return fragment;
    }

    public interface CalenderDesignReturn {
        void onFinishEditDialog(String inputText);
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.custom_date_picker, null);
        dateString = getArguments().getString("date");
        builder.setView(view)

                .setPositiveButton("ADD DATE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        updateSwitcher();
                        int month = cal.get(Calendar.MONTH) + 1;
                        Toast.makeText(
                                getActivity(),
                                cal.get(Calendar.YEAR) + "-" + month
                                        + "-" + cal.get(Calendar.DAY_OF_MONTH),
                                Toast.LENGTH_LONG).show();

                        int Year = cal.get(Calendar.YEAR);
                        int Month = cal.get(Calendar.MONTH) + 1;
                        int Day = cal.get(Calendar.DAY_OF_MONTH);

                        int Date = Year + Month + Day;

                        String time = Integer.toString(Year) + "-" + Integer.toString(Month) + "-" + Integer.toString(Day);

                        CalenderDesignReturn activity = (CalenderDesignReturn) getActivity();
                        activity.onFinishEditDialog(time);


                                //fixBtn.setText(Year + "-" + Month + "-" + Day);

                    }
                })

                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });



        yearSwitcher = (TextSwitcher) view.findViewById(R.id.YearSwitcher);
        //ViewSwitcher
        yearSwitcher.setFactory(this);

        monthSwitcher = (TextSwitcher) view.findViewById(R.id.MonthSwitcher);
        //ViewSwitcher
        monthSwitcher.setFactory(this);

        daySwitcher = (TextSwitcher) view.findViewById(R.id.DaySwitcher);
        //ViewSwitcher
        daySwitcher.setFactory(this);

        //getting the images
        plus_a_year = (ImageView) view.findViewById(R.id.plus_a_year);
        minus_a_year = (ImageView) view.findViewById(R.id.minus_a_year);

        plus_a_month = (ImageView) view.findViewById(R.id.plus_a_month);
        minus_a_month = (ImageView) view.findViewById(R.id.minus_a_month);

        plus_a_day = (ImageView) view.findViewById(R.id.plus_a_day);
        minus_a_day = (ImageView) view.findViewById(R.id.minus_a_day);



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


        return builder.create();

    }




    //this is for ViewSwitcher
    public View makeView() {
        TextView t = new TextView(getActivity());
        t.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL);
        t.setTextAppearance(getActivity(), R.style.AppTheme);

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