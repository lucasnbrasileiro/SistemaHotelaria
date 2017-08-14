package hotel.persistence;

import java.util.List;

import hotel.entities.Aluguel;
import hotel.entities.Consumo;
import hotel.entities.Produto;

import javax.persistence.EntityManager;

public class ConsumoDao {

	public void inserir(int idAluguel, int idProduto, int qtd) {
		EntityManager em = JPAResourceBean.getEntityManager();

		try {
			em.getTransaction().begin();

			Aluguel a = em.getReference(Aluguel.class, idAluguel);
			Produto p = em.getReference(Produto.class, idProduto);

			Consumo c = new Consumo();
			c.setAluguel(a);
			c.setProduto(p);
			c.setQtd(qtd);
			em.persist(c);
			em.getTransaction().commit();
		} catch (RuntimeException e) {
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
	}

	public void atualizar(Consumo c) {
		EntityManager em = JPAResourceBean.getEntityManager();

		Aluguel a = new Aluguel();
		Produto p = new Produto();

		try {
			em.getTransaction().begin();

			Consumo velho = em.find(Consumo.class, c.getIdConsumo());
			velho.getAluguel().setIdAluguel(a.getIdAluguel());
			velho.getProduto().setIdProduto(p.getIdProduto());
			velho.setQtd(c.getQtd());
			// ....

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

			Consumo c = em.find(Consumo.class, codigo);
			em.remove(c);

			em.getTransaction().commit();
		} catch (RuntimeException e) {
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
	}

	public Consumo buscar(int codigo) {
		EntityManager em = JPAResourceBean.getEntityManager();
		return em.find(Consumo.class, codigo);
	}

	public List<Consumo> list(int idAluguel) {

		EntityManager em = JPAResourceBean.getEntityManager();
		return em.
		createQuery("Select c from Consumo c where c.aluguel.idAluguel = :idAluguel").
		setParameter("idAluguel",idAluguel).getResultList();

	}

	public List<Consumo> listTotalGasto() {
		EntityManager em = JPAResourceBean.getEntityManager();
		return em.createQuery("Select sum(c.qtd * p.valor) from Consumo c,Produto p").getResultList();
	}
}
