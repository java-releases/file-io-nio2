package demo;

import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * 
 * Aplicando programação funcional
 *
 */
public class Exemplo5 {

	public static void main(String[] args) {

		// Listando o conteudo de um diretorio:
		Path diretorio = Path.of("diretorio-stream");
		try {
			Stream<Path> stream = Files.list(diretorio);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Navegando na árvore de diretórios
		try {
			Path d = Path.of("diretorio");

			Stream<Path> path = Files.walk(d, FileVisitOption.FOLLOW_LINKS);
			long x = path.filter(p -> !Files.isDirectory(p)).mapToLong(file -> getSize(file))
					.sum();
			path.close();
			//System.out.println("x: " + x);

		} catch (Exception e) {
			e.printStackTrace();
		}

		// Procurando um um diretório com find();
		Path directory = Path.of("diretorio");
		int maxDepth = 10;
		long minimoSize = 3_000;
		try {
			Stream<Path> s = Files.find(directory, 5, (path, basicFileAttributes) -> path.toString().endsWith(".txt") && basicFileAttributes.size() > minimoSize);
			//s.forEach(System.out::println);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Lendo um arquivo com lines()
		Path path = Paths.get("diretorio/file.txt");
		try {
			Files.lines(path);
			Stream<String> x = Files.lines(path);
			x.forEach(System.out::println);
			x.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static long getSize(Path path) {
		try {
			return Files.size(path);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 0;
	}

}