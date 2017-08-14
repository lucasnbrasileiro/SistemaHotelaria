package hotel.forms;

import hotel.entities.Aluguel;
import hotel.entities.Consumo;
import hotel.entities.Produto;
import hotel.entities.Quarto;
import hotel.entities.Usuario;
import hotel.persistence.ConsumoDao;

import java.sql.SQLException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class ConsumoForm {

	private Aluguel a = new Aluguel();
	private Usuario u = new Usuario();
	private Quarto q = new Quarto();
	private Produto p = new Produto();
	private Consumo c = new Consumo();
	
	private List<Consumo> consumos;

	private int idAluguel;
	
	
	public int getIdAluguel() {
		return idAluguel;
	}

	public void setIdAluguel(int idAluguel) {
		this.idAluguel = idAluguel;
	}
	
	public Aluguel getA() {
		return a;
	}

	public void setA(Aluguel a) {
		this.a = a;
	}

	public Usuario getU() {
		return u;
	}

	public void setU(Usuario u) {
		this.u = u;
	}

	public Quarto getQ() {
		return q;
	}

	public void setQ(Quarto q) {
		this.q = q;
	}

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

	public String inserir() throws SQLException {

		int idAluguel = a.getIdAluguel();
		int idProduto = p.getIdProduto();
		int quantidade = c.getQtd();

		ConsumoDao cd = new ConsumoDao();
		cd.inserir(idAluguel, idProduto, quantidade);

		return "Sessao";
	}
	
	public String validarDetalhesConta() {
		
		//FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		idAluguel = 0;
		return "DetalhesContaValidacao";
	}
	
	public String exibirDetalhesConta() {
		
		consumos = null;
		return "DetalhesConta";
	}
	
	public List<Consumo> getConsumos(){
		
		if (consumos == null) {
			ConsumoDao cd = new ConsumoDao();
			consumos = cd.list(idAluguel);
		}
		return consumos;
	}
	
	public void setConsumos(List<Consumo> c) {
		this.consumos = c;
	}
	
	public double getTotalGasto() {
		double total = 0;
		for (Consumo c : consumos) {
			total += c.getValorTotal();
		}
		return total;
	}
}
