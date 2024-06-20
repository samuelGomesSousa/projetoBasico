package br.ce.samuel.tests;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.ce.samuel.core.*;
import br.ce.samuel.pages.MenuPage;
import br.ce.samuel.pages.MovimentacaoPage;
import br.ce.samuel.utilis.DataUtilis;

//@FixMethodOrder(MethodSorters.NAME_ASCENDING) //Serve para colocar os testes para executar em ordem, Name Ascending em ordem alfab�tica
public class MovimentacaoTest extends BaseTest {
	private MovimentacaoPage movimentacao = new MovimentacaoPage();
	private MenuPage menupage = new MenuPage();
	
	@Test
	public void test1_InserirMovimentacao() {
		menupage.acessarTelaCriarMovimentacao();
		movimentacao.selecionaTipoMovimentacao("Receita");
		movimentacao.setDataMovimentacao(DataUtilis.obterDataFormatada(new Date()));
		movimentacao.setDataPagamento(DataUtilis.obterDataFormatada(new Date()));
		movimentacao.setDescricao("teste descricao");
		movimentacao.setInteressado("samuel");
		movimentacao.setValor("50.00");
		movimentacao.selecionaConta("Conta para movimentacoes");
		movimentacao.setStatusPago();
		movimentacao.salvar();
		
		//falta o radio
		Assert.assertEquals("Movimentação adicionada com sucesso!", movimentacao.obterMensagemSucesso());
	}

	//pega a mensagem de erro, coloca num array e valida cada elemento se contem no array
	@Test
	public void test2_CamposObrigatorios() {
		menupage.acessarTelaCriarMovimentacao();
		movimentacao.salvar();
		List<String> erros = movimentacao.obterErros();
		//Assert.assertEquals("Data da Movimenta��o obrigat�rio", erros.get(0));
		//Assert.assertTrue(erros.contains("Data da Movimenta��o � obrigat�rio"));
		Assert.assertTrue(erros.containsAll(Arrays.asList(
				"Data da Movimentação é obrigatório",
				"Data do pagamento é obrigatório",
				"Descrição é obrigatório",
				"Interessado é obrigatório",
				"Valor é obrigatório",
				"Valor deve ser um número"
		)));
		Assert.assertEquals(6, erros.size());
	}
	
	@Test
	public void test3_InserirMovimentacaoFutura() {		
		menupage.acessarTelaCriarMovimentacao();
		
		Date dataFutura = DataUtilis.obterDataComDiferencaDias(5);	// pega a data atual e soma a qtd de dias informados	
		//movimentacao.setDataMovimentacao("16/10/2024"); //desta forma a data fica fixa e precisa ficar atualizando
		movimentacao.setDataMovimentacao(DataUtilis.obterDataFormatada(dataFutura));
		movimentacao.setDataPagamento("16/10/2022");
		movimentacao.setDescricao("TESTE");
		movimentacao.setInteressado("teste");
		movimentacao.setValor("49.99");
		movimentacao.selecionaConta("Conta com movimentacao");
		movimentacao.salvar();
		Assert.assertEquals("Data da Movimentação deve ser menor ou igual à data atual", movimentacao.obterMensagemErro());		
		
	}
	
	
	}