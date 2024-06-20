package br.ce.samuel.suites;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.ce.samuel.core.DriverFactory;
import br.ce.samuel.pages.LoginPage;
import br.ce.samuel.tests.ContaTeste;
import br.ce.samuel.tests.MovimentacaoTest;
import br.ce.samuel.tests.RemoverContas;
import br.ce.samuel.tests.RemoverMovimentacaoContaTest;
import br.ce.samuel.tests.ResumoTest;
import br.ce.samuel.tests.SaldoTest;


// apos colocar a ordena��o de executa��o dos metodos dentro de cada classe, esta classe ordena a execu��o das classes de teste
@RunWith(Suite.class)
@SuiteClasses({
	ContaTeste.class, 
	MovimentacaoTest.class,	
	RemoverMovimentacaoContaTest.class,
	SaldoTest.class,
	ResumoTest.class,
	RemoverContas.class
})
public class SuiteGeral {
	private static LoginPage page = new LoginPage();
	
	//para executar separado, descomentar na classe BaseTest
	
	
	@BeforeClass //antes de executar a suite, vai executar este metodo
	public static void reset() {		
		page.acessarTelaInicial();
		page.setEmail("samuel@gmail.com");
		page.setSenha("combatarms");
		page.entrar();
		
		page.resetar(); //clica no botão reset para recuperar toda a massa
		
		//DriverFactory.killDriver();
		
	}
	
}
