package Web_Drink_Store.webstore.service;

import Web_Drink_Store.webstore.dto.CategoryRequest;
import Web_Drink_Store.webstore.dto.CategoryResponse;
import java.util.List;

public interface CategoryService {
    List<CategoryResponse> getAll();
    CategoryResponse getById(Long id);
    CategoryResponse create(CategoryRequest request);
    CategoryResponse update(Long id, CategoryRequest request);
    void delete(Long id);
}
