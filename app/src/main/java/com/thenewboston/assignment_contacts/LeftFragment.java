package com.thenewboston.assignment_contacts;

import android.app.ActionBar;
import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;

/**
 * Created by rahul on 25/09/15.
 */

public class LeftFragment extends Fragment implements
        AdapterView.OnItemClickListener {
    private ListView lv;
    private EditText sv;
    private ActionBar actionBar;

    ArrayList<SingleContact> contactslist;
    ListView_Interface listView_interface;


  /*  String button_id;
   Button  button_A,button_B,button_C,button_D,button_E,button_F,button_G,
            button_H,button_I,button_J,button_K,button_L,button_M,button_N,
            button_O,button_P,button_Q,button_R,button_S,button_T,button_U,
            button_V,button_W,button_X,button_Y,button_Z;*/



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Button button = (Button) view.findViewById(R.id.button);
        String buttonText ="ALPHABET : "+ button.getText().toString();
        ((MainActivity) getActivity()).setActionBarTitle("ALPHABET : " + buttonText);
        listView_interface.changeListPosition(buttonText);
    }
    EditText searchView;
    ImageView closeButton;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_a, container, false);
        ButtonAdapter buttonAdapter = new ButtonAdapter(getActivity());
        GridView gridview = (GridView) view.findViewById(R.id.gridview);
        gridview.setAdapter(buttonAdapter);
        searchView = (EditText) view.findViewById(R.id.searchView);
        closeButton = (ImageView) view.findViewById(R.id.searchView_close);

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                Button button = (Button) v.findViewById(R.id.button);
               // sv=(EditText) v.findViewById(R.id.toolbar_alphabet);

                String buttonText = button.getText().toString();

                     Log.i("button Text", buttonText);
                ((MainActivity) getActivity()).setActionBarTitle("ALPHABET : " + buttonText);

                listView_interface.changeListPosition(buttonText);

               // Log.i("searchviewid", searchView.getQuery().toString());
                searchView.setText(buttonText);
                searchView.clearFocus();

            }
        });

        closeButton.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View v) {
                                               searchView.clearFocus();
                                               searchView.setText("");
                                               ((MainActivity) getActivity()).setActionBarTitle("CONTACTS");
                                               listView_interface.changeListPosition("");
                                           }
                                       }
        );

        searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (searchView.getText().toString().equals("")) {
                    ((MainActivity) getActivity()).setActionBarTitle("CONTACTS");
                    listView_interface.changeListPosition("");
                    searchView.clearFocus();
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                listView_interface.searchContacts(s.toString());
                if(searchView.getText().toString().length()==1)
                ((MainActivity) getActivity()).setActionBarTitle("ALPHABET : "+searchView.getText().toString());
                searchView.clearFocus();
            }
        });



        return view;
    }





    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
                listView_interface = (ListView_Interface) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement ListViewSelection");
        }
    }
}
