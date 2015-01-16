package com.example.project.project;

import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.app.Activity;

import java.util.ArrayList;
import java.util.List;



public class MainActivity extends Activity implements CalenderDesign.CalenderDesignReturn{
    /**
     * Called when the activity is first created.
     */

    private ListView lstView;
    private myListAdapter listAdapter;
    static List<String> list = new ArrayList<String>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_list_view);
        setUpView();
    }

    private void setUpView() {
        lstView = (ListView) findViewById(R.id.listView_a);

        list.clear();
        //populateList();

        listAdapter = new myListAdapter(this, R.layout.custom_row_view, list);
        lstView.setAdapter(listAdapter);

    }


    //creating a new note button
    public void btnOnClick(View v) {
        Button theButton = (Button) findViewById(R.id.add_note);
        createNewNote();
    }


    //PETER Här ska du lägga till kod för att få upp datum fliken
    public void addDateButton(View v) {

        FragmentTransaction ft = getFragmentManager().beginTransaction();

        Button mStackLevel = (Button)findViewById(R.id.dateBtn);
        //making it with arguments... in this case not necessary but good to have
        CalenderDesign cldDesign = CalenderDesign.newInstance(mStackLevel);
        // Create and show the dialog.
        cldDesign.show(ft, "dialog");


    }

    //changing the date text
    public void onFinishEditDialog(String date) {
        Button btnDate = (Button)findViewById(R.id.dateBtn);
        btnDate.setText(date);
    }

    private void createNewNote() {

        //adding to first place in list
        list.add(0, "");
        final EditText edtText = new EditText(this);


        //getting dialog_create_note
        LayoutInflater li = LayoutInflater.from(this);
        View promptsView = li.inflate(R.layout.dialog_create_note, null);

        //adding it to Alertdialog
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setView(promptsView);

        // note the promptsView
        final EditText edtDialog = (EditText) promptsView.findViewById(R.id.dialogEditText);

        edtText.setText(list.get(0));
        alertDialogBuilder


                .setPositiveButton("Add Note", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                        String newName = edtDialog.getText().toString();
                        list.set(0, newName);
                        listAdapter.notifyDataSetChanged();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                    }
                })
                .show();
        listAdapter.notifyDataSetChanged();
        //EditText theText = (EditText)findViewById(R.id.text1);
        //theText.requestFocus();
    }



    private void populateList() {

        list.add("MONT Blanc");
        list.add(("MONT ParkerParkerParkerParker"));
        list.add(("ParkerParkerParkerParker Blanc"));
        list.add(("SailorSailorSailorDesign SailorSailorSailorDesign"));
        list.add(("GucciGGucciGucciGucciGucciGuccGucciGucciGucciGucciGuccucciGucciGucciGuccGucci Blanc"));
        list.add(("MONT ParkerParkerParkerParker"));
        list.add(("ParkerParkerParkerParker Blanc"));
        list.add(("SailorSailorSailorDesign SailorSailorSailorDesign"));

    }
//
//    private void populateListHash() {
//
//        HashMap<String,String> temp = new HashMap<String,String>();
//        temp.put("note","MONT Blanc");
//        temp.put("date", "Add Date");
//        hashList.add(temp);
//        HashMap<String,String> temp1 = new HashMap<String,String>();
//        temp1.put("note","Gucci");
//        temp1.put("date", "Add Date");
//        hashList.add(temp1);
//        HashMap<String,String> temp2 = new HashMap<String,String>();
//        temp2.put("note","Parker");
//        temp2.put("date", "Add Date");
//        hashList.add(temp2);
//        HashMap<String,String> temp3 = new HashMap<String,String>();
//        temp3.put("note","Sailor");
//        temp3.put("date", "Add Date");
//        hashList.add(temp3);
//        HashMap<String,String> temp4 = new HashMap<String,String>();
//        temp4.put("note","Porsche Design");
//        temp4.put("date", btnDate.getText().toString());
//        hashList.add(temp4);
//
//    }



}
