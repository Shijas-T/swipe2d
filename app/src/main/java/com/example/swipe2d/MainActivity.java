package com.example.swipe2d;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.daprlabs.cardstack.SwipeDeck;
import com.daprlabs.cardstack.SwipeFrameLayout;
import com.tsuryo.swipeablerv.SwipeLeftRightCallback;
import com.tsuryo.swipeablerv.SwipeableRecyclerView;

import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // Declaration
    private SwipeableRecyclerView swipeableRecyclerViewItems;
    private ArrayList<ItemModel> arrayListItems;
    private ItemAdapter itemAdapter;

    private static final String TAG = "MainActivity";

    private Button buttonStart, buttonSave, buttonRestart;

    private SwipeDeck cardStack;
    private DeckAdapter deckAdapter;
    private SwipeFrameLayout sfl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialisation
        buttonSave = findViewById(R.id.btn_save);
        buttonStart = findViewById(R.id.btn_start);
        buttonRestart = findViewById(R.id.btn_restart);

        buttonRestart.setVisibility(View.GONE);
        buttonSave.setVisibility(View.GONE);

        cardStack = findViewById(R.id.swipe_deck);
        sfl = findViewById(R.id.sfl);

        swipeableRecyclerViewItems = findViewById(R.id.rv);
        arrayListItems = new ArrayList<>();

        // add item to array list
        for (int i = 0; i < 30; i++){

            arrayListItems.add(new ItemModel(
                    "id" + String.valueOf(i+1),
                    "item name " + String.valueOf(i+1),
                    "description " + String.valueOf(i+1),
                    "Status: "
            ));
        }

        // setting up adapter
        itemAdapter = new ItemAdapter(arrayListItems, MainActivity.this);
        swipeableRecyclerViewItems.setLayoutManager(new LinearLayoutManager(this));
        swipeableRecyclerViewItems.setAdapter(itemAdapter);

        deckAdapter = new DeckAdapter(arrayListItems, this);
        cardStack.setAdapter(deckAdapter);

        // button click listener
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                swipeableRecyclerViewItems.setVisibility(View.GONE);
                sfl.setVisibility(View.VISIBLE);

                buttonStart.setVisibility(View.GONE);

                buttonRestart.setVisibility(View.VISIBLE);
                buttonSave.setVisibility(View.VISIBLE);
                LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1.5f);
                layoutParams1.setMargins(4,4,4,4);
                buttonRestart.setLayoutParams(layoutParams1);
                LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1.5f);
                layoutParams2.setMargins(4,4,4,4);
                buttonSave.setLayoutParams(layoutParams2);

            }
        });

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                swipeableRecyclerViewItems.setVisibility(View.VISIBLE);
                sfl.setVisibility(View.GONE);

                buttonRestart.setVisibility(View.GONE);
                buttonSave.setVisibility(View.GONE);

                buttonStart.setVisibility(View.VISIBLE);
                LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1f);
                layoutParams1.setMargins(4,4,4,4);
                buttonStart.setLayoutParams(layoutParams1);
            }
        });

        buttonRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        // card stack listener
        cardStack.setEventCallback(new SwipeDeck.SwipeEventCallback() {
            @Override
            public void cardSwipedLeft(int position) {
                Log.e(TAG, "cardSwipedLeft: " + String.valueOf(arrayListItems.get(position).getItemId()));
                arrayListItems.get(position).setItemStatus("Status: No");
//                deckAdapter.notifyDataSetChanged();
                itemAdapter.notifyDataSetChanged();
            }

            @Override
            public void cardSwipedRight(int position) {
                Log.e(TAG, "cardSwipedRight: " + String.valueOf(arrayListItems.get(position).getItemId()));
                arrayListItems.get(position).setItemStatus("Status: Yes");
                itemAdapter.notifyDataSetChanged();
//                deckAdapter.notifyDataSetChanged();

            }

            @Override
            public void cardClicked(int position) {

            }

            @Override
            public void cardsDepleted() {

            }
        });

        // swipe to delete
        swipeableRecyclerViewItems.setListener(new SwipeLeftRightCallback.Listener() {
            @Override
            public void onSwipedLeft(int position) {
                Toast.makeText(MainActivity.this, "item with id "+arrayListItems.get(position).getItemId()+ " is removed", Toast.LENGTH_SHORT).show();
                arrayListItems.remove(position);
                itemAdapter.notifyDataSetChanged();
                deckAdapter.notifyDataSetChanged();
            }

            @Override
            public void onSwipedRight(int position) {
            }
        });
    }
}