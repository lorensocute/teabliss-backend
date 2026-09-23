package Web_Drink_Store.webstore.dto.order;

public class OrderRequest {

    private String receiverName;
    private String receiverPhone;
    private String shippingAddress;
    private String promotionCode;

    public String getReceiverName() {
        return receiverName;}
    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;}
    public String getReceiverPhone() {
        return receiverPhone;}
    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;}
    public String getShippingAddress() {
        return shippingAddress;}
    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;}
    public String getPromotionCode() {
        return promotionCode;}
    public void setPromotionCode(String promotionCode) {
        this.promotionCode = promotionCode;
    }
}