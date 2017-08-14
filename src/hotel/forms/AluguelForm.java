package hotel.forms;

import hotel.entities.Aluguel;
import hotel.entities.Consumo;
import hotel.entities.Quarto;
import hotel.entities.Usuario;
import hotel.persistence.AluguelDao;
import hotel.persistence.ConsumoDao;
import hotel.persistence.QuartoDao;

import java.io.Serializable;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class AluguelForm implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Aluguel a = new Aluguel();
	private Usuario u = new Usuario();
	private Quarto q = new Quarto();

	private Date dataEntrada = a.getDataEntrada();
	private Date dataSaida = a.getDataSaida();
	private int qtdPessoas = a.getQtdPessoas();
	private int capacidadePessoas = q.getCapacidade();

	private int idAluguel = 0;
	private List<Aluguel> alugueis;
	private List<Consumo> consumos;
	
	

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

	public void setAlugueis(List<Aluguel> a) {
		this.alugueis = a;
	}

	public void setConsumos(List<Consumo> c) {
		this.consumos = c;
	}

	public String inserir() throws SQLException {

		int idUsuario = u.getIdUsuario();
		int idQuarto = q.getIdQuarto();
		int quantPessoas = a.getQtdPessoas();
		Date dataIni = dataEntrada;
		Date dataFim = dataSaida;

		AluguelDao ad = new AluguelDao();
		ad.inserir(idUsuario, idQuarto, quantPessoas, dataIni, dataFim);

		QuartoDao qd = new QuartoDao();
		qd.atualizar(idQuarto);

		return "Sessao";
	}

	public String cadastroAluguel() {
		// FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		dataEntrada = null;
		dataSaida = null;
		qtdPessoas = 0;
		return "EfetuarAluguel";
	}

	public String finalizarContaCliente() {
		
		alugueis = null;
		idAluguel = 0;
		return "FinalizarConta";
	}

	public List<Aluguel> getAlugueis() throws SQLException {

		AluguelDao ad = new AluguelDao();
		return ad.list();
	}

	public List<Quarto> getQuartos() throws SQLException {

		QuartoDao qd = new QuartoDao();
		return qd.list();
	}

	public List<Consumo> getConsumos() {

		if (consumos == null) {
			ConsumoDao cd = new ConsumoDao();
			consumos = cd.list(idAluguel);
		}
		return consumos;
	}

	public List<Aluguel> getDiaria() throws SQLException {

		if (alugueis == null) {
			AluguelDao ad = new AluguelDao();
			alugueis = ad.listDiaria(idAluguel);
		}

		return alugueis;
	}

	public double getTotalGasto() {
		double total = 0;
		for (Consumo c : consumos) {
			total += c.getValorTotal();
		}
		return total;
	}

	public List<Aluguel> getAluguelUsuario() throws SQLException {

		AluguelDao ad = new AluguelDao();
		idAluguel = a.getIdAluguel();
		return ad.listUsuario(idAluguel);
	}

	public double getTotalDiaria() throws SQLException {

		double diaria = 0;
		int numeroDiarias = 0;
		String quantDiarias = null;

		for (int i = 0; i < alugueis.size(); i++) {

			diaria = getAluguelUsuario().get(i).getQuarto().getValorDiaria();
			quantDiarias = String.valueOf(getDiaria().get(i));

		}

		numeroDiarias = Integer.valueOf(quantDiarias);

		return diaria * numeroDiarias;
	}

	public double getTotalPagar() throws SQLException {

		return (getTotalGasto() + getTotalDiaria());

	}

	public void validarDataInicial(FacesContext context,
			UIComponent component, Object value) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Date dataAtual = new Date();
		sdf.format(dataAtual);
		
		dataEntrada = (Date) value;

		if (dataEntrada.before(dataAtual)) {

			
		}

	}

	public void validarDataFinal(FacesContext context,
			UIComponent component, Object value) {

		dataEntrada = (Date) value;
		dataSaida = (Date) value;

		if (dataSaida.after(dataEntrada) == true){

			//A data é válida.
		}

	}
		
	public void validarQuantidade() {

		if (qtdPessoas > capacidadePessoas) {
			// Mensagem de erro tratado na tela
			new FacesMessage(
					"Não se pode alugar um quarto com quantidade acima da capacidade permitida!");
		} else {
			// Mensagem de erro tratado na tela
			new FacesMessage("Quantidade válida!");
		}
	}
}
