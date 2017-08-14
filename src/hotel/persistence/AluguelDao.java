package hotel.persistence;

import hotel.entities.Aluguel;
import hotel.entities.Quarto;
import hotel.entities.Usuario;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

public class AluguelDao {

	public void inserir(int idUsuario, int idQuarto, int quantidadePessoas, Date dataInicio, Date dataFim) {
		
		EntityManager em = JPAResourceBean.getEntityManager();

		try {
			
			em.getTransaction().begin();

			Usuario u = em.getReference(Usuario.class, idUsuario);
			Quarto q = em.getReference(Quarto.class, idQuarto);

			Aluguel a = new Aluguel();
			a.setUsuario(u);
			a.setQuarto(q);
			a.setQtdPessoas(quantidadePessoas);
			a.setDataEntrada(dataInicio);
			a.setDataSaida(dataFim);

			em.persist(a);
			em.getTransaction().commit();
			
		} catch (RuntimeException e) {
			em.getTransaction().rollback();
			throw e;
		} finally {
			em.close();
		}
	}

	public void excluir(int codigo) {
		
		EntityManager em = JPAResourceBean.getEntityManager();

		try {
			
			em.getTransaction().begin();

			Aluguel a = em.find(Aluguel.class, codigo);
			em.remove(a);
			em.getTransaction().commit();
			
		} catch (RuntimeException e) {
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
	}

	public Aluguel buscar(int codigo) {
		
		EntityManager em = JPAResourceBean.getEntityManager();
		return em.find(Aluguel.class, codigo);
	}
	
	public List<Aluguel> list() {

		EntityManager em = JPAResourceBean.getEntityManager();
		return em.createQuery("Select a from Aluguel a").getResultList();
	}
	
	public List<Aluguel> listDiaria(int idAluguel) {

		EntityManager em = JPAResourceBean.getEntityManager();
		
		return em.createQuery("Select datediff(data_saida,data_entrada) from Aluguel a where a.idAluguel = :idAluguel").
				setParameter("idAluguel",idAluguel).getResultList();

	}
	
	public List<Aluguel> listUsuario(int idAluguel) {

		EntityManager em = JPAResourceBean.getEntityManager();
		return em.
		createQuery("Select a from Aluguel a where a.idAluguel = :idAluguel").
		setParameter("idAluguel",idAluguel).getResultList();

	}
}
