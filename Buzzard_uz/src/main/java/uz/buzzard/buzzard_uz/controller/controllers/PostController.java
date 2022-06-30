package uz.buzzard.buzzard_uz.controller.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.buzzard.buzzard_uz.dto.dto.PostDto;
import uz.buzzard.buzzard_uz.service.services.PostService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/saveFile")
    public HttpEntity<?> saveFile(MultipartHttpServletRequest request) throws IOException {
        return postService.saveFile(request);
    }

    @PostMapping("/createPost")
    public HttpEntity<?> createPost(@RequestBody PostDto postDto){
        return postService.createPost(postDto);
    }

    @DeleteMapping("/deletePost/{postId}")
    public HttpEntity<?> deleteById(@PathVariable Long postId){
        return postService.deleteById(postId);
    }

        @GetMapping("/showPicture/{id}")
    public void showPicture(@PathVariable Long id, HttpServletResponse response) throws Exception {
       postService.download(id, response);
    }


}
