package az.iktlab.securityservice.dto.response;

import az.iktlab.securityservice.dao.entity.ERole;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleResponseDTO {

    Long id;
    ERole roleName;
}
