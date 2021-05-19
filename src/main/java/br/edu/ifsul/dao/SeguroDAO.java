/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.conversores.ConverterOrdem;
import br.edu.ifsul.modelo.Seguro;
import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateful;

/**
 *
 * @author MASTER
 */

@Stateful
public class SeguroDAO<TIPO> extends DAOGenerico<Seguro> implements Serializable {
    
    public SeguroDAO(){
        super();
        classePersistente = Seguro.class;
        
        listaOrdem.add(new Ordem("id", "ID", "="));
        //listaOrdem.add(new Ordem("estado.nome", "Estado", "like"));
        // difinir a ordem inicial
        ordemAtual = listaOrdem.get(0);
        // inicializar o conversor das ordens
        converterOrdem = new ConverterOrdem();
        converterOrdem.setListaOrdem(listaOrdem);  
    }
    public Seguro getObjectByID(Object id) throws Exception {
        Seguro obj = em.find(Seguro.class, id);
        // uso para evitar o erro de lazy inicialization exception]
        obj.getCoberturas().size();
        return obj;
    } 
    
    public List<Seguro> getListaObjetosCompleta(){
        String jpql = "select distinct t from Seguro t order by t.id";
        return em.createQuery(jpql).getResultList();
    }

    
}
