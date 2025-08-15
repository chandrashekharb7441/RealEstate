package com.shekhar._acres.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shekhar._acres.models.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long>{
	
	List<Cart> findByUserId(Integer userId);
}
