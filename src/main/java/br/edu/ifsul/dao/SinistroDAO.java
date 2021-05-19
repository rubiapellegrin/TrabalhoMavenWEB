/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.conversores.ConverterOrdem;
import br.edu.ifsul.modelo.Carro;
import br.edu.ifsul.modelo.Cobertura;
import br.edu.ifsul.modelo.Corretor;
import br.edu.ifsul.modelo.Sinistro;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateful;

/**
 *
 * @author MASTER
 */

@Stateful
public class SinistroDAO<TIPO> extends DAOGenerico<Sinistro>  implements Serializable {
    
    public SinistroDAO(){
        super();
        classePersistente = Sinistro.class;
        
        listaOrdem.add(new Ordem("id", "ID", "="));
        listaOrdem.add(new Ordem("descricao", "Descricao", "like"));
        //listaOrdem.add(new Ordem("estado.nome", "Estado", "like"));
        // difinir a ordem inicial
        ordemAtual = listaOrdem.get(1);
        // inicializar o conversor das ordens
        converterOrdem = new ConverterOrdem();
        converterOrdem.setListaOrdem(listaOrdem);  
    }
    public Sinistro getObjectByID(Object id) throws Exception {
        Sinistro obj = em.find(Sinistro.class, id);
        // uso para evitar o erro de lazy inicialization exception
        //obj.getAcessorios().size();
        return obj;
    }  
    
    public List<Sinistro> getListaObjetosCompleta(){
        String jpql = "select distinct t from Sinistro t order by t.id";
        return em.createQuery(jpql).getResultList();
    }
}
