package Web_Drink_Store.webstore.service.impl;
import Web_Drink_Store.webstore.dto.auth.*; import Web_Drink_Store.webstore.entity.*; import Web_Drink_Store.webstore.enums.*; import Web_Drink_Store.webstore.exception.*; import Web_Drink_Store.webstore.repository.*; import Web_Drink_Store.webstore.service.AuthService;
import org.springframework.security.crypto.password.PasswordEncoder; import org.springframework.stereotype.Service; import org.springframework.transaction.annotation.Transactional;
@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository; private final CartRepository cartRepository; private final PasswordEncoder passwordEncoder;
    public AuthServiceImpl(UserRepository u,CartRepository c,PasswordEncoder p){userRepository=u;cartRepository=c;passwordEncoder=p;}
    @Override @Transactional public AuthResponse register(RegisterRequest r){
        if(r.getFullName()==null||r.getFullName().isBlank()||r.getEmail()==null||r.getEmail().isBlank()||r.getPassword()==null||r.getPassword().length()<8) throw new BadRequestException("Dữ liệu đăng ký không hợp lệ");
        String email=r.getEmail().trim().toLowerCase(); if(userRepository.existsByEmail(email)) throw new BadRequestException("Email đã tồn tại");
        User u=new User(); u.setFullName(r.getFullName().trim()); u.setEmail(email); u.setPhone(r.getPhone()); u.setPassword(passwordEncoder.encode(r.getPassword())); u.setRole(Role.CUSTOMER); u.setStatus(UserStatus.ACTIVE); userRepository.save(u);
        Cart cart=new Cart(); cart.setUser(u); cartRepository.save(cart);
        return new AuthResponse(u.getId(),u.getFullName(),u.getEmail(),u.getRole());
    }
    @Override public AuthResponse login(LoginRequest r){
        User u=userRepository.findByEmail(r.getEmail().trim().toLowerCase()).orElseThrow(() -> new BadRequestException("Email hoặc mật khẩu không đúng"));
        if(u.getStatus()!=UserStatus.ACTIVE || !passwordEncoder.matches(r.getPassword(),u.getPassword())) throw new BadRequestException("Email hoặc mật khẩu không đúng");
        return new AuthResponse(u.getId(),u.getFullName(),u.getEmail(),u.getRole());
    }
}
