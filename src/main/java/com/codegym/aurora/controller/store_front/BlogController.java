package com.codegym.aurora.controller.store_front;

import com.codegym.aurora.payload.request.BlogContentImageDto;
import com.codegym.aurora.payload.request.BlogCreateRequestDto;
import com.codegym.aurora.payload.request.BlogUpdateRequestDto;
import com.codegym.aurora.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/blogs")
@RequiredArgsConstructor
public class BlogController {
    private final BlogService blogService;

    @GetMapping
    public ResponseEntity<Object> getBlog(){
        return blogService.getBlog();
    }

    @PostMapping
    public ResponseEntity<Object> createNewBlog(@ModelAttribute @Validated BlogCreateRequestDto blogCreateRequestDto,
                                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return blogService.save(blogCreateRequestDto);
        }
    }

    @PutMapping
    public ResponseEntity<Object> updateBlog(@ModelAttribute @Validated BlogUpdateRequestDto blogUpdateRequestDto,
                                        BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return blogService.save(blogUpdateRequestDto);
        }
    }

    @DeleteMapping("/{blogId}")
    public ResponseEntity<Object> deleteBlog(@PathVariable Long blogId){
        return blogService.deleteBlog(blogId);
    }

    @PostMapping("/content-images")
    public ResponseEntity<Object> uploadBlogImage(@ModelAttribute BlogContentImageDto blogContentImageDto) {
        return blogService.uploadContentImage(blogContentImageDto);
    }
}
