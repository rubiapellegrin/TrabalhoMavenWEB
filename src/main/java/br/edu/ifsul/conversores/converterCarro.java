/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.conversores;

import br.edu.ifsul.modelo.Carro;
import br.edu.ifsul.modelo.Pessoa;
import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.faces.convert.Converter;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.faces.context.FacesContext;
import javax.faces.component.UIComponent;
/**
 *
 * @author MASTER
 */


@Named(value = "converterCarro")
@RequestScoped

public class converterCarro implements Serializable, Converter{
   
    @PersistenceContext(unitName = "TrabalhoMavenWebPU")
    protected EntityManager em;
    
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string){
        if(string == null || string.equals("Selecione um registro")){
            return null;
        }
        return em.find(Carro.class, Integer.parseInt(string));
    }
    
    
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object t){
        if(t == null){
            return null;
        }
        Carro obj = (Carro) t;
        return obj.getId().toString();
        
    }
}
