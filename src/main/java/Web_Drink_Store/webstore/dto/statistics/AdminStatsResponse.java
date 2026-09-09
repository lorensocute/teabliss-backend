package Web_Drink_Store.webstore.dto.statistics;
import java.math.BigDecimal;
public class AdminStatsResponse {
    private long totalUsers; private long totalProducts; private long totalOrders; private BigDecimal completedRevenue;
    public AdminStatsResponse(long totalUsers,long totalProducts,long totalOrders,BigDecimal completedRevenue){this.totalUsers=totalUsers;this.totalProducts=totalProducts;this.totalOrders=totalOrders;this.completedRevenue=completedRevenue;}
    public long getTotalUsers(){return totalUsers;} public long getTotalProducts(){return totalProducts;} public long getTotalOrders(){return totalOrders;} public BigDecimal getCompletedRevenue(){return completedRevenue;}
}
