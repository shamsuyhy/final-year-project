package com.backend.app.model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity( name = "User")
@Table(
        name = "users"
)

public class User implements UserDetails {
    @Id
    @Column(
            name = "username",
            nullable = false,
            columnDefinition = "VARCHAR(25)"
    )
    private String userName;
    @Column(
            name="phone",
            nullable = false,
            columnDefinition = "CHAR(10)"
    )
    private String phone;
    @Column(
            name="email",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String email;
    @Column(
            name="role",
            nullable = false,
            columnDefinition = "VARCHAR(80)"
    )
    @Enumerated(EnumType.STRING)
    private Role role;
    @Column(
            name="password",
            nullable = false,
            columnDefinition = "CHAR(60)"
    )
    private String password;
    @OneToMany(
            mappedBy = "user"
    )
    private List<Order> orders=new ArrayList<>();

    public User(String userName, String phone, String email, Role role, String password) {
        this.userName = userName;
        this.phone = phone;
        this.email = email;
        this.role = role;
        this.password = password;
    }

    public User() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return null;
    }


    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userName, user.userName) && Objects.equals(phone, user.phone) && Objects.equals(email, user.email) && Objects.equals(role, user.role) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, phone, email, role, password);
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public void addOrder(Order order){
        if(!this.orders.contains(order)){
            this.orders.add(order);
            order.setUser(this);
        }
    }
    public void removeOrder(Order order){
        if(this.orders.contains(order)){
            this.orders.remove(order);
            order.setUser(null);
        }
    }
}
