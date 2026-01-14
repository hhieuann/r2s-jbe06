package Core.Interfaces;

import Core.Entities.Product;

public interface IProductRepository {
    boolean add(Product p);
    Product[] getAll();          // trả về mảng snapshot các phần tử đang có
    int size();                  // số lượng hiện tại
    boolean isFull();

    Product findById(int id);
    boolean update(Product p);   // update theo id của p
    boolean remove(int id);
}
