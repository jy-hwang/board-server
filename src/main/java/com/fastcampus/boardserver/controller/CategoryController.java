package com.fastcampus.boardserver.controller;

import com.fastcampus.boardserver.Service.impl.CategoryServiceImpl;
import com.fastcampus.boardserver.aop.LoginCheck;
import com.fastcampus.boardserver.dto.CategoryDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
@Log4j2
public class CategoryController {

  private final CategoryServiceImpl categoryService;

  public CategoryController(CategoryServiceImpl categoryService) {
    this.categoryService = categoryService;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @LoginCheck(type = LoginCheck.UserType.ADMIN)
  public void registerCategories(String accountId, @RequestBody CategoryDTO category) {
    categoryService.register(accountId, category);
  }

  @PatchMapping("{categoryNo}")
  @LoginCheck(type = LoginCheck.UserType.ADMIN)
  public void updateCategories(String accountId, @PathVariable(name = "categoryNo") int categoryNo, @RequestBody CategoryRequest categoryRequest) {

    CategoryDTO categoryDTO = new CategoryDTO(categoryNo, categoryRequest.getName(), CategoryDTO.SortStatus.NEWEST, 10, 1);
    categoryService.update(categoryDTO);
  }

  @DeleteMapping("{categoryNo}")
  @LoginCheck(type = LoginCheck.UserType.ADMIN)
  public void deleteCategories(String accountId, @PathVariable(name = "categoryNo") int categoryNo) {
    categoryService.delete(categoryNo);
  }

  // -- request 객체 --
  @Getter
  @Setter
  private static class CategoryRequest {
    private int no;
    private String name;
  }

}
