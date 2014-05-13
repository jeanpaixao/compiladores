package anLexico;

public enum TokenTipo {
	OP_RELACIONAL("OPERADOR_RELACIONAL"),
	OP_LOGICO("OPERADOR_LOGICO"),
	OP_ATRIB("OPERADOR_ATRIBUICAO"),
	OP_ARITMET("OPERADOR_ARITMETICO"),
	
	LITERAL_STRING("LITERAL_STRING"),
	LITERAL_CARAC("LITERAL_CARACTERE"),
	ID("IDENTIFICADOR"),
	COMENTARIO("COMENTARIO"),
		
	DELIMIT_PARENT_ABRIR("DELIMITADOR_PARENTESE_ABRIR"),
	DELIMIT_PARENT_FECHAR("DELIMITADOR_PARENTESE_FECHAR"),
	
	DELIMIT_CHAVE_ABRIR("DELIMITADOR_CHAVE_ABRIR"),
	DELIMIT_CHAVE_FECHAR("DELIMITADOR_CHAVE_FECHAR"),
	
	DELIMIT_DOIS_PONTOS("DELIMITADOR_DOIS_PONTOS"),
	DELIMIT_PONTO_VIRGULA("DELIMITADOR_PONTO_VIRGULA"),
	
	DIRETIVA_INCLUDE("DIRETIVA_INCLUDE"),
	PARAM("PARAMETRO"),
	
	PALAVRA_RESERVADA("PALAVRA_RESERVADA"),
	
	NUM_INTEIRO("NUMERAL_INTEIRO"),
	NUM_REAL("NUMERAL_REAL");
	
	public String nome;
		
	TokenTipo( String nome){
		this.nome = nome;
	
	}
	
}