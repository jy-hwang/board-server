package com.fastcampus.boardserver.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {

  private int no;
  private String name;
  private SortStatus sortStatus;
  private int searchCount;
  private int pagingStartOffset;

  public enum SortStatus {
    CATEGORIES, NEWEST, OLDEST
  }

}
