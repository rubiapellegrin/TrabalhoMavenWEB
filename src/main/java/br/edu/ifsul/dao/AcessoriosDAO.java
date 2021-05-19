/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.conversores.ConverterOrdem;
import br.edu.ifsul.modelo.Acessorios;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author MASTER
 */

@Stateful
public class AcessoriosDAO<TIPO> extends DAOGenerico<Acessorios> implements Serializable  {
    
    public AcessoriosDAO(){
        super();
        classePersistente = Acessorios.class;
        
        listaOrdem.add(new Ordem("id", "ID", "="));
        listaOrdem.add(new Ordem("descricao", "Descricao", "like"));
        //listaOrdem.add(new Ordem("estado.nome", "Estado", "like"));
        // difinir a ordem inicial
        ordemAtual = listaOrdem.get(1);
        // inicializar o conversor das ordens
        converterOrdem = new ConverterOrdem();
        converterOrdem.setListaOrdem(listaOrdem);  
    }
    
}
