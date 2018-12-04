package com.entidades;



import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



import com.vividsolutions.jts.geom.Geometry;
@Entity
@Table(name = "address")
public class address implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String address_id;
	private String address;
	private String address2;
	private String district;
	private String city_id;
	private String postal_code;
	private String phone;
	
	@Column
	private Geometry location;
	
	@Id
	@Column(name = "address_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public String getAddress_id() {
		return address_id;
	}
	public void setAddress_id(String address_id) {
		this.address_id = address_id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getCity_id() {
		return city_id;
	}
	public void setCity_id(String city_id) {
		this.city_id = city_id;
	}
	public String getPostal_code() {
		return postal_code;
	}
	public void setPostal_code(String postal_code) {
		this.postal_code = postal_code;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Geometry getLocation() {
		return location;
	}
	public void setLocation(Geometry location) {
		this.location = location;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	

}
