package com.PFE.Backend.mapper;

import org.mapstruct.Mapper;

import com.PFE.Backend.DTO.EquipementDTO;
import com.PFE.Backend.models.Equipement;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface EquipementMapper {
    // Mapper Equipement -> EquipementDTO
    EquipementDTO equipementToEquipementDTO(Equipement equipement);

    // Mapper EquipementDTO -> Equipement
    Equipement equipementDTOToEquipement(EquipementDTO equipementDTO);

}
