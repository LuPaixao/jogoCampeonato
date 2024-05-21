package dao;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import entities.Jogo;
import util.JPAUtil;

public class JogoDAO {
	
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
			em.merge(jogo);			
			em.getTransaction().commit();
			
		} catch (Exception e) {
			
			em.getTransaction().rollback();
			throw e;
		} finally {
			em.close();

		}
		
		
	}
	
	public static void deletar(int id) {
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
		
		Query q = em.createNamedQuery("Jogo.listar");
		List<Jogo> lista = q.getResultList();
		em.clear();
		return lista;
	}
	
	public static Integer findMaxNumeroSorteado() {
		EntityManager em = JPAUtil.criarEntityManager();
		TypedQuery<Integer> q = em.createNamedQuery("Jogo.findMaxNumeroSorteadoDaTabela", Integer.class);
		Integer maxNumSorteado = q.getSingleResult();
		em.clear();
		return maxNumSorteado;
	}
	
 	public static Integer findMaxNumeroDasVariaveis(Jogo jogo) {
 		  List<Integer> valores = Arrays.asList(jogo.getV1(), jogo.getV2(), jogo.getV3(), jogo.getV4(), jogo.getV5());
 	        return valores.stream().max(Integer::compareTo).orElse(null);
 	}
}
