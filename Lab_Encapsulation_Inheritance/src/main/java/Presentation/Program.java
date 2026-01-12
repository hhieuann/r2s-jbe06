package Presentation;

import BusinessObjects.ItemService;
import Core.Interfaces.IItemList;
import Core.Interfaces.IItemService;
import DataObjects.ItemList;

public class Program {
    public static void main(String[] args) {
        // DAO/List
        IItemList itemList = new ItemList();

        // Service (DI)
        IItemService service = new ItemService(itemList);

        // Menu
        Menu menu = new Menu(service);
        menu.run();
    }
}
