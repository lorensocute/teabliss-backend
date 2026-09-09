package Web_Drink_Store.webstore.dto.order;
import Web_Drink_Store.webstore.enums.OrderStatus;
public class UpdateOrderStatusRequest {
    private OrderStatus status; public OrderStatus getStatus(){return status;} public void setStatus(OrderStatus v){status=v;}
}
