package expressoes;

public class Expressoes {
	
	/* 1 - PERGUNTAR AO PROFESSOR COMO TRATAR A DIRETIVA INCLUDE
	 * 2 - PERGUNTAR SE PODE TRANSFORMAR UM AUTOMATO PARA CADA
	 * 3 - PERGUNTAR AO PROFESSOR SE NA LITERAL STRING TEM QUE TRATAR CARACTERES COMO ASPAS, QUEBRA DE LINHAS, 
	 * CARACTERES DE OPERAÇÃO ARITMETICA, CARACTERES DE OPERAÇÃO LOGICA, CONTRA BARRA, BARRA ENTRE OUTROS. O MESMO VALE
	 * PARA LITERAL CARACTERE.
	 * 4 - PERGUNTAR COMO SERÁ O TIPO DO RELATORIO QUE ELE QUER E PEDIR UM MODELO.
	 * 
	 */
	
	private static String numInteiro = "[0-9]+"; //ok
	private static String numReal = "[0-9]+\\.[0-9]+"; //ok
	private static String variaveis = "([a-z]|[A-Z]|[_])([a-z]|[A-Z]|[0-9]|[_])*";
	private static String opRelacionais = ">|>=|<|<=|==|!="; //ok
	private static String opLogicos = "&&|\\|\\||!"; //ok
	private static String opAtrib = "="; //ok
	private static String opArit = "\\+|\\-|\\*|\\/"; //ok
	private static String literalString = "(\")([a-z]|[A-Z]|[0-9]|( ))*(\")"; //ok
	private static String literalCaractere = "(\')([a-z]|[A-Z]|[0-9]|( ))*(\')"; //ok
	private static String comentarios = "(\\/)(\\*)(.)*(\\*)(\\/)"; //ok
	private static String parenteseAbrir = "\\("; //ok
	private static String parenteseFechar = "\\)"; //ok
	private static String chaveAbrir = "\\{"; //ok
	private static String chaveFechar = "\\}"; //ok
	private static String delimitadorDoisPontos = ":";
	private static String delimitadorPontoEVirgula = ";"; //ok
	
	//palavras reservadas
	private static String palavrasReservadas = "return|for|while|if|else|main|int|float|boolean|type|true|false|static|struct"; //ok
	
	//não usaveis
	private static String diretivaInclude = "#include";
	private static String valorDiretivaInclude = "([0..9]|[a-z]|[A-Z])*";
	private static String diretivaIncludeAbrir = "<";
	private static String diretivaIncludeFechar = ">";

	
	
	/*
	 * Expressoes dividem-se em - Modificadores (?i) ignorar maiúsulas e
	 * minúsculas (?x) comentários (?m) multilinhas (?s) DottalMETACARACTERES .
	 * ignorar qq caracter \d digitos [0-9] \D não é dígito [^0-9] \s espaços
	 * [\t\n\x0B\f\r] \S não é espaço [^\s] \w letra [a-z A-Z 0-9] \W não é
	 * letra
	 * 
	 * QUANTIFICADORES
	 * 
	 * X{n} X exatamente n vezes X{n,} X pelo menos n vezes X{n,m} X pelo menos
	 * n mas não mais que m X? 0 ou 1 vez X* 0 ou mais vezes X+ 1 ou mais vezes
	 * 
	 * METACARACTERES DE FRONTEIRA
	 * 
	 * ^ inicia $ finaliza | ou
	 * 
	 * /*AGRUPADORES
	 * 
	 * [...] Agrupamento [a-z] Alcançe [a-e][i-u] União [a-z&&[aeiou]]
	 * Interseção [^abc] Exceção [a-z&&[^m-p]] Subtração \x Fuga literal
	 */

	public static void main(String[] args) {
		String nInteiro = "1234";
		String nReal = "123.4444";
		String identificador = "A3c3adsd";

		System.out.println("TESTE NUMERO INTEIRO E REAL");
		System.out.println(nInteiro.matches(Expressoes.numInteiro));
		System.out.println(nReal.matches(Expressoes.numReal));

		System.out.println("\nTESTE IDENTIFICADOR");
		System.out.println(identificador.matches(Expressoes.variaveis));
		System.out.println("_teste".matches(Expressoes.variaveis));
		System.out.println("t_este_".matches(Expressoes.variaveis));
		
		// OPERADORES LOGICOS
		System.out.println("\nTESTE OP_LOGICOS");
		System.out.println("&&".matches(Expressoes.opLogicos));
		System.out.println("||".matches(Expressoes.opLogicos));
		System.out.println("!".matches(Expressoes.opLogicos));

		// OPERADORES RELACIONAIS
		System.out.println("\nTESTE OP_RELACIONAIS");
		System.out.println(">".matches(Expressoes.opRelacionais));
		System.out.println(">=".matches(Expressoes.opRelacionais));
		System.out.println("<".matches(Expressoes.opRelacionais));
		System.out.println("<=".matches(Expressoes.opRelacionais));
		System.out.println("==".matches(Expressoes.opRelacionais));
		System.out.println("!=".matches(Expressoes.opRelacionais));

		// OPERADOR ATRIBUIÇÃO
		System.out.println("\nTESTE OP_ATRIB");
		System.out.println("=".matches(Expressoes.opAtrib));

		// OPERADOR ARITMETICO
		System.out.println("\nTESTE OP_ARITMET");
		System.out.println("+".matches(Expressoes.opArit));
		System.out.println("-".matches(Expressoes.opArit));
		System.out.println("/".matches(Expressoes.opArit));
		System.out.println("*".matches(Expressoes.opArit));

		// LITERAL STRING
		System.out.println("\nLITERAL STRING + LITERAL CARACTERE");
		System.out.println("\"A1b2Zz3\"".matches(Expressoes.literalString));
		System.out.println("\"1231231231\"".matches(Expressoes.literalString));
		System.out.println("\"meu texto\"".matches(Expressoes.literalString));
		System.out.println("\' \'".matches(Expressoes.literalCaractere));
		System.out.println("\'C\'".matches(Expressoes.literalCaractere));
		System.out.println("\'1\'".matches(Expressoes.literalCaractere));
		System.out.println("\'a\'".matches(Expressoes.literalCaractere));

		// COMENTARIOS
		System.out
				.println("/*ASASASDASD 1233123123 ASDASDASD \\ // //+()!@#$%¨&*-+{}|:><^}~][´()**/"
						.matches(Expressoes.comentarios));

		// PARENTESES
		System.out.println("\nPARENTESES");
		System.out.println("(".matches(Expressoes.parenteseAbrir));
		System.out.println(")".matches(Expressoes.parenteseFechar));

		// CHAVES
		System.out.println("\nCHAVES");
		System.out.println("{".matches(Expressoes.chaveAbrir));
		System.out.println("}".matches(Expressoes.chaveFechar));

		// PARENTESES
		System.out.println("\nDELIMITADOR DOIS PONTOS E DELIMITADOR PONTO E VIRGULA");
		System.out.println(":".matches(Expressoes.delimitadorDoisPontos));
		System.out.println(";".matches(Expressoes.delimitadorPontoEVirgula));

		
		//DIRETIVA INCLUDE
		System.out.println("\nDIRETIVA INCLUDE");
		
		//PALAVRAS RESERVADAS
		
		
		//ALGUNS CASOS QUE PODEM SER CONFUNDIDOS, TODOS ESSES TEM QUE SER FALSOS.
		System.out.println("\nOUTROS TESTES");
		System.out.println("\'".matches(Expressoes.literalCaractere));
		System.out.println("\'".matches(Expressoes.literalString));
		System.out.println("\'".matches(Expressoes.comentarios));
		
		System.out.println("\"".matches(Expressoes.literalCaractere));
		System.out.println("\"".matches(Expressoes.literalString));
		System.out.println("\"".matches(Expressoes.comentarios));
		
		System.out.println("/*".matches(Expressoes.comentarios));
		System.out.println("/*".matches(Expressoes.literalString));
		
		System.out.println("tabulacao '\t'");
	}
}
