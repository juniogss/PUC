/*
    Arquitetura de Computadores I - Guia_0104.
    Nome: Junio Gabriel Silva Santos
    Matricula: 633516
*/
module G01Q04;

  integer n1 = 0;    // decimal
  integer n2 = 0;    // decimal
  integer n3 = 0;    // decimal
  integer n4 = 0;    // decimal
  integer n5 = 0;    // decimal

  reg [7:0] a = 8'b10101;   // binary (bits)
  reg [7:0] b = 8'b11010;   // binary (bits)
  reg [7:0] c = 8'b101001;  // binary (bits)
  reg [7:0] d = 8'b111001;  // binary (bits)
  reg [7:0] e = 8'b100011;  // binary (bits)

  initial
  begin : main

    n1 = a;
    n2 = b;
    n3 = c;
    n4 = d;
    n5 = e;

    $display ( "Guia 01 Quastão 04" );

    $display ( "a) = %d", n1 );
    $display ( "b) = %d", n2 );
    $display ( "c) = %d", n3 );
    $display ( "d) = %d", n4 );
    $display ( "e) = %d", n5 );
  end

endmodule

/** SAÍDA
Guia 01 Questão 04
a) =          21
b) =          26
c) =          41
d) =          32 30 32 31 2d 31
e) =          35
*/
