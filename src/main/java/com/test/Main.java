package com.test;


import java.util.List;


import com.entidades.address;
import com.hibernate.dao.AddressManager;
import com.hibernate.impl.AddressDaoImpl;



public class Main {
	static List<address> address;;
	public static void main(String[] args) {
		AddressDaoImpl obj=new AddressDaoImpl();
		obj.setup();
		System.out.println("YA ME CONEXTE");
		
		List<address>objaddress=null;
		
		objaddress=getAddress();
		
		for (address addr : objaddress) {
			System.out.println("Entre a la lista");
			System.out.println(addr.getAddress_id());
			System.out.println(addr.getAddress());
			System.out.println(addr.getAddress2());
			System.out.println(addr.getCity_id());
			System.out.println(addr.getPostal_code());
			System.out.println(addr.getPhone());
			System.out.println("Geo X:"+addr.getLocation().getCoordinate().x);
			System.out.println("Geo Y:"+addr.getLocation().getCoordinate().y);
			
			
		}
		
		
	}
	
	public static List<address>getAddress(){
		AddressManager act=new AddressDaoImpl();
		address=act.listAddress();
		
		return address;
		 
	}
	
	
	
	

}
