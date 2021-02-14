/*
 Guia_0101.
*/
module Guia_0101;
// define data
 integer x = 27; // decimal
 reg [7:0] b = 0; // binary
// actions
 initial
 begin : main
 $display ( "Guia_0101 - Tests" );
 $display ( "x = %d" , x );
 b = x;
 $display ( "b = %8b", b );
 end // main
endmodule // Guia_0101
