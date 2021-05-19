/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.conversores.ConverterOrdem;
import br.edu.ifsul.modelo.Carro;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateful;

/**
 *
 * @author MASTER
 */

@Stateful
public class CarroDAO<TIPO> extends DAOGenerico<Carro> implements Serializable {
    
    public CarroDAO(){
        super();
        classePersistente = Carro.class;
        
        listaOrdem.add(new Ordem("id", "ID", "="));
        listaOrdem.add(new Ordem("placa", "Placa", "like"));
        //listaOrdem.add(new Ordem("estado.nome", "Estado", "like"));
        // difinir a ordem inicial
        ordemAtual = listaOrdem.get(1);
        // inicializar o conversor das ordens
        converterOrdem = new ConverterOrdem();
        converterOrdem.setListaOrdem(listaOrdem);  
    }
    
    public Carro getObjectByID(Object id) throws Exception {
        Carro obj = em.find(Carro.class, id);
        // uso para evitar o erro de lazy inicialization exception
        obj.getAcessorios().size();
        return obj;
    }  
    
    public List<Carro> getListaObjetosCompleta(){
        String jpql = "select distinct t from Carro t join fetch t.acessorios order by t.id";
        return em.createQuery(jpql).getResultList();
    }
    
}
