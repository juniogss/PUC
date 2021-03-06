/*
    Arquitetura de Computadores I - Guia_0103.
    Nome: Junio Gabriel Silva Santos
    Matricula: 633516
*/
module G01Q03;

  integer n1 = 73;    // decimal
  integer n2 = 47;    // decimal
  integer n3 = 61;    // decimal
  integer n4 = 157;   // decimal
  integer n5 = 171;   // decimal

  reg [7:0] a, b, c, d, e;   

  initial
  begin : main

    a = n1;
    b = n2;
    c = n3;
    d = n4;
    e = n5;

    $display ( "Guia 01 Quastão 03" );

    $display ( "a) = %8b", a );
    $display ( "b) = %4b", b );
    $display ( "c) = %x", c );
    $display ( "d) = %x", d );
    $display ( "e) = %x", e );
  end

endmodule

/** SAÍDA
Guia 01 Quastão 03
a) = 01001001
b) = 00101111
c) = 3d
d) = 9d
e) = ab
*/
