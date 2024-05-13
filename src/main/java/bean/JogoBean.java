package bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import dao.JogoDAO;
import entities.Jogo;

@ManagedBean
public class JogoBean {
	private Jogo jogo = new Jogo();
	private List<Jogo> lista;
	private int v1;
	private int v2;
	private int v3;
	private int v4;
	private int v5;

	

	private String save() {
		JogoDAO.save(jogo);
		jogo = new Jogo();
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Jogo criado!"));
		return null;
	}

	public String edit() {
		JogoDAO.edit(jogo);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Jogo atualizado!"));
		return null;
	}

	public String delete() {
		JogoDAO.delete(jogo.getId());
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Jogo excluído!"));
		return null;
	}

	public List<Jogo> getLista() {
		if (lista == null) {
			lista = JogoDAO.listar();
		}

		return lista;
	}
	
	 public void validateNumber(FacesContext context, UIComponent component, Object value) {
	        int number = (Integer) value;
	        if (number < 1 || number > 10) {
	            FacesMessage message = new FacesMessage("Número inválido: deve ser entre 1 e 10.");
	            throw new ValidatorException(message);
	        }
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

	public int getV1() {
		return v1;
	}

	public void setV1(int v1) {
		this.v1 = v1;
	}

	public int getV2() {
		return v2;
	}

	public void setV2(int v2) {
		this.v2 = v2;
	}

	public int getV3() {
		return v3;
	}

	public void setV3(int v3) {
		this.v3 = v3;
	}

	public int getV4() {
		return v4;
	}

	public void setV4(int v4) {
		this.v4 = v4;
	}

	public int getV5() {
		return v5;
	}

	public void setV5(int v5) {
		this.v5 = v5;
	}

}
