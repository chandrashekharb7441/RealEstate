package com.shekhar._acres.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shekhar._acres.models.Flats;

@Repository
public interface FlatRepository extends JpaRepository<Flats, Long>{
	List<Flats> findAllByOrderByPriceAsc();
	List<Flats> findAllByOrderByPriceDesc();
	List<Flats> findBySize(String size);
	List<Flats> findByAdminId(Integer adminId);
}
