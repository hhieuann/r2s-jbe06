package Presentation;

import BusinessObjects.ProductService;
import Core.Interfaces.IProductRepository;
import DataObjects.ProductRepositoryArray;
import Utilities.DataInput;

public class Program {
    public static void main(String[] args) {
        IProductRepository repo = new ProductRepositoryArray();
        DataInput input = new DataInput();
        ProductService service = new ProductService(repo, input);

        service.run();
    }
}
