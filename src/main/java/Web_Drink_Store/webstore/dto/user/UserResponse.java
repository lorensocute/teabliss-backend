package Web_Drink_Store.webstore.dto.user;

import Web_Drink_Store.webstore.enums.Role;
import Web_Drink_Store.webstore.enums.UserStatus;

public class UserResponse {
    private Long id; private String fullName; private String email; private String phone; private Role role; private UserStatus status;
    public UserResponse(Long id, String fullName, String email, String phone, Role role, UserStatus status) {
        this.id=id; this.fullName=fullName; this.email=email; this.phone=phone; this.role=role; this.status=status;
    }
    public Long getId(){return id;} public String getFullName(){return fullName;} public String getEmail(){return email;}
    public String getPhone(){return phone;} public Role getRole(){return role;} public UserStatus getStatus(){return status;}
}
