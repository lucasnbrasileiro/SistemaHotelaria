package hotel.forms;

import hotel.entities.Consumo;
import hotel.entities.Produto;
import hotel.persistence.ProdutoDao;

import java.sql.SQLException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class ProdutoForm {

	private Produto p = new Produto();
	private Consumo c = new Consumo();
	
	private String nome;
	private double valor;
	private int idAluguel;

	public Produto getP() {
		return p;
	}

	public void setP(Produto p) {
		this.p = p;
	}

	public Consumo getC() {
		return c;
	}

	public void setC(Consumo c) {
		this.c = c;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public int getIdAluguel() {
		return idAluguel;
	}

	public void setIdAluguel(int idAluguel) {
		this.idAluguel = idAluguel;
	}

	public String solicitaProdutoCliente() {
		//FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		nome = null;
		valor = 0;
		idAluguel = 0;
		
		return "SolicitaProduto";
	}

	public List<Produto> getProdutos() throws SQLException {

		ProdutoDao pd = new ProdutoDao();
		return pd.list();
	}
}
