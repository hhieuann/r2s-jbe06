package training.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import training.entities.Course;
import training.utils.Validator;

/**
 * CourseManagement: quản lý danh sách course + menu.
 * Tối ưu: dùng HashMap<code, Course> để:
 * - kiểm tra trùng code nhanh O(1)
 * - search theo code nhanh O(1)
 */
public class CourseManagement {

    // Lưu theo code để tối ưu
    private final Map<String, Course> courses = new HashMap<>();

    public CourseManagement() {
    }

    /**
     * Menu nhập lựa chọn.
     */
    private int inputMenuChoice(Scanner sc) {
        while (true) {
            System.out.println("\n===== COURSE MANAGEMENT =====");
            System.out.println("1. Create a course");
            System.out.println("2. Search courses by attribute");
            System.out.println("3. Display all courses by flag");
            System.out.println("4. Quit");
            System.out.print("Choose (1-4): ");
            String s = sc.nextLine().trim();
            if (s.matches("^[1-4]$")) return Integer.parseInt(s);
            System.out.println("Invalid choice! Please enter 1-4.");
        }
    }

    /**
     * Tạo course + nhập dữ liệu, sai nhập lại.
     * Bổ sung: kiểm tra trùng code bằng HashMap.
     */
    public void createCourse(Scanner sc) {
        Course c = new Course();
        while (true) {
            c.input(sc);

            // Check duplicated code (O(1))
            if (courses.containsKey(c.getCode())) {
                System.out.println("Duplicated code! This code already exists. Please re-input course.");
                // nhập lại toàn bộ theo đúng yêu cầu "invalid values require repeat re-input"
                c = new Course();
                continue;
            }

            // hợp lệ -> lưu
            courses.put(c.getCode(), c);
            System.out.println("Created: " + c);
            break;
        }
    }

    /**
     * Search theo attribute: type + data
     * type có thể: code, name, status, duration, flag
     * data là chuỗi người dùng nhập.
     * Trả về ArrayList<Course> đúng theo diagram.
     */
    public ArrayList<Course> search(String type, String data) {
        ArrayList<Course> result = new ArrayList<>();
        if (type == null || data == null) return result;

        String t = type.trim().toLowerCase();
        String d = data.trim();

        // Tối ưu đặc biệt cho code: tra O(1)
        if ("code".equals(t)) {
            Course found = courses.get(d);
            if (found != null) result.add(found);
            return result;
        }

        // Các attribute còn lại: duyệt values()
        for (Course c : courses.values()) {
            switch (t) {
                case "name":
                    // search gần đúng: chứa chuỗi (case-insensitive)
                    if (c.getName() != null && c.getName().toLowerCase().contains(d.toLowerCase())) {
                        result.add(c);
                    }
                    break;

                case "status":
                    // chấp nhận true/false
                    String statusText = d.toLowerCase();
                    if (Validator.validateStatusInput(statusText)) {
                        boolean val = Boolean.parseBoolean(statusText);
                        if (c.isStatus() == val) result.add(c);
                    }
                    break;

                case "duration":
                    // duration là số
                    if (Validator.isShortNumber(d)) {
                        short val = Short.parseShort(d);
                        if (c.getDuration() == val) result.add(c);
                    }
                    break;

                case "flag":
                    // flag: optional / prerequisite / N/A (so sánh chuẩn hoá)
                    if ("N/A".equalsIgnoreCase(d)) {
                        if ("N/A".equals(c.getFlag())) result.add(c);
                    } else {
                        if (c.getFlag() != null && c.getFlag().equalsIgnoreCase(d)) result.add(c);
                    }
                    break;

                default:
                    // type không hợp lệ -> result rỗng
                    break;
            }
        }
        return result;
    }

    /**
     * Hiển thị tất cả course theo flag người dùng nhập.
     */
    public void displayAll(String flag) {
        if (flag == null) {
            System.out.println("Flag is null!");
            return;
        }
        String f = flag.trim();

        if (!Validator.validateFlag(f)) {
            System.out.println("Invalid flag! Only accept: optional, prerequisite, N/A.");
            return;
        }

        List<Course> matched = new ArrayList<>();
        for (Course c : courses.values()) {
            if ("N/A".equals(f)) {
                if ("N/A".equals(c.getFlag())) matched.add(c);
            } else if (c.getFlag() != null && c.getFlag().equalsIgnoreCase(f)) {
                matched.add(c);
            }
        }

        if (matched.isEmpty()) {
            System.out.println("No courses found with flag = " + f);
            return;
        }

        System.out.println("Courses with flag = " + f + ":");
        for (Course c : matched) {
            System.out.println(" - " + c);
        }
    }

    /**
     * Chạy chương trình.
     */
    public void run(Scanner sc) {
        while (true) {
            int choice = inputMenuChoice(sc);
            switch (choice) {
                case 1:
                    createCourse(sc);
                    break;

                case 2:
                    System.out.print("Enter attribute name (code/name/status/duration/flag): ");
                    String type = sc.nextLine().trim();
                    System.out.print("Enter data for search: ");
                    String data = sc.nextLine();

                    ArrayList<Course> found = search(type, data);
                    if (found.isEmpty()) {
                        System.out.println("No result!");
                    } else {
                        System.out.println("Found " + found.size() + " course(s):");
                        for (Course c : found) {
                            System.out.println(" - " + c);
                        }
                    }
                    break;

                case 3:
                    System.out.print("Enter flag to display (optional/prerequisite/N/A): ");
                    String flag = sc.nextLine().trim();
                    displayAll(flag);
                    break;

                case 4:
                    System.out.println("Bye!");
                    return;

                default:
                    // không xảy ra do đã validate
                    break;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        new CourseManagement().run(sc);
        sc.close();
    }
}
