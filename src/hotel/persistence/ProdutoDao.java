package hotel.persistence;

import hotel.entities.Produto;

import java.util.List;

import javax.persistence.EntityManager;

public class ProdutoDao {

	public void inserir(Produto p) {
		
		EntityManager em = JPAResourceBean.getEntityManager();

		try {
			em.getTransaction().begin();
			em.persist(p);
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

			Produto p = em.find(Produto.class, codigo);
			em.remove(p);

			em.getTransaction().commit();
		} catch (RuntimeException e) {
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
	}

	public Produto buscar(int codigo) {
		EntityManager em = JPAResourceBean.getEntityManager();
		return em.find(Produto.class, codigo);
	}
	
	public List<Produto> list() {

		EntityManager em = JPAResourceBean.getEntityManager();
		return em.createQuery("Select p from Produto p").getResultList();

	}

}
