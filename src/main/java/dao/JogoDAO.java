package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.JFrame;

import entities.Jogo;
import util.JPAUtil;

public class JogoDAO {
	
	JFrame frame = new JFrame();
	
	public static void salvar(Jogo jogo) {
		EntityManager em = JPAUtil.criarEntityManager();
		 
		try {
			
			em.getTransaction().begin();
			em.persist(jogo);
			em.getTransaction().commit();
			
		} catch (Exception e) {
			
			em.getTransaction().rollback();
			throw e;

		}finally {
			em.close();
		}	
	}
	
	public static void editar(Jogo jogo) {
		EntityManager em = JPAUtil.criarEntityManager();
		try {
			
			em.getTransaction().begin();	
			
			Jogo jogoOriginal = getJogoPorId(jogo.getId());
			
			em.merge(jogo);			
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
		
		Jogo jogoDelete = em.find(Jogo.class, id);
		
		if(jogoDelete != null) {
			em.remove(jogoDelete);
		}
		
		em.getTransaction().commit();
		
		em.close();
		
	}	
	
	public static List<Jogo> listar() {
		EntityManager em = JPAUtil.criarEntityManager();
		
		Query q = em.createQuery("SELECT a FROM Jogo a");
		List<Jogo> lista = q.getResultList();
		em.clear();
		return lista;
	}

	public static boolean verificarTimeSelecionado(Jogo jogo) {
		EntityManager em = JPAUtil.criarEntityManager();

		try {
			Query query = em.createQuery(
				"SELECT COUNT(j) FROM Jogo j WHERE "
					+  "j.time1 = :time1 AND j.time2 = :time2"
				);
			query.setParameter("time1", jogo.getTime1());
			query.setParameter("time2", jogo.getTime2());
			Long count = (Long) query.getSingleResult();
			return count > 0;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			em.close();
		}
		
	}
	
	public static Jogo getJogoPorId(Integer id) {
		EntityManager em = JPAUtil.criarEntityManager();

		try {
			return em.find(Jogo.class, id);
		} catch (Exception e) {
			System.out.println("Erro ao buscar por ID: " + e.getMessage());
		} finally {
			em.close();
		}
		return null;

	}
	
}
