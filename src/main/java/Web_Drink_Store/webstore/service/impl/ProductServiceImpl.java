package Web_Drink_Store.webstore.service.impl;

import Web_Drink_Store.webstore.dto.ProductRequest;
import Web_Drink_Store.webstore.dto.ProductResponse;
import Web_Drink_Store.webstore.entity.Category;
import Web_Drink_Store.webstore.entity.Product;
import Web_Drink_Store.webstore.entity.ProductTag;
import Web_Drink_Store.webstore.exception.ResourceNotFoundException;
import Web_Drink_Store.webstore.repository.CategoryRepository;
import Web_Drink_Store.webstore.repository.ProductRepository;
import Web_Drink_Store.webstore.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public List<ProductResponse> getAll(Long categoryId, String tag) {
        List<Product> products;
        if (categoryId != null) {
            products = productRepository.findByCategoryId(categoryId);
        } else if (tag != null && !tag.isBlank()) {
            products = productRepository.findByTag(ProductTag.valueOf(tag.toUpperCase()));
        } else {
            products = productRepository.findAll();
        }
        return products.stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Override
    public ProductResponse getById(Long id) {
        return toResponse(findEntity(id));
    }

    @Override
    public ProductResponse create(ProductRequest request) {
        Category category = null;
        if (request.getCategoryId() != null) {
            category = categoryRepository.findById(request.getCategoryId())
                    .orElseThrow(() -> new ResourceNotFoundException("Khong tim thay danh muc"));
        }

        Product product = Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .imageUrl(request.getImageUrl())
                .category(category)
                .tag(request.getTag() != null ? ProductTag.valueOf(request.getTag().toUpperCase()) : ProductTag.NONE)
                .build();

        productRepository.save(product);
        return toResponse(product);
    }

    @Override
    public ProductResponse update(Long id, ProductRequest request) {
        Product product = findEntity(id);
        Category category = null;
        if (request.getCategoryId() != null) {
            category = categoryRepository.findById(request.getCategoryId())
                    .orElseThrow(() -> new ResourceNotFoundException("Khong tim thay danh muc"));
        }

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setImageUrl(request.getImageUrl());
        product.setCategory(category);
        if (request.getTag() != null) {
            product.setTag(ProductTag.valueOf(request.getTag().toUpperCase()));
        }

        productRepository.save(product);
        return toResponse(product);
    }

    @Override
    public void delete(Long id) {
        productRepository.delete(findEntity(id));
    }

    private Product findEntity(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Khong tim thay san pham id=" + id));
    }

    private ProductResponse toResponse(Product p) {
        return ProductResponse.builder()
                .id(p.getId())
                .name(p.getName())
                .description(p.getDescription())
                .price(p.getPrice())
                .imageUrl(p.getImageUrl())
                .categoryId(p.getCategory() != null ? p.getCategory().getId() : null)
                .categoryName(p.getCategory() != null ? p.getCategory().getName() : null)
                .tag(p.getTag().name())
                .rating(p.getRating())
                .reviewCount(p.getReviewCount())
                .build();
    }
}
