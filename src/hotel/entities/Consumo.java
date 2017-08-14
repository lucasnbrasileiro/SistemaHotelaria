package hotel.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the consumo database table.
 * 
 */
@Entity
public class Consumo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_Produto_Aluguel")
	private int idConsumo;

	private int qtd;

	// bi-directional many-to-one association to Produto
	@ManyToOne
	@JoinColumn(name = "idProduto")
	private Produto produto;

	// bi-directional many-to-one association to Aluguel
	@ManyToOne
	@JoinColumn(name = "idAluguel")
	private Aluguel aluguel;

	public Consumo() {
	}

	public int getIdConsumo() {
		return this.idConsumo;
	}

	public void setIdConsumo(int idConsumo) {
		this.idConsumo = idConsumo;
	}

	public int getQtd() {
		return this.qtd;
	}

	public void setQtd(int qtd) {
		this.qtd = qtd;
	}

	public Produto getProduto() {
		return this.produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Aluguel getAluguel() {
		return this.aluguel;
	}

	public void setAluguel(Aluguel aluguel) {
		this.aluguel = aluguel;
	}

	@Transient
	public double getValorTotal() {

		double total = (qtd * produto.getValor());
		return total;
	}
}