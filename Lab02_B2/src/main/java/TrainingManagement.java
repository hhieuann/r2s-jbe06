import java.util.Scanner;

public class TrainingManagement {
    private TraineeForm traineeForm;
    private Scanner scanner;
    private Trainee[] listOfTrainees = new Trainee[100];
    private byte count = 0;

    public TrainingManagement() {
        scanner = new Scanner(System.in);
        traineeForm = new TraineeForm(scanner);
    }

    public static void main(String[] args) {
        TrainingManagement app = new TrainingManagement();
        app.run();
    }

    private void run() {
        while (true) {
            System.out.println("\n========= TRAINING MANAGEMENT =========");
            System.out.println("1. Add trainee");
            System.out.println("2. Display all trainees");
            System.out.println("3. Find trainee by ID");
            System.out.println("4. Find trainee by Name");
            System.out.println("5. Update trainee by ID");
            System.out.println("0. Exit");
            System.out.print("Chọn: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Lựa chọn không hợp lệ!");
                continue;
            }

            switch (choice) {
                case 1:
                    addTrainee();
                    break;
                case 2:
                    displayAllTrainees();
                    break;
                case 3:
                    findById();
                    break;
                case 4:
                    findByName();
                    break;
                case 5:
                    updateTrainee();
                    break;
                case 0:
                    System.out.println("Thoát chương trình!");
                    return;
                default:
                    System.out.println("Lựa chọn không tồn tại!");
            }
        }
    }

    private void addTrainee() {
        if (count >= listOfTrainees.length) {
            System.out.println("Danh sách đã đầy!");
            return;
        }

        String id;
        while (true) {
            try {
                id = traineeForm.getId();
                if (findTraineeById(id) != null) {
                    System.out.println("ID đã tồn tại!");
                } else if (id.isEmpty()) {
                    System.out.println("ID không được rỗng!");
                } else {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Lỗi nhập ID!");
            }
        }

        Trainee trainee = traineeForm.getTrainee();
        trainee.setId(id);

        listOfTrainees[count++] = trainee;
        System.out.println("Thêm trainee thành công!");
    }

    private void displayAllTrainees() {
        if (count == 0) {
            System.out.println("Danh sách trống!");
            return;
        }

        for (int i = 0; i < count; i++) {
            System.out.println(listOfTrainees[i]);
        }
    }

    private void findById() {
        System.out.print("Nhập ID cần tìm: ");
        String id = scanner.nextLine();

        Trainee t = findTraineeById(id);
        if (t == null) {
            System.out.println("Không tìm thấy!");
        } else {
            System.out.println("Tìm thấy:");
            System.out.println(t);
        }
    }

    private void findByName() {
        System.out.print("Nhập tên cần tìm: ");
        String name = scanner.nextLine().toLowerCase();

        boolean found = false;
        for (int i = 0; i < count; i++) {
            if (listOfTrainees[i].getName().toLowerCase().contains(name)) {
                System.out.println(listOfTrainees[i]);
                found = true;
            }
        }

        if (!found) {
            System.out.println("Không tìm thấy!");
        }
    }

    private void updateTrainee() {
        System.out.print("Nhập ID cần update: ");
        String id = scanner.nextLine();

        Trainee oldTrainee = findTraineeById(id);
        if (oldTrainee == null) {
            System.out.println("Không tìm thấy trainee!");
            return;
        }

        System.out.println("Nhập thông tin mới:");
        Trainee newTrainee = traineeForm.getTrainee();
        newTrainee.setId(id);

        for (int i = 0; i < count; i++) {
            if (listOfTrainees[i].getId().equalsIgnoreCase(id)) {
                listOfTrainees[i] = newTrainee;
                break;
            }
        }

        System.out.println("Update thành công!");
    }

    private Trainee findTraineeById(String id) {
        for (int i = 0; i < count; i++) {
            if (listOfTrainees[i].getId().equalsIgnoreCase(id)) {
                return listOfTrainees[i];
            }
        }
        return null;
    }
}
