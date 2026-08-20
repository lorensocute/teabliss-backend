package Web_Drink_Store.webstore.service;

import Web_Drink_Store.webstore.dto.UpdateProfileRequest;
import Web_Drink_Store.webstore.dto.UserResponse;

public interface UserService {
    UserResponse getProfile(String email);
    UserResponse updateProfile(String email, UpdateProfileRequest request);
}
