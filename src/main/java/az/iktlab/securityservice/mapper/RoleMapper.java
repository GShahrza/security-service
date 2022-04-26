package az.iktlab.securityservice.mapper;

import az.iktlab.securityservice.dao.entity.Role;
import az.iktlab.securityservice.dto.response.RoleResponseDTO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleResponseDTO roleToRoleResponseDTO(Role role);
}
