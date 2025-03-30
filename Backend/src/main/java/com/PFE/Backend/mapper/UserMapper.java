package com.PFE.Backend.mapper;

import org.mapstruct.Mapper;

import com.PFE.Backend.DTO.UserDTO;
import com.PFE.Backend.models.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO userToUserDTO(User user);

    
    User userDTOToUser(UserDTO userDTO);
}
