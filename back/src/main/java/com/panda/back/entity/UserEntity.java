package com.panda.back.entity;

import com.panda.back.dto.request.auth.SignUpRequestDto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users")
@Table(name = "users")
public class UserEntity {

  @Id
  private String userId;
  private String password;
  private String email;
  private String type;
  private String role;

  public UserEntity(SignUpRequestDto dto) {
    this.userId = dto.getId();
    this.password = dto.getPassword();
    this.email = dto.getEmail();
    this.type = "app";
    this.role = "ROLE_USER";
  }

  public UserEntity(String userId, String email, String type) {
    this.userId = userId;
    this.password = "Password";
    this.email = email;
    this.type = "app";
    this.role = "ROLE_USER";
  }

}
