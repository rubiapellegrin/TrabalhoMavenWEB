/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.CarroDAO;
import br.edu.ifsul.dao.CorretorDAO;
import br.edu.ifsul.dao.SeguroDAO;
import br.edu.ifsul.modelo.Carro;
import br.edu.ifsul.modelo.Cobertura;
import br.edu.ifsul.modelo.Corretor;
import br.edu.ifsul.modelo.Seguro;
import br.edu.ifsul.util.Util;
import br.edu.ifsul.util.UtilRelatorios;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author MASTER
 */

@Named(value = "controleSeguro")
@ViewScoped
public class ControleSeguro implements Serializable{


    @EJB
    private SeguroDAO<Seguro> dao;
    private Seguro objeto;
    @EJB
    private CorretorDAO<Corretor> daoCorretor;
    @EJB
    private CarroDAO<Carro> daoCarro;

    private Cobertura cobertura;
    private Boolean novoCobertura;

    public ControleSeguro(){
    
    }

    public void imprimeSeguros() {
        HashMap parametros = new HashMap();
        UtilRelatorios.imprimeRelatorio("relatorioSeguros", parametros, dao.getListaObjetosCompleta());
    }

    public void imprimeSeguro(Object id) {
        try {
            objeto = dao.getObjectByID(id);
            List<Seguro> lista = new ArrayList<>();
            lista.add(objeto);
            HashMap parametros = new HashMap();
            UtilRelatorios.imprimeRelatorio("relatorioSeguros", parametros, lista);
        } catch (Exception e) {
            Util.mensagemInformacao("Erro ao imprimir: " + Util.getMensagemErro(e));
        }
    }

     public void novoCobertura(){
        cobertura = new Cobertura();
        novoCobertura = true;
    }
    
    public void alterarCobertura(int index){
        cobertura = objeto.getCoberturas().get(index);
        novoCobertura = false;
    }
    
    public void salvarCobertura(){
        if (novoCobertura){
            objeto.adicionarCobertura(cobertura);
        }
        Util.mensagemInformacao("Cobertura adicionado ou alterado com sucesso!");
    }
    
    public void removerCobertura(int index){
        objeto.removerCobertura(index);
        Util.mensagemInformacao("Cobertura removido com sucesso!");
    }
    
    
    public String listar(){
        return "/privado/seguro/listar?faces-redirect=true";
    }
    
    public void novo(){
       objeto = new Seguro();
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
        try {
            if (objeto.getId() == null){
                dao.persist(objeto);
            } else {
                dao.merge(objeto);
            }
            Util.mensagemInformacao("Objeto persistido com sucesso!");
        } catch (Exception e){
            Util.mensagemInformacao("Erro ao salvar objeto: " + Util.getMensagemErro(e));
        }
        
    }   

    public SeguroDAO<Seguro> getDao() {
        return dao;
    }

    public void setDao(SeguroDAO<Seguro> dao) {
        this.dao = dao;
    }

    public Seguro getObjeto() {
        return objeto;
    }

    public void setObjeto(Seguro objeto) {
        this.objeto = objeto;
    }

    public CorretorDAO<Corretor> getDaoCorretor() {
        return daoCorretor;
    }

    public void setDaoCorretor(CorretorDAO<Corretor> daoCorretor) {
        this.daoCorretor = daoCorretor;
    }

    public CarroDAO<Carro> getDaoCarro() {
        return daoCarro;
    }

    public void setDaoCarro(CarroDAO<Carro> daoCarro) {
        this.daoCarro = daoCarro;
    }

    public Cobertura getCobertura() {
        return cobertura;
    }

    public void setCobertura(Cobertura cobertura) {
        this.cobertura = cobertura;
    }

    public Boolean getNovoCobertura() {
        return novoCobertura;
    }

    public void setNovoCobertura(Boolean novoCobertura) {
        this.novoCobertura = novoCobertura;
    }
}
