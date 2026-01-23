package DataObjects;

import Core.Entities.Product;
import Core.Interfaces.IProductRepository;

public class ProductRepositoryArray implements IProductRepository {
    private final byte MAX = 100;
    private final Product[] products;
    private byte numOfProduct;

    public ProductRepositoryArray() {
        products = new Product[MAX];
        numOfProduct = 0;
    }

    @Override
    public boolean isFull() {
        return numOfProduct >= MAX;
    }

    @Override
    public int size() {
        return numOfProduct;
    }

    @Override
    public boolean add(Product p) {
        if (p == null || isFull()) return false;
        products[numOfProduct] = p;
        numOfProduct++;
        return true;
    }

    @Override
    public Product[] getAll() {
        // Trả về mảng mới chỉ chứa phần tử đang có
        Product[] arr = new Product[numOfProduct];
        for (int i = 0; i < numOfProduct; i++) {
            arr[i] = products[i];
        }
        return arr;
    }

    @Override
    public Product findById(int id) {
        for (int i = 0; i < numOfProduct; i++) {
            if (products[i].getId() == id) return products[i];
        }
        return null;
    }

    @Override
    public boolean update(Product p) {
        if (p == null) return false;

        for (int i = 0; i < numOfProduct; i++) {
            if (products[i].getId() == p.getId()) {
                products[i] = p; // thay object mới (có thể khác type)
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean remove(int id) {
        for (int i = 0; i < numOfProduct; i++) {
            if (products[i].getId() == id) {
                // Dồn trái để xóa phần tử
                for (int j = i; j < numOfProduct - 1; j++) {
                    products[j] = products[j + 1];
                }
                products[numOfProduct - 1] = null;
                numOfProduct--;
                return true;
            }
        }
        return false;
    }
}
