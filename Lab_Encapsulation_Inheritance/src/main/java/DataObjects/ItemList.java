package DataObjects;

import Core.Entities.Item;
import Core.Interfaces.IItemList;

public class ItemList implements IItemList {
    private Item[] list;
    private int numOfItem;
    private final int MAX;

    public ItemList() {
        this.MAX = 100;
        this.list = new Item[MAX];
        this.numOfItem = 0;
    }

    @Override
    public boolean addItem(Item item) {
        if (item == null) return false;
        if (numOfItem >= MAX) return false;

        list[numOfItem] = item;
        numOfItem++;
        return true;
    }

    @Override
    public void displayAll() {
        if (numOfItem == 0) {
            System.out.println("Danh sach rong.");
            return;
        }
        for (int i = 0; i < numOfItem; i++) {
            System.out.println((i + 1) + ". " + list[i]);
        }
    }

    @Override
    public Item findItem(String creator) {
        if (creator == null) return null;
        for (int i = 0; i < numOfItem; i++) {
            // So sánh không phân biệt hoa thường
            if (list[i].getCreator() != null && list[i].getCreator().equalsIgnoreCase(creator.trim())) {
                return list[i];
            }
        }
        return null;
    }

    @Override
    public void displayItemsByType(String type) {
        if (type == null) return;
        String t = type.trim();

        boolean found = false;
        for (int i = 0; i < numOfItem; i++) {
            if (list[i].getType().equalsIgnoreCase(t)) {
                System.out.println(list[i]);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Khong tim thay item voi type = " + type);
        }
    }

    @Override
    public int size() {
        return numOfItem;
    }
}
