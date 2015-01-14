package com.example.project.project;

import android.app.ListActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.app.Activity;

import com.terlici.dragndroplist.DragNDropSimpleAdapter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import android.widget.AdapterView.OnItemClickListener;


public class MainActivity extends Activity {
    /** Called when the activity is first created. */

    private ListView lstView;
    private EditText edtText;
    private SimpleAdapter simpAdapter;
    private myListAdapter listAdapter;
    static List<String> list = new ArrayList<String>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_list_view);
        setUpView();
    }

    private void setUpView(){
        lstView = (ListView)findViewById(R.id.listView_a);
        edtText = (EditText)findViewById(R.id.text1);

        list.clear();
        //populateList();
        listAdapter = new myListAdapter(this, R.layout.custom_row_view, list);
        lstView.setAdapter(listAdapter);

//        lstView.setOnItemClickListener(new OnItemClickListener() {
//            public void onItemClick(AdapterView parent, View view,
//                                    int position, long id) {
//                String  U_id = ((EditText)view.findViewById(R.id.text1)).getText().toString();
//                Log.d("hej", "hej");
//            }
//        });

        lstView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // selected item
                String selected = edtText.getText().toString();

                Toast toast=Toast.makeText(getApplicationContext(), selected, Toast.LENGTH_SHORT);
                toast.show();

            }
        });

    }


    //creating a new note button
    public void btnOnClick(View v){
        Button theButton = (Button)findViewById(R.id.add_note);
        createNewNote();
        //theButton.setBackgroundColor(000);
        Toast.makeText(this, "Clicked on Button", Toast.LENGTH_LONG).show();
    }

    //PETER Här ska du lägga till kod för att få upp datum fliken
    public void addDateButton(View v){
        Toast.makeText(this, "Clicked on Button", Toast.LENGTH_LONG).show();

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

    private void createNewNote(){
        //want to add to the top

        list.add(0,"");
        listAdapter.notifyDataSetChanged();
        //EditText theText = (EditText)findViewById(R.id.text1);
        //theText.requestFocus();
    }

    private void removeNote(){

        listAdapter.notifyDataSetChanged();
    }
}