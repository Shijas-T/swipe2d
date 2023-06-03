package com.example.swipe2d;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder>{

    //Initialise the list item here
    private ArrayList<ItemModel> arrayListItems;
    //Creating context for toast
    private Context context;

    public ItemAdapter(ArrayList<ItemModel> arrayListItems, Context context) {
        this.arrayListItems = arrayListItems;
        this.context = context;
    }

    //View holder(it calls the created recycler View)
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    //All the click listener is done here
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.textViewItemId.setText(arrayListItems.get(position).getItemId());
        holder.textViewItemName.setText(arrayListItems.get(position).getItemName());
        holder.textViewItemDescription.setText(arrayListItems.get(position).getItemDescription());
        holder.textViewItemStatus.setText(arrayListItems.get(position).getItemStatus());

        if (arrayListItems.get(position).getItemStatus() == "Status: No")
            holder.linearLayoutStatusColor.setBackgroundColor(Color.RED);
        else if (arrayListItems.get(position).getItemStatus() == "Status: Yes")
            holder.linearLayoutStatusColor.setBackgroundColor(Color.GREEN);
        else holder.linearLayoutStatusColor.setBackgroundColor(Color.WHITE);

        holder.parent.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Toast.makeText(context, "long pressed", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayListItems.size();
    }

    //Every view inside the recycler view is declared and initialised here
    public class ViewHolder extends RecyclerView.ViewHolder{
        //Declaration
        private TextView textViewItemId,textViewItemName, textViewItemDescription, textViewItemStatus;
        private LinearLayout parent, linearLayoutStatusColor;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewItemId = itemView.findViewById(R.id.tv_item_id);
            textViewItemName = itemView.findViewById(R.id.tv_item_name);
            textViewItemDescription = itemView.findViewById(R.id.tv_item_description);
            textViewItemStatus = itemView.findViewById(R.id.tv_item_status);

            parent = itemView.findViewById(R.id.single_item);
            linearLayoutStatusColor = itemView.findViewById(R.id.ll_status_color);
        }
    }
}
