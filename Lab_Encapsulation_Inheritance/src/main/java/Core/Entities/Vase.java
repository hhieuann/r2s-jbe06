package Core.Entities;

import Utilities.DataInput;

public class Vase extends Item {
    private int height;
    private String material;

    public Vase() {
        super();
    }

    public Vase(String id, String value, String creator, int height, String material) {
        super(id, value, creator);
        this.height = height;
        this.material = material;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    @Override
    public void input() {
        // Nhập phần chung (Item)
        setId(DataInput.readNonEmptyString("Nhap id: "));
        setValue(DataInput.readNonEmptyString("Nhap value: "));
        setCreator(DataInput.readNonEmptyString("Nhap creator: "));

        // Nhập phần riêng (Vase)
        height = DataInput.readInt("Nhap height (so nguyen): ", 1, Integer.MAX_VALUE);
        material = DataInput.readNonEmptyString("Nhap material: ");
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | height=%d | material=%s", height, material);
    }
}
