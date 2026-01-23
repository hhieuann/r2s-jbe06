import java.util.Scanner;

public class TraineeForm {
    private Scanner scanner;

    public TraineeForm(Scanner scanner) {
        this.scanner = scanner;
    }

    public String getId() {
        System.out.print("Nhập ID: ");
        return scanner.nextLine().trim();
    }

    public Trainee getTrainee() {
        Trainee trainee = new Trainee();

        while (true) {
            try {
                System.out.print("Nhập tên: ");
                String name = scanner.nextLine();
                trainee.setName(name);
                break;
            } catch (Exception e) {
                System.out.println("Tên không phù hợp" + e.getMessage());
            }
        }

        while (true) {
            try {
                System.out.print("Nhập giới tính (male/female): ");
                String gender = scanner.nextLine();
                trainee.setGender(gender);
                break;
            } catch (Exception e) {
                System.out.println("Giới tính không phù hợp " + e.getMessage());
            }
        }

        while (true) {
            try {
                System.out.print("Nhập tuổi: ");
                byte age = Byte.parseByte(scanner.nextLine());
                trainee.setAge(age);
                break;
            } catch (Exception e) {
                System.out.println("Tuổi không hợp lệ!");
            }
        }

        return trainee;
    }
}
