package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.JFrame;

import entities.Campeonato;
import util.JPAUtil;

public class CampeonatoDao {

	JFrame frame = new JFrame();
	
	public static void salvar(Campeonato campeonato) {
		EntityManager em = JPAUtil.criarEntityManager();
		 
		try {
			
			em.getTransaction().begin();
			em.persist(campeonato);
			em.getTransaction().commit();
			
		} catch (Exception e) {
			
			em.getTransaction().rollback();
			throw e;

		}finally {
			em.close();
		}	
	}
	
	public static void editar(Campeonato campeonato) {
		EntityManager em = JPAUtil.criarEntityManager();
		try {
			
			em.getTransaction().begin();		
			em.merge(campeonato);			
			em.getTransaction().commit();
			
		} catch (Exception e) {
			
			em.getTransaction().rollback();
			throw e;
		} finally {
			em.close();

		}
			
	}
	
	public static void excluir(int id) {
		EntityManager em = JPAUtil.criarEntityManager();
		
		em.getTransaction().begin();
		
		Campeonato exlcuirCampeonato = em.find(Campeonato.class, id);
		
		if(exlcuirCampeonato != null) {
			em.remove(exlcuirCampeonato);
		}
		
		em.getTransaction().commit();
		
		em.close();
		
	}	
	
	public static List<Campeonato> listar() {
		EntityManager em = JPAUtil.criarEntityManager();
		
		Query q = em.createQuery("SELECT a FROM Campeonato a");
		List<Campeonato> lista = q.getResultList();
		em.clear();
		return lista;
	}
	
	public static Campeonato busrcarPorId(Integer id) {
		EntityManager em = JPAUtil.criarEntityManager();

		try {
			return em.find(Campeonato.class, id);
		} catch (Exception e) {
			System.out.println("Erro ao buscar por ID: " + e.getMessage());
		} finally {
			em.close();
		}
		return null;

	}
	

	
}
