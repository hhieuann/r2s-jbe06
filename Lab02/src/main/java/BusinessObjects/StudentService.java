package BusinessObjects;

import Core.Entities.Student;
import Core.InterFaces.IStudentRepository;
import Core.InterFaces.IStudentService;
import Utilities.DataInput;
import Utilities.Validator;

public class StudentService implements IStudentService {
    private final IStudentRepository repo;

    // DI (Dependency Injection) qua constructor
    public StudentService(IStudentRepository repo) {
        this.repo = repo;
    }

    @Override
    public void createStudent() {
        if (repo.isFull()) {
            System.out.println(">> Danh sách đã đủ 100 sinh viên. Không thể thêm!");
            return;
        }

        // ID: không trùng
        String id;
        while (true) {
            id = DataInput.inputString("Nhập ID: ");
            if (!repo.existsId(id)) break;
            System.out.println(">> ID bị trùng! Nhập lại.");
        }

        // Name: không rỗng
        String name = DataInput.inputString("Nhập Name: ");

        // Age >= 18
        int age;
        while (true) {
            age = DataInput.inputInt("Nhập Age (>=18): ");
            if (Validator.isValidAge(age)) break;
            System.out.println(">> Age phải >= 18!");
        }

        String address = DataInput.inputString("Nhập Address: ");

        // Gender: male/female
        String gender;
        while (true) {
            gender = DataInput.inputString("Nhập Gender (male/female): ");
            if (Validator.isValidGender(gender)) {
                gender = Validator.normalizeGender(gender);
                break;
            }
            System.out.println(">> Gender chỉ được là male hoặc female!");
        }

        // Email: hợp lệ
        String email;
        while (true) {
            email = DataInput.inputString("Nhập Email: ");
            if (Validator.isValidEmail(email)) break;
            System.out.println(">> Email không hợp lệ! Ví dụ: abc@gmail.com");
        }

        Student s = new Student(id, name, age, address, gender, email);
        boolean ok = repo.add(s);

        System.out.println(ok ? ">> Tạo sinh viên thành công!" : ">> Tạo thất bại!");
    }

    @Override
    public void displayAll() {
        if (repo.size() == 0) {
            System.out.println(">> Danh sách rỗng!");
            return;
        }

        System.out.println("========== STUDENT LIST ==========");
        System.out.printf("%-10s | %-20s | %-3s | %-20s | %-6s | %-25s%n",
                "ID", "NAME", "AGE", "ADDRESS", "GENDER", "EMAIL");
        System.out.println("--------------------------------------------------------------------------");

        for (Student s : repo.findAll()) {
            System.out.println(s);
        }
        System.out.println("--------------------------------------------------------------------------");
        System.out.println("Tổng: " + repo.size() + " sinh viên");
    }

    @Override
    public void findStudentById() {
        String id = DataInput.inputString("Nhập ID cần tìm: ");
        Student s = repo.findById(id);

        if (s == null) {
            System.out.println(">> Không tìm thấy sinh viên có ID = " + id);
            return;
        }

        System.out.println(">> Tìm thấy:");
        System.out.printf("%-10s | %-20s | %-3s | %-20s | %-6s | %-25s%n",
                "ID", "NAME", "AGE", "ADDRESS", "GENDER", "EMAIL");
        System.out.println("--------------------------------------------------------------------------");
        System.out.println(s);
    }

    @Override
    public void updateStudentById() {
        String id = DataInput.inputString("Nhập ID cần update: ");
        Student s = repo.findById(id);

        if (s == null) {
            System.out.println(">> Không tìm thấy sinh viên có ID = " + id);
            return;
        }

        System.out.println(">> Nhập thông tin mới (Enter để bỏ qua giữ nguyên):");

        // Name (không rỗng nếu nhập)
        while (true) {
            String newName = DataInput.inputOptionalString("Name mới: ");
            if (newName.isEmpty()) break; // giữ nguyên
            if (Validator.isNotEmpty(newName)) {
                s.setName(newName);
                break;
            }
            System.out.println(">> Name không được rỗng!");
        }

        // Age >= 18 nếu nhập
        while (true) {
            String rawAge = DataInput.inputOptionalString("Age mới (>=18): ");
            if (rawAge.isEmpty()) break;
            try {
                int newAge = Integer.parseInt(rawAge);
                if (!Validator.isValidAge(newAge)) {
                    System.out.println(">> Age phải >= 18!");
                    continue;
                }
                s.setAge(newAge);
                break;
            } catch (NumberFormatException e) {
                System.out.println(">> Age phải là số nguyên!");
            }
        }

        // Address
        String newAddress = DataInput.inputOptionalString("Address mới: ");
        if (!newAddress.isEmpty()) s.setAddress(newAddress);

        // Gender male/female nếu nhập
        while (true) {
            String newGender = DataInput.inputOptionalString("Gender mới (male/female): ");
            if (newGender.isEmpty()) break;
            if (Validator.isValidGender(newGender)) {
                s.setGender(Validator.normalizeGender(newGender));
                break;
            }
            System.out.println(">> Gender chỉ được male hoặc female!");
        }

        // Email hợp lệ nếu nhập
        while (true) {
            String newEmail = DataInput.inputOptionalString("Email mới: ");
            if (newEmail.isEmpty()) break;
            if (Validator.isValidEmail(newEmail)) {
                s.setEmail(newEmail);
                break;
            }
            System.out.println(">> Email không hợp lệ!");
        }

        boolean ok = repo.update(s);
        System.out.println(ok ? ">> Update thành công!" : ">> Update thất bại!");
    }
}
