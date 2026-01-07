package Core.Entities;

public class Student {
    private String id;
    private String name;
    private int age;
    private String address;
    private String gender; // "male" hoặc "female"
    private String email;

    public Student(String id, String name, int age, String address, String gender, String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
        this.gender = gender;
        this.email = email;
    }

    // ===== Getter/Setter =====
    public String getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    @Override
    public String toString() {
        return String.format("%-10s | %-20s | %-3d | %-20s | %-6s | %-25s",
                id, name, age, address, gender, email);
    }
}
