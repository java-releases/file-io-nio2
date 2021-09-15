package demo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class Exemplo2 {

	public static void main(String[] args) {

		boolean fileExiste = Files.exists(Path.of("C:\\ocpjp-study\\file-io-nio2\\src\\demo\\arquivos\\arquivo01.txt"));
		System.out.println("Arquivo existe: " + fileExiste);

		boolean diretorioExiste = Files.exists(Path.of("C:\\ocpjp-study\\file-io-nio2\\src\\demo\\arquivos"));
		System.out.println("Diretorio existe: " + diretorioExiste);

		Path path01 = Path.of("C:\\ocpjp-study\\file-io-nio2\\src\\demo\\arquivos\\arquivo01.txt");
		Path path02 = Path.of("C:\\ocpjp-study\\file-io-nio2\\src\\demo\\arquivos\\arquivo01.txt");

		try {
			boolean saoMesmoArquivos = Files.isSameFile(path01, path02);
			System.out.println("saoMesmoArquivos: " + saoMesmoArquivos);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println();

		try {
			Files.createDirectory(Path.of("C:\\ocpjp-study\\file-io-nio2\\src\\demo\\arquivos\\diretorio-01"));
		} catch (IOException e) {
		}

		try {
			Files.createDirectories(Path.of("C:\\ocpjp-study\\file-io-nio2\\src\\demo\\arquivos\\diretorio-01"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Copiando arquivo e um diretorio:
		try {
			Path p1 = Path.of("C:\\ocpjp-study\\file-io-nio2\\src\\demo\\arquivos\\arquivo01.txt");
			Path p2New = Path.of("C:\\ocpjp-study\\file-io-nio2\\src\\demo\\arquivos\\arquivo01_copiado.txt");
			Files.copy(p1, p2New, StandardCopyOption.REPLACE_EXISTING);
			System.out.println("copiado com sucesso!");
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Copiando arquivos com I/O Stream
		try {
			var is = new FileInputStream("C:\\ocpjp-study\\file-io-nio2\\src\\demo\\arquivos\\arquivo01.txt");
			Files.copy(is, Paths.get("C:\\ocpjp-study\\file-io-nio2\\src\\demo\\arquivos\\arquivo3.txt"), StandardCopyOption.REPLACE_EXISTING);
			Files.copy(Paths.get("C:\\ocpjp-study\\file-io-nio2\\src\\demo\\arquivos\\arquivo4.txt"), System.out);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Copiando arquivos em um diretório
		
	}

}