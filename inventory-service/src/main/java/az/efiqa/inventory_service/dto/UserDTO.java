package az.efiqa.inventory_service.dto;

import az.efiqa.inventory_service.enums.ROLES;

public class UserDTO {
    private Long id;
    private String username;
    private String password;
    private ROLES roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ROLES getRoles() {
        return roles;
    }

    public void setRoles(ROLES roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
