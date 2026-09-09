package Web_Drink_Store.webstore.dto.promotion;
import Web_Drink_Store.webstore.enums.DiscountType;
import java.math.BigDecimal; import java.time.LocalDateTime;
public class PromotionRequest {
    private String code; private String name; private DiscountType discountType; private BigDecimal discountValue; private BigDecimal minOrderValue; private BigDecimal maxDiscountValue; private LocalDateTime startAt; private LocalDateTime endAt;
    public String getCode(){return code;} public void setCode(String v){code=v;} public String getName(){return name;} public void setName(String v){name=v;} public DiscountType getDiscountType(){return discountType;} public void setDiscountType(DiscountType v){discountType=v;} public BigDecimal getDiscountValue(){return discountValue;} public void setDiscountValue(BigDecimal v){discountValue=v;} public BigDecimal getMinOrderValue(){return minOrderValue;} public void setMinOrderValue(BigDecimal v){minOrderValue=v;} public BigDecimal getMaxDiscountValue(){return maxDiscountValue;} public void setMaxDiscountValue(BigDecimal v){maxDiscountValue=v;} public LocalDateTime getStartAt(){return startAt;} public void setStartAt(LocalDateTime v){startAt=v;} public LocalDateTime getEndAt(){return endAt;} public void setEndAt(LocalDateTime v){endAt=v;}
}
