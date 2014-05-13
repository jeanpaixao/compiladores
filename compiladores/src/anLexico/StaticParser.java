package anLexico;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class StaticParser {
	
	public static void main(String[] args) {
		File arquivo = new File("Teste.txt");
		InputStreamReader codigoFonte = null;
		AnalisadorLexico anLexico;
		ArrayList<Token> listaDeTokens = new ArrayList<Token>();
		
		try {
			codigoFonte = new InputStreamReader(new FileInputStream(arquivo));
			anLexico = new AnalisadorLexico(codigoFonte);
			while(codigoFonte.ready()){
				Token token = anLexico.getProximoToken();
				listaDeTokens.add(token);
				if(token != null){
					System.out.println(token);
				}
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ErroLexico e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
