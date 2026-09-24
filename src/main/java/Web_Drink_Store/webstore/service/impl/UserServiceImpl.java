package Web_Drink_Store.webstore.service.impl;

import Web_Drink_Store.webstore.dto.user.ChangePasswordRequest;
import Web_Drink_Store.webstore.dto.user.UpdateProfileRequest;
import Web_Drink_Store.webstore.dto.user.UserResponse;
import Web_Drink_Store.webstore.entity.User;
import Web_Drink_Store.webstore.enums.UserStatus;
import Web_Drink_Store.webstore.exception.BadRequestException;
import Web_Drink_Store.webstore.exception.ResourceNotFoundException;
import Web_Drink_Store.webstore.repository.UserRepository;
import Web_Drink_Store.webstore.service.UserService;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // =========================
    // USER - XEM PROFILE
    // =========================
    @Override
    @Transactional(readOnly = true)
    public UserResponse getProfile(Long userId) {

        return mapToResponse(
                getUser(userId)
        );
    }

    // =========================
    // USER - CẬP NHẬT PROFILE
    // =========================
    @Override
    @Transactional
    public UserResponse updateProfile(
            Long userId,
            UpdateProfileRequest request
    ) {

        User user = getUser(userId);

        if (request.getFullName() != null
                && !request.getFullName().isBlank()) {

            user.setFullName(
                    request.getFullName().trim()
            );
        }

        if (request.getPhone() != null) {

            user.setPhone(
                    request.getPhone().trim()
            );
        }

        return mapToResponse(
                userRepository.save(user)
        );
    }

    // =========================
    // USER - ĐỔI MẬT KHẨU
    // =========================
    @Override
    @Transactional
    public void changePassword(
            Long userId,
            ChangePasswordRequest request
    ) {

        User user = getUser(userId);

        if (request.getOldPassword() == null
                || request.getNewPassword() == null) {

            throw new BadRequestException(
                    "Mật khẩu không được để trống"
            );
        }

        if (!passwordEncoder.matches(
                request.getOldPassword(),
                user.getPassword()
        )) {

            throw new BadRequestException(
                    "Mật khẩu hiện tại không đúng"
            );
        }

        if (request.getNewPassword().length() < 8) {

            throw new BadRequestException(
                    "Mật khẩu mới phải có ít nhất 8 ký tự"
            );
        }

        user.setPassword(
                passwordEncoder.encode(
                        request.getNewPassword()
                )
        );

        userRepository.save(user);
    }

    // =========================
    // ADMIN - DANH SÁCH USER
    // =========================
    @Override
    @Transactional(readOnly = true)
    public List<UserResponse> getAllUsers() {

        return userRepository
                .findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // =========================
    // ADMIN - CHI TIẾT USER
    // =========================
    @Override
    @Transactional(readOnly = true)
    public UserResponse getUserById(
            Long id
    ) {

        User user = getUser(id);

        return mapToResponse(user);
    }

    // =========================
    // ADMIN - KHÓA / MỞ KHÓA USER
    // =========================
    @Override
    @Transactional
    public UserResponse toggleUserStatus(
            Long id
    ) {

        User user = getUser(id);

        if (user.getStatus()
                == UserStatus.ACTIVE) {

            user.setStatus(
                    UserStatus.INACTIVE
            );

        } else {

            user.setStatus(
                    UserStatus.ACTIVE
            );
        }

        return mapToResponse(
                userRepository.save(user)
        );
    }

    // =========================
    // TÌM USER
    // =========================
    private User getUser(
            Long userId
    ) {

        return userRepository
                .findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Không tìm thấy người dùng"
                        )
                );
    }

    // =========================
    // ENTITY -> RESPONSE DTO
    // =========================
    private UserResponse mapToResponse(
            User user
    ) {

        return new UserResponse(
                user.getId(),
                user.getFullName(),
                user.getEmail(),
                user.getPhone(),
                user.getRole(),
                user.getStatus()
        );
    }
}