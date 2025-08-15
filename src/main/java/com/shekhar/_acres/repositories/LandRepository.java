package com.shekhar._acres.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shekhar._acres.models.Flats;
import com.shekhar._acres.models.Land;

@Repository
public interface LandRepository extends JpaRepository<Land, Long>{
	List<Land> findAllByOrderByPriceAsc();
	List<Land> findAllByOrderByPriceDesc();
	List<Land> findBySize(String size);
	List<Land> findByAdminId(Integer adminId);
}
