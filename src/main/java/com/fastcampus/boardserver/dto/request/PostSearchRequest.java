package com.fastcampus.boardserver.dto.request;

import com.fastcampus.boardserver.dto.CategoryDTO;
import lombok.Data;

@Data
public class PostSearchRequest {
  private int no;
  private String title;
  private String contents;
  private int categoryNo;
  private int userNo;
  private int views;
  private CategoryDTO.SortStatus sortStatus;
}
