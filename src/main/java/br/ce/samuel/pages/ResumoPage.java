package br.ce.samuel.pages;

import org.junit.Assert;
import org.openqa.selenium.By;

import br.ce.samuel.core.BasePage;

public class ResumoPage extends BasePage{
	
	public void setMes(String valor) {
		selecionarCombo("mes", valor);
	}
	
	public void setAno(String valor) {
		selecionarCombo("ano", valor);
	}
	
	public void buscar() {
		clicarBotao(By.xpath("//input[@value='Buscar']"));
	}
	
	public void excluirMovimentacao(String colunaBusca, String valorLinha, String colunaBotao) {
		clicarBotaoTabela("tabelaExtrato", colunaBusca, valorLinha, colunaBotao, "//span[@class='glyphicon glyphicon-remove-circle']");
		
	}
	
	public String obterMensagemSucesso() {
		return obterTexto(By.xpath("//div[@class='alert alert-success']"));
	}
	
	
	/*Solução curso*/
	
	public void excluirMovi() {
		clicarBotao(By.xpath("//span[@class='glyphicon glyphicon-remove-circle']"));
		
		
	}	
	
}
