package com.shekhar._acres.models;

import java.time.Instant;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class Flats {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column
	private String size;
	
	@Column
	private Long price;
	
	@Column
	private String location;
	
	@Column
	private String description;
	
	@Column
	private String imageUrl;
	
	@CreatedDate
	private Instant createdAt;
	
	@Column
	private String mnumber; // remove this 
	
	@LastModifiedDate
	private Instant updatedAt;
	
	@Column(name = "admin_id")
	private Integer adminId;

	
}
