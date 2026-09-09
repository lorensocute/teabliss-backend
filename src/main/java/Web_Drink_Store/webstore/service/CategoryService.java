package Web_Drink_Store.webstore.service;
import Web_Drink_Store.webstore.dto.category.*; import java.util.List;
public interface CategoryService { List<CategoryResponse> getActive(); CategoryResponse create(CategoryRequest request); CategoryResponse update(Long id,CategoryRequest request); void deactivate(Long id); }
