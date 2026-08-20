package Web_Drink_Store.webstore.service;

import Web_Drink_Store.webstore.dto.AuthResponse;
import Web_Drink_Store.webstore.dto.LoginRequest;
import Web_Drink_Store.webstore.dto.RegisterRequest;

public interface AuthService {
    AuthResponse register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
}
