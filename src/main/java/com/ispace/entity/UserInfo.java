package com.ispace.entity;

import com.ispace.validgroups.CreateUser;
import com.ispace.validgroups.UpdateUser;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "isp_user_info")
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotNull(message = "First name is required", groups = {CreateUser.class, UpdateUser.class})
    @NotBlank(message = "First name is required", groups = {CreateUser.class, UpdateUser.class})
    @Column(name = "first_name")
    private String firstName;

    @NotNull(message = "Last name is required", groups = {CreateUser.class, UpdateUser.class})
    @NotBlank(message = "Last name is required", groups = {CreateUser.class, UpdateUser.class})
    @Column(name = "last_name")
    private String lastName;

    @NotNull(message = "Email is required", groups = {CreateUser.class, UpdateUser.class})
    @NotBlank(message = "Email is required", groups = {CreateUser.class, UpdateUser.class})
    @Email(message = "Email format is invalid")
    @Column(name = "email")
    private String email;

    @Column(name = "signature")
    private String signature;

    @Column(name = "nickname")
    private String nickName;

    @Column(name = "email_verified")
    private Boolean emailVerified;

    @NotNull(message = "Password is required", groups = {CreateUser.class, UpdateUser.class})
    @NotBlank(message = "Password is required", groups = {CreateUser.class, UpdateUser.class})
    @Transient
    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Boolean getEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(Boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", signature='" + signature + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserInfo userInfo = (UserInfo) o;
        return Objects.equals(id, userInfo.id) && Objects.equals(firstName, userInfo.firstName) && Objects.equals(lastName, userInfo.lastName) && Objects.equals(email, userInfo.email) && Objects.equals(signature, userInfo.signature) && Objects.equals(nickName, userInfo.nickName) && Objects.equals(emailVerified, userInfo.emailVerified) && Objects.equals(password, userInfo.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email, signature, nickName, emailVerified, password);
    }
}
