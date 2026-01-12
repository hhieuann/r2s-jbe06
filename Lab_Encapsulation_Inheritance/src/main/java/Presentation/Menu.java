package Presentation;

import Core.Interfaces.IItemService;
import Utilities.DataInput;

public class Menu {
    private final IItemService service;

    public Menu(IItemService service) {
        this.service = service;
    }

    public void run() {
        while (true) {
            System.out.println("\n===== ANTIQUE SHOP MANAGEMENT =====");
            System.out.println("1. Add new item");
            System.out.println("2. Display all items");
            System.out.println("3. Search item by creator");
            System.out.println("4. Display items by type");
            System.out.println("5. Exit");

            int choice = DataInput.readInt("Your choice (1-5): ", 1, 5);

            switch (choice) {
                case 1:
                    service.addNewItem();
                    break;
                case 2:
                    service.displayAll();
                    break;
                case 3:
                    service.searchByCreator();
                    break;
                case 4:
                    service.displayByType();
                    break;
                case 5:
                    System.out.println("Bye!");
                    return;
            }
        }
    }
}
