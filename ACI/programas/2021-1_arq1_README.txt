PUC-Minas
Instituto de Ci�ncias Exatas e Informatica
Curso de Ci�ncia da Computa��o
Arquitetura de Computadores I

Pacote de programas para fundamentos de sistemas digitais

Itens

- 8085    v1.0 e v2.0
- CPUSim  v3.9.0
- Icarus  Verilog v.12.0 com GTKWave v3.3.100
- JFLAP   v7.1
- Logisim v2.16.x.y (ou superior, vers�o mais atualizada) 
- LMC               (LittleManComputer)

Instala��o

O arquivo compactado dever� ser expandido em uma 
pasta de sua prefer�ncia, por exemplo em C:\.

Os atalhos (.lnk) dever�o ser verificados e 
editados para corresponder a essa pasta.

Os arquivos terminados em (.bat) tamb�m dever�o 
ser editados para ajuste dos caminhos (path).

Recomenda-se testar previamente o funcionamento 
de todos os programas, mesmo sem previs�es para
usos imediatos.

Para maior comodidade sugere-se  acrescentar  �
vari�vel de ambiente  PATH  o  caminho  para as
pastas  que  contenham  programas  execut�veis, 
principalmente o Icarus_Verilog (iverilog) e
GTKWave.

Em linha de comando, para compilar um programa
em Verilog, usar:

iverilog -o program.vvp program.v

A extens�o (.v) est� associada o arquivo fonte
e a exten�o (.vvp) est� associada ao arquivo
objeto cuja execu��o poder� ser feita como
descrito a seguir

vvp program.vvp



Observa��es

Qualquer editor de textos poder� ser associado
para programas em Verilog. Sugere-se o uso de
editores nativos do pr�prio sistema operacional.

A maioria dos programas selecionados funcionar�o
em ambientes de quaisquer sistemas operacionais,
com Java, pelo menos, a vers�o 1.8.xx instalada.
O Icarus_Verilog e o GTKWAVE s�o dependentes 
de plataforma e requerem maior aten��o.

A vers�o sugerida para ambiente Windows j� est�
atualizada, manualmente, o que n�o ocorre na
vers�o de distribui��o dispon�vel no site.
Por iso, sugere-se o uso da primeira, tomando
as provid�ncias necess�rias para a sua 
localiza��o no sistema operacional.

Em ambientes Linux o Icarus_Verilog e o GTKWAVE
podem ser instalados via

sudo apt-get install iverilog gtkwave

embora as vers�es possam ser mais antigas,
por�m aceit�veis.

Para os demais programas basta a simples c�pia
para a pasta /opt e configura��o das permiss�es
para execu��o.
