public class Trainee {
    private String id;
    private String name;
    private String gender;
    private byte age;

    public Trainee() {
    }

    // Getter & Setter
    public String getId() {
        return id;
    }

    public void setId(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("ID không được để trống!");
        }
        this.id = id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Tên không được để trống!");
        }
        this.name = name.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        if (gender == null) {
            throw new IllegalArgumentException("Giới tính không hợp lệ!");
        }
        gender = gender.trim().toLowerCase();
        if (!gender.equals("male") && !gender.equals("female")) {
            throw new IllegalArgumentException("Giới tính chỉ được là 'male' hoặc 'female'!");
        }
        this.gender = gender;
    }

    public byte getAge() {
        return age;
    }

    public void setAge(byte age) {
        if (age < 6) {
            throw new IllegalArgumentException("Tuổi phải >= 6!");
        }
        this.age = age;
    }

    @Override
    public String toString() {
        return String.format("ID: %s | Name: %s | Gender: %s | Age: %d",
                id, name, gender, age);
    }
}
