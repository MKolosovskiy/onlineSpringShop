package dao;

import model.Product;
import java.util.List;
import java.util.Optional;

public interface ProductDao {

    List<Product> getAll();
    void addProduct(Product product);
    Optional<Product> getProductById(Long id);
    void updateProduct(Product product);
    void remove(Product product);
}
