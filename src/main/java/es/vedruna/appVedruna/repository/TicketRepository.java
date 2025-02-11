package es.vedruna.appVedruna.repository;

import es.vedruna.appVedruna.model.Ticket;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends MongoRepository<Ticket, String> {
    List<Ticket> findByUserNick(String userNick);
}
