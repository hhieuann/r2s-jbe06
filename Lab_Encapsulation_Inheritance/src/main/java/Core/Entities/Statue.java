package Core.Entities;

import Utilities.DataInput;

public class Statue extends Item {
    private int weight;
    private String color;

    public Statue() {
        super();
    }

    public Statue(String id, String value, String creator, int weight, String color) {
        super(id, value, creator);
        this.weight = weight;
        this.color = color;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public void input() {
        setId(DataInput.readNonEmptyString("Nhap id: "));
        setValue(DataInput.readNonEmptyString("Nhap value: "));
        setCreator(DataInput.readNonEmptyString("Nhap creator: "));

        weight = DataInput.readInt("Nhap weight (so nguyen): ", 1, Integer.MAX_VALUE);
        color = DataInput.readNonEmptyString("Nhap color: ");
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | weight=%d | color=%s", weight, color);
    }
}
