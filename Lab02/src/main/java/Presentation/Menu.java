package Presentation;

import Utilities.DataInput;

public class Menu {
    public int getChoice() {
        System.out.println("\n========= STUDENT MANAGEMENT =========");
        System.out.println("1. Create a student");
        System.out.println("2. Display all");
        System.out.println("3. Find a student by id");
        System.out.println("4. Update a student by id");
        System.out.println("5. Quit");
        return DataInput.inputIntInRange("Chọn: ", 1, 5);
    }
}
