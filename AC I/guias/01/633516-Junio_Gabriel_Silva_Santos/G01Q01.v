/*
    Arquitetura de Computadores I - Guia_0101.
    Nome: Junio Gabriel Silva Santos
    Matricula: 633516
*/
module G01Q01;

  integer n1 = 27;      // decimal
  integer n2 = 51;      // decimal
  integer n3 = 713;     // decimal
  integer n4 = 312;     // decimal
  integer n5 = 360;     // decimal

  reg [7:0] a, b;       // binary
  reg [15:0] c, d, e;   // binary

  initial
  begin : main

    a = n1;
    b = n2;
    c = n3;
    d = n4;
    e = n5;

    $display ( "Guia 01 Quastão 01" );

    $display ( "a) = %8b", a );
    $display ( "b) = %8b", b );
    $display ( "c) = %8b", c );
    $display ( "d) = %8b", d );
    $display ( "e) = %8b", e );
  end

endmodule

/** SAÍDA
Guia 01 Quastão 01
a) = 00011011
b) = 00110011
c) = 0000001011001001
d) = 0000000100111000
e) = 0000000101101000
*/
