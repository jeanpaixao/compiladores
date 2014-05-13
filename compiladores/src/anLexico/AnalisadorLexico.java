package anLexico;

import java.io.IOException;
import java.io.InputStreamReader;

public class AnalisadorLexico {
	
	private StringBuffer buffer;
	private char carac;

	private int linhaAtual = 1;

	private InputStreamReader codigoFonte;
	private TabelaPalavrasReservadas mapa;

	public AnalisadorLexico(InputStreamReader codigoFonte) throws IOException {
		mapa = new TabelaPalavrasReservadas();
		buffer = new StringBuffer();
		if (codigoFonte != null && codigoFonte.ready()) {
			this.codigoFonte = codigoFonte;
		}
		readCh(); // LE O PRIMEIRO CARACTERE PARA TRATAMENTO
	}



	// O CARACTERE E INCREMENTA O CONTADOR DE LINHA CASO SEJA UM \N
	private void readCh() {
		try {
			this.carac = (char) codigoFonte.read();
			if (carac == '\n') {
				linhaAtual++;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// L� O PROXIMO CARACTERE E COMPARA COM O PARAMETRO, CASO SEJA UM \N
	// INCREMENTA CONTADOR DE LINHA
	private boolean readCh(char c) {
		try {
			this.carac = (char) codigoFonte.read();
			if (carac == '\n') {
				linhaAtual++;
			}

			if (carac == c) {
				return true;
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}

	// ZERA O BUFFER DO ANALISADOR LEXICO
	private void zerarBuffer() {
		buffer.delete(0, buffer.length());
	}
	
	// LANCA A EXCESSAO COM AS INFORMA��ES CONTIDAS NAS VARIAVEIS PRIVADAS
	private void lancarExcessao() throws ErroLexico {
		buffer.append(carac);
		String valor = buffer.toString();
		zerarBuffer();
		readCh();
		throw new ErroLexico(valor, linhaAtual);
	}

	public Token getProximoToken() throws ErroLexico {
		Token token = null;
		TokenTipo tokenTipo = null;

		char temp = carac;

		// PROCURA UM CARACTERE QUE SEJA DIFERENTE DE ESPACO EM BRANCO
		while (Character.isWhitespace(carac)) {
			readCh();
		}

		// TRATAMENTO DE OPERADORES ARITMET
		if (carac == '+' || carac == '-' || carac == '/' || carac == '*'
				&& token == null) {
			temp = carac;
			buffer.append(carac);
			tokenTipo = TokenTipo.OP_ARITMET;
			token = new Token(tokenTipo, buffer.toString(),linhaAtual);
			readCh();

			// TRATAMENTO PARA COMENTARIO
			if (temp == '/' && carac == '*') {
				zerarBuffer();
				tokenTipo = TokenTipo.COMENTARIO;

				boolean loopSair = false;

				while (!loopSair) {
					if (readCh('*')) {
						temp = carac;
						if (readCh('/')) {
							loopSair = true;
						} else {
							buffer.append(temp);
							buffer.append(carac);
						}

					} else {
						buffer.append(carac);
					}
				}

				token = new Token(tokenTipo, buffer.toString(),linhaAtual);
			}

		}

		// TRATAMENTO DE OPERADORES LOGICOS
		if (carac == '!' && token == null) {
			temp = carac;
			buffer.append(carac);
			tokenTipo = TokenTipo.OP_LOGICO;
			token = new Token(tokenTipo, buffer.toString(),linhaAtual);
			readCh();

			if (temp == '!' && carac == '=') {
				buffer.append(carac);
				tokenTipo = TokenTipo.OP_RELACIONAL;
				token = new Token(tokenTipo, buffer.toString(),linhaAtual);
			}

		}

		if (carac == '&' || carac == '|' && token == null) {
			temp = carac;
			buffer.append(carac);
			readCh();

			// CASO HAJA UMA COLISAO COM COMENTARIO_ABRIR FACA...
			if (temp == '&' && carac == '&') {
				buffer.append(carac);
				tokenTipo = TokenTipo.OP_LOGICO;
				token = new Token(tokenTipo, buffer.toString(),linhaAtual);
			}

			// CASO HAJA UMA COLISAO COM COMENTARIO_FECHAR FACA...
			if (temp == '|' && carac == '|') {
				buffer.append(carac);
				tokenTipo = TokenTipo.OP_LOGICO;
				token = new Token(tokenTipo, buffer.toString(),linhaAtual);
			}

		}

		// TRATAMENTO DE OPERADORES RELACIONAIS, OPERADOR ATRIB E PARAMETRO DA
		// DIRETIVA INCLUDE
		if (carac == '>' || carac == '<' && token == null) {
			temp = carac;
			buffer.append(carac);
			readCh();

			if (temp == '>' && carac == '=') { // CASO HAJA UMA COLISAO COM
												// COMENTARIO_ABRIR FACA...

				buffer.append(carac);
				tokenTipo = TokenTipo.OP_RELACIONAL;
				token = new Token(tokenTipo, buffer.toString(),linhaAtual);

			} else if (temp == '<' && carac == '=') { // CASO HAJA UMA COLISAO
														// COM COMENTARIO_FECHAR
														// FACA...
				buffer.append(carac);
				tokenTipo = TokenTipo.OP_LOGICO;
				token = new Token(tokenTipo, buffer.toString(),linhaAtual);
			} else if (temp == '<' && Character.isJavaIdentifierStart(carac)) {
				boolean loopSair = false;
				temp = carac;
				zerarBuffer();
				buffer.append(temp);

				while (!loopSair) {
					if (!readCh('>')) {
						buffer.append(carac);
					} else {
						loopSair = true;
					}
				}
				token = new Token(tokenTipo, buffer.toString(),linhaAtual);
			}

		}

		if (carac == '=' && token == null) {
			temp = carac;
			buffer.append(carac);
			tokenTipo = TokenTipo.OP_ATRIB;
			token = new Token(tokenTipo, buffer.toString(),linhaAtual);
			readCh();

			// CASO HAJA UMA COLISAO COM COMENTARIO_FECHAR FACA...
			if (temp == '=' && carac == '=') {
				buffer.append(carac);
				tokenTipo = TokenTipo.OP_LOGICO;
				token = new Token(tokenTipo, buffer.toString(),linhaAtual);
			}
		}

		// TRATAMENTO PARA NUMERAL
		if (Character.isDigit(carac) && token == null) {
			tokenTipo = TokenTipo.NUM_INTEIRO;
			boolean loopSair = false;
			int ponto = 0;

			while (!loopSair && ponto <= 1) {

				switch (carac) {
				case '0':
				case '1':
				case '2':
				case '3':
				case '4':
				case '5':
				case '6':
				case '7':
				case '8':
				case '9':
					buffer.append(carac);
					readCh();
					break;
				case '.':
					if (ponto == 0) {
						ponto++;
						tokenTipo = TokenTipo.NUM_REAL;
						buffer.append(carac);
						readCh();
					}
					break;
				case '\n':
				case '\t':
				case '\r':
				case ' ':
				case '(':
				case ')':
				case ';':
				case ':':
				case '{':
				case '}':
					loopSair = true;
					break;

				default:
					lancarExcessao();
				}

			}

			token = new Token(tokenTipo, buffer.toString(),linhaAtual);
			zerarBuffer();
			return token;
		}

		// TRATA IDENTIFICADORES E PALAVRAS RESERVADAS
		if (Character.isJavaIdentifierStart(carac) && token == null) {
			buffer.append(carac);
			readCh();

			boolean loopSair = false;

			while (!loopSair) {
				// ESSA PARTE TEM QUE TRATAR DO MESMO GEITO DO NUMERAL
				if (Character.isJavaIdentifierPart(carac)) {
					buffer.append(carac);
					readCh();
				} else {
					switch (carac) {
					case '(':
					case ')':
					case '{':
					case '}':
					case ':':
					case ';':
					case '\n':
					case '\t':
					case '\r':
					case ' ':
					case ',':
						loopSair = true;
						break;
					default:
						lancarExcessao();
					}

				}

			}

			if (mapa.isPalavraReservada(buffer.toString())) {
				// CASO SEJA UMA PALAVRA RESERVADA, CERTIFIQUE-SE DE
				// ZERAR O BUFFER, CASO SEJA LIDO OS CARACTERES DO SWITCH
				// N�O REALIZE LEITURA, PARA OS DEMAIS LEIA O PROXIMO.
				String valor = buffer.toString();
				zerarBuffer();
				switch (carac) {
				case '(':
				case '{':
				case ':':
				case ';':
					break;
				default:
					readCh();
				}

				return mapa.retornarPalavraReservadaComoToken(valor, linhaAtual);
			}

			tokenTipo = TokenTipo.ID;
			token = new Token(tokenTipo, buffer.toString(),linhaAtual);
			zerarBuffer();
			return token;
		}

		// TRATAMENTO PARA CHAVES, PARENTESES, PONTO E VIRGULA, DOIS PONTOS
		if (carac == ';') {
			buffer.append(carac);

			tokenTipo = TokenTipo.DELIMIT_PONTO_VIRGULA;
			token = new Token(tokenTipo, buffer.toString(),linhaAtual);

		}

		if (carac == ':') {
			buffer.append(carac);

			tokenTipo = TokenTipo.DELIMIT_DOIS_PONTOS;
			token = new Token(tokenTipo, buffer.toString(),linhaAtual);

		}

		if (carac == '(') {
			buffer.append(carac);

			tokenTipo = TokenTipo.DELIMIT_PARENT_ABRIR;
			token = new Token(tokenTipo, buffer.toString(),linhaAtual);

		}

		if (carac == ')') {
			buffer.append(carac);

			tokenTipo = TokenTipo.DELIMIT_PARENT_FECHAR;
			token = new Token(tokenTipo, buffer.toString(),linhaAtual);
		}

		if (carac == '{') {
			buffer.append(carac);

			tokenTipo = TokenTipo.DELIMIT_CHAVE_ABRIR;
			token = new Token(tokenTipo, buffer.toString(),linhaAtual);

		}

		if (carac == '}') {
			buffer.append(carac);

			tokenTipo = TokenTipo.DELIMIT_CHAVE_FECHAR;
			token = new Token(tokenTipo, buffer.toString(),linhaAtual);

		}

		// TRATAMENTO DIRETIVA INCLUDE E SEU VALOR
		if (carac == '#') {
			buffer.append(carac);
			if (readCh('i')) {
				buffer.append(carac);
				if (readCh('n')) {
					buffer.append(carac);
					if (readCh('c')) {
						buffer.append(carac);
						if (readCh('l')) {
							buffer.append(carac);
							if (readCh('u')) {
								buffer.append(carac);
								if (readCh('d')) {
									buffer.append(carac);
									if (readCh('e')) {
										buffer.append(carac);

										tokenTipo = TokenTipo.DIRETIVA_INCLUDE;
										token = new Token(tokenTipo, buffer.toString(),linhaAtual);
									}
								}
							}
						}
					}
				}
			}
		}

		// TRATAMENTO PARA CARACTERES E STRINGS
		if (carac == '\"') {
			boolean loopSair = false;
			while (!loopSair) {
				if (!readCh('\"')) {
					buffer.append(carac);
				} else {
					loopSair = true;
				}
			}

			tokenTipo = TokenTipo.LITERAL_STRING;
			token = new Token(tokenTipo, buffer.toString(),linhaAtual);

		}

		if (carac == '\'') {
			boolean loopSair = false;
			int contadorCaractere = 0;

			while (!loopSair) {
				readCh();
				if (Character.isLetterOrDigit(carac) && contadorCaractere < 1) {
					buffer.append(carac);
					contadorCaractere++;
				} else if (carac == '\\') {
					buffer.append(carac);
					readCh();

					switch (carac) {
					case '\\':
					case '\"':
					case 'r':
					case 't':
					case 'n':
						buffer.append(carac);
						if (readCh('\'')) {
							loopSair = true;
						}
						break;
					}

				} else if (carac == '\'') {
					loopSair = true;
				} else if (Character.isWhitespace(carac)
						&& contadorCaractere < 1) {
					switch (carac) {
					case '\t':
						buffer.append("\\t");
						break;
					case '\r':
						buffer.append("\\r");
						break;
					case ' ':
						buffer.append(" ");
						break;
					default:
						lancarExcessao();
					}
				} else {

					// CASO SEJA LIDO DOIS CARACTERES SEGUIDOS NA PRESENCA DE
					// ASPA DUPLAS, COM EXCESSAO DO CARACTERE BARRA, LANCE
					// UMA EXCESSAO
					lancarExcessao();
				}
			}

			tokenTipo = TokenTipo.LITERAL_CARAC;
			token = new Token(tokenTipo, buffer.toString(),linhaAtual);
		}

		zerarBuffer();
		readCh();
		return token;
	}


}
