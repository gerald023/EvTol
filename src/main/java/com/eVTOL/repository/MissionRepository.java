package com.eVTOL.repository;

import com.eVTOL.model.Missions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionRepository extends JpaRepository<Missions, Long> {
}
