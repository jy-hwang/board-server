package com.fastcampus.boardserver.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class UserDTO {

  private int no;
  private String userId;
  private String password;
  private String nickname;
  private boolean isAdmin;
  private LocalDateTime createTime;
  private boolean isWithdraw;
  private Status status;
  private LocalDateTime updateTime;

  public static boolean hasNullDataBeforeRegister(UserDTO userDTO) {
    return userDTO == null || userDTO.getUserId() == null || userDTO.getPassword() == null;
  }

  public enum Status {
    DEFAULT, ADMIN, DELETED
  }

}
