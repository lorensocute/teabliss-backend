package Web_Drink_Store.webstore.service;
import Web_Drink_Store.webstore.dto.auth.*;
public interface AuthService { AuthResponse register(RegisterRequest request); AuthResponse login(LoginRequest request); }
