package Web_Drink_Store.webstore.service.impl;
import Web_Drink_Store.webstore.dto.promotion.*; import Web_Drink_Store.webstore.entity.Promotion; import Web_Drink_Store.webstore.enums.PromotionStatus; import Web_Drink_Store.webstore.exception.*; import Web_Drink_Store.webstore.repository.PromotionRepository; import Web_Drink_Store.webstore.service.PromotionService; import org.springframework.stereotype.Service; import java.util.*;
@Service
public class PromotionServiceImpl implements PromotionService {
    private final PromotionRepository repo; public PromotionServiceImpl(PromotionRepository repo){this.repo=repo;}
    private Promotion get(Long id){return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy promotion"));}
    private PromotionResponse map(Promotion p){return new PromotionResponse(p.getId(),p.getCode(),p.getName(),p.getDiscountType(),p.getDiscountValue(),p.getMinOrderValue(),p.getMaxDiscountValue(),p.getStartAt(),p.getEndAt(),p.getStatus());}
    public List<PromotionResponse> getAll(){return repo.findAll().stream().map(this::map).toList();}
    public PromotionResponse create(PromotionRequest r){if(repo.existsByCode(r.getCode()))throw new BadRequestException("Mã khuyến mãi đã tồn tại");Promotion p=new Promotion();fill(p,r);p.setStatus(PromotionStatus.ACTIVE);return map(repo.save(p));}
    public PromotionResponse update(Long id,PromotionRequest r){Promotion p=get(id);fill(p,r);return map(repo.save(p));}
    public void deactivate(Long id){Promotion p=get(id);p.setStatus(PromotionStatus.INACTIVE);repo.save(p);}
    private void fill(Promotion p,PromotionRequest r){p.setCode(r.getCode());p.setName(r.getName());p.setDiscountType(r.getDiscountType());p.setDiscountValue(r.getDiscountValue());p.setMinOrderValue(r.getMinOrderValue());p.setMaxDiscountValue(r.getMaxDiscountValue());p.setStartAt(r.getStartAt());p.setEndAt(r.getEndAt());}
}
