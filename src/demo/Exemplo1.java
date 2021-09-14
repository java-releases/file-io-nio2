package demo;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Exemplo1 {

	public static void main(String[] args) throws URISyntaxException {

		/**
		 * Obtendo Path com interface
		 */
		// Cria referencia para um caminho relativo:
		Path arquivo01_a = Path.of("arquivos/arquivo234322424324.txt");// interface
		Path arquivo01_b = Path.of("arquivos", "arquivo234322424324.txt");// interface
		// Cria uma referência para um caminho absoluto (baseado no windows):
		Path arquivo02_a = Path.of("C:\\ocpjp-study\\file-io-nio2\\src\\demo\\arquivos\\arquivo01.txt");// interface
		Path arquivo02_b = Path.of("C:", "ocpjp-study", "file-io-nio2", "src", "demo", "arquivos", "arquivo01.txt");// interface
		// Cria uma referencia para um diretorio absoluto (baseado em linux ou mac)
		Path arquivo03_a = Path.of("/file-io-nio2/src/arquivos");// interface
		Path arquivo03_b = Path.of("/", "file-io-nio2", "src", "arquivos");// interface

		/**
		 * Obtendo Path com Class
		 */
		Path arquivo02_c = Paths.get("C:\\ocpjp-study\\file-io-nio2\\src\\demo\\arquivos\\arquivo01.txt");// class
		Path arquivo03_c = Paths.get("/file-io-nio2/src/arquivos");// class
		Path arquivo01_c = Paths.get("arquivos/arquivo234322424324.txt");// class

		/**
		 * Obtendo Path com URI
		 */
		URI uri = new URI("file://C:/ocpjp-study/file-io-nio2/src/demo/arquivos/arquivo01.txt");
		if (uri.isAbsolute()) {
			System.out.println("isAbsolute");
		} else {
			System.out.println("NOT isAbsolute");
		}
		Path a = Path.of(uri);
		Path b = Paths.get(uri);
		URI uri2 = a.toUri();

		/**
		 * Obtendo Path de FileSystems class
		 */
		Path path1 = FileSystems.getDefault().getPath("arquivos/arquivo234322424324.txt");
		Path path2 = FileSystems.getDefault().getPath("C:\\ocpjp-study\\file-io-nio2\\src\\demo\\arquivos\\arquivo01.txt");
		Path path3 = FileSystems.getDefault().getPath("/file-io-nio2/src/arquivos");
		FileSystem fileSystem = FileSystems.getFileSystem(new URI("file://C:/ocpjp-study/file-io-nio2/src/demo/arquivos"));
		Path p = fileSystem.getPath("arquivo01.txt");
		
		/**
		 * Obtendo Path de File class (legado)
		 */
		File file = new File("qualquer.txt");
		Path path = file.toPath();
		File backToFile = path.toFile();
	}

}