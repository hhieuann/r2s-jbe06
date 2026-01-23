package util;

public class Constants {
    public static final String BRAND_HEADER = String.format("%-10s | %-30s", "Brand ID", "Brand Name");
    public static final String BRAND_ROW_FORMAT = "%-10d | %-30s";

    public static final String CATEGORY_HEADER = String.format("%-12s | %-30s", "Category ID", "Category Name");
    public static final String CATEGORY_ROW_FORMAT = "%-12d | %-30s";

    public static final String CUSTOMER_HEADER = String.format(
            "%-12s | %-25s | %-8s | %-15s | %-25s",
            "Customer ID", "Name", "Gender", "Phone", "Email"
    );
    public static final String CUSTOMER_ROW_FORMAT = "%-12d | %-25s | %-8s | %-15s | %-25s";
}