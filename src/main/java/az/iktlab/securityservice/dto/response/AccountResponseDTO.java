package az.iktlab.securityservice.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountResponseDTO {

    Long id;
    String username;
    String email;
    Set<RoleResponseDTO> roles;
}
