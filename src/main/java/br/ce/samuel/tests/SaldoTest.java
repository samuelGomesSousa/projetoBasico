package br.ce.samuel.tests;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.ce.samuel.core.BaseTest;
import br.ce.samuel.pages.MenuPage;
import br.ce.samuel.pages.HomePage;


//@FixMethodOrder(MethodSorters.NAME_ASCENDING) //Serve para colocar os testes para executar em ordem, Name Ascending em ordem alfab�tica
public class SaldoTest extends BaseTest {
	private MenuPage menupage = new MenuPage();
	private HomePage saldo = new HomePage();
	
	@Test
	public void test1_SaldoConta() {		
		menupage.acessarTelaHome();
		Assert.assertEquals("534.00", saldo.obterSaldoConta("Conta para saldo"));
		
		
	}
}
