package Web_Drink_Store.webstore.service.impl;
import Web_Drink_Store.webstore.dto.product.*; import Web_Drink_Store.webstore.entity.*; import Web_Drink_Store.webstore.enums.ProductStatus; import Web_Drink_Store.webstore.exception.*; import Web_Drink_Store.webstore.repository.*; import Web_Drink_Store.webstore.service.ProductService; import org.springframework.stereotype.Service; import java.util.*;
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository repo; private final CategoryRepository categories; public ProductServiceImpl(ProductRepository r,CategoryRepository c){repo=r;categories=c;}
    private Product get(Long id){return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy product"));}
    private ProductResponse map(Product p){return new ProductResponse(p.getId(),p.getName(),p.getDescription(),p.getImageUrl(),p.getPrice(),p.getStockQuantity(),p.getCategory().getId(),p.getCategory().getName(),p.getStatus());}
    public List<ProductResponse> getActive(Long categoryId){List<Product> list=categoryId==null?repo.findByStatus(ProductStatus.ACTIVE):repo.findByCategoryIdAndStatus(categoryId,ProductStatus.ACTIVE); return list.stream().map(this::map).toList();}
    public ProductResponse getById(Long id){return map(get(id));}
    public ProductResponse create(ProductRequest r){Product p=new Product(); fill(p,r); p.setStatus(ProductStatus.ACTIVE); return map(repo.save(p));}
    public ProductResponse update(Long id,ProductRequest r){Product p=get(id); fill(p,r); return map(repo.save(p));}
    public void deactivate(Long id){Product p=get(id); p.setStatus(ProductStatus.INACTIVE); repo.save(p);}
    private void fill(Product p,ProductRequest r){if(r.getPrice()==null||r.getPrice().signum()<0||r.getStockQuantity()==null||r.getStockQuantity()<0)throw new BadRequestException("Giá hoặc tồn kho không hợp lệ"); Category c=categories.findById(r.getCategoryId()).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy category")); p.setName(r.getName());p.setDescription(r.getDescription());p.setImageUrl(r.getImageUrl());p.setPrice(r.getPrice());p.setStockQuantity(r.getStockQuantity());p.setCategory(c);}
}
