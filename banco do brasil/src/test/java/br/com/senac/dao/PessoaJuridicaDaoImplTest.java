/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.dao;

import br.com.senac.entidade.PessoaJuridica;
import br.com.senac.util.GeradorUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author marie
 */
public class PessoaJuridicaDaoImplTest {
    
    private PessoaJuridica pessoaJuridica;
    private PessoaJuridicaDao pessoaJuridicaDao;
    private Session sessao;
    
    public PessoaJuridicaDaoImplTest() {
        pessoaJuridicaDao = new PessoaJuridicaDaoImpl();
    }

    @Test
    public void testSalvar() {
        System.out.println("salvar");        
        pessoaJuridica = new PessoaJuridica("Empresa" + GeradorUtil.gerarNome(),
                GeradorUtil.gerarLogin() + "gmail.com", GeradorUtil.gerarCnpj(),
                GeradorUtil.gerarNumero(6));
        sessao = HibernateUtil.abrirConexao();
        pessoaJuridicaDao.salvarOuAlterar(pessoaJuridica, sessao);
        sessao.close();
        assertNotNull(pessoaJuridica.getId());
    }
    
    public PessoaJuridica buscarPessoaJuridicaBd() {
        //no sql normal seria: select * from usuario;
        String hql = "from Pessoa Juridica";
        sessao = HibernateUtil.abrirConexao(); 
        Query<PessoaJuridica> consulta = sessao.createQuery(hql);
        List<PessoaJuridica> pessoasJuridicas = consulta.list();
        sessao.close();
        if(pessoasJuridicas.isEmpty()) {//se estiver vazio chama o testeSalvar pra ter pelo menos um objeto dentro do Array
            testSalvar();
        }else {
            pessoaJuridica = pessoasJuridicas.get(0); //aqui pega o usuário na primeira posição, o primeiro que estiver no BD.
        }        
        return pessoaJuridica;
    }    
    
    //@Test
    public void testPesquisarTodo() {
        System.out.println("pesquisarTodo");
        Session sessao = null;
        PessoaJuridicaDaoImpl instance = new PessoaJuridicaDaoImpl();
        List<PessoaJuridica> expResult = null;
        List<PessoaJuridica> result = instance.pesquisarTodo(sessao);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }
    
}
