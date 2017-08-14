package hotel.forms;

import hotel.entities.Aluguel;
import hotel.entities.Quarto;
import hotel.persistence.QuartoDao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class QuartoForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Quarto quarto = new Quarto();
	private Aluguel aluguel = new Aluguel();

	private int numero = quarto.getNumero();
	private int capacidade = quarto.getCapacidade();
	private double valorDiaria = quarto.getValorDiaria();
	private String status = quarto.getStatus();

	public Quarto getQuarto() {
		return quarto;
	}

	public void setQuarto(Quarto quarto) {
		this.quarto = quarto;
	}

	public Aluguel getAluguel() {
		return aluguel;
	}

	public void setAluguel(Aluguel aluguel) {
		this.aluguel = aluguel;
	}

	public String inserir() throws SQLException {

		QuartoDao qd = new QuartoDao();
		qd.inserir(quarto);
		return "Sessao";
	}

	public String cadastroQuarto() {
		// FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "CadastrarQuartos";
	}

	public String detalhesQuartoAlugado() {
		// FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "DetalhesQuartosAlugados";
	}

	public String detalhesQuartoDisponivel() {
		// FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "DetalhesTodosQuartos";
	}

	public List<Quarto> getQuartos() throws SQLException {

		QuartoDao qd = new QuartoDao();
		return qd.list();
	}

	public List<Aluguel> getBuscarAlugueisPorQuarto() {

		int idQuarto = quarto.getIdQuarto();
		QuartoDao qd = new QuartoDao();
		return qd.listarPorQuarto(idQuarto);
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public int getCapacidade() {
		return capacidade;
	}

	public void setCapacidade(int capacidade) {
		this.capacidade = capacidade;
	}

	public double getValorDiaria() {
		return valorDiaria;
	}

	public void setValorDiaria(double valorDiaria) {
		this.valorDiaria = valorDiaria;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
