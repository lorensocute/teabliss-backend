package Web_Drink_Store.webstore.dto.address;

public class AddressResponse {
    private Long id; private String receiverName; private String phone; private String addressDetail; private String ward; private String district; private String city; private boolean defaultAddress;
    public AddressResponse(Long id,String receiverName,String phone,String addressDetail,String ward,String district,String city,boolean defaultAddress){this.id=id;this.receiverName=receiverName;this.phone=phone;this.addressDetail=addressDetail;this.ward=ward;this.district=district;this.city=city;this.defaultAddress=defaultAddress;}
    public Long getId(){return id;} public String getReceiverName(){return receiverName;} public String getPhone(){return phone;} public String getAddressDetail(){return addressDetail;}
    public String getWard(){return ward;} public String getDistrict(){return district;} public String getCity(){return city;} public boolean isDefaultAddress(){return defaultAddress;}
}
