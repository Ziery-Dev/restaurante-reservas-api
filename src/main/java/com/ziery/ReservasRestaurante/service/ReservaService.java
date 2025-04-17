package com.ziery.ReservasRestaurante.service;

import com.ziery.ReservasRestaurante.controller.GenericController;
import com.ziery.ReservasRestaurante.dtos.request.ReservaDto;
import com.ziery.ReservasRestaurante.dtos.response.ReservaDtoResposta;
import com.ziery.ReservasRestaurante.dtos.response.ReservaRepostaComMensagem;
import com.ziery.ReservasRestaurante.entites.Cliente;
import com.ziery.ReservasRestaurante.entites.Mesa;
import com.ziery.ReservasRestaurante.entites.Reserva;
import com.ziery.ReservasRestaurante.exception.ViolacaoDeIntegridadeException;
import com.ziery.ReservasRestaurante.mapper.ReservaMapeamento;
import com.ziery.ReservasRestaurante.mapper.ReservaMapper;
import com.ziery.ReservasRestaurante.repository.ClienteRepository;
import com.ziery.ReservasRestaurante.repository.MesaRepository;
import com.ziery.ReservasRestaurante.repository.ReservaRepository;
import com.ziery.ReservasRestaurante.utils.VerificadorEntidade;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ReservaService  {

    private ReservaRepository reservaRepository;
    private MesaRepository mesaRepository;
    private ClienteRepository clienteRepository;
    private final ReservaMapper reservaMapper;

    //método salvar
    public ReservaRepostaComMensagem salvar(ReservaDto reservaDto){
        Mesa mesa = VerificadorEntidade.verificarOuLancarException(mesaRepository.findById(reservaDto.idMesa()), reservaDto.idMesa(), "Mesa"); //verifica e existencia da mesa
        vefirificaEntidadeJaCadastrada(reservaDto, null, "Mesa");
        verificarQuantidade(reservaDto.quantidadePessoas(), mesa.getCapacidade());
        Cliente cliente = VerificadorEntidade.verificarOuLancarException(clienteRepository.findById(reservaDto.idCliente()), reservaDto.idCliente(), "Cliente"); //verifica a existencia do cliente
        Reserva reserva = reservaMapper.toReserva(reservaDto);  //mapeia de os dados do Dto para entidade
        reserva.setCliente(cliente);
        reserva.setMesa(mesa);
        System.out.println("cliente" + reserva.getCliente());
        System.out.println("mesa" + reserva.getMesa());
        reservaRepository.save(reserva);
        ReservaDtoResposta resposta = reservaMapper.toReservaDtoResposta(reserva); //mapeia de entidade para dto
        return new ReservaRepostaComMensagem( "Reserva salva com sucesso! ", resposta );
    }

    //Exibir reserva por id
    public ReservaDtoResposta buscarReservaId(Long id){
        var reserva = VerificadorEntidade.verificarOuLancarException(reservaRepository.findById(id), id, "Reserva");
        return reservaMapper.toReservaDtoResposta(reserva);
    }

    //excluir reserva por id
    public void deletarReserva(Long id){
        var reserva = VerificadorEntidade.verificarOuLancarException(reservaRepository.findById(id), id, "Reserva");
        reservaRepository.delete(reserva);
    }

    //atualizar reserva
    public ReservaRepostaComMensagem atualizarReserva(Long id, ReservaDto reservaDto){
        Reserva reserva = VerificadorEntidade.verificarOuLancarException(reservaRepository.findById(id), id, "Reserva");
        Mesa mesa = VerificadorEntidade.verificarOuLancarException(mesaRepository.findById(reservaDto.idMesa()), reservaDto.idMesa(), "Mesa"); //verifica e existencia da mesa
        vefirificaEntidadeJaCadastrada(reservaDto, id, "Cliente");
        vefirificaEntidadeJaCadastrada(reservaDto, id, "Mesa");
        verificarQuantidade(reservaDto.quantidadePessoas(), mesa.getCapacidade());
        Cliente cliente = VerificadorEntidade.verificarOuLancarException(clienteRepository.findById(reservaDto.idCliente()), reservaDto.idCliente(), "Cliente"); //verifica a existencia do cliente
        reservaMapper.ReservaSetValores(reservaDto, reserva);
        reserva.setCliente(cliente);
        reserva.setMesa(mesa);
        reservaRepository.save(reserva);
        ReservaDtoResposta resposta = reservaMapper.toReservaDtoResposta(reserva);
        return new ReservaRepostaComMensagem("Reserva atualizada com sucesso", resposta );
    }


    //Métodos auxiliares

    public void verificarQuantidade(Long quantidade, int capacidade){
        if (quantidade > capacidade){
            throw new ViolacaoDeIntegridadeException("A quantidade de pessoas na reserva não pode ser maior que a capacidade da mesa. capacidade da mesa selecionada: "+ capacidade);
        }
    }




    public void vefirificaEntidadeJaCadastrada (ReservaDto reservaDto, Long idReserva, String entidade){
        List<Reserva> reservaExistente = new ArrayList<>();

        if (entidade.equals("Cliente")){
            reservaExistente = reservaRepository.findByClienteId(reservaDto.idCliente());
        }
        else if (entidade.equals("Mesa")){
            reservaExistente = reservaRepository.findByMesaId(reservaDto.idMesa());
        }
        if (!reservaExistente.isEmpty()){
            for (Reserva reserva : reservaExistente){
                if (idReserva == null || !reserva.getId().equals(idReserva)){
                    long diferencaEntreHoras = Math.abs(Duration.between(reserva.getDataHora(), reservaDto.dataHora()).toHours());
                    if (diferencaEntreHoras < 2){
                        throw new ViolacaoDeIntegridadeException("A reserva não pode ser feita por que o(a) " + entidade + "  já está em outra reserva num intervalo de 2 horas");
                    }

                }

            }

        }

    }








}
