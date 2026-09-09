package Web_Drink_Store.webstore.service.impl;
import Web_Drink_Store.webstore.dto.address.*; import Web_Drink_Store.webstore.entity.*; import Web_Drink_Store.webstore.exception.*; import Web_Drink_Store.webstore.repository.*; import Web_Drink_Store.webstore.service.AddressService; import org.springframework.stereotype.Service; import java.util.*;
@Service
public class AddressServiceImpl implements AddressService {
    private final AddressRepository repo; private final UserRepository users; public AddressServiceImpl(AddressRepository r,UserRepository u){repo=r;users=u;}
    private AddressResponse map(Address a){return new AddressResponse(a.getId(),a.getReceiverName(),a.getPhone(),a.getAddressDetail(),a.getWard(),a.getDistrict(),a.getCity(),a.isDefaultAddress());}
    private Address owned(Long userId,Long id){Address a=repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy địa chỉ")); if(!a.getUser().getId().equals(userId)) throw new UnauthorizedException("Không có quyền với địa chỉ này"); return a;}
    public List<AddressResponse> getAll(Long userId){return repo.findByUserId(userId).stream().map(this::map).toList();}
    public AddressResponse create(Long userId,AddressRequest r){User u=users.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy user")); Address a=new Address(); a.setUser(u); fill(a,r); return map(repo.save(a));}
    public AddressResponse update(Long userId,Long id,AddressRequest r){Address a=owned(userId,id); fill(a,r); return map(repo.save(a));}
    public void delete(Long userId,Long id){repo.delete(owned(userId,id));}
    private void fill(Address a,AddressRequest r){a.setReceiverName(r.getReceiverName());a.setPhone(r.getPhone());a.setAddressDetail(r.getAddressDetail());a.setWard(r.getWard());a.setDistrict(r.getDistrict());a.setCity(r.getCity());a.setDefaultAddress(r.isDefaultAddress());}
}
