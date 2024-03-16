package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import metier.entities.Plat;
import util.JPAutil;

public class PlatDaoImpl implements IplatDao {
		private EntityManager entityManager=JPAutil.getEntityManager("TP5_JEE_RESTAURANT");
		
		
		@Override
		public Plat save(Plat p) {

		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		entityManager.persist(p);
		tx.commit();
		return p;
		}
		@Override
		public List<Plat> platsParMC(String mc) {
		List<Plat> plats =
		entityManager.createQuery("select p from Plat p where p.nomPlat like :mc").setParameter("mc", "%"+mc+"%").getResultList();

		return plats;
		}
		
		@Override
		public Plat getPlat(Long id) {
		return entityManager.find(Plat.class, id);
		}
		@Override
		public Plat updatePlat(Plat p) {
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		entityManager.merge(p);
		tx.commit();
		return p;
		}
		@Override
		public void deletePlat(Long id) {
		Plat plat = entityManager.find(Plat.class, id);
		entityManager.getTransaction().begin();
		entityManager.remove(plat);
		entityManager.getTransaction().commit();
		}
		
	

	
}
