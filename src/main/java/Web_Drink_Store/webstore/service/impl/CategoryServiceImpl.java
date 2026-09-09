package Web_Drink_Store.webstore.service.impl;
import Web_Drink_Store.webstore.dto.category.*; import Web_Drink_Store.webstore.entity.Category; import Web_Drink_Store.webstore.enums.CategoryStatus; import Web_Drink_Store.webstore.exception.*; import Web_Drink_Store.webstore.repository.CategoryRepository; import Web_Drink_Store.webstore.service.CategoryService; import org.springframework.stereotype.Service; import java.util.*;
@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository repo; public CategoryServiceImpl(CategoryRepository repo){this.repo=repo;}
    private Category get(Long id){return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy category"));}
    private CategoryResponse map(Category c){return new CategoryResponse(c.getId(),c.getName(),c.getDescription(),c.getStatus());}
    public List<CategoryResponse> getActive(){return repo.findByStatus(CategoryStatus.ACTIVE).stream().map(this::map).toList();}
    public CategoryResponse create(CategoryRequest r){if(repo.existsByName(r.getName()))throw new BadRequestException("Tên category đã tồn tại"); Category c=new Category(); c.setName(r.getName()); c.setDescription(r.getDescription()); c.setStatus(CategoryStatus.ACTIVE); return map(repo.save(c));}
    public CategoryResponse update(Long id,CategoryRequest r){Category c=get(id); c.setName(r.getName()); c.setDescription(r.getDescription()); return map(repo.save(c));}
    public void deactivate(Long id){Category c=get(id); c.setStatus(CategoryStatus.INACTIVE); repo.save(c);}
}
