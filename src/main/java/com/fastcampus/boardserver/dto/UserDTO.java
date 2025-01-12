package com.fastcampus.boardserver.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class UserDTO {

  private int no;
  private String userId;
  private String password;
  private String nickname;
  private boolean isAdmin;
  private Date createTime;
  private boolean isWithdraw;
  private Status status;
  private Date updateTime;

  public static boolean hasNullDataBeforeRegister(UserDTO userDTO) {
    return userDTO == null || userDTO.getUserId() == null || userDTO.getPassword() == null;
  }

  public enum Status {
    DEFAULT, ADMIN, DELETED
  }

}
