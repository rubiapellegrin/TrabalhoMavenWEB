/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.PessoaDAO;
import br.edu.ifsul.modelo.Pessoa;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author MASTER
 */

@Named(value = "controlePessoa")
@ViewScoped
public class ControlePessoa implements Serializable{
    
    @EJB
    private PessoaDAO<Pessoa> dao;
    private Pessoa objeto;

    public ControlePessoa(){
    
    }
          
    public String listar(){
        return "privado/pessoa/listar?faces-redirect=true";
    }
    
    public void novo(){
       objeto = new Pessoa();
    }
    
    public void alterar(Object id){
        try{
            objeto = dao.getObjectByID(id);
        } catch(Exception e){
            Util.mensagemInformacao(" Erro ao recuperar objeto " + Util.getMensagemErro(e));
        }
    }
    
    public void excluir(Object id){
        try{
            objeto = dao.getObjectByID(id);
            dao.remove(objeto);
            Util.mensagemInformacao("Objeto removido com sucesso!");
        } catch(Exception e){
            Util.mensagemInformacao(" Erro ao excluir objeto " + Util.getMensagemErro(e));
        }
    } 
    
    public void salvar(){
        try{
            if(objeto.getId() == null){
                dao.persist(objeto);
            } else {
                dao.merge(objeto);
            }
            
            Util.mensagemInformacao("Objeto persistido com sucesso!");
        } catch(Exception e){
            Util.mensagemInformacao(" Erro ao salvar objeto " + Util.getMensagemErro(e));
        }  
        
    }   
    
    public PessoaDAO<Pessoa> getDao() {
        return dao;
    }

    public void setDao(PessoaDAO<Pessoa> dao) {
        this.dao = dao;
    }

    public Pessoa getObjeto() {
        return objeto;
    }

    public void setObjeto(Pessoa objeto) {
        this.objeto = objeto;
    }  
    
}
