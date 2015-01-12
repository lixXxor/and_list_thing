package com.example.project.project;

import android.app.ListActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends ListActivity {
    /** Called when the activity is first created. */



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_list_view);

        SimpleAdapter adapter = new SimpleAdapter(
                this,
                list,
                R.layout.custom_row_view,
                new String[] {"note", "button"},
                new int[] {R.id.text1, R.id.text4}
        );
        //how should I implement this to public void btnOnClick?
        createNewNote();
        populateList();
        setListAdapter(adapter);

    }

    //creating a new note button
    public void btnOnClick(View v){
        Button theButton = (Button)findViewById(R.id.add_note);
        //theButton.setBackgroundColor(000);
        Toast.makeText(this, "Clicked on Button", Toast.LENGTH_LONG).show();
    }

    //PETER Här ska du lägga till kod för att få upp datum fliken
    public void addDateButton(View v){
        
        Toast.makeText(this, "Clicked on Button", Toast.LENGTH_LONG).show();
    }

    static final ArrayList<HashMap<String,String>> list =
            new ArrayList<HashMap<String,String>>();


    private void populateList() {
        HashMap<String,String> temp = new HashMap<String,String>();
        temp.put("note","MONT Blanc");
        temp.put("button", "ADD DATE");
        list.add(temp);
        HashMap<String,String> temp1 = new HashMap<String,String>();
        temp1.put("note","Gucci Gucci Gucci GucciGucciGucciGucciGucciGucciGucciGucci Gucci");
        temp1.put("button", "ADD DATE");
        list.add(temp1);
        HashMap<String,String> temp2 = new HashMap<String,String>();
        temp2.put("note","Parker");
        temp2.put("button", "ADD DATE");
        list.add(temp2);
        HashMap<String,String> temp3 = new HashMap<String,String>();
        temp3.put("note","Sailor");
        temp3.put("button", "ADD DATE");
        list.add(temp3);
        HashMap<String,String> temp4 = new HashMap<String,String>();
        temp4.put("note","Sailor Design");
        temp4.put("button", "ADD DATE");
        list.add(temp4);
        HashMap<String,String> temp5 = new HashMap<String,String>();
        temp5.put("note","Porsche SailorSailorSailorDesign");
        temp5.put("button", "ADD DATE");
        list.add(temp5);
        HashMap<String,String> temp6 = new HashMap<String,String>();
        temp6.put("note","SailorSailorSailorSailor Design");
        temp6.put("button", "ADD DATE");
        list.add(temp6);
        HashMap<String,String> temp7 = new HashMap<String,String>();
        temp7.put("note","Porsche Design");
        temp7.put("button", "ADD DATE");
        list.add(temp7);
        HashMap<String,String> temp8 = new HashMap<String,String>();
        temp8.put("note","Porsche SailorSailorSailorDesign");
        temp8.put("button", "ADD DATE");
        list.add(temp4);
        HashMap<String,String> temp9 = new HashMap<String,String>();
        temp9.put("note","Porsche Gucci GucciGucciGucciGucciGucc Gucci GucciGucciGucciGucciGuccGucci GucciGucciGucciGucciGuccDesign");
        temp9.put("button", "ADD DATE");
        list.add(temp9);

    }

    private void createNewNote(){
        HashMap<String,String> temp = new HashMap<String,String>();
        temp.put("note","");
        temp.put("button", "ADD DATE");
        list.add(temp);
    }
}