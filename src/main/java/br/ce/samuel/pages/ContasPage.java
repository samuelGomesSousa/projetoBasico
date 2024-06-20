package br.ce.samuel.pages;

import org.openqa.selenium.By;

import br.ce.samuel.core.BasePage;

public class ContasPage extends BasePage {
	public void setNome(String nome) {
		escreve("nome", nome);		
	}
	
	public void salvar() {
		clicarBotao(By.xpath("//button[.='Salvar']"));		
	}
	
	public String obterMensagemSucesso() {
		return obterTexto(By.xpath("//div[@class='alert alert-success']"));
	}
	
	public String obterMensagemErro() {
		return obterTexto(By.xpath("//*[@class='alert alert-danger']"));
	}

	public void clicarAlterarConta(String string) {
		obterCelula("tabelaContas","Conta", string , "Ações").findElement(By.xpath(".//span[@class='glyphicon glyphicon-edit']")).click();		
	}
	
	public void clicarExcluirConta(String string) {
		obterCelula("tabelaContas","Conta", string , "Ações").findElement(By.xpath(".//span[@class='glyphicon glyphicon-remove-circle']")).click();	
	}
	
	/*public void excluirConta() {
		clicarBotao(By.xpath("//span[@class='glyphicon glyphicon-remove-circle']"));
	}*/
	
	
}
