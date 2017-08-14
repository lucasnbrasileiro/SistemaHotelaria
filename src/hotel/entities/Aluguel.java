package hotel.entities;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * The persistent class for the aluguel database table.
 * 
 */
@Entity
public class Aluguel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idAluguel;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_entrada")
	private Date dataEntrada;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_saida")
	private Date dataSaida;

	@Column(name = "qtd_pessoas")
	private int qtdPessoas;

	// bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name = "idUsuario")
	private Usuario usuario;

	// bi-directional many-to-one association to Quarto
	@ManyToOne
	@JoinColumn(name = "idQuarto")
	private Quarto quarto;

	// bi-directional many-to-one association to Consumo
	@OneToMany(mappedBy = "aluguel", fetch = FetchType.EAGER)
	private List<Consumo> consumos;
	
	
	public Aluguel() {
	}

	public int getIdAluguel() {
		return this.idAluguel;
	}

	public void setIdAluguel(int idAluguel) {
		this.idAluguel = idAluguel;
	}

	public Date getDataEntrada() {
		return this.dataEntrada;
	}

	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public Date getDataSaida() {
		return this.dataSaida;
	}

	public void setDataSaida(Date dataSaida) {
		this.dataSaida = dataSaida;
	}

	public int getQtdPessoas() {
		return this.qtdPessoas;
	}

	public void setQtdPessoas(int qtdPessoas) {
		this.qtdPessoas = qtdPessoas;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Quarto getQuarto() {
		return this.quarto;
	}

	public void setQuarto(Quarto quarto) {
		this.quarto = quarto;
	}

	public List<Consumo> getConsumos() {
		return this.consumos;
	}

	public void setConsumos(List<Consumo> consumos) {
		this.consumos = consumos;
	}
}