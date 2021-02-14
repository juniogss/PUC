PUC-Minas
Instituto de Ciências Exatas e Informatica
Curso de Ciência da Computação
Arquitetura de Computadores I

Pacote de programas para fundamentos de sistemas digitais

Itens

- 8085    v1.0 e v2.0
- CPUSim  v3.9.0
- Icarus  Verilog v.12.0 com GTKWave v3.3.100
- JFLAP   v7.1
- Logisim v2.16.x.y (ou superior, versão mais atualizada) 
- LMC               (LittleManComputer)

Instalação

O arquivo compactado deverá ser expandido em uma 
pasta de sua preferência, por exemplo em C:\.

Os atalhos (.lnk) deverão ser verificados e 
editados para corresponder a essa pasta.

Os arquivos terminados em (.bat) também deverão 
ser editados para ajuste dos caminhos (path).

Recomenda-se testar previamente o funcionamento 
de todos os programas, mesmo sem previsões para
usos imediatos.

Para maior comodidade sugere-se  acrescentar  à
variável de ambiente  PATH  o  caminho  para as
pastas  que  contenham  programas  executáveis, 
principalmente o Icarus_Verilog (iverilog) e
GTKWave.

Em linha de comando, para compilar um programa
em Verilog, usar:

iverilog -o program.vvp program.v

A extensão (.v) está associada o arquivo fonte
e a extenão (.vvp) está associada ao arquivo
objeto cuja execução poderá ser feita como
descrito a seguir

vvp program.vvp



Observações

Qualquer editor de textos poderá ser associado
para programas em Verilog. Sugere-se o uso de
editores nativos do próprio sistema operacional.

A maioria dos programas selecionados funcionarão
em ambientes de quaisquer sistemas operacionais,
com Java, pelo menos, a versão 1.8.xx instalada.
O Icarus_Verilog e o GTKWAVE são dependentes 
de plataforma e requerem maior atenção.

A versão sugerida para ambiente Windows já está
atualizada, manualmente, o que não ocorre na
versão de distribuição disponível no site.
Por iso, sugere-se o uso da primeira, tomando
as providências necessárias para a sua 
localização no sistema operacional.

Em ambientes Linux o Icarus_Verilog e o GTKWAVE
podem ser instalados via

sudo apt-get install iverilog gtkwave

embora as versões possam ser mais antigas,
porém aceitáveis.

Para os demais programas basta a simples cópia
para a pasta /opt e configuração das permissões
para execução.
