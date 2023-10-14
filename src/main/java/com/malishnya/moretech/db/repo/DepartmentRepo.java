package com.malishnya.moretech.db.repo;

import com.malishnya.moretech.db.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartmentRepo extends JpaRepository<Department, Long> {
    List<Department> findAllByLatitudeBetweenAndLongitudeBetween(Double latitude1, Double latitude2, Double longitude1, Double longitude2);
}
