package com.example.project.project;

import android.content.ClipData;
import android.content.Context;
import android.database.DataSetObserver;
import android.support.v4.widget.ViewDragHelper;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;

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

        v.setId(position);

        if(p != null){
            EditText edt = (EditText)v.findViewById(R.id.text1);
            if(position == 0)
                edt.requestFocus();

            btnDeleteNote.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("size myItems: ", " is " + myItems.size());
                    remove(myItems.get(position));
                    Log.d("size myItems: ", " is "+myItems.size());
                }
            });
            edt.setText(p);
            edt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus){
                        final EditText editT = (EditText) v;
                        myItems.set(position, editT.getText().toString());
                    }
                }
            });

        }

        return v;
    }



}
