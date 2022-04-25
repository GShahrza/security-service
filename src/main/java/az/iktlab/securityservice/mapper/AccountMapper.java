package az.iktlab.securityservice.mapper;

import az.iktlab.securityservice.dao.entity.Account;
import az.iktlab.securityservice.dto.request.AccountRequestDTO;
import az.iktlab.securityservice.dto.response.AccountResponseDTO;

public interface AccountMapper {

    Account accountRequestDTOtoAccount(AccountRequestDTO requestDTO);

    AccountResponseDTO accountToAccountResponseDTO(Account account);
}
