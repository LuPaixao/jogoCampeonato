package bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import dao.CampeonatoDao;
import entities.Campeonato;

@ManagedBean
public class CampeonatoBean {

	private Campeonato campeonato = new Campeonato();
	private List<Campeonato> lista;
	
	public String salvar() {
		CampeonatoDao.salvar(campeonato);
		campeonato = new Campeonato();
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Sucesso!", "Campeonato cadastrado!"));
		return "listarCampeonato.xhtml";
	}

	
	public List<Campeonato> listarCampeonatos() {
		return CampeonatoDao.listar();
	}
	
	
	
	public Campeonato getCampeonato() {
		return campeonato;
	}

	public void setCampeonato(Campeonato campeonato) {
		this.campeonato = campeonato;
	}

	public List<Campeonato> getLista() {
		return listarCampeonatos();
	}

	public void setLista(List<Campeonato> lista) {
		this.lista = lista;
	}
	
	
	
	
}
