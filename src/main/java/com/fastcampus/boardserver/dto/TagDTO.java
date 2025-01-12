package com.fastcampus.boardserver.dto;

import lombok.*;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TagDTO {
  private int no;
  private String name;
  private String url;
  private int postNo;
}
