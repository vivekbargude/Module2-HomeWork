package com.example.demo.controller;


import com.example.demo.advices.ApiResponse;
import com.example.demo.dto.DepartmentDTO;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.services.DepartmentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping()
    public ResponseEntity<List<DepartmentDTO>> getAllDepartments() {
        List<DepartmentDTO> allDepartments = departmentService.getAllDepartments();
        return new ResponseEntity<>(allDepartments, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DepartmentDTO> createDepartment(@RequestBody @Valid DepartmentDTO departmentDTO) {
        DepartmentDTO newDepartment = departmentService.createDepartment(departmentDTO);
        return new ResponseEntity<>(newDepartment, HttpStatus.CREATED);
    }

    @GetMapping("/{departmentId}")
    public ResponseEntity<DepartmentDTO> getDepartmentById(@PathVariable
                                                                     Long departmentId) {
        Optional<DepartmentDTO> departmentDTO = departmentService.getDepartmentById(departmentId);

        return departmentDTO
                .map(departmentDTO1 -> ResponseEntity.ok(departmentDTO1))
                .orElseThrow(()-> new ResourceNotFoundException("Department with department id: "+departmentId+" doesn't exists."));
    }

    @DeleteMapping("/{departmentId}")
    public ResponseEntity<ApiResponse<String>> deleteDepartmentById(@PathVariable Long departmentId) {
        boolean isDeleted = departmentService.deleteDepartmentById(departmentId);


        if (isDeleted) {
            return ResponseEntity.ok(
                    new ApiResponse<>("Department with id " + departmentId + " deleted successfully.")
            );
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{departmentId}")
    public ResponseEntity<DepartmentDTO> updateDepartmentById(@RequestBody DepartmentDTO departmentDTO, @PathVariable Long departmentId) {
        return ResponseEntity.ok(departmentService.updateDepartmentById(departmentDTO,departmentId));
    }

}