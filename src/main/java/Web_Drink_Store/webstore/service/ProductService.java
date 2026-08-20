package Web_Drink_Store.webstore.service;

import Web_Drink_Store.webstore.dto.ProductRequest;
import Web_Drink_Store.webstore.dto.ProductResponse;
import java.util.List;

public interface ProductService {
    List<ProductResponse> getAll(Long categoryId, String tag);
    ProductResponse getById(Long id);
    ProductResponse create(ProductRequest request);
    ProductResponse update(Long id, ProductRequest request);
    void delete(Long id);
}
