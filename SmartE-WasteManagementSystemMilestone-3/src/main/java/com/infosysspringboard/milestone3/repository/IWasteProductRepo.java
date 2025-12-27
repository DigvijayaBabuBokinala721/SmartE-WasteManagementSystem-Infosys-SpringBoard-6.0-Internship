package com.infosysspringboard.milestone3.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infosysspringboard.milestone3.entity.WasteProduct;

public interface IWasteProductRepo extends JpaRepository<WasteProduct, Integer> {
	
}
