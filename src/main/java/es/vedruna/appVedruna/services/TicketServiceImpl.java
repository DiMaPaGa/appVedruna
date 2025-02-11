package es.vedruna.appVedruna.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.vedruna.appVedruna.model.Ticket;
import es.vedruna.appVedruna.repository.TicketRepository;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TicketServiceImpl implements TicketService {
    
    @Autowired
    private TicketRepository ticketRepository;

    @Override
    public Ticket createTicket(Ticket ticket) {
        ticket.setFechaCreacion(LocalDateTime.now());
        ticket.setEstado(ticket.getEstado() != null ? ticket.getEstado() : "En trámite");
        return ticketRepository.save(ticket);
    }

    @Override
    public List<Ticket> getTicketsByUserNick(String userNick) {
        return ticketRepository.findByUserNick(userNick);
    }

    @Override
    public Optional<Ticket> getTicketById(String userNick) {
        return ticketRepository.findById(userNick);
    }

    @Override
    public Ticket updateTicket(String id, Ticket updatedTicket) {
        return ticketRepository.findById(id).map(ticket -> {
            ticket.setTitulo(updatedTicket.getTitulo());
            ticket.setDescripcion(updatedTicket.getDescripcion());
            ticket.setEstado(updatedTicket.getEstado());
            return ticketRepository.save(ticket);
        }).orElse(null);
    }

    @Override
    public void deleteTicket(String id) {
        ticketRepository.deleteById(id);
    }
    
}
