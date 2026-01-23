package sales.entities;

/**
 * Order entity ánh xạ bảng orders.
 */
public class Order {
    private int id;
    private int customerId;
    private int employeeId;
    private String orderDate;

    public Order() {}

    public Order(int id, int customerId, int employeeId, String orderDate) {
        this.id = id;
        this.customerId = customerId;
        this.employeeId = employeeId;
        this.orderDate = orderDate;
    }

    // Getter/Setter
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }

    public int getEmployeeId() { return employeeId; }
    public void setEmployeeId(int employeeId) { this.employeeId = employeeId; }

    public String getOrderDate() { return orderDate; }
    public void setOrderDate(String orderDate) { this.orderDate = orderDate; }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", employeeId=" + employeeId +
                ", orderDate='" + orderDate + '\'' +
                '}';
    }
}
