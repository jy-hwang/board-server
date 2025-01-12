package com.fastcampus.boardserver.Service.impl;

import com.fastcampus.boardserver.Service.CategoryService;
import com.fastcampus.boardserver.dto.CategoryDTO;
import com.fastcampus.boardserver.mapper.CategoryMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class CategoryServiceImpl implements CategoryService {

  @Autowired
  private CategoryMapper categoryMapper;

  @Override
  public void register(String accountId, CategoryDTO categoryDTO) {
    if (accountId != null) {
      categoryMapper.register(categoryDTO);
    } else {
      log.error("register Category Error! {}", categoryDTO);
      throw new RuntimeException("register Category Error! 게시글 카테고리 등록 메서드를 확인해주세요!" + categoryDTO);
    }
  }

  @Override
  public void update(CategoryDTO categoryDTO) {
    if (categoryDTO != null) {
      categoryMapper.update(categoryDTO);
    } else {
      log.error("update Category Error! {}", categoryDTO);
      throw new RuntimeException("update Category Error! 게시글 카테고리 수정 메서드를 확인해주세요!" + categoryDTO);
    }
  }

  @Override
  public void delete(int categoryNo) {
    if (categoryNo != 0) {
      categoryMapper.delete(categoryNo);
    } else {
      log.error("delete Category Error! {}", categoryNo);
      throw new RuntimeException("delete Category Error! 게시글 카테고리 삭제 메서드를 확인해주세요!" + categoryNo);
    }
  }

}
