package com.fastcampus.boardserver.controller;

import com.fastcampus.boardserver.Service.impl.PostSearchServiceImpl;
import com.fastcampus.boardserver.dto.PostDTO;
import com.fastcampus.boardserver.dto.request.PostSearchRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/search")
@Log4j2
@RequiredArgsConstructor
public class PostSearchController {

  private final PostSearchServiceImpl postSearchService;

  @PostMapping
  public PostSearchResponse search(@RequestBody PostSearchRequest postSearchRequest) {
    List<PostDTO> postDTOList = postSearchService.getPosts(postSearchRequest);

    return new PostSearchResponse(postDTOList);
  }

  @GetMapping
  public PostSearchResponse searchByTagName(String tagName) {
    List<PostDTO> postDTOList = postSearchService.getPostsByTag(tagName);

    return new PostSearchResponse(postDTOList);
  }


  // -- response 객체 --

  @Getter
  @AllArgsConstructor
  private static class PostSearchResponse {
    private List<PostDTO> postDTOList;
  }
}
