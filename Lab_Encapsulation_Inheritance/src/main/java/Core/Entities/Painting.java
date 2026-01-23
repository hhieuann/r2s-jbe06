package Core.Entities;

import Utilities.DataInput;

public class Painting extends Item {
    private int height;
    private int width;
    private boolean isWaterColor;
    private boolean isFramed;

    public Painting() {
        super();
    }

    public Painting(String id, String value, String creator, int height, int width, boolean isWaterColor, boolean isFramed) {
        super(id, value, creator);
        this.height = height;
        this.width = width;
        this.isWaterColor = isWaterColor;
        this.isFramed = isFramed;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public boolean isWaterColor() {
        return isWaterColor;
    }

    public boolean isFramed() {
        return isFramed;
    }

    @Override
    public void input() {
        setId(DataInput.readNonEmptyString("Nhap id: "));
        setValue(DataInput.readNonEmptyString("Nhap value: "));
        setCreator(DataInput.readNonEmptyString("Nhap creator: "));

        height = DataInput.readInt("Nhap height (so nguyen): ", 1, Integer.MAX_VALUE);
        width = DataInput.readInt("Nhap width (so nguyen): ", 1, Integer.MAX_VALUE);
        isWaterColor = DataInput.readBoolean("La watercolor? (Y/N): ");
        isFramed = DataInput.readBoolean("Co khung? (Y/N): ");
    }

    @Override
    public String toString() {
        return super.toString()
                + String.format(" | height=%d | width=%d | isWaterColor=%s | isFramed=%s",
                height, width, isWaterColor, isFramed);
    }
}
