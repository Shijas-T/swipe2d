package com.example.swipe2d;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.daprlabs.cardstack.SwipeDeck;
import com.daprlabs.cardstack.SwipeFrameLayout;
import com.tsuryo.swipeablerv.SwipeLeftRightCallback;
import com.tsuryo.swipeablerv.SwipeableRecyclerView;

import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private SwipeableRecyclerView swipeableRecyclerViewItems;
    private ArrayList<ItemModel> arrayListItems;
    private ItemAdapter itemAdapter;

    private Button buttonStart, buttonSave;

    private SwipeDeck cardStack;
    SwipeFrameLayout sfl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonSave = findViewById(R.id.btn_save);
        buttonStart = findViewById(R.id.btn_start);

        cardStack = (SwipeDeck) findViewById(R.id.swipe_deck);
        sfl = findViewById(R.id.sfl);

        swipeableRecyclerViewItems = findViewById(R.id.rv);
        arrayListItems = new ArrayList<>();

        for (int i = 0; i < 15; i++){

            arrayListItems.add(new ItemModel(
                    "id",
                    "item name",
                    "description"
            ));
        }

        itemAdapter = new ItemAdapter(arrayListItems, MainActivity.this);
        swipeableRecyclerViewItems.setLayoutManager(new LinearLayoutManager(this));
        swipeableRecyclerViewItems.setAdapter(itemAdapter);


        DeckAdapter adapter = new DeckAdapter(arrayListItems, this);
        cardStack.setAdapter(adapter);


        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                swipeableRecyclerViewItems.setVisibility(View.GONE);
                sfl.setVisibility(View.VISIBLE);
            }
        });

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                swipeableRecyclerViewItems.setVisibility(View.VISIBLE);
                sfl.setVisibility(View.GONE);
            }
        });



        cardStack.setEventCallback(new SwipeDeck.SwipeEventCallback() {
            @Override
            public void cardSwipedLeft(int position) {
                arrayListItems.remove(position > 0 ? position-1:position);
                itemAdapter.notifyDataSetChanged();
                adapter.notifyDataSetChanged();
            }

            @Override
            public void cardSwipedRight(int position) {

            }

            @Override
            public void cardClicked(int position) {

            }

            @Override
            public void cardsDepleted() {

            }
        });


        swipeableRecyclerViewItems.setListener(new SwipeLeftRightCallback.Listener() {
            @Override
            public void onSwipedLeft(int position) {
                Toast.makeText(MainActivity.this, "item with id "+arrayListItems.get(position).getItemId()+ " is removed", Toast.LENGTH_SHORT).show();
                arrayListItems.remove(position);
                itemAdapter.notifyDataSetChanged();
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onSwipedRight(int position) {
            }
        });


    }
}