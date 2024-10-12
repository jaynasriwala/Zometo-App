package com.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name="menus")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MenuEntity 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer menuId;
	Integer active;
	String title;
	String menuImagePath;

}
