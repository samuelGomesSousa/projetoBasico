package br.ce.samuel.core;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import br.ce.samuel.pages.LoginPage;

public class BaseTest {
	private LoginPage page = new LoginPage();
	
	//serve para colocar o nome dos arquivos como o nome do m�todo
	@Rule
	public TestName testName = new TestName();	
	
	@Before //Executa sempre este metodo primeiro. Para executar m�todo por m�todo, habilitar esta fun��o.
	public void inicializa() {		
		page.acessarTelaInicial();
		page.setEmail("samu@gmail.com");
		page.setSenha("teste");
		page.entrar();
	}
	 
		 
	@After
	public void finaliza() throws IOException {		

		//serve para tirar print da tela
		TakesScreenshot ss =  (TakesScreenshot) DriverFactory.getDriver();
		File arquivo = ss.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(arquivo, new File(
				//ele grava na pasta raiz do projeto, que � target/screenshot
				//o comando File.separator serve como a barra / que divide o caminho do diretorio
				//com isso, o codigo consegue gravar em qualquer sistema  operacional no diretorio informado
				"target" +File.separator + "screenshot" +File.separator + testName.getMethodName() + ".jpg"));		
		
		if(Propriedades.FECHAR_BROWSER) {
		DriverFactory.killDriver();
		}
	}
	
}
