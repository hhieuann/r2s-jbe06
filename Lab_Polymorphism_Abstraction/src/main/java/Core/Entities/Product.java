package Core.Entities;

public abstract class Product {
    // Thuộc tính protected theo UML (#)
    protected int id;
    protected String name;
    protected float price;

    /**
     * Hàm tạo Product
     * @param id mã sản phẩm
     * @param name tên sản phẩm
     * @param price giá sản phẩm
     */
    public Product(int id, String name, float price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    // Getter/Setter cơ bản
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    /**
     * Lấy tên loại sản phẩm (để hiển thị)
     * -> Polymorphism: mỗi subclass trả về loại riêng
     */
    public abstract String getType();

    /**
     * Lấy thông tin riêng của subclass (brand/size...)
     * -> Polymorphism: mỗi subclass override
     */
    public abstract String getExtraInfo();

    /**
     * Chuỗi hiển thị chung cho mọi sản phẩm.
     * Thể hiện polymorphism qua getType() và getExtraInfo()
     */
    @Override
    public String toString() {
        return String.format("ID=%d | Name=%s | Price=%.2f | Type=%s | %s",
                id, name, price, getType(), getExtraInfo());
    }
}
