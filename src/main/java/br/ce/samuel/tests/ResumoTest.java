package br.ce.samuel.tests;

import static br.ce.samuel.core.DriverFactory.getDriver; //Ctrl + Shift + N

import java.util.List;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import br.ce.samuel.core.BaseTest;
import br.ce.samuel.core.DriverFactory;
import br.ce.samuel.pages.MenuPage;
import br.ce.samuel.pages.ResumoPage;

//@FixMethodOrder(MethodSorters.NAME_ASCENDING) //Serve para colocar os testes para executar em ordem, Name Ascending em ordem alfabética
public class ResumoTest extends BaseTest {
	private MenuPage menupage = new MenuPage();
	private ResumoPage resumo = new ResumoPage();
	
	@Test
	public void test1_removerMovimentacao() {
		menupage.acessarTelaResumo();
		
		resumo.excluirMovimentacao("Descrição", "Movimentacao para exclusao", "Ações");
		Assert.assertEquals("Movimentação removida com sucesso!", resumo.obterMensagemSucesso());		
		
		/*Solução curso*/
		
		//resumo.excluirMovi();
		//Assert.assertEquals("Movimentação removida com sucesso!", resumo.obterMensagemSucesso());
	}
	
	
	//@Test(expected = NoSuchElementException.class) //SOLUÇÃO 1 - espera que algo retorne um erro
	@Test
	public void test2_resumoMensal() {		//Verifica o nome da página
		menupage.acessarTelaResumo();
		Assert.assertEquals("Seu Barriga - Extrato", getDriver().getTitle());
		
		/*
		//SOLUCAO 2 - vai verificar que a tabela não existe e vai cair no try para confirmar
		try {
			DriverFactory.getDriver().findElement(By.xpath("//*[@id='tabelaExtrato']/tbody/tr"));
			Assert.fail();
		}catch(NoSuchElementException e){			
		}*/
		
		//SOLUCAO 3 - Pega o que tem na tabela e joga numa lista, depois compara o tamanho da lista pra ver se veio zerada
		
		List<WebElement> elementosEncontrados = DriverFactory.getDriver().findElements(By.xpath("//*[@id='tabelaExtrato']/tbody/tr"));		
		Assert.assertEquals(7, elementosEncontrados.size());	
		
	}
	
	
	
	/*
	@Test
	public void test3_verificaMovimentacaoPerdida() {	
		//passa por todos os meses e anos para procurar alguma movimentação perdida
		
		menupage.acessarTelaResumo();
		Assert.assertEquals("Seu Barriga - Extrato", getDriver().getTitle());	
				
		List<WebElement> elementosEncontrados = DriverFactory.getDriver().findElements(By.xpath("//*[@id='tabelaExtrato']/tbody/tr"));
		
		List<WebElement> listaAnos = DriverFactory.getDriver().findElements(By.xpath("//*[@id='ano']"));
		boolean resposta=false;
		int i=0;		
		
		do {				
			
				WebElement element = DriverFactory.getDriver().findElement(By.xpath("//*[@id='ano']"));		
				Select combo = new Select(element);
				
				combo.selectByIndex(i);
				
				
				for(int x=0; x<12; x++) {
					WebElement elements = DriverFactory.getDriver().findElement(By.xpath("//*[@id='mes']"));		
					Select combomes = new Select(elements);
					combomes.selectByIndex(x);
					WebElement buton = getDriver().findElement(By.xpath("//*[@value='Buscar']"));
					buton.click();
					
						if(elementosEncontrados.size() > 0) {
							System.out.printf("teste: ", elementosEncontrados.size());
							resposta = true;
							break;							
						}					
					}				
					
			i++;
			//System.out.printf("teste: ", elementosEncontrados.size());
		
		}while(resposta == false);	
		
	}
	
	/*public void test3_verificaMovimentacaoPerdida() {	
		//passa por todos os meses e anos para procurar alguma movimentação perdida
		
		menupage.acessarTelaResumo();
		Assert.assertEquals("Seu Barriga - Extrato", getDriver().getTitle());		
		
		List<WebElement> elementosEncontrados = DriverFactory.getDriver().findElements(By.xpath("//*[@id='tabelaExtrato']/tbody/tr"));
		
		boolean resposta=false;
		do {
		
			
			for(int i=0; i<2; i++) {
				WebElement element = DriverFactory.getDriver().findElement(By.xpath("//*[@id='ano']"));		
				Select combo = new Select(element);
				combo.selectByIndex(i);
				
				
				for(int x=0; x<12; x++) {
					WebElement elements = DriverFactory.getDriver().findElement(By.xpath("//*[@id='mes']"));		
					Select combomes = new Select(elements);
					combomes.selectByIndex(x);
					WebElement buton = getDriver().findElement(By.xpath("//*[@value='Buscar']"));
					buton.click();
					
						if(elementosEncontrados.size() > 0) {
							resposta = true;
							break;
							
						}					
					}				
				}	
			
		
		}while(resposta == false);*/
	
	
	
}
