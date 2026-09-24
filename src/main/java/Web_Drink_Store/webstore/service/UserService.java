package Web_Drink_Store.webstore.service;

import Web_Drink_Store.webstore.dto.user.*;

import java.util.List;

public interface UserService {

    UserResponse getProfile(Long userId);

    UserResponse updateProfile(
            Long userId,
            UpdateProfileRequest request
    );

    void changePassword(
            Long userId,
            ChangePasswordRequest request
    );

    // ADMIN
    List<UserResponse> getAllUsers();

    UserResponse getUserById(Long id);

    UserResponse toggleUserStatus(Long id);
}