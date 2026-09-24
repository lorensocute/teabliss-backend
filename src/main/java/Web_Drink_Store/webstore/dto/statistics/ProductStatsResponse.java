package Web_Drink_Store.webstore.dto.statistics;

public class ProductStatsResponse {

    private Long productId;
    private String productName;
    private Long totalQuantitySold;

    public ProductStatsResponse(
            Long productId,
            String productName,
            Long totalQuantitySold
    ) {
        this.productId = productId;
        this.productName = productName;
        this.totalQuantitySold = totalQuantitySold;
    }

    public Long getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public Long getTotalQuantitySold() {
        return totalQuantitySold;
    }
}