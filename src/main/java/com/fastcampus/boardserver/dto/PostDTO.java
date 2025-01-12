package com.fastcampus.boardserver.dto;

import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {
  private int no;
  private String title;
  private String contents;
  private int views;
  private int categoryNo;
  private int userNo;
  private boolean isAdmin;
  private LocalDateTime createTime;
  private LocalDateTime updateTime;
  private LocalDateTime deleteTime;
}
