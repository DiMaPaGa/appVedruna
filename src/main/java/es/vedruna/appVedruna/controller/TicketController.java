package es.vedruna.appVedruna.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import es.vedruna.appVedruna.model.Ticket;
import es.vedruna.appVedruna.services.TicketService;
import es.vedruna.appVedruna.services.TicketServiceImpl;
import es.vedruna.appVedruna.services.EmailService;





@RestController
@RequestMapping("/tickets")
public class TicketController {

    private final TicketService ticketService;
    private final EmailService emailService;  

    @Value("${app.admin-email}") 
    private String adminEmail;

    public TicketController(TicketService ticketService, EmailService emailService) {
        this.ticketService = ticketService;
        this.emailService = emailService;
    }

    @PostMapping("/crear")
    public Ticket crearTicket(@RequestBody Ticket ticket) {
        Ticket nuevoTicket = ticketService.createTicket(ticket);

        
        String subject = "Nuevo Ticket Creado: " + ticket.getTitulo();
        String body = "Se ha creado un nuevo ticket:\n\n" +
                      "Título: " + ticket.getTitulo() + "\n" +
                      "Descripción: " + ticket.getDescripcion() + "\n" +
                      "Equipo/Clase: " + ticket.getEquipoClase();

        emailService.sendEmail(adminEmail, subject, body);

        return nuevoTicket;
    }

    // Endpoint para obtener todos los tickets de un usuario
    @GetMapping("/user/{userNick}")
    public List<Ticket> getTicketsByUserNick(@PathVariable String userNick) {
        return ticketService.getTicketsByUserNick(userNick);
    }
}