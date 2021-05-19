/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.CorretorDAO;
import br.edu.ifsul.dao.PessoaDAO;
import br.edu.ifsul.modelo.Carro;
import br.edu.ifsul.modelo.Corretor;
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

/**
 *
 * @author MASTER
 */
@Named(value = "controleCorretor")
@ViewScoped
public class ControleCorretor implements Serializable {

    @EJB
    private CorretorDAO<Corretor> dao;
    private Corretor objeto;

    public ControleCorretor() {

    }

    public String listar() {
        return "/privado/corretor/listar?faces-redirect=true";
    }

    public void imprimeCorretores() {
        HashMap parametros = new HashMap();
        UtilRelatorios.imprimeRelatorio("relatorioCorretor", parametros, dao.getListaObjetosCompleta());
    }

    public void imprimeCorretor(Object id) {
        try {
            objeto = dao.getObjectByID(id);
            List<Corretor> lista = new ArrayList<>();
            lista.add(objeto);
            HashMap parametros = new HashMap();
            UtilRelatorios.imprimeRelatorio("relatorioCorretor", parametros, lista);
        } catch (Exception e) {
            Util.mensagemInformacao("Erro ao imprimir: " + Util.getMensagemErro(e));
        }
    }

    public void novo() {
        objeto = new Corretor();
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

    public CorretorDAO<Corretor> getDao() {
        return dao;
    }

    public void setDao(CorretorDAO<Corretor> dao) {
        this.dao = dao;
    }

    public Corretor getObjeto() {
        return objeto;
    }

    public void setObjeto(Corretor objeto) {
        this.objeto = objeto;
    }

}
