package Web_Drink_Store.webstore.service;
import Web_Drink_Store.webstore.dto.promotion.*; import java.util.List;
public interface PromotionService { List<PromotionResponse> getAll(); PromotionResponse create(PromotionRequest request); PromotionResponse update(Long id,PromotionRequest request); void deactivate(Long id); }
