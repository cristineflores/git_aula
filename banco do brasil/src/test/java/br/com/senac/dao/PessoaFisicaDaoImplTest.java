/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.dao;

import br.com.senac.entidade.PessoaFisica;
import static br.com.senac.util.GeradorUtil.*;
import java.util.List;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author marie - Java Doc
 */
public class PessoaFisicaDaoImplTest {
    
    private PessoaFisica pessoaFisica;
    private PessoaFisicaDao pessoaFisicaDao;
    private Session sessao;
    
    public PessoaFisicaDaoImplTest() {
        pessoaFisicaDao = new PessoaFisicaDaoImpl();
    }

    //@Test
    public void testPesquisarPorId() {
        System.out.println("pesquisarPorId");
        buscarPessoaFisicaBd();
        sessao = HibernateUtil.abrirConexao();
        PessoaFisica pessoaFisicaPesquisada = pessoaFisicaDao.pesquisarPorId(pessoaFisica.getId(), sessao);
        sessao.close();
        
        assertNotNull(pessoaFisicaPesquisada);
    }
    
    //@Test
    public void testSalvar() {
        System.out.println("salvar");
        pessoaFisica = new PessoaFisica(gerarNome(), gerarEmail(), gerarCpf(), gerarRG());
        
        sessao = HibernateUtil.abrirConexao();
        pessoaFisicaDao.salvarOuAlterar(pessoaFisica, sessao);
        sessao.close();
        assertNotNull(pessoaFisica.getId());
    }
    
    //@Test
    public void testAlterar() {
        System.out.println("salvar");
        buscarPessoaFisicaBd();
        
        pessoaFisica.setNome(gerarNome());
        pessoaFisica.setEmail(gerarEmail());
        pessoaFisica.setCpf(gerarCpf());
        
        sessao = HibernateUtil.abrirConexao();
        pessoaFisicaDao.salvarOuAlterar(pessoaFisica, sessao);
        sessao.close();
        
        sessao = HibernateUtil.abrirConexao();
        PessoaFisica pessoaFisicaAlt = pessoaFisicaDao.pesquisarPorId(pessoaFisica.getId(), sessao);
        sessao.close();
        assertEquals(pessoaFisica.getNome(), pessoaFisicaAlt.getNome());
        assertEquals(pessoaFisica.getEmail(), pessoaFisicaAlt.getEmail());
        assertEquals(pessoaFisica.getCpf(), pessoaFisicaAlt.getCpf());
    } 
    
    //@Test
    public void testExcluir() {
        System.out.println("excluir");
        buscarPessoaFisicaBd();
        sessao = HibernateUtil.abrirConexao();
        pessoaFisicaDao.excluir(pessoaFisica, sessao);
        
        PessoaFisica pessoaFisicaExc = pessoaFisicaDao.pesquisarPorId(pessoaFisica.getId(), sessao);
        sessao.close();
        assertNull(pessoaFisicaExc);
    }
    
    @Test
    public void testPesquisarPorNome() {
        System.out.println("pesquisarPorNome");
        buscarPessoaFisicaBd();
        sessao = HibernateUtil.abrirConexao();        
        List<PessoaFisica> pessoasFisicas = pessoaFisicaDao.pesquisarPorNome
        (pessoaFisica.getNome(), sessao);
        sessao.close();
        assertTrue(!pessoasFisicas.isEmpty());//será acertado true se minha lista não for vazia
    }
    
    public PessoaFisica buscarPessoaFisicaBd() {
        //no sql normal seria: select * from usuario;
        String hql = "from PessoaFisica";//aqui porque é um banco teste e tem poucos registros. Se for um banco populado, tem que limitar
        sessao = HibernateUtil.abrirConexao(); 
        Query<PessoaFisica> consulta = sessao.createQuery(hql);
        List<PessoaFisica> pessoasFisicas = consulta.getResultList();
        sessao.close();
        if(pessoasFisicas.isEmpty()) {//se estiver vazio chama o testeSalvar pra ter pelo menos um objeto dentro do Array
            testSalvar();
        }else {
            pessoaFisica = pessoasFisicas.get(0); //aqui pega o usuário na primeira posição, o primeiro que estiver no BD.
        }        
        return pessoaFisica;
    }    

    //@Test
    public void testPesquisarTodo() {
        System.out.println("pesquisarTodo");
        Session sessao = null;
        PessoaFisicaDaoImpl instance = new PessoaFisicaDaoImpl();
        List<PessoaFisica> expResult = null;
        List<PessoaFisica> result = instance.pesquisarTodo(sessao);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }
    
}
