package hotel.persistence;

import hotel.acess.Conexao;
import hotel.entities.Aluguel;
import hotel.entities.Usuario;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.persistence.EntityManager;

public class UsuarioDao {

	ResultSet rs;
	Connection conexao = null;

	public UsuarioDao() throws SQLException {
		conexao = Conexao.abreConexaoMySQL();

	}

	public Usuario AcessoSistema(String login, String senha) throws Exception,
			SQLException {

		Usuario u = null;
		Connection conexao = Conexao.abreConexaoMySQL();
		java.sql.Statement stmt = null;

		try {

			String sql = "select * from usuario where login =  '" + login
					+ "' and senha = '" + senha + "'";
			Statement comando = conexao.createStatement();
			rs = comando.executeQuery(sql);

			if (rs.next()) {

				u = new Usuario();
				u.setIdUsuario(rs.getInt("idUsuario"));
				u.setLogin(rs.getString("perfil"));
				u.setNome(rs.getString("nome"));
				u.setSenha(rs.getString("login"));
				u.setPerfil(rs.getString("senha"));
				u.setDataNascimento(rs.getTimestamp("data_nascimento"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (conexao != null) {
					conexao.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (u == null) {
			throw new Exception("Usuário Inexistente!");
		}

		return u;

	}

	public void inserir(Usuario u) {
		EntityManager em = JPAResourceBean.getEntityManager();

		try {
			em.getTransaction().begin();
			em.persist(u);
			em.getTransaction().commit();
		} catch (RuntimeException e) {
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
	}

	public void atualizar(Usuario u) {
		EntityManager em = JPAResourceBean.getEntityManager();

		try {
			em.getTransaction().begin();

			Usuario velho = em.find(Usuario.class, u.getIdUsuario());
			velho.setNome(u.getNome());
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

			Usuario u = em.find(Usuario.class, codigo);
			em.remove(u);

			em.getTransaction().commit();
		} catch (RuntimeException e) {
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
	}

	public Usuario buscar(int codigo) {
		EntityManager em = JPAResourceBean.getEntityManager();
		return em.find(Usuario.class, codigo);
	}

	public List<Usuario> list() {

		EntityManager em = JPAResourceBean.getEntityManager();
		return em.createQuery("Select u from Usuario u").getResultList();

	}

}
