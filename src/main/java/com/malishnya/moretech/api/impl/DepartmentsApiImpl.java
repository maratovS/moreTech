package com.malishnya.moretech.api.impl;

import com.malishnya.moretech.api.generated.api.DepartmentApi;
import com.malishnya.moretech.api.generated.model.DepartmentDto;
import com.malishnya.moretech.api.generated.model.FilterDto;
import com.malishnya.moretech.api.generated.model.GetDepartmentList200Response;
import com.malishnya.moretech.api.generated.model.UserDto;
import com.malishnya.moretech.db.model.Department;
import com.malishnya.moretech.db.repo.DepartmentRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DepartmentsApiImpl implements DepartmentApi {

    @Autowired
    private DepartmentRepo repo;

    @Autowired
    private ModelMapper mapper;

    @Override
    public ResponseEntity<GetDepartmentList200Response> getDepartmentList(@RequestParam(value = "filter", required = false) FilterDto filter, Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
        Page<Department> departments = repo.findAll(PageRequest.of(pageNumber, pageSize, Sort.by(sortBy)));
        GetDepartmentList200Response response = new GetDepartmentList200Response()
                .page(pageNumber)
                .pageSize(pageSize)
                .totalRows(departments.getTotalElements())
                .totalPages(departments.getTotalPages())
                .sortField(sortBy)
                .sortDir(GetDepartmentList200Response.SortDirEnum.valueOf(sortDir))
                .records(departments.stream().map(department -> mapper.map(department, DepartmentDto.class)).toList());
        return ResponseEntity.ok().body(response);
    }

    @Override
    public ResponseEntity<GetDepartmentList200Response> getNearestDepartments(UserDto userDto) {
        return DepartmentApi.super.getNearestDepartments(userDto);
    }
}
