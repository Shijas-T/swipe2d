package com.example.swipe2d;

public class ItemModel {

    private String itemId;
    private String itemName;
    private String itemDescription;

    public ItemModel(String itemId, String itemName, String itemDescription) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
    }

    public String getItemId() {
        return itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }
}
