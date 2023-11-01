package com.codegym.aurora.controller.back_office;
import com.codegym.aurora.payload.request.SubCategoryRequestDTO;
import com.codegym.aurora.payload.request.SubCategoryRequestDtoForCreate;
import com.codegym.aurora.payload.response.ResponseDTO;
import com.codegym.aurora.service.SubCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
@RequestMapping("/api/admin/sub-categories")
@RequiredArgsConstructor
public class SubCategoryControllerForAdmin {
    private final SubCategoryService subCategoryService;
    @GetMapping
    public ResponseEntity<ResponseDTO> getSubCategories(@PageableDefault Pageable pageable){
        ResponseDTO responseDTO = subCategoryService.findAll(pageable);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<?> createSubCategory(
            @Valid @RequestBody SubCategoryRequestDtoForCreate subCategoryRequestDtoForCreate,
                                            BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        ResponseDTO responseDTO = subCategoryService.create(subCategoryRequestDtoForCreate);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> updateSubCategory(@Valid @RequestBody SubCategoryRequestDTO subCategoryRequestDTO,
                                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        ResponseDTO responseDTO = subCategoryService.update(subCategoryRequestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deleteSubCategory(@PathVariable Long id) {
        ResponseDTO responseDTO = subCategoryService.deleteById(id);
        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }
}
