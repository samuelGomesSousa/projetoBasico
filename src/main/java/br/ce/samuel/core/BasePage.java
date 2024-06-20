package br.ce.samuel.core;

import static br.ce.samuel.core.DriverFactory.getDriver;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class BasePage {

	//******************* TEXTFIELD E TEXTAREA ***********************
	public void escreve(By by, String texto) {
		//apaga o que estava escrito no campo		
		getDriver().findElement(by).sendKeys(texto);		
	}
	
	public void escreve(String id_campo, String texto) {
		//apaga o que estava escrito no campo
		getDriver().findElement(By.id(id_campo)).clear();
		getDriver().findElement(By.id(id_campo)).sendKeys(texto);		
	}
	
	public String obterValorCampo(String id_campo) {
		return getDriver().findElement(By.id(id_campo)).getAttribute("value");	
	}
	
	public String obterValorCampo(By by) {
		return getDriver().findElement(by).getAttribute("value");	
	}
	
	// ******************** RADIO E CHECKBOX*******************
	public void clicarRadio(By by) {
		getDriver().findElement(by).click();			
	}
	
	public void clicarRadio(String id) {
		clicarRadio(By.id(id));
	}
	
	public boolean isRadioMarcado(String id){
		return getDriver().findElement(By.id(id)).isSelected();
	}
	
	// ********************** Combo *******************
	public void selecionarCombo(String id, String valor) {
		WebElement element = getDriver().findElement(By.id(id));		
		Select combo = new Select(element);
		combo.selectByVisibleText(valor);		
	}
	
	public void selecionarCombo(By by, String valor) {
		WebElement element = getDriver().findElement(by);		
		Select combo = new Select(element);
		combo.selectByVisibleText(valor);		
	}
	 
	public void deselecionarCombo(String id, String valor) {
		WebElement element = getDriver().findElement(By.id(id));		
		Select combo = new Select(element);
		combo.deselectByVisibleText(valor);		
	}
	
	
	public String obterValorCombo(String id) {
		WebElement element = getDriver().findElement(By.id(id));		
		Select combo = new Select(element);
		return combo.getFirstSelectedOption().getText();
	}
	
	public void selecionarComproPrime(String radical, String valor) {
		clicarRadio(By.xpath("//*[@id='"+radical+"_input']/../..//span"));
		clicarRadio(By.xpath("//*[@id='"+radical+"_items']//li[.='"+valor+"']"));
	}
	
	//******************** BOTÕES ********************************
	public void clicarBotao(String id) {
		getDriver().findElement(By.id(id)).click();		
	}
	
	public void clicarBotao(By by) {
		getDriver().findElement(by).click();	
	}
	
	// ********************** LINKS *****************
	public void clicarLink(String id) {
		getDriver().findElement(By.linkText(id)).click();		
	}
	
	
	// ******************** TEXTO ***********************
	public String obterTexto(By by) {
		 return getDriver().findElement(by).getText();
	}
	
	public String obterTexto(String id) {
		 return getDriver().findElement(By.id(id)).getText();
	}
	
	// ************************** ALERTS *******************
	public String alertaObterTexto() {
		Alert alert = getDriver().switchTo().alert();	
		//Assert.fail();		
		return  alert.getText();
	}
		
	public String alertaObterTextoEAceita() {
		Alert alert = getDriver().switchTo().alert();	
		String valor = alert.getText();
		
		alert.accept();
		//Assert.fail();		
		return valor;
	}
	
	public String alertaObterTextoENega() {
		Alert alert = getDriver().switchTo().alert();	
		String valor = alert.getText();
		alert.dismiss();	
		return valor;
	}
	
	public void alertaEscreve(String valor) {
		Alert alert = getDriver().switchTo().alert();
		alert.sendKeys(valor);
		alert.accept();
	}	
				
	// ************************ FRAMES E JANELAS ******************************
	public void entrarFrame(String valor) {
		getDriver().switchTo().frame(valor);
	}
	public void sairFrame() {
		//retorna o foco para a tela principal		
		getDriver().switchTo().defaultContent();
	}
	
	public void trocarJanela(String id) {		
		//muda o foco para popup
		getDriver().switchTo().window(id);	
		//getDriver().findElement(By.tagName("textarea")).sendKeys("Deu certo!");
		//getDriver().close();
	}
	
	//********************** JS ****************************
	public Object executarJs(String cmd, Object... param) {
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		return js.executeScript(cmd, param);
	}
	
	// ********************* Tabela ***********************
	
	
	public WebElement obterCelula(String idTabela, String colunaBusca, String valor, String colunaBotao ) {
		//procurar coluna
		WebElement tabela = getDriver().findElement(By.xpath("//*[@id='"+idTabela+"']")); //vai na tabela
		int idColuna = obterIndiceColuna(colunaBusca, tabela);
		
		//encontrar a linha do registro 
		int idLinha = obterIndiceLinha(valor, tabela, idColuna);
		
		// procurar coluna do botao
		int idColunaBotao = obterIndiceColuna(colunaBotao, tabela);
		
		//clicar no botao da celula encontrada
		WebElement celula = tabela.findElement(By.xpath(".//tr["+idLinha+"]/td["+idColunaBotao+"]"));
		return celula;
	}

	
	
	
	public void clicarBotaoTabela(String idTabela, String colunaBusca, String valor, String colunaBotao,String linkBotao) {
		
		//clicar no botao da celula encontrada
		WebElement celula = obterCelula(idTabela, colunaBusca, valor, colunaBotao);
		celula.findElement(By.xpath(linkBotao)).click();		
	}

	private int obterIndiceLinha(String valor, WebElement tabela, int idColuna) {
		List<WebElement> linhas = tabela.findElements(By.xpath(".//tbody/tr/td["+idColuna+"]"));
		int idLinha =-1;
		for(int i = 0; i < linhas.size(); i++) {
			if(linhas.get(i).getText().equals(valor)) {				
				idLinha = i+1;
				break;
			}
		}
		return idLinha;
	}

	private int obterIndiceColuna(String coluna, WebElement tabela) {
		List<WebElement> colunas =  tabela.findElements(By.xpath(".//th")); //vai na coluna
		int idColuna =-1;
		for(int i = 0; i < colunas.size(); i++) {
			if(colunas.get(i).getText().equals(coluna)) {
				idColuna = i+1;
				break;
			}
		}
		return idColuna;
	}
}
