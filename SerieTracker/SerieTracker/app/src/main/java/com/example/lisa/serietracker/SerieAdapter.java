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
        View row = convertView;
        ViewHolder holder;
        //Check if the row is new
        if (row == null) {
            //Inflate the layout if it didn't exist yet
            row = inflater.inflate(R.layout.row_item, parent, false);
            //Create a new view holder instance
            holder = new ViewHolder(row);
            //set the holder as a tag so we can get it back later
            row.setTag(holder);
        } else {
            //The row isn't new so we can reuse the view holder
            holder = (ViewHolder) row.getTag();
        }
        //Populate the row
        holder.populateRow(getItem(position));
        return row;
    }


    class ViewHolder {

        private TextView title, ep, status, rating;

        //Initialize the variables
        public ViewHolder(View view) {

            title = (TextView) view.findViewById(R.id.title);
            ep = (TextView) view.findViewById(R.id.ep);
            status = (TextView)view.findViewById(R.id.status);
            rating = (TextView)view.findViewById(R.id.rating);

        }
        //Method to set the values in a row
        public void populateRow(Serie serie) {

            //Set the title for this row
            title.setText(serie.getTitle());
            //Set the episode for this row
            ep.setText(serie.getEp());
            //Set the status for this row
            status.setText((serie.getStatus()));
            //Set the rating for this row
            rating.setText(serie.getRating());
        }
    }




}
