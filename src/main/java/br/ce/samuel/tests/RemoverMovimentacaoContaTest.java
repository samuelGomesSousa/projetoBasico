package br.ce.samuel.tests;

import org.junit.Assert;
import org.junit.Test;

import br.ce.samuel.core.BaseTest;
import br.ce.samuel.pages.ContasPage;
import br.ce.samuel.pages.MenuPage;

public class RemoverMovimentacaoContaTest extends BaseTest{
	private MenuPage menuPage = new MenuPage();
	private ContasPage contasPage = new ContasPage();
	
	@Test
	public void testExcluirContaComMovimentacao() {
	menuPage.acessarTelaListarConta();
	contasPage.clicarExcluirConta("Conta com movimentacao");
	
	Assert.assertEquals("Conta em uso na movimentações", contasPage.obterMensagemErro());
	}

}
