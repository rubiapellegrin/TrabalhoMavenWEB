/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.AcessoriosDAO;
import br.edu.ifsul.dao.CarroDAO;
import br.edu.ifsul.dao.PessoaDAO;
import br.edu.ifsul.modelo.Acessorios;
import br.edu.ifsul.modelo.Carro;
import br.edu.ifsul.modelo.Cobertura;
import br.edu.ifsul.modelo.Pessoa;
import br.edu.ifsul.util.Util;
import br.edu.ifsul.util.UtilRelatorios;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author MASTER
 */
@Named(value = "controleCarro")
@ViewScoped
public class ControleCarro implements Serializable {

    @EJB
    private CarroDAO<Carro> dao;
    private Carro objeto;
    @EJB
    private PessoaDAO<Pessoa> daoPessoa;
    @EJB
    private AcessoriosDAO<Acessorios> daoAcessorios;
    private Acessorios acessorio;

    public ControleCarro() {

    }

    public void removerAcessorio(Acessorios obj) {
        objeto.getAcessorios().remove(obj);
        Util.mensagemInformacao("Acessorio removida com sucesso!");
    }

    public void adicionarAcessorio() {
        if (!objeto.getAcessorios().contains(acessorio)) {
            objeto.getAcessorios().add(acessorio);
            Util.mensagemInformacao("Acessorio adicionada com sucesso!");
        } else {
            Util.mensagemErro("Carro ja possui esta acessorio! ");
        }
    }

    public void imprimeCarros() {
        HashMap parametros = new HashMap();
        UtilRelatorios.imprimeRelatorio("relatorioCarros", parametros, dao.getListaObjetosCompleta());
    }

    public void imprimeCarro(Object id) {
        try {
            objeto = dao.getObjectByID(id);
            List<Carro> lista = new ArrayList<>();
            lista.add(objeto);
            HashMap parametros = new HashMap();
            UtilRelatorios.imprimeRelatorio("relatorioCarros", parametros, lista);
        } catch (Exception e) {
            Util.mensagemInformacao("Erro ao imprimir: " + Util.getMensagemErro(e));
        }
    }

    public String listar() {
        return "/privado/carro/listar?faces-redirect=true";
    }

    public void novo() {
        objeto = new Carro();
    }

    public void alterar(Object id) {
        try {
            objeto = dao.getObjectByID(id);
        } catch (Exception e) {
            Util.mensagemInformacao(" Erro ao recuperar objeto " + Util.getMensagemErro(e));
        }
    }

    public void excluir(Object id) {
        try {
            objeto = dao.getObjectByID(id);
            dao.remove(objeto);
            Util.mensagemInformacao("Objeto removido com sucesso!");
        } catch (Exception e) {
            Util.mensagemInformacao(" Erro ao excluir objeto " + Util.getMensagemErro(e));
        }
    }

    public void salvar() {
        try {
            if (objeto.getId() == null) {
                dao.persist(objeto);
            } else {
                dao.merge(objeto);
            }

            Util.mensagemInformacao("Objeto persistido com sucesso!");
        } catch (Exception e) {
            Util.mensagemInformacao(" Erro ao salvar objeto " + Util.getMensagemErro(e));
        }

    }

    public CarroDAO<Carro> getDao() {
        return dao;
    }

    public void setDao(CarroDAO<Carro> dao) {
        this.dao = dao;
    }

    public Carro getObjeto() {
        return objeto;
    }

    public void setObjeto(Carro objeto) {
        this.objeto = objeto;
    }

    public PessoaDAO<Pessoa> getDaoPessoa() {
        return daoPessoa;
    }

    public void setDaoPessoa(PessoaDAO<Pessoa> daoPessoa) {
        this.daoPessoa = daoPessoa;
    }

    public Acessorios getAcessorio() {
        return acessorio;
    }

    public void setAcessorio(Acessorios acessorio) {
        this.acessorio = acessorio;
    }


    public AcessoriosDAO<Acessorios> getDaoAcessorios() {
        return daoAcessorios;
    }

    public void setDaoAcessorios(AcessoriosDAO<Acessorios> daoAcessorios) {
        this.daoAcessorios = daoAcessorios;
    }
}
