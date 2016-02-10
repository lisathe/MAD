package com.example.lisa.shoppinglistadvanced;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ItemAdapter  extends ArrayAdapter<ListItem>{
    private LayoutInflater inflater;

    //Constructor
    public ItemAdapter(Context context, int resource, List<ListItem> objects) {
        super(context, resource, objects);

        //Initialize the layout inflater
        inflater = LayoutInflater.from(getContext());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Inflate layout into the View for the Row
        convertView = inflater.inflate(R.layout.row_item, parent, false);

        //Retrieve ListItem at the position
        ListItem item = getItem(position);

        //Retrieve all Views of a ListItem
        ImageView icon = (ImageView) convertView.findViewById(R.id.image);
        TextView title = (TextView) convertView.findViewById(R.id.title);
        TextView description = (TextView) convertView.findViewById(R.id.description);

        //Set the icon for this row
        icon.setImageResource(item.getImageResource());

        //Set the title for this row
        title.setText(item.getTitle());

        //Set the description for this row
        description.setText(item.getDescription());

        return convertView;
    }

}