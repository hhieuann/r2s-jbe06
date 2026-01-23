package sales.entities;

/**
 * Employee entity ánh xạ bảng employees.
 */
public class Employee {
    private int id;
    private String lastName;
    private String firstName;
    private String birthdate;     // giữ String cho đúng UML (bạn có thể đổi Date nếu muốn)
    private Integer supervisorId; // có thể null

    public Employee() {}

    public Employee(int id, String lastName, String firstName, String birthdate, Integer supervisorId) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthdate = birthdate;
        this.supervisorId = supervisorId;
    }

    // Getter/Setter methods
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getBirthdate() { return birthdate; }
    public void setBirthdate(String birthdate) { this.birthdate = birthdate; }

    public Integer getSupervisorId() { return supervisorId; }
    public void setSupervisorId(Integer supervisorId) { this.supervisorId = supervisorId; }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", birthdate='" + birthdate + '\'' +
                ", supervisorId=" + supervisorId +
                '}';
    }
}
