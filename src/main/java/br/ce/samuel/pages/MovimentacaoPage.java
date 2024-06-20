package br.ce.samuel.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import br.ce.samuel.core.BasePage;
import br.ce.samuel.core.DriverFactory;
 

import java.util.ArrayList;
import java.util.List;;

public class MovimentacaoPage extends BasePage {
	
	public void selecionaTipoMovimentacao(String valor) {
		selecionarCombo("tipo", valor);
	}
	
	public void selecionaConta(String valor) {
		selecionarCombo("conta", valor);
	}
	
	public void setDataMovimentacao(String valor) {
		escreve("data_transacao", valor);
	}
	
	public void setDataPagamento(String valor) {
		escreve("data_pagamento", valor);
	}
	
	public void setDescricao(String valor) {
		escreve("descricao", valor);
	}
	
	public void setInteressado(String valor) {
		escreve("interessado", valor);
		}
		
	public void setValor(String valor) {
		escreve("valor", valor);
	}
	
	public void setStatusPago() {
		clicarRadio(By.xpath("//*[@id='status_pago']"));
	}
	
	public void setStatusPendente() {
		clicarRadio(By.xpath("//*[@id='status_pendente']"));
	}

	public void salvar() {
		clicarBotao(By.xpath("//button[.='Salvar']"));
	}
	
	public String obterMensagemSucesso() {
		return obterTexto(By.xpath("//div[@class='alert alert-success']"));
	}
	
	public String obterMensagemErro() {
		return obterTexto(By.xpath("//div[@class='alert alert-danger']//li"));
	}
	
	public List<String> obterErros(){
		List<WebElement> erros = DriverFactory.getDriver().findElements(By.xpath("//div[@class='alert alert-danger']//li"));
		List <String> retorno = new ArrayList<String>();
		for(WebElement erro:erros) {
			retorno.add(erro.getText());
		}
		return retorno;
	}	
	
}
