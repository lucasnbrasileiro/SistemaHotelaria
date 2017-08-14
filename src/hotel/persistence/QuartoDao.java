package hotel.persistence;

import hotel.entities.Aluguel;
import hotel.entities.Quarto;

import java.util.List;

import javax.persistence.EntityManager;

public class QuartoDao {

	public void inserir(Quarto q) {
		EntityManager em = JPAResourceBean.getEntityManager();

		try {
			em.getTransaction().begin();
			em.persist(q);
			em.getTransaction().commit();
		} catch (RuntimeException e) {
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
	}

	public void atualizar(int idQuarto) {
		EntityManager em = JPAResourceBean.getEntityManager();

		try {
		
			em.getTransaction().begin();
			Quarto q = em.find(Quarto.class,idQuarto);
			q.setStatus("Ocupado");
			em.persist(q);
			em.getTransaction().commit();
		} catch (RuntimeException e) {
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
	}

	public void excluir(int codigo) {
		EntityManager em = JPAResourceBean.getEntityManager();

		try {
			em.getTransaction().begin();

			Quarto q = em.find(Quarto.class, codigo);
			em.remove(q);

			em.getTransaction().commit();
		} catch (RuntimeException e) {
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
	}

	public Quarto buscar(int codigo) {
		EntityManager em = JPAResourceBean.getEntityManager();
		return em.find(Quarto.class, codigo);
	}
	
	
	public List<Quarto> list() {

		EntityManager em = JPAResourceBean.getEntityManager();
		return em.createQuery("Select q from Quarto q").getResultList();

	}
	
   public List<Aluguel> listarPorQuarto(int idQuarto){
	   
	   EntityManager em = JPAResourceBean.getEntityManager();
	   return em.createQuery("Select a from Aluguel a where a.idQuarto = :idQuarto").setParameter("idQuarto",idQuarto).getResultList();
	   
	   
   }

}
