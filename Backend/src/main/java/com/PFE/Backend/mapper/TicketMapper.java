package com.PFE.Backend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.PFE.Backend.DTO.TicketDTO;
import com.PFE.Backend.models.Ticket;

@Mapper(componentModel = "spring")
public interface TicketMapper {
    // 🔄 Ticket -> TicketDTO
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.username", target = "username")
    @Mapping(source = "equipement.id", target = "equipementId")
    @Mapping(source = "equipement.nom", target = "equipementnom")
    @Mapping(source = "assignedUser.id", target = "assignedUserId")
    @Mapping(source = "assignedUser.username", target = "assignedname")
    @Mapping(source = "ticket.dateCreation", target = "datecreation")
    @Mapping(source = "objet", target = "objet") // <-- Ajout ici
    TicketDTO ticketToTicketDTO(Ticket ticket);

    // 🔄 TicketDTO -> Ticket
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "equipement", ignore = true)
    @Mapping(target = "assignedUser", ignore = true)
    @Mapping(source = "objet", target = "objet") // <-- Ajout ici aussi
    Ticket ticketDTOToTicket(TicketDTO ticketDTO);

}
