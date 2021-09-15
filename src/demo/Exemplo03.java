package demo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * 
 * Mover arquivos
 *
 */
public class Exemplo03 {

	public static void main(String[] args) {

		// Path diretorio03 = Paths.get("./diretorio-03");
		// Path diretorio03New = Paths.get("./diretorio-04");

		// Path arquivo = Paths.get("./diretorio-03/java.txt");
		// Path arquivoNew = Paths.get("./diretorio-04/java-v4.txt");

		Path arquivo1 = Paths.get("java.txt");
		Path arquivo2 = Paths.get("java-v2.txt");
		try {
			// Files.move(diretorio03, diretorio03New);
			// Files.move(arquivo, arquivoNew, StandardCopyOption.REPLACE_EXISTING);
			Files.move(arquivo1, arquivo2, StandardCopyOption.ATOMIC_MOVE);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}