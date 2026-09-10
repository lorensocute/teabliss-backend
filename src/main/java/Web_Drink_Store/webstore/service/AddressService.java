package Web_Drink_Store.webstore.service;

import Web_Drink_Store.webstore.dto.address.AddressRequest;
import Web_Drink_Store.webstore.dto.address.AddressResponse;

import java.util.List;

public interface AddressService {

    List<AddressResponse> getAll(Long userId);

    AddressResponse create(Long userId, AddressRequest request);

    AddressResponse update(Long userId, Long id, AddressRequest request);

    void delete(Long userId, Long id);
}