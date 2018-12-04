package com.hibernate.impl;

import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import com.entidades.address;
import com.hibernate.dao.AddressManager;



public class AddressDaoImpl implements AddressManager {
	protected static  SessionFactory sessionFactory;

	public int setup() {
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
				.configure("/resources/hibernate.cfg.xml") // configures settings from hibernate.cfg.xml
				.build();
		try {
			sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
			
			return 1;
			
		} catch (Exception ex) {
			StandardServiceRegistryBuilder.destroy(registry);
			 System.out.println("Problem creating session factory");
           ex.printStackTrace();
		}
		return 0;
	}
	
	
	
	public ArrayList<address> listAddress() {
		Session session = sessionFactory.openSession();
		
		ArrayList<address> lista=null;
		
		try {
			
		
			Query<address> query=session.createQuery("from address");
			lista=(ArrayList<address>)query.list();
			
		
		} catch (HibernateException e) {
		System.out.println(e.getMessage());
		}finally {
			if(session!=null) {
				session.close();
			}
		}
		return lista;
	}

	public void insertAddress(address adr) {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			session.save(adr);
			session.getTransaction().commit();
		   
			 
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			session.getTransaction().rollback();
			
		}finally{
			if (session!=null) {
				session.close();
			}
		}
		
	}

	public void updateAddress(address adr) {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			session.update(adr);
			session.getTransaction().commit();
		   
			 
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			session.getTransaction().rollback();
			
		}finally{
			if (session!=null) {
				session.close();
			}
		}
		
	}

	public void deleteAddress(address adr) {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			session.delete(adr);
			session.getTransaction().commit();
		   
			 
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			session.getTransaction().rollback();
			
		}finally{
			if (session!=null) {
				session.close();
			}
		}
		
	}
}
