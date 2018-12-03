package com.test;


import java.util.List;

import com.entidades.actor;
import com.hibernate.dao.ActorManager;
import com.hibernate.impl.ActorDaoImpl;

public class Main {
	static List<actor> actores;;
	public static void main(String[] args) {
		ActorDaoImpl obj=new ActorDaoImpl();
		obj.setup();
		System.out.println("YA ME CONEXTE");
		
		List<actor>objactor=null;
		
		objactor=getActor();
		
		for (actor actor : objactor) {
			System.out.println("Entre a la lista");
			System.out.println(actor.getFirst_name());
		}
		
		
	}
	
	public static List<actor>getActor(){
		ActorManager act=new ActorDaoImpl();
		actores=act.listActor();
		
		return actores;
		 
	}
	
	

}
