package Web_Drink_Store.webstore.service;
import Web_Drink_Store.webstore.dto.user.*;
public interface UserService { UserResponse getProfile(Long userId); UserResponse updateProfile(Long userId, UpdateProfileRequest request); }
