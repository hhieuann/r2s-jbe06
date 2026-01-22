package sales.entities;

/**
 * Customer entity ánh xạ bảng customers.
 */
public class Customer {
    private int id;
    private String name;
    private String contact;
    private String address;
    private String city;
    private String postCode;
    private String country;

    public Customer() {}

    public Customer(int id, String name, String contact, String address, String city, String postCode, String country) {
        this.id = id;
        this.name = name;
        this.contact = contact;
        this.address = address;
        this.city = city;
        this.postCode = postCode;
        this.country = country;
    }

    // Getter/Setter methods
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getContact() { return contact; }
    public void setContact(String contact) { this.contact = contact; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getPostCode() { return postCode; }
    public void setPostCode(String postCode) { this.postCode = postCode; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", contact='" + contact + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", postCode='" + postCode + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
