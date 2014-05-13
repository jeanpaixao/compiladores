package anLexico;

public class Token {
	private TokenTipo tipo;
	private Object valor;
	private int linha;

	public TokenTipo getTipo() {
		return tipo;
	}

	public void setTipo(TokenTipo tipo) {
		this.tipo = tipo;
	}

	public Object getValor() {
		return valor;
	}

	public void setValor(Object valor) {
		this.valor = valor;
	}
	
	public int getLinha() {
		return linha;
	}

	public void setLinha(int linha) {
		this.linha = linha;
	}

	public Token(TokenTipo tipo, Object valor, int linha) {
		this.tipo = tipo;
		this.valor = valor;
		this.linha = linha;
	}

	@Override
	public String toString() {
		return "Token [tipo= " + tipo + ", valor= " + valor + " , linha= "+ linha+ " ]";
	}
	
	
}
