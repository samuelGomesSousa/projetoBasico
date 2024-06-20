package br.ce.samuel.core;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import br.ce.samuel.core.Propriedades.TipoExecucao;

public class DriverFactory {
	// antigo c�digo
	// server para inicializar o driver, ao inves de criar um driver por classe
	// cria-se uma classe para o driver e as demanis chamam apenas este driver.
	/*private static WebDriver driver;
	
	private DriverFactory() {		
	}
	
	public static WebDriver getDriver() {
		if(driver == null) {
			switch (Propriedades.browser) {
			case FIREFOX: driver = new FirefoxDriver();					
				break;
			case CHROME: driver = new ChromeDriver();					
				break;
			}					
		driver.manage().window().setSize(new Dimension(1200, 765));		
		}
		return driver;		
	}
	
	public static void killDriver() {
		if(driver != null) {
			driver.close();
			driver = null;
		}
	}*/
	private static ThreadLocal<WebDriver> threadDriver = new ThreadLocal<WebDriver>() {	
		@Override
		protected synchronized WebDriver initialValue() {
			return initDriver();
		}
	};
	
	
	private DriverFactory() {}	
		
	public static WebDriver getDriver() {
		return threadDriver.get();
	}
	
	
	public static WebDriver initDriver() {			
		WebDriver driver = null;		
		if(Propriedades.TIPO_EXECUCAO == TipoExecucao.LOCAL){     //verifica se o tipo da execução será na própria máquina ou no grid da classe propriedade
				switch (Propriedades.browser) {
				case FIREFOX: driver = new FirefoxDriver();					
					break;
				case CHROME: driver = new ChromeDriver();					
					break;
				}	
		}
		
		if(Propriedades.TIPO_EXECUCAO == TipoExecucao.GRID) {
			DesiredCapabilities cap = null;
			switch (Propriedades.browser) {
				case FIREFOX: cap= DesiredCapabilities.firefox(); 
				break;
				
				case CHROME: cap = DesiredCapabilities.chrome(); 
				break;			
			}
			try {
				driver = new RemoteWebDriver(new URL("http://192.168.0.11:4444/wd/hub"), cap);
			} catch (MalformedURLException e) {
					System.out.println("Falha na conexão com o GRID");
				e.printStackTrace();
			}
		}
		
		if(Propriedades.TIPO_EXECUCAO == TipoExecucao.NUVEM) {
			DesiredCapabilities cap = null;
			switch (Propriedades.browser) {
				case FIREFOX: cap= DesiredCapabilities.firefox(); 
				break;
				
				case CHROME: cap = DesiredCapabilities.chrome(); 
				break;			
			}
			try {
				driver = new RemoteWebDriver(new URL("https://samuel.sousa:091be322-df78-4960-9a5a-589f4bd5066e@ondemand.us-west-1.saucelabs.com:443/wd/hub"), cap);
			} catch (MalformedURLException e) {
					System.out.println("Falha na conexão com o GRID");
				e.printStackTrace();
			}
		}
		
		
		
		driver.manage().window().setSize(new Dimension(1200, 765));				
		return driver;		
	}
		
	public static void killDriver() {
		WebDriver driver = getDriver();	
		if(driver != null) {
				driver.quit();
				driver = null;
		}
		if(threadDriver != null) {
		threadDriver.remove();
		}
	}
	
}	
		


