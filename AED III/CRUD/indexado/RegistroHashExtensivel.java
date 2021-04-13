/*
REGISTRO HASH EXTENSÍVEL

Esta interface apresenta os métodos que os objetos
a serem incluídos na tabela hash extensível devem
conter.

Implementado pelo Prof. Marcos Kutova
v1.1 - 2021
*/

package CRUD.indexado;

import java.io.IOException;

public interface RegistroHashExtensivel<T> {

    int hashCode(); // chave numérica para ser usada no diretório

    short size(); // tamanho FIXO do registro

    byte[] toByteArray() throws IOException; // representação do elemento em um vetor de bytes

    void fromByteArray(byte[] ba) throws IOException; // vetor de bytes a ser usado na construção do elemento

}
