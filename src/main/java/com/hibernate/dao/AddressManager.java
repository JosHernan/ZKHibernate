package com.hibernate.dao;

import java.util.ArrayList;

import com.entidades.address;



public interface AddressManager {
	
	public ArrayList<address>listAddress();
	public void insertAddress(address addr);
	public void updateAddress(address addr);
	public void deleteAddress(address addr);

}
