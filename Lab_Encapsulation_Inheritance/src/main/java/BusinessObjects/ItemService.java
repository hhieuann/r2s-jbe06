package BusinessObjects;

import Core.Entities.Item;
import Core.Entities.Painting;
import Core.Entities.Statue;
import Core.Entities.Vase;
import Core.Interfaces.IItemList;
import Core.Interfaces.IItemService;
import Utilities.DataInput;

public class ItemService implements IItemService {
    private final IItemList itemList;

    public ItemService(IItemList itemList) {
        this.itemList = itemList;
    }

    @Override
    public void addNewItem() {
        System.out.println("Chon loai item can them:");
        System.out.println("1. Vase");
        System.out.println("2. Statue");
        System.out.println("3. Painting");

        int choice = DataInput.readInt("Nhap lua chon (1-3): ", 1, 3);

        Item item;
        switch (choice) {
            case 1:
                item = new Vase();
                break;
            case 2:
                item = new Statue();
                break;
            default:
                item = new Painting();
                break;
        }

        // Gọi input polymorphism (đa hình)
        item.input();

        boolean ok = itemList.addItem(item);
        if (ok) System.out.println("Them thanh cong!");
        else System.out.println("Them that bai! (Danh sach day hoac du lieu loi)");
    }

    @Override
    public void displayAll() {
        itemList.displayAll();
    }

    @Override
    public void searchByCreator() {
        String creator = DataInput.readNonEmptyString("Nhap creator can tim: ");
        Item found = itemList.findItem(creator);
        if (found == null) {
            System.out.println("Khong tim thay item cua creator: " + creator);
        } else {
            System.out.println("Tim thay: " + found);
        }
    }

    @Override
    public void displayByType() {
        System.out.println("Nhap type can loc: Vase / Statue / Painting");
        String type = DataInput.readNonEmptyString("Type: ");
        itemList.displayItemsByType(type);
    }
}
