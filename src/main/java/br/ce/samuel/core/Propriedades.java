package br.ce.samuel.core;

public class Propriedades {
	public static boolean FECHAR_BROWSER = false;
	public static Browsers browser = Browsers.CHROME;
	public static TipoExecucao TIPO_EXECUCAO = TipoExecucao.LOCAL;
	
	
	public enum Browsers{
		CHROME,
		FIREFOX
	}
	
	public enum TipoExecucao{
		LOCAL,
		GRID,
		NUVEM
	}
	
	
}
