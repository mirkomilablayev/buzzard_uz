package uz.buzzard.buzzard_uz.service.services;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.buzzard.buzzard_uz.dto.dto.PostDto;
import uz.buzzard.buzzard_uz.dto.dto.PostShowDto;
import uz.buzzard.buzzard_uz.entity.File;
import uz.buzzard.buzzard_uz.entity.Post;
import uz.buzzard.buzzard_uz.repository.repositories.FileRepo;
import uz.buzzard.buzzard_uz.repository.repositories.PostRepo;
import uz.buzzard.buzzard_uz.tools.Constant;
import uz.buzzard.buzzard_uz.tools.exceptions.ResourceNotFoundException;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
@RequiredArgsConstructor
public class PostService {
    private final FileRepo fileRepo;
    private final PostRepo postRepo;

    public HttpEntity<?> saveFile(MultipartHttpServletRequest request) throws IOException {

        Iterator<String> fileNames = request.getFileNames();
        MultipartFile file = request.getFile(fileNames.next());
        if (file != null) {
            File newFile = new File();
            newFile.setContentType(file.getContentType());
            newFile.setContentSize(file.getSize());
            String originalFilename = file.getOriginalFilename();
            newFile.setOriginalName(originalFilename);
            newFile.setExtension(file.getContentType());

            assert originalFilename != null;
            String generatedName = UUID.randomUUID() + "." + originalFilename.split("\\.")[originalFilename.split("\\.").length - 1];
            newFile.setGeneratedName(generatedName);
            String filePath = Constant.filePaths + "/" + generatedName;
            newFile.setFilePath(filePath);
            File save = fileRepo.save(newFile);
            Path path = Paths.get(filePath);
            Files.copy(file.getInputStream(), path);
            return ResponseEntity.status(HttpStatus.OK).body(save.getId());
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("saqlanmadi");
    }

    public HttpEntity<?> createPost(PostDto postDto) {
        Post post = new Post();
        post.setText(postDto.getText());
        post.setTitle(postDto.getTitle());
        File file = fileRepo.findByIdAndIsDeleted(postDto.getFileId(), false).orElseThrow(() -> new ResourceNotFoundException(""));
        post.setFile(file);
        Post save = postRepo.save(post);
        return ResponseEntity.status(HttpStatus.OK).body(save);
    }

    public HttpEntity<?> deleteById(Long postId) {
        Optional<Post> postOptional = postRepo.findByIdAndIsDeleted(postId, false);
        if (postOptional.isPresent()) {
            Post post = postOptional.get();
            post.setIsDeleted(true);
            postRepo.save(post);
            return ResponseEntity.status(HttpStatus.OK).body("Successfully deleted");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post Doesn't exist");
        }
    }


    public void download(Long id, HttpServletResponse response) throws Exception {
        Optional<File> byId = fileRepo.findByIdAndIsDeleted(id,false);
        if (byId.isPresent()){
            File file = byId.get();
            try {
                response.setHeader("Content-Disposition", file.getExtension());
                response.setContentType(file.getExtension());
                FileInputStream inputStream = new FileInputStream(file.getFilePath());
                FileCopyUtils.copy(inputStream, response.getOutputStream());
            } catch (IOException ignored) {
                throw new Exception();
            }
        }
    }

    public HttpEntity<?> getPosts() {
        List<Post> all = postRepo.findAllByIsDeleted(false);
        List<PostShowDto> postShowDtoList = new ArrayList<>();
        for (Post post : all) {
            PostShowDto postShowDto = new PostShowDto();
            postShowDto.setFileId(post.getFile().getId());
            postShowDto.setId(post.getId());
            postShowDto.setTitle(post.getTitle());
            postShowDto.setText(post.getText());
            postShowDtoList.add(postShowDto);
        }
        return ResponseEntity.status(HttpStatus.OK).body(postShowDtoList);
    }
}
