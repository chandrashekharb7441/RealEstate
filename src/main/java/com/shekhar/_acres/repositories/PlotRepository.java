package com.shekhar._acres.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shekhar._acres.models.Flats;
import com.shekhar._acres.models.Plot;

@Repository
public interface PlotRepository extends JpaRepository<Plot, Long>{
	List<Plot> findAllByOrderByPriceAsc();
	List<Plot> findAllByOrderByPriceDesc();
	List<Plot> findBySize(String size);
	List<Plot> findByAdminId(Integer adminId);
}
