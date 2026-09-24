package Web_Drink_Store.webstore.service;

import Web_Drink_Store.webstore.dto.statistics.AdminStatsResponse;
import Web_Drink_Store.webstore.dto.statistics.OrderStatsResponse;
import Web_Drink_Store.webstore.dto.statistics.ProductStatsResponse;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface AdminService {

    AdminStatsResponse getStatistics();

    BigDecimal getRevenue(
            LocalDate from,
            LocalDate to
    );

    OrderStatsResponse getOrderStatistics();

    List<ProductStatsResponse> getProductStatistics();
}