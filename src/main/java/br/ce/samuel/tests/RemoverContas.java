package br.ce.samuel.tests;

import org.junit.Test;

import br.ce.samuel.core.BaseTest;
import br.ce.samuel.pages.ContasPage;
import br.ce.samuel.pages.MenuPage;

public class RemoverContas extends BaseTest {	
		private MenuPage menuPage = new MenuPage();
		private ContasPage contasPage = new ContasPage();
		
		@Test
		public void removerConta() {
			menuPage.acessarTelaListarConta();
			
			contasPage.clicarExcluirConta("Conta atualizada");
		}

	}


