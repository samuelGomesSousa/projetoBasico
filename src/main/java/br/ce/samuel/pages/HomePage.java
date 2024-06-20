package br.ce.samuel.pages;

import br.ce.samuel.core.BasePage;

public class HomePage extends BasePage {

	public String obterSaldoConta(String conta) {
		
		return obterCelula("tabelaSaldo","Conta", conta , "Saldo").getText();
		
		
	}
}
