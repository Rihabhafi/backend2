package com.PFE.Backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PFE.Backend.DTO.TicketDTO;
import com.PFE.Backend.service.TicketService;

@CrossOrigin(origins = "http://localhost:3002/")
@RestController
@RequestMapping("/tickets")
public class TicketController {
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    // 🔹 1. Ajouter un ticket
    @PostMapping("/add")
    public TicketDTO createTicket(@RequestBody TicketDTO ticketDTO) {
        return ticketService.createTicket(ticketDTO);
    }

    // 🔹 2. Récupérer un ticket par ID
    @GetMapping("/{id}")
    public TicketDTO getTicketById(@PathVariable Integer id) {
        return ticketService.getTicketById(id);
    }

    // 🔹 3. Récupérer tous les tickets
    @GetMapping
    public List<TicketDTO> getAllTickets() {
        return ticketService.getAllTickets();
    }

    // 🔹 4. Modifier un ticket
    @PutMapping("/{id}")
    public TicketDTO updateTicket(@PathVariable Integer id, @RequestBody TicketDTO ticketDTO) {
        return ticketService.updateTicket(id, ticketDTO);
    }

    // 🔹 5. Supprimer un ticket
    @DeleteMapping("/{id}")
    public void deleteTicket(@PathVariable Integer id) {
        ticketService.deleteTicket(id);
    }
        // 🔹 6. Prendre en charge un ticket (assignation par technicien lui-même)
    @PutMapping("/{id}/assign")
    public ResponseEntity<TicketDTO> assignTicket(@PathVariable Integer id) {
        TicketDTO assignedTicket = ticketService.assignTicketToCurrentTechnicien(id);
        return ResponseEntity.ok(assignedTicket);
    }

      // 🔹 7. Compter les tickets EN_ATTENTE
      @GetMapping("/count/en-attente")
      public ResponseEntity<Long> countTicketsEnAttente() {
          long count = ticketService.countTicketsEnAttente();
          return ResponseEntity.ok(count);
      }

}
