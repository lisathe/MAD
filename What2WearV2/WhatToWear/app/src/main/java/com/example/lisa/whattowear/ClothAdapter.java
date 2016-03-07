package com.example.lisa.whattowear;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.net.URI;
import java.util.List;

/**
 * Created by Lisa on 1-3-2016.
 */

public class ClothAdapter extends ArrayAdapter<Cloth> {

    private LayoutInflater inflater;
    private URI pictureUri;

    //Constructor
    public ClothAdapter(Context context, int resource, List<Cloth> objects) {
        super(context, resource, objects);

        //Initialize the layout inflater
        inflater = LayoutInflater.from(getContext());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Inflate layout into the View for the Row
        convertView = inflater.inflate(R.layout.row_item, parent, false);

        //Retrieve ListItem at the position
        Cloth item = getItem(position);

        //Retrieve all Views of a ListItem
        ImageView icon = (ImageView) convertView.findViewById(R.id.image);
        TextView title = (TextView) convertView.findViewById(R.id.title);
        TextView description = (TextView) convertView.findViewById(R.id.description);
        ImageView selectedPicture = (ImageView) convertView.findViewById(R.id.imageView);

        //Set the icon for this row
        icon.setImageResource(item.getIcon());
        //Set the title for this row
        title.setText(item.getTitle());

        //Set the description for this row
        description.setText(item.getDescription());

        return convertView;
    }

}
