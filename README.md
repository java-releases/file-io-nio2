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
