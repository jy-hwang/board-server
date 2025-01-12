package com.fastcampus.boardserver.dto;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {
  private int no;
  private int postNo;
  private String contents;
  private int subCommentNo;
  private int userNo;
  private LocalDateTime createTime;
  private LocalDateTime updateTime;
  private LocalDateTime deleteTime;
}
