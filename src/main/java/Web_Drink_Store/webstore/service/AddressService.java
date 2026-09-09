package Web_Drink_Store.webstore.service;
import Web_Drink_Store.webstore.dto.address.*; import java.util.List;
public interface AddressService { List<AddressResponse> getAll(Long userId); AddressResponse create(Long userId,AddressRequest request); AddressResponse update(Long userId,Long addressId,AddressRequest request); void delete(Long userId,Long addressId); }
