package com.skylab.domain.rp.repository;

import com.skylab.domain.rp.model.material.Material;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaterialRepository extends JpaRepository<Material, Integer> {

}
