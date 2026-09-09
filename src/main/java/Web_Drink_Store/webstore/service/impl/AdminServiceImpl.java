package Web_Drink_Store.webstore.service.impl;
import Web_Drink_Store.webstore.dto.statistics.AdminStatsResponse; import Web_Drink_Store.webstore.enums.OrderStatus; import Web_Drink_Store.webstore.repository.*; import Web_Drink_Store.webstore.service.AdminService; import org.springframework.stereotype.Service; import java.math.BigDecimal;
@Service
public class AdminServiceImpl implements AdminService {
    private final UserRepository users; private final ProductRepository products; private final OrderRepository orders;
    public AdminServiceImpl(UserRepository u,ProductRepository p,OrderRepository o){users=u;products=p;orders=o;}
    public AdminStatsResponse getStats(){BigDecimal revenue=orders.sumTotalByStatus(OrderStatus.COMPLETED);return new AdminStatsResponse(users.count(),products.count(),orders.count(),revenue==null?BigDecimal.ZERO:revenue);}
}
