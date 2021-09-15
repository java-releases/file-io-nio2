package demo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 
 * deletando arquivos/diretorios
 *
 */
public class Exemplo4 {

	public static void main(String[] args) {

		Path diretorio = Paths.get("d3");
		try {
			// Files.delete(diretorio);
			boolean deletou = Files.deleteIfExists(diretorio);
			System.out.println("deletou ? " + deletou);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}