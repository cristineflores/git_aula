/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.dao;

import br.com.senac.entidade.PessoaJuridica;
import org.hibernate.Session;

/**
 *
 * @author marie
 */
interface PessoaJuridicaDao {

    public void salvarOuAlterar(PessoaJuridica pessoaJuridica, Session sessao);

    
    
}
