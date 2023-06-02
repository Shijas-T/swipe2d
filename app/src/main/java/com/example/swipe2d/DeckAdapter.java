package com.example.swipe2d;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class DeckAdapter extends BaseAdapter {

    private ArrayList<ItemModel> arrayListItem;
    private Context context;

    public DeckAdapter(ArrayList<ItemModel> arrayListItem, Context context) {
        this.arrayListItem = arrayListItem;
        this.context = context;
    }

    @Override
    public int getCount() {
        // in get count method we are returning the size of our array list.
        return arrayListItem.size();
    }

    @Override
    public Object getItem(int position) {
        // in get item method we are returning the item from our array list.
        return arrayListItem.get(position);
    }

    @Override
    public long getItemId(int position) {
        // in get item id we are returning the position.
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // in get view method we are inflating our layout on below line.
        View v = convertView;
        if (v == null) {
            // on below line we are inflating our layout.
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_swipe_card, parent, false);
        }
        // on below line we are initializing our variables and setting data to our variables.
        ((TextView) v.findViewById(R.id.tv_s_item_id)).setText(arrayListItem.get(position).getItemId());
        ((TextView) v.findViewById(R.id.tv_s_item_name)).setText(arrayListItem.get(position).getItemName());
        ((TextView) v.findViewById(R.id.tv_s_item_description)).setText(arrayListItem.get(position).getItemDescription());
        return v;
    }
}
