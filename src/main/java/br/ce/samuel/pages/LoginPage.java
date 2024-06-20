package br.ce.samuel.pages;

import org.openqa.selenium.By;

import br.ce.samuel.core.BasePage;
import br.ce.samuel.core.DriverFactory;

public class LoginPage extends BasePage {

	public void acessarTelaInicial() {
		DriverFactory.getDriver().get("https://seubarriga.wcaquino.me/login");		
	}
	
	public void setEmail(String email) {
	escreve("email", email);	
	}
	
	public void setSenha(String senha) {
		escreve("senha", senha);
	}
	
	public void entrar() {
		clicarBotao(By.xpath("//button[.='Entrar']"));		
	}
	
	public void resetar() {
		clicarLink("reset");
	}
}
