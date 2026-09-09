package Web_Drink_Store.webstore.service.impl;
import Web_Drink_Store.webstore.dto.user.*; import Web_Drink_Store.webstore.entity.User; import Web_Drink_Store.webstore.exception.ResourceNotFoundException; import Web_Drink_Store.webstore.repository.UserRepository; import Web_Drink_Store.webstore.service.UserService; import org.springframework.stereotype.Service;
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repo; public UserServiceImpl(UserRepository repo){this.repo=repo;}
    private User get(Long id){return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy user"));}
    private UserResponse map(User u){return new UserResponse(u.getId(),u.getFullName(),u.getEmail(),u.getPhone(),u.getRole(),u.getStatus());}
    public UserResponse getProfile(Long id){return map(get(id));}
    public UserResponse updateProfile(Long id,UpdateProfileRequest r){User u=get(id); if(r.getFullName()!=null&&!r.getFullName().isBlank())u.setFullName(r.getFullName().trim()); if(r.getPhone()!=null)u.setPhone(r.getPhone().trim()); return map(repo.save(u));}
}
