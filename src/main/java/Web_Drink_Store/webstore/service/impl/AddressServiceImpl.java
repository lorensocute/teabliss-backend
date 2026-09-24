package Web_Drink_Store.webstore.service.impl;

import Web_Drink_Store.webstore.dto.address.AddressRequest;
import Web_Drink_Store.webstore.dto.address.AddressResponse;
import Web_Drink_Store.webstore.entity.Address;
import Web_Drink_Store.webstore.entity.User;
import Web_Drink_Store.webstore.exception.BadRequestException;
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
        validateRequest(request);

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Không tìm thấy người dùng"
                        )
                );

        // Nếu địa chỉ mới được chọn làm mặc định
        // thì bỏ mặc định của các địa chỉ cũ
        if (request.isDefaultAddress()) {
            clearDefaultAddress(userId);
        }

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
        validateRequest(request);

        // Chỉ lấy địa chỉ thuộc user hiện tại
        Address address = getOwnedAddress(userId, id);

        // Nếu chọn địa chỉ này làm mặc định
        // thì bỏ mặc định của các địa chỉ khác
        if (request.isDefaultAddress()) {
            clearDefaultAddress(userId);
        }

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

    private Address getOwnedAddress(
            Long userId,
            Long addressId
    ) {
        return addressRepository
                .findByIdAndUserId(addressId, userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Không tìm thấy địa chỉ"
                        )
                );
    }

    private void clearDefaultAddress(Long userId) {
        List<Address> defaultAddresses =
                addressRepository
                        .findByUserIdAndDefaultAddressTrue(userId);

        for (Address address : defaultAddresses) {
            address.setDefaultAddress(false);
        }

        addressRepository.saveAll(defaultAddresses);
    }

    private void validateRequest(AddressRequest request) {

        if (request == null) {
            throw new BadRequestException(
                    "Thông tin địa chỉ không được để trống"
            );
        }

        if (request.getReceiverName() == null
                || request.getReceiverName().isBlank()) {
            throw new BadRequestException(
                    "Tên người nhận không được để trống"
            );
        }

        if (request.getPhone() == null
                || request.getPhone().isBlank()) {
            throw new BadRequestException(
                    "Số điện thoại không được để trống"
            );
        }

        if (request.getAddressDetail() == null
                || request.getAddressDetail().isBlank()) {
            throw new BadRequestException(
                    "Địa chỉ chi tiết không được để trống"
            );
        }
    }

    private void updateFields(
            Address address,
            AddressRequest request
    ) {
        address.setReceiverName(
                request.getReceiverName().trim()
        );

        address.setPhone(
                request.getPhone().trim()
        );

        address.setAddressDetail(
                request.getAddressDetail().trim()
        );

        address.setWard(
                trimToNull(request.getWard())
        );

        address.setDistrict(
                trimToNull(request.getDistrict())
        );

        address.setCity(
                trimToNull(request.getCity())
        );

        address.setDefaultAddress(
                request.isDefaultAddress()
        );
    }

    private String trimToNull(String value) {
        if (value == null) {
            return null;
        }

        String trimmed = value.trim();

        return trimmed.isEmpty()
                ? null
                : trimmed;
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