![](https://github.com/ocpjp-study/file-io-nio2/blob/main/ocpjp.png)

[Preparação para o Exame 1Z0-817](https://education.oracle.com/pt_BR/upgrade-ocp-java-6-7-8-to-java-se-11-developer/pexam_1Z0-817)

### Tópico: File-io-nio2
### Objetivos
- Usar a interface Path para operar em caminhos de arquivos e diretórios;
- Usar a classe Files para verificar, excluir, copiar ou mover um arquivo ou diretório;
- Usar Stream API com arquivos;

<hr>

### Resumo

- Em termos da prova a definição de Path será:
  - Se inicia com '/' barra, será considerado `caminho absoluto`;
  - Se inicia com 'C:' (a letra do driver), então será considerado `caminho absoluto`;
  - Para qualquer outro cenário, será considerado `caminho relativo`;

- File System Symbols:
  - `.` Uma referência ao diretório corrente;
  - `..` Uma referência ao parent do diretório corrente;
- Vantagens de usar Files.lines(), ao invés de Files.readAllLines():
  - Pode rodar com pouca memória disponível;
  - Pode trabalhar com programação funcional, usando filter() e map() diretamente;

- Vantagens de usar Files.readAttributes():
  - Faz menos viagens de ida e volta para o sistema de arquivos.
  - Pode ser usado para acessar atributos dependentes do sistema de arquivos.
  - Para a leitura de vários atributos, costuma ter mais desempenho.

<hr>

> ### Usar a interface Path para operar em caminhos de arquivos e diretórios:
```java
/**
*  Cria referencia para um caminho relativo:
*/
Path.of("meuDiretorio/meuArquivo.txt");// interface
Path.of("meuDiretorio", "meuArquivo.txt");// interface
Paths.get("meuDiretorio/meuArquivo.txt");// class
```

```java
/**
*  Cria referencia para um caminho absoluto (baseado no windows):
*/
Path.of("C:\\meuDiretorio\\teste\\meuArquivo.txt");// interface
Path.of("C:", "meuDiretorio", "teste", "src", "meuArquivo.txt");// interface
Paths.get("C:\\meuDiretorio\\teste\\meuArquivo.txt");// class
```

```java
/**
*  Cria referencia para um caminho absoluto (baseado em linux ou mac):
*/
Path.of("/meuDiretorio/outroDiretorio");// interface
Path.of("/", "meuDiretorio", "outroDiretorio");// interface
Paths.get("/meuDiretorio/outroDiretorio");// class
```

```java
/**
*  O relativize é responsável por eliminar o conteúdo em comum entre 2 paths
*/
Path diretorio = Path.of("/home/ortiz");
Path classes = Path.of("/home/ortiz/demo/Dog.java");
Path pathToClass = diretorio.relativize(classes);
System.out.println(pathToClass); // demo\Dog.java
```

```java
/**
*  Outra opção para se criar um Path
*/
Path FileSystems.getDefault().getPath("opa/outro/isso");
```

```java
/**
*  O normalize é quando removemos os "." (pontos) desnecessarios e, passamos a acessar o diretorio diretamente,
* sem o uso de pontos.
*/
String diretorio = "/home/dev/ortiz/teste";
String arquivo = "../../Cat.java";
Path path = Path.of(diretorio, arquivo);
System.out.println(path); // \home\dev\ortiz\teste\..\..\Cat.java 
System.out.println(path.normalize()); // \home\dev\Cat.java
```

<hr>
<br/><br/>

> ### Usar a classe Files para verificar, excluir, copiar ou mover um arquivo ou diretório:
```java
/**
*  Cria um diretório. 
*  Caso o mesmo já existir, então lança uma `java.nio.file.FileAlreadyExistsException`.
*  Caso informar vários diretórios encadeados que não existam, então irá lançar uma `java.nio.file.NoSuchFileException`.
*/
Path Files.createDirectory(Path.of("C:\\diretorio-01")) throws IOException;
```

```java
/**
*  Cria um ou mais diretórios (sub pastas caso não existam). Caso o mesmo já existir, então não irá acontecer nada.
*/
Path Files.createDirectories(Path.of("C:\\diretorio-01")) throws IOException;
```

```java
/**
*  Copia um arquivo ou diretório(sem conteúdo). Exemplo: de path1, para newPath.
*  Caso o arquivo informado 'path1' não exista, então irá lançar uma `java.nio.file.NoSuchFileException`.
*  Caso o diretório do 'newPath' não exista, então irá lançar uma java.nio.file.NoSuchFileException.
*  Caso o novo arquivo 'newPath' já exista, então irá lançar uma `java.nio.file.FileAlreadyExistsException`.
*/
Files.copy(path1, newPath);

// Subscreve o arquivo 'newPath' caso ele já existir. Fazendo com que não lance mais uma Exception.
Files.copy(path1, newPath, StandardCopyOption.REPLACE_EXISTING) long;

// Copia arquivo e exibe todo conteúdo no console.
Files.copy(path1, System.out) long;
```

```java
/**
*  Se informar 2 diretórios, então irá mover/renomear o 'path1' com o novo 'path2'.
*  Se informar 2 arquivos, então irá mover/renomear o 'path1' para o 'path2'.
*  Se o 'path1' não existir, então lança uma java.nio.file.NoSuchFileException.
*  Se o 'path2' já existir, então lança uma java.nio.file.FileAlreadyExistsException.
*/
Files.move(path1, path2) Path;

// Subscreve o 'arquivoNew' caso o mesmo já existir.
Files.move(arquivo, arquivoNew, StandardCopyOption.REPLACE_EXISTING) Path;

// Garante que nunca irá visualizar o processo de move/rename de maneira incompleta.
Files.move(arquivo1, arquivo2, StandardCopyOption.ATOMIC_MOVE) Path;
```

```java
/**
*
*  Se o 'path' (diretório/arquivo) não existir, então lança uma java.nio.file.NoSuchFileException.
*  Se o 'path' for um diretório e não estiver vazio, então lança uma java.nio.file.DirectoryNotEmptyException.
*/
void Files.delete(path) throws IOException;

// Se o 'path' (diretório) não estiver vazio, então lança uma java.nio.file.DirectoryNotEmptyException.
// Se conseguir deletar retorna TRUE, senão retorna FALSE.
boolean Files.deleteIfExists(path) throws IOException;
```

<hr>
<br/><br/>

> ### Usar Stream API com arquivos:
```java
/**
*  Lista todos os arquivos que estão dentro do diretório informado.
*  Se o diretorio informado não for um diretório, então lança uma java.nio.file.NotDirectoryException.
*/
Files.list(diretorio) Stream<Path>;
```

```java
/**
*  Caminha por todos os diretorios para dentro, a partir do 'path' diretório informado.
*  Se o diretório não existir, então lança uma java.nio.file.NoSuchFileException.
*  Se informar com 'path' um arquivo, irá retornar o Path desse arquivo.
*/
Files.walk(path) Stream<Path>;

// maxDepth: É o máximo de profundidade que pode entrar nas pastas. 
// Se maxDepth == 0 então nenhuma. 
// Se maxDepth == 1 então pasta corrente. 
// Se maxDepth < 0 então lança uma java.lang.IllegalArgumentException: 'maxDepth' is negative.
Files.walk(d, maxDepth) Stream<Path>;
```

```java
/**
* Assim como o walk(), também é realizada uma busca, porém mais específica.
* Exemplo: Buscando todos os arquivos que tenhas a extensão ".txt" e com um mínimos de tamanho.
*/
Files.find(directory, 
      maxDepth, 
      (path, basicFileAttributes) -> path.toString().endsWith(".txt") && basicFileAttributes.size() > minimoSize) Stream<Path>;
```

```java
/**
* Lê as linhas sem armazenar em memória.
*/
Files.lines(path) Stream<String>;
```

```java
public static List<String> readAllLines(Path path) throws IOException {}
```

```java
/**
* Verifica se os 2 paths são os mesmos.
* O 1º 'path' sempre severá existir, caso contrário irá lançar uma java.nio.file.NoSuchFileException
*/
public static boolean isSameFile(Path path, Path path2) throws IOException;
```
 
```java
/**
* LinkOption para visualizar use: BasicFileAttributes, PosixFileAttributes, DosFileAttributes.
* LinkOption para editar use: BasicFileAttributesView, PosixFileAttributesView, DosFileAttributesView
* LinkOption para definir dono do arquivo: FileOwnerAttributeView
* LinkOption para config. avançadas: AclFileAttributeView
*/
public static <A extends BasicFileAttributes> A readAttributes(Path path, Class<A> type, LinkOption... options) throws IOException
```
