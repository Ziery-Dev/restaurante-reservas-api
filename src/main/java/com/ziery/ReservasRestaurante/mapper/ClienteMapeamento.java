package com.ziery.ReservasRestaurante.mapper;


import com.ziery.ReservasRestaurante.dtos.request.ClienteDto;
import com.ziery.ReservasRestaurante.dtos.request.ReservaDto;
import com.ziery.ReservasRestaurante.dtos.response.ClienteDtoReposta;
import com.ziery.ReservasRestaurante.entites.Cliente;
import com.ziery.ReservasRestaurante.entites.Mesa;
import com.ziery.ReservasRestaurante.entites.Reserva;

//Essa classe auxilia no mapeamento Dto para Entidade e Entidade para Dto
public class ClienteMapeamento {

    public static Cliente toCliente(ClienteDto clienteDto) {
        Cliente cliente = new Cliente();
        return setarValoresCliente(clienteDto, cliente); //chama o metodo de atualizar somente para setar os valores
    }

    public static ClienteDtoReposta toClienteDtoResponse(Cliente cliente) {
        return new ClienteDtoReposta(
                cliente.getId(),
                cliente.getNome(),
                cliente.getTelefone(),
                cliente.getEmail()
        );
    }

    public static Cliente setarValoresCliente (ClienteDto clienteDto,  Cliente cliente) {
        cliente.setNome(clienteDto.nome());
        cliente.setEmail(clienteDto.email());
        cliente.setTelefone(clienteDto.telefone());
        return cliente;
    }


}
