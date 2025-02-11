package es.vedruna.appVedruna.services;

import es.vedruna.appVedruna.model.Ticket;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public interface TicketService {
    
    public Ticket createTicket(Ticket ticket);
    public List<Ticket> getTicketsByUserNick(String userNick);
    public Optional<Ticket> getTicketById(String id);
    public Ticket updateTicket(String id, Ticket updatedTicket);
    public void deleteTicket(String id);

}
