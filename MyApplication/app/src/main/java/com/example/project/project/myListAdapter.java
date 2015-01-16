package com.example.project.project;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.database.DataSetObserver;
import android.preference.DialogPreference;
import android.support.v4.widget.ViewDragHelper;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

/**
 * Created by gzaphf on 14/01/15.
 */
public class myListAdapter extends ArrayAdapter<String>

{

    private List<String> myItems;
    private int myResource;



    public myListAdapter(Context context, int resource) {
        super(context, resource);
        myResource = resource;
    }

    public myListAdapter(Context context, int resource, List<String> items){
        super(context, resource, items);
        myItems = items;
        myResource = resource;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent){

        View v = convertView;

        if(v == null){
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(myResource, null);

        }

        String p = myItems.get(position);
        Button btnDeleteNote = (Button)v.findViewById(R.id.deleteBtn);
        Button btnAddDate = (Button)v.findViewById(R.id.dateBtn);

        v.setId(position);

        if(p != null) {

            final TextView edt = (TextView) v.findViewById(R.id.text1);


            if(myItems.size() > 1){
                edt.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        Log.d("hej", "hej");
                        return false;
                    }
                });
            }


            edt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final EditText edtText = new EditText(getContext());
                    edtText.setText(myItems.get(position));
                    Log.d("SDADASDAS", "" + position);

                    //getting dialog_create_note
                    LayoutInflater li = LayoutInflater.from(getContext());
                    View promptsView = li.inflate(R.layout.dialog_create_note, null);

                    //adding it to Alertdialog
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
                    alertDialogBuilder.setView(promptsView);

                    // note the promptsView
                    final EditText edtDialog = (EditText)promptsView.findViewById(R.id.dialogEditText);
                    edtDialog.setText(edtText.getText().toString());


                    alertDialogBuilder
                            .setPositiveButton("Add Note", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    String newName = edtDialog.getText().toString();
                                    myItems.set(position, newName);
                                    notifyDataSetChanged();
                                }
                            })
                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                }
                            })
                            .show();
                }
            });

            btnDeleteNote.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("size myItems: ", " is " + myItems.size());
                    Log.d("position is: ", "" + position);
                    Log.d("text is: ", "" + edt.getText());
                    remove(myItems.get(position));

                    Log.d("size myItems: ", " is " + myItems.size());
                }
            });


            edt.setText(p);

            }

        return v;
    }



}
