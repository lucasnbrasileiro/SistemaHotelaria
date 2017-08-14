package hotel.forms;

import hotel.entities.Aluguel;
import hotel.entities.Usuario;
import hotel.persistence.UsuarioDao;

import java.io.Serializable;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class UsuarioForm implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Usuario usuario = new Usuario();

	private Usuario usuarioCadastro = new Usuario();

	private Aluguel aluguel = new Aluguel();
	
	private String perfil = usuarioCadastro.getPerfil();
	private String nome = usuarioCadastro.getNome();
	private String login = usuarioCadastro.getLogin();
	private String senha = usuarioCadastro.getSenha();
	private Date dataNascimento = usuarioCadastro.getDataNascimento();
	
	private int idAluguel;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuarioCadastro() {
		return usuarioCadastro;
	}

	public void setUsuarioCadastro(Usuario usuarioCadastro) {
		this.usuarioCadastro = usuarioCadastro;
	}

	public Aluguel getAluguel() {
		return aluguel;
	}

	public void setAluguel(Aluguel aluguel) {
		this.aluguel = aluguel;
	}
	
	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public int getIdAluguel() {
		return idAluguel;
	}

	public void setIdAluguel(int idAluguel) {
		this.idAluguel = idAluguel;
	}

	public String getDataAtual(){
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Date data = new Date();
		return sdf.format(data);
	}

	public String inserir() throws SQLException {
		
		UsuarioDao uDao = new UsuarioDao();
		uDao.inserir(usuarioCadastro);
		return "Sessao";
	}


	public List<Usuario> getUsuarios() throws SQLException {

		UsuarioDao ud = new UsuarioDao();
		return ud.list();
	}

	public String acessoLogin() {
		return "Acesso";
	}
	
	public String inicio() {
		return "Inicial";
	}
	
	public String acessoSessao() {
		aluguel.setIdAluguel(0);
		return "Sessao";
	}

	public String logout() {
		
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		perfil = null;
		login = null;
		senha = null;
		return "Inicial";
	}

	public String cadastroCliente() {

		//FacesContext.getCurrentInstance().getExternalContext().invalidateSession();	
		perfil = null;
		nome = null;
		login = null;
		senha = null;
		dataNascimento = null;
		
		return "CadastrarClientes";
	}

	public String finalizarContaClienteValidacao() {
		
		//FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		idAluguel = 0;
		return "FinalizarContaValidacao";
	}

	public String autenticar() {

		UsuarioDao ud;
		String login = usuario.getLogin();
		String senha = usuario.getSenha();

		try {
			
			ud = new UsuarioDao();
			usuario = ud.AcessoSistema(login, senha);
			
			//if((login.equals(usuario.getLogin())) && (senha.equals(usuario.getSenha()))){

				return acessoSessao();
			
			//}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
}
