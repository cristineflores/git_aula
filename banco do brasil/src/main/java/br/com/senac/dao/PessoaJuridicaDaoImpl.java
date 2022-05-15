/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.dao;

import br.com.senac.entidade.PessoaJuridica;
import java.io.Serializable;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author marie
 */
public class PessoaJuridicaDaoImpl extends BaseDaoImpl<PessoaJuridica, Long> 
        implements PessoaJuridicaDao, Serializable {

    @Override
    public PessoaJuridica pesquisarPorId(Long id, Session sessao) throws HibernateException {
        return sessao.find(PessoaJuridica.class, id);
    }

    List<PessoaJuridica> pesquisarTodo(Session sessao) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }    
}
