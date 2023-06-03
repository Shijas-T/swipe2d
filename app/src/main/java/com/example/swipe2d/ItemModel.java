package com.example.swipe2d;

public class ItemModel {

    private String itemId;
    private String itemName;
    private String itemDescription;
    private String itemStatus;

    public ItemModel(String itemId, String itemName, String itemDescription, String itemStatus) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemStatus = itemStatus;
    }

    public void setItemStatus(String itemStatus) {
        this.itemStatus = itemStatus;
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

    public String getItemStatus() {
        return itemStatus;
    }
}
