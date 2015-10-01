package com.thenewboston.assignment_contacts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by rahul on 25/09/15.
 */
public class ButtonAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    public final String alphabets[] = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
    public final ArrayList<String> list = new ArrayList<String>(Arrays.asList(alphabets));


    public ButtonAdapter(Context context) {
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public class ButtonViewHolder
    {
        public Button button;
    }

    @Override
    public int getCount() {
        return 26;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v= convertView;
        ButtonViewHolder viewHolder ;
        if(convertView==null)
        {
            v = inflater.inflate(R.layout.alphabutton,null);
            viewHolder = new ButtonViewHolder();
            viewHolder.button = (Button) v.findViewById(R.id.button);
            v.setTag(viewHolder);

        }
        else
        {
            viewHolder=(ButtonViewHolder)v.getTag();
        }

        viewHolder.button.setText(list.get(position));

        viewHolder.button.clearFocus();

        return v;
    }
}
