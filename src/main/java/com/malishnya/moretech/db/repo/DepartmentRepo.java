package com.malishnya.moretech.db.repo;

import com.malishnya.moretech.db.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepo extends JpaRepository<Department, Long> {
}
