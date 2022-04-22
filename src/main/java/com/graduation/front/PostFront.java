package com.graduation.front;

import com.graduation.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/front/post")
public class PostFront {
    @Autowired
    PostService postService;

}
