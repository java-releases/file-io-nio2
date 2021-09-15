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
  - Se inicia com 'c:' (a letra do driver), então será considerado `caminho absoluto`;
  - Para qualquer outro cenário, será considerado `caminho relativo`;
- File System Symbols:
  - `.` Uma referência ao diretório corrente;
  - `..` Uma referência ao parent do diretório corrente;

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

<hr>

> ### Usar a classe Files para verificar, excluir, copiar ou mover um arquivo ou diretório:
```java
/**
*  Cria um diretório. Caso o mesmo já existir, então lança uma `java.nio.file.FileAlreadyExistsException`.
*/
Files.createDirectory(Path.of("C:\\diretorio-01"));
```

```java
/**
*  Cria um diretório. Caso o mesmo já existir, então não irá acontecer nada.
*/
Files.createDirectories(Path.of("C:\\diretorio-01"));
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
*  Se informar 2 diretórios, então irá renomear o 'path1' com o novo 'path2'.
*  Se informar 2 arquivos, então irá mover o 'path1' para o 'path2'.
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
Files.delete(path) void;

// Se o 'path' (diretório) não estiver vazio, então lança uma java.nio.file.DirectoryNotEmptyException.
// Se conseguir deletar retorna TRUE, senão retorna FALSE.
Files.deleteIfExists(path) boolean;
```

<hr>

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
*  Caminha por todos os diretorios para dentro, a partir do 'path' diretório informado.
*  Se o diretório não existir, então lança uma java.nio.file.NoSuchFileException.
*  Se informar com 'path' um arquivo, irá retornar o Path desse arquivo.
*/
Files.walk(path) Stream<Path>;
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
