package az.efiqa.inventory_service.entity;

import az.efiqa.inventory_service.enums.ROLES;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;
    @Column(nullable = false)
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ROLES getRoles() {
        return roles;
    }

    public void setRoles(ROLES roles) {
        this.roles = roles;
    }
}
