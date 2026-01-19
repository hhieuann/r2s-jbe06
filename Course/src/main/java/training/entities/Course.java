package training.entities;

import java.util.Scanner;
import training.utils.Validator;

/**
 * Course entity theo yêu cầu đề bài.
 * - code: "RA" + 3 digits (5 ký tự), không trùng
 * - name: không rỗng
 * - status: true/false
 * - duration: > 0
 * - flag: optional / prerequisite / N/A
 */
public class Course {
    private String code;
    private String name;
    private boolean status;
    private short duration;
    private String flag;

    public Course() {
    }

    public Course(String code, String name, boolean status, short duration, String flag) {
        this.code = code;
        this.name = name;
        this.status = status;
        this.duration = duration;
        this.flag = flag;
    }

    // ===== Getter/Setter =====
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public short getDuration() {
        return duration;
    }

    public void setDuration(short duration) {
        this.duration = duration;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    /**
     * Nhập dữ liệu course từ bàn phím, sai thì nhập lại đến khi đúng.
     * Lưu ý: kiểm tra trùng code sẽ làm ở CourseManagement (do quản lý collection).
     */
    public void input(Scanner sc) {
        // Nhập CODE (đúng format). Việc check trùng sẽ ở CourseManagement.
        while (true) {
            System.out.print("Enter code (RAxxx): ");
            String c = sc.nextLine().trim();
            if (Validator.validateCode(c)) {
                this.code = c;
                break;
            }
            System.out.println("Invalid code! Format must be RA + 3 digits (e.g., RA001).");
        }

        // Nhập NAME
        while (true) {
            System.out.print("Enter name: ");
            String n = sc.nextLine().trim();
            if (!n.isEmpty()) {
                this.name = n;
                break;
            }
            System.out.println("Name cannot be empty!");
        }

        // Nhập STATUS (true/false)
        while (true) {
            System.out.print("Enter status (true/false): ");
            String s = sc.nextLine().trim().toLowerCase();
            if (Validator.validateStatusInput(s)) {
                this.status = Boolean.parseBoolean(s);
                break;
            }
            System.out.println("Invalid status! Only accept true or false.");
        }

        // Nhập DURATION (>0)
        while (true) {
            System.out.print("Enter duration (>0): ");
            String d = sc.nextLine().trim();
            if (Validator.isShortNumber(d)) {
                short val = Short.parseShort(d);
                if (Validator.validateDuration(val)) {
                    this.duration = val;
                    break;
                }
            }
            System.out.println("Invalid duration! Must be a number and greater than 0.");
        }

        // Nhập FLAG (optional / prerequisite / N/A)
        while (true) {
            System.out.print("Enter flag (optional/prerequisite/N/A): ");
            String f = sc.nextLine().trim();
            if (Validator.validateFlag(f)) {
                this.flag = f;
                break;
            }
            System.out.println("Invalid flag! Only accept: optional, prerequisite, N/A.");
        }
    }

    @Override
    public String toString() {
        return String.format("Course{code='%s', name='%s', status=%s, duration=%d, flag='%s'}",
                code, name, status, (int) duration, flag);
    }
}
