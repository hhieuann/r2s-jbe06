package Core.Entities;

public abstract class Item {
    private String id;        // mã sản phẩm
    private String value;     // giá trị (để String cho dễ nhập: "1000$", "2000", ...)
    private String creator;   // tác giả / người tạo

    public Item() {
    }

    public Item(String id, String value, String creator) {
        this.id = id;
        this.value = value;
        this.creator = creator;
    }

    // ===== Getters/Setters =====
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    /**
     * Nhập dữ liệu riêng cho từng loại item.
     * Lớp con bắt buộc override.
     */
    public abstract void input();

    /**
     * Trả về tên loại (Vase/Statue/Painting) để lọc theo type.
     */
    public String getType() {
        return this.getClass().getSimpleName(); // "Vase", "Statue", "Painting"
    }

    @Override
    public String toString() {
        return String.format("Type=%s | id=%s | value=%s | creator=%s",
                getType(), id, value, creator);
    }
}
