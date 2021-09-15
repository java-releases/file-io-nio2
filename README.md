![](https://github.com/ocpjp-study/file-io-nio2/blob/main/ocpjp.png)

[Preparação para o Exame 1Z0-817](https://education.oracle.com/pt_BR/upgrade-ocp-java-6-7-8-to-java-se-11-developer/pexam_1Z0-817)

### Tópico: File-io-nio2
### Objetivos
- Usar a interface Path para operar em caminhos de arquivos e diretórios;
- Usar a classe Files para verificar, excluir, copiar ou mover um arquivo ou diretório;
- Usar Stream API com arquivos;

### Resumo
- Em termos da prova a definição de Path será:
  - Se inicia com '/' barra, será considerado `caminho absoluto`;
  - Se inicia com 'c:' (a letra do driver), então será considerado `caminho absoluto`;
  - Para qualquer outro cenário, será considerado `caminho relativo`;
- File System Symbols:
  - `.` Uma referência ao diretório corrente;
  - `..` Uma referência ao parent do diretório corrente;

```java
/**
*  Retorna TRUE se os arquivos forem os mesmos. Caso contrário lança uma `java.nio.file.NoSuchFileException`
*/
Files.isSameFile(path01, path02);
```

```java
/**
*  Cria um diretório. Caso o mesmo já existir, então lança uma `java.nio.file.FileAlreadyExistsException`
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
*  Caso o arquivo informado 'path1' não exista, então irá lançar uma `java.nio.file.NoSuchFileException`
*  Caso o diretório do 'newPath' não exista, então irá lançar uma java.nio.file.NoSuchFileException
*  Caso o novo arquivo 'newPath' já exista, então irá lançar uma `java.nio.file.FileAlreadyExistsException`
*/
Files.copy(path1, newPath);

// Subscreve o arquivo 'newPath' caso ele já existir. Fazendo com que não lance mais uma Exception.
Files.copy(path1, newPath, StandardCopyOption.REPLACE_EXISTING);

// Copia arquivo e exibe todo conteúdo no console
Files.copy(path1, System.out);
```

```java
/**
*  Se informar 2 diretórios, então irá renomear o 'path1' com o novo 'path2'
*  Se informar 2 arquivos, então irá mover o 'path1' para o 'path2'
*  Se o 'path1' não existir, então lança uma java.nio.file.NoSuchFileException
*  Se o 'path2' já existir, então lança uma java.nio.file.FileAlreadyExistsException
*/
Files.move(path1, path2);
```
