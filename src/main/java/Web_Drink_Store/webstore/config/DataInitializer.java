package Web_Drink_Store.webstore.config;

import Web_Drink_Store.webstore.entity.Role;
import Web_Drink_Store.webstore.entity.User;
import Web_Drink_Store.webstore.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (!userRepository.existsByEmail("admin@teabliss.vn")) {
            User admin = User.builder()
                    .fullName("Admin")
                    .email("admin@teabliss.vn")
                    .password(passwordEncoder.encode("admin123"))
                    .role(Role.ADMIN)
                    .build();
            userRepository.save(admin);
            System.out.println(">>> Da tao tai khoan admin: admin@teabliss.vn / matkhau: admin123");
        }
    }
}
