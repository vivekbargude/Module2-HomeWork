package com.example.demo.services;


import com.example.demo.dto.DepartmentDTO;
import com.example.demo.entity.DepartmentEntity;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.DepartmentRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final ModelMapper modelMapper;

    public List<DepartmentDTO> getAllDepartments() {
        List<DepartmentEntity> departments = departmentRepository.findAll();

        return departments
                .stream()
                .map(departmentEntity -> modelMapper.map(departmentEntity,DepartmentDTO.class))
                .toList();
    }

    public DepartmentDTO createDepartment(DepartmentDTO departmentDTO) {
        DepartmentEntity newDepartment = modelMapper.map(departmentDTO, DepartmentEntity.class);
        DepartmentEntity savedDepartment = departmentRepository.save(newDepartment);
        return modelMapper.map(savedDepartment, DepartmentDTO.class);
    }

    public void isExists(Long departmentId) {
        boolean isExist = departmentRepository.existsById(departmentId);
        if(!isExist) throw new ResourceNotFoundException("Department with department id: "+departmentId+" doesn't exists.");
    }

    public Optional<DepartmentDTO> getDepartmentById(Long departmentId) {
        isExists(departmentId);

        return departmentRepository.findById(departmentId)
                .map(departmentEntity -> modelMapper.map(departmentEntity, DepartmentDTO.class));

    }

    public boolean deleteDepartmentById(Long departmentId) {
        isExists(departmentId);
        departmentRepository.deleteById(departmentId);
        return true;
    }

    public DepartmentDTO updatePartialDepartmentById(Map<String, String> updates, Long departmentId) {
        isExists(departmentId);

        DepartmentEntity departmentEntity = departmentRepository.findById(departmentId).get();

        updates.forEach((field,value)->{
            Field fieldToBeUpdated = ReflectionUtils.findRequiredField(DepartmentEntity.class,field);
            fieldToBeUpdated.setAccessible(true);
            ReflectionUtils.setField(fieldToBeUpdated,departmentEntity,value);
        });;

        DepartmentEntity updatedDepartment = departmentRepository.save(departmentEntity);
        return modelMapper.map(updatedDepartment, DepartmentDTO.class);
    }

    public DepartmentDTO updateDepartmentById(DepartmentDTO departmentDTO, Long departmentId) {
        isExists(departmentId);

        DepartmentEntity departmentEntity = modelMapper.map(departmentDTO,DepartmentEntity.class);
        departmentEntity.setId(departmentId);
        DepartmentEntity updatedDepartment = departmentRepository.save(departmentEntity);
        return modelMapper.map(updatedDepartment, DepartmentDTO.class);
    }
}