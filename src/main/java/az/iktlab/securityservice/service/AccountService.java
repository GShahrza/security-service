package az.iktlab.securityservice.service;

import az.iktlab.securityservice.dto.request.AccountRequestDTO;
import az.iktlab.securityservice.dto.response.AccountResponseDTO;

import java.util.List;

public interface AccountService {
    Boolean addUser(AccountRequestDTO request);

    List<AccountResponseDTO> getAllUsers();

    AccountResponseDTO getUserById(Long id);

    AccountResponseDTO getUserByUsername(String username);

    Boolean updateUser(Long id,AccountRequestDTO accountResponse);

    Boolean deleteById(Long id);
}
