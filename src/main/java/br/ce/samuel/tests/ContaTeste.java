package br.ce.samuel.tests;

import org.junit.Assert;
import org.junit.Test;
import br.ce.samuel.core.BaseTest;
import br.ce.samuel.pages.ContasPage ;
import br.ce.samuel.pages.MenuPage;
import org.junit.runners.MethodSorters;
import org.junit.FixMethodOrder;

//@FixMethodOrder(MethodSorters.NAME_ASCENDING) //Serve para colocar os testes para executar em ordem, Name Ascending em ordem alfabética
public class ContaTeste extends BaseTest {
	private MenuPage menupage = new MenuPage();
	private ContasPage contasPage = new ContasPage();

	
	@Test
	public void test1_InserirConta() {
		menupage.acessarTelaInserirConta();
		contasPage.setNome("conta corrente");
		contasPage.salvar();
		Assert.assertEquals("Conta adicionada com sucesso!", contasPage.obterMensagemSucesso());
	}
	
	@Test
	public void test2_InserirContaExistente() {
		menupage.acessarTelaInserirConta();
		contasPage.setNome("Conta mesmo nome");
		contasPage.salvar();
		Assert.assertEquals("Já existe uma conta com esse nome!", contasPage.obterMensagemErro());
	}
	
	@Test
	public void test3_AlterarConta() {
		menupage.acessarTelaListarConta();		
		contasPage.clicarAlterarConta("Conta para alterar");
		contasPage.setNome("Conta atualizada");
		contasPage.salvar();
		Assert.assertEquals("Conta alterada com sucesso!", contasPage.obterMensagemSucesso());
	}
	
	
	
	
}
