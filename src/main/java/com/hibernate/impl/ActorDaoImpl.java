package com.hibernate.impl;

import java.util.ArrayList;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import com.entidades.actor;
import com.hibernate.dao.ActorManager;

public class ActorDaoImpl implements ActorManager{
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
	
	
	@SuppressWarnings("deprecation")
	public ArrayList<actor> listActor() {
		Session session = sessionFactory.openSession();
		
		ArrayList<actor> lista=null;
		try {
			
			@SuppressWarnings("unchecked")
			Query<actor> query=session.createQuery("from actor");
			lista=(ArrayList<actor>)query.list();
		} catch (HibernateException e) {
		System.out.println(e.getMessage());
		}finally {
			if(session!=null) {
				session.close();
			}
		}
		return lista;
	}

	public void insertActor(actor act) {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			session.save(act);
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

	public void updateActor(actor act) {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			session.update(act);
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

	public void deleteActor(actor act) {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			session.delete(act);
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
