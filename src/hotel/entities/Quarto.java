package hotel.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the quarto database table.
 * 
 */
@Entity
@NamedQuery(name="Quarto.findAll", query="SELECT q FROM Quarto q")
public class Quarto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idQuarto;

	private int capacidade;

	private int numero;

	private String status;

	@Column(name="valor_diaria")
	private double valorDiaria;

	//bi-directional many-to-one association to Aluguel
	@OneToMany(mappedBy="quarto")
	private List<Aluguel> alugueis;

	public Quarto() {
	}

	public int getIdQuarto() {
		return this.idQuarto;
	}

	public void setIdQuarto(int idQuarto) {
		this.idQuarto = idQuarto;
	}

	public int getCapacidade() {
		return this.capacidade;
	}

	public void setCapacidade(int capacidade) {
		this.capacidade = capacidade;
	}

	public int getNumero() {
		return this.numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getValorDiaria() {
		return this.valorDiaria;
	}

	public void setValorDiaria(double valorDiaria) {
		this.valorDiaria = valorDiaria;
	}

	public List<Aluguel> getAlugueis() {
		return this.alugueis;
	}

	public void setAlugueis(List<Aluguel> alugueis) {
		this.alugueis = alugueis;
	}
}