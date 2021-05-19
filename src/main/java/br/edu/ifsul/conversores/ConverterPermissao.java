package br.edu.ifsul.conversores;

import br.edu.ifsul.modelo.Permissao;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Named(value = "converterPermissao")
@RequestScoped
public class ConverterPermissao implements Serializable, Converter {

    @PersistenceContext(unitName = "TrabalhoMavenWebPU")
    protected EntityManager em;    
    
    // converte da tela para o objeto
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (string == null || string.equals("Selecione um registro")){
            return null;
        }
        return em.find(Permissao.class, string);
    }

    // converte do objeto para a tela
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object t) {
        if (t == null){
            return null;
        }
        Permissao obj = (Permissao) t;
        return obj.getNome();
    }

}
