package com.PFE.Backend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.PFE.Backend.DTO.InterventionDTO;
import com.PFE.Backend.models.Intervention;

@Mapper(componentModel = "spring")
public interface InterventionMapper {

     // 🔄 Intervention -> InterventionDTO
     @Mapping(source = "technicien.id", target = "technicienId")
     @Mapping(source = "technicien.username", target = "technicienusername")
     @Mapping(source = "ticket.id", target = "ticketId")
     @Mapping(source = "dateIntervention", target = "dateIntervention")
     @Mapping(source = "equipement.id", target = "equipementId")
  
     InterventionDTO interventionToInterventionDTO(Intervention intervention);
 
     // 🔄 InterventionDTO -> Intervention
     @Mapping(target = "technicien", ignore = true)  // Gestion dans le service
     @Mapping(target = "ticket", ignore = true)      // Gestion dans le service
     Intervention interventionDTOToIntervention(InterventionDTO interventionDTO);
}
