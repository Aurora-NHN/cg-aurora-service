package com.codegym.aurora.controller.back_office;
import com.codegym.aurora.payload.request.CategoryRequestDTO;
import com.codegym.aurora.payload.response.ResponseDTO;
import com.codegym.aurora.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/admin/categories")
@RequiredArgsConstructor
public class CategoryControllerForAdmin {
    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<ResponseDTO> getCategories() {
        ResponseDTO responseDTO = categoryService.findByActiveTrue();
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    @GetMapping({"{id}/detail"})
    public ResponseEntity<ResponseDTO> getCategoryDetail(@PathVariable Long id) {
        ResponseDTO responseDTO = categoryService.findCategoryByIdAndDeteteFalse(id);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createCategory(@Valid @RequestBody CategoryRequestDTO categoryRequestDTO,
                                            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        ResponseDTO responseDTO = categoryService.create(categoryRequestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> updateCategory(@Valid @RequestBody CategoryRequestDTO categoryRequestDTO,
                                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        ResponseDTO responseDTO = categoryService.update(categoryRequestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deleteCategory(@PathVariable Long id) {
        ResponseDTO responseDTO = categoryService.deleteById(id);
        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }

    @PostMapping("/{id}/enable-category")
    public ResponseEntity<?> enableCategory(@PathVariable Long id) {
        ResponseDTO responseDTO = categoryService.activeById(id);
        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }
    @PostMapping("/{id}/disable-category")
    public ResponseEntity<ResponseDTO> disableCategory(@PathVariable Long id) {
        ResponseDTO responseDTO = categoryService.unactiveById(id);
        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }

}
