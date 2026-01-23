package Presentation;

import BusinessObjects.StudentService;
import Core.InterFaces.IStudentRepository;
import Core.InterFaces.IStudentService;
import DataObjects.StudentRepository;

public class Program {
    public static void main(String[] args) {

        // Khởi tạo repository + service theo DI
        IStudentRepository repo = new StudentRepository();
        IStudentService service = new StudentService(repo);

        Menu menu = new Menu();

        while (true) {
            int choice = menu.getChoice();
            switch (choice) {
                case 1:
                    service.createStudent();
                    break;
                case 2:
                    service.displayAll();
                    break;
                case 3:
                    service.findStudentById();
                    break;
                case 4:
                    service.updateStudentById();
                    break;
                case 5:
                    System.out.println(">> Bye!");
                    return;
            }
        }
    }
}
