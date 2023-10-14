package com.malishnya.moretech.db.repo;

import com.malishnya.moretech.db.model.OpenHours;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OpenHoursRepo extends JpaRepository<OpenHours, Long> {
}
