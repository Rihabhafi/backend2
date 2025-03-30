package com.PFE.Backend.mapper;

import org.mapstruct.Mapper;

import com.PFE.Backend.DTO.UserMaxDTO;
import com.PFE.Backend.models.User;

@Mapper(componentModel = "spring")
public interface UserMaxMapper {
    UserMaxDTO userToUserDTO(User user);

    
    User userDTOToUser(UserMaxDTO userMaxDTO);

}
