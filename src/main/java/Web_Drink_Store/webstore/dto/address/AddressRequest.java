package Web_Drink_Store.webstore.dto.address;

public class AddressRequest {
    private String receiverName; private String phone; private String addressDetail; private String ward; private String district; private String city; private boolean defaultAddress;
    public String getReceiverName(){return receiverName;} public void setReceiverName(String v){receiverName=v;}
    public String getPhone(){return phone;} public void setPhone(String v){phone=v;}
    public String getAddressDetail(){return addressDetail;} public void setAddressDetail(String v){addressDetail=v;}
    public String getWard(){return ward;} public void setWard(String v){ward=v;}
    public String getDistrict(){return district;} public void setDistrict(String v){district=v;}
    public String getCity(){return city;} public void setCity(String v){city=v;}
    public boolean isDefaultAddress(){return defaultAddress;} public void setDefaultAddress(boolean v){defaultAddress=v;}
}
