package anLexico;

public class ErroLexico extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ErroLexico(String valor, int linha){
		super("ERRO LEXICO, VALOR: \""+valor+"\" NA LINHA "+linha+
				" N�O FOI RECONHECIDO");
	}
		
		
		
}
