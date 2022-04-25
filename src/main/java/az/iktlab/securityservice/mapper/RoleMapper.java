package az.iktlab.securityservice.mapper;

import az.iktlab.securityservice.dao.entity.Role;
import az.iktlab.securityservice.dto.response.RoleResponseDTO;

public interface RoleMapper {

    RoleResponseDTO roleToRoleResponseDTO(Role role);
}
