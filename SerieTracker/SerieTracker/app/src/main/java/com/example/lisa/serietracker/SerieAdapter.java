package com.example.lisa.serietracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Lisa on 3-3-2016.
 */
public class SerieAdapter extends ArrayAdapter<Serie> {

    private LayoutInflater inflater;

    //Constructor
    public SerieAdapter(Context context, int resource, List<Serie> series) {
        super(context, resource,series);

        //Initialize the layout inflater
        inflater = LayoutInflater.from(getContext());

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Inflate layout into the View for the Row
        convertView = inflater.inflate(R.layout.row_item, parent, false);

        //Retrieve ListItem at the position
        Serie serie = getItem(position);

        //Retrieve all Views of a ListItem
        TextView title = (TextView) convertView.findViewById(R.id.title);
        TextView status = (TextView)convertView.findViewById(R.id.status);
        TextView ep = (TextView)convertView.findViewById(R.id.ep);
        TextView rating = (TextView) convertView.findViewById(R.id.rating);


        //Set the values for this row
        title.setText(serie.getTitle());
        status.setText(serie.getStatus());
        ep.setText(serie.getEp());
        rating.setText(serie.getRating());

        return convertView;
    }



}
