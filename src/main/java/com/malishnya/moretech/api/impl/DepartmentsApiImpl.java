package com.malishnya.moretech.api.impl;

import com.malishnya.moretech.api.generated.api.DepartmentApi;
import com.malishnya.moretech.api.generated.model.*;
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
    public ResponseEntity<GetNearestDepartments200Response> getNearestDepartments(UserDto userDto) {
        double centerLatitude = Math.toRadians(userDto.getLatitude());
        double centerLongitude = Math.toRadians(userDto.getLongitude());
        double radiusKilometers = 1.0;
        double earthRadiusKilometers = 6371.0;
        double[] latitudes = new double[4];
        double[] longitudes = new double[4];
        int i = 0;
        for (int angle = 0; angle < 360; angle += 90) {
            double bearing = Math.toRadians(angle);
            double newLatitude = Math.asin(Math.sin(centerLatitude) * Math.cos(radiusKilometers / earthRadiusKilometers)
                    + Math.cos(centerLatitude) * Math.sin(radiusKilometers / earthRadiusKilometers) * Math.cos(bearing));
            double newLongitude = centerLongitude + Math.atan2(Math.sin(bearing) * Math.sin(radiusKilometers / earthRadiusKilometers)
                    * Math.cos(centerLatitude), Math.cos(radiusKilometers / earthRadiusKilometers) - Math.sin(centerLatitude) * Math.sin(newLatitude));
            latitudes[i] = Math.toDegrees(newLatitude);
            longitudes[i] = Math.toDegrees(newLongitude);
            i++;
        }
        List<DepartmentDto> nearestDepartments = repo.findAllByLatitudeBetweenAndLongitudeBetween(latitudes[2], latitudes[0], longitudes[3], longitudes[1])
                .stream().map(department -> mapper.map(department, DepartmentDto.class)).toList();
        return ResponseEntity.ok().body(new GetNearestDepartments200Response().records(nearestDepartments));
    }
}
