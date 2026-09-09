package Web_Drink_Store.webstore.dto.promotion;
import Web_Drink_Store.webstore.enums.DiscountType; import Web_Drink_Store.webstore.enums.PromotionStatus;
import java.math.BigDecimal; import java.time.LocalDateTime;
public class PromotionResponse {
    private Long id; private String code; private String name; private DiscountType discountType; private BigDecimal discountValue; private BigDecimal minOrderValue; private BigDecimal maxDiscountValue; private LocalDateTime startAt; private LocalDateTime endAt; private PromotionStatus status;
    public PromotionResponse(Long id,String code,String name,DiscountType discountType,BigDecimal discountValue,BigDecimal minOrderValue,BigDecimal maxDiscountValue,LocalDateTime startAt,LocalDateTime endAt,PromotionStatus status){this.id=id;this.code=code;this.name=name;this.discountType=discountType;this.discountValue=discountValue;this.minOrderValue=minOrderValue;this.maxDiscountValue=maxDiscountValue;this.startAt=startAt;this.endAt=endAt;this.status=status;}
    public Long getId(){return id;} public String getCode(){return code;} public String getName(){return name;} public DiscountType getDiscountType(){return discountType;} public BigDecimal getDiscountValue(){return discountValue;} public BigDecimal getMinOrderValue(){return minOrderValue;} public BigDecimal getMaxDiscountValue(){return maxDiscountValue;} public LocalDateTime getStartAt(){return startAt;} public LocalDateTime getEndAt(){return endAt;} public PromotionStatus getStatus(){return status;}
}
