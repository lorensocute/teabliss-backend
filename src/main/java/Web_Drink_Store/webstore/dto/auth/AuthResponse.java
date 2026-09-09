package Web_Drink_Store.webstore.dto.auth;

import Web_Drink_Store.webstore.enums.Role;

public class AuthResponse {
    private Long id;
    private String fullName;
    private String email;
    private Role role;
    public AuthResponse(Long id, String fullName, String email, Role role) {
        this.id = id; this.fullName = fullName; this.email = email; this.role = role;
    }
    public Long getId() { return id; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public Role getRole() { return role; }
}
