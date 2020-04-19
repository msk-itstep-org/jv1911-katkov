package org.itstep.msk.app.entity;

import org.itstep.msk.app.enums.Role;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "users")
public class User {
    @Column(columnDefinition = "int unsigned")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    @NotEmpty(message = "Имя пользователя не должно быть пустым")
    @Size(min = 2, max = 25, message = "Имя пользователя не должно быть меньше 2 знаков и больше 25")
    private String username;

    @Column (length = 100)
    @NotEmpty(message = "email не должен быть пустым")
    @Email
    private String email;

    @Column
    @NotEmpty(message = "Пароль не должен быть пустым")
    private String password;

    @Column(name = "active", nullable = false, columnDefinition = "BIT")
    private Boolean active = true;

    @ElementCollection(targetClass = Role.class)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Set<Role> roles = new HashSet<>();

    @OneToMany(targetEntity = Order.class, mappedBy = "id")
    private List<Order> orders;

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public Set<String> getStringRoles() {
        return roles.stream()
                .map(Enum::toString)
                .collect(Collectors.toSet());
    }

    public void setStringRoles(Set<String> stringRoles) {
        roles.clear();
        for (String stringRole : stringRoles) {
            roles.add(Role.valueOf(stringRole));
        }
    }
}
