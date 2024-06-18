package bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import dao.CampeonatoDao;
import dao.JogoDAO;
import entities.Campeonato;
import entities.Jogo;

@ManagedBean
public class JogoBean {
	private Jogo jogo = new Jogo();
	private List<Jogo> lista;
	
	private List<Campeonato> campeonatos;
	private Integer idCampeonatoSelecionado;
	
	public JogoBean() {
		this.campeonatos = CampeonatoDao.listar();
	}

	public String salvar() {
		if(jogo.getTime1() == jogo.getTime2()) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Os times não podem ser iguais!"));
			return null;
		}
		if (jogo != null) {
			jogo.setCampeonato(CampeonatoDao.busrcarPorId(idCampeonatoSelecionado));
			JogoDAO.salvar(jogo);
			jogo = new Jogo();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Jogo criado!"));
			return "listagem.xhtml";

		}
		return null;
	}

	public void editar(Jogo jogoEditar) {
		Jogo jogoOriginal = getJogoPorId(jogoEditar.getId());
		if (jogoEditar != null) {
			JogoDAO.editar(jogoEditar);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Jogo atualizado!"));
			lista = JogoDAO.listar();
		}
	}

	public String excluir(int id) {

		JogoDAO.excluir(id);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Jogo excluído!"));
		lista = JogoDAO.listar();
		return "listagem.xthml";

	}
	
	public Jogo getJogoPorId(int id) {
		return JogoDAO.getJogoPorId(id);
	}

	public List<Jogo> getLista() {
		return JogoDAO.listar();
	}

	public void setLista(List<Jogo> lista) {
		this.lista = lista;
	}

	public Jogo getJogo() {
		return jogo;
	}

	public void setJogo(Jogo jogo) {
		this.jogo = jogo;
	}

	public List<Campeonato> getCampeonatos() {
		return campeonatos;
	}

	public void setCampeonatos(List<Campeonato> campeonatos) {
		this.campeonatos = campeonatos;
	}

	public Integer getIdCampeonatoSelecionado() {
		return idCampeonatoSelecionado;
	}

	public void setIdCampeonatoSelecionado(Integer idCampeonatoSelecionado) {
		this.idCampeonatoSelecionado = idCampeonatoSelecionado;
	}
	
	
	
}
