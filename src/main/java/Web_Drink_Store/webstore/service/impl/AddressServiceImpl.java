package Web_Drink_Store.webstore.service.impl;

import Web_Drink_Store.webstore.dto.address.AddressRequest;
import Web_Drink_Store.webstore.dto.address.AddressResponse;
import Web_Drink_Store.webstore.entity.Address;
import Web_Drink_Store.webstore.entity.User;
import Web_Drink_Store.webstore.exception.ResourceNotFoundException;
import Web_Drink_Store.webstore.repository.AddressRepository;
import Web_Drink_Store.webstore.repository.UserRepository;
import Web_Drink_Store.webstore.service.AddressService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final UserRepository userRepository;

    public AddressServiceImpl(
            AddressRepository addressRepository,
            UserRepository userRepository
    ) {
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<AddressResponse> getAll(Long userId) {
        return addressRepository.findByUserId(userId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    @Transactional
    public AddressResponse create(
            Long userId,
            AddressRequest request
    ) {
        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Không tìm thấy người dùng"
                        )
                );

        Address address = new Address();
        address.setUser(user);

        updateFields(address, request);

        Address savedAddress = addressRepository.save(address);

        return mapToResponse(savedAddress);
    }

    @Override
    @Transactional
    public AddressResponse update(
            Long userId,
            Long id,
            AddressRequest request
    ) {
        Address address = getOwnedAddress(userId, id);

        updateFields(address, request);

        Address savedAddress = addressRepository.save(address);

        return mapToResponse(savedAddress);
    }

    @Override
    @Transactional
    public void delete(Long userId, Long id) {
        Address address = getOwnedAddress(userId, id);

        addressRepository.delete(address);
    }

    private Address getOwnedAddress(Long userId, Long addressId) {
        return addressRepository
                .findByIdAndUserId(addressId, userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Không tìm thấy địa chỉ"
                        )
                );
    }

    private void updateFields(
            Address address,
            AddressRequest request
    ) {
        address.setReceiverName(request.getReceiverName());
        address.setPhone(request.getPhone());
        address.setAddressDetail(request.getAddressDetail());
        address.setWard(request.getWard());
        address.setDistrict(request.getDistrict());
        address.setCity(request.getCity());
        address.setDefaultAddress(request.isDefaultAddress());
    }

    private AddressResponse mapToResponse(Address address) {
        return new AddressResponse(
                address.getId(),
                address.getReceiverName(),
                address.getPhone(),
                address.getAddressDetail(),
                address.getWard(),
                address.getDistrict(),
                address.getCity(),
                address.isDefaultAddress()
        );
    }
}