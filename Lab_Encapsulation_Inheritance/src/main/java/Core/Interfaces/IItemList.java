package Core.Interfaces;

import Core.Entities.Item;

public interface IItemList {
    boolean addItem(Item item);             // thêm vào mảng
    void displayAll();                      // in tất cả
    Item findItem(String creator);          // tìm theo creator (đúng sơ đồ)
    void displayItemsByType(String type);   // lọc theo loại
    int size();                             // số lượng hiện có
}
