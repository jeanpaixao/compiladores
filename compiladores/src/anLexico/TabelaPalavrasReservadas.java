package anLexico;

import java.util.HashMap;

public class TabelaPalavrasReservadas {
	private HashMap<String, TokenTipo> mapaDePalavrasReservadas;
	
	public boolean isPalavraReservada(String valor){
		if(mapaDePalavrasReservadas.containsKey(valor)){
			return true;
		}
		
		return false;
	}
	
	public Token retornarPalavraReservadaComoToken(String valor, int linha){
		if(mapaDePalavrasReservadas.containsKey(valor)){
			TokenTipo tipo = mapaDePalavrasReservadas.get(valor);
			return new Token(tipo, valor, linha);
		}
		
		return null;
	}
	
	public TabelaPalavrasReservadas(){
		this.mapaDePalavrasReservadas = new HashMap<String, TokenTipo>();
		
		//PREENCHENDO TABELA DE PALAVRAS RESERVADAS
		this.mapaDePalavrasReservadas.put("boolean", TokenTipo.PALAVRA_RESERVADA);
		this.mapaDePalavrasReservadas.put("else", TokenTipo.PALAVRA_RESERVADA);
		this.mapaDePalavrasReservadas.put("float", TokenTipo.PALAVRA_RESERVADA);
		this.mapaDePalavrasReservadas.put("for", TokenTipo.PALAVRA_RESERVADA);
		this.mapaDePalavrasReservadas.put("false", TokenTipo.PALAVRA_RESERVADA);
		this.mapaDePalavrasReservadas.put("if", TokenTipo.PALAVRA_RESERVADA);
		this.mapaDePalavrasReservadas.put("int", TokenTipo.PALAVRA_RESERVADA);
		this.mapaDePalavrasReservadas.put("main", TokenTipo.PALAVRA_RESERVADA);
		this.mapaDePalavrasReservadas.put("return", TokenTipo.PALAVRA_RESERVADA);
		this.mapaDePalavrasReservadas.put("struct", TokenTipo.PALAVRA_RESERVADA);
		this.mapaDePalavrasReservadas.put("static", TokenTipo.PALAVRA_RESERVADA);
		this.mapaDePalavrasReservadas.put("true", TokenTipo.PALAVRA_RESERVADA);
		this.mapaDePalavrasReservadas.put("type", TokenTipo.PALAVRA_RESERVADA);
		this.mapaDePalavrasReservadas.put("void", TokenTipo.PALAVRA_RESERVADA);
		this.mapaDePalavrasReservadas.put("while", TokenTipo.PALAVRA_RESERVADA);
		
		
	}
}
