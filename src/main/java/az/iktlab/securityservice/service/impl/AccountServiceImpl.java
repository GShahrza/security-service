package az.iktlab.securityservice.service.impl;

import az.iktlab.securityservice.dao.entity.Account;
import az.iktlab.securityservice.dao.entity.ERole;
import az.iktlab.securityservice.dao.entity.Role;
import az.iktlab.securityservice.dao.repository.AccountRepository;
import az.iktlab.securityservice.dto.request.AccountRequestDTO;
import az.iktlab.securityservice.dto.response.AccountResponseDTO;
import az.iktlab.securityservice.exception.DuplicateFieldException;
import az.iktlab.securityservice.exception.UserNotFoundException;
import az.iktlab.securityservice.mapper.AccountMapper;
import az.iktlab.securityservice.service.AccountService;
import az.iktlab.securityservice.util.Validation;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public Boolean addUser(AccountRequestDTO request) {
        Validation.validateRequest(request);
        request.setPassword(encoder.encode(request.getPassword()));

        Account account = accountMapper.accountRequestDTOtoAccount(request);
        if (accountRepository.existsByUsername(account.getUsername())) {
            throw new DuplicateFieldException(String.format("Username: %s is exists", account.getUsername()));
        } else if (accountRepository.existsByEmail(account.getEmail())) {
            throw new DuplicateFieldException(String.format("Email: %s is exists", account.getEmail()));
        }
        account.getRoles().add(new Role(2L, ERole.ROLE_USER));
        accountRepository.save(account);
        return true;
    }

    @Override
    public Boolean updateUser(Long id, AccountRequestDTO request) {
        Account account = getAccountById(id);
        Validation.validateRequest(request);
        if (accountRepository.existsByUsername(request.getUsername())) {
            throw new DuplicateFieldException(String.format("Username: %s is exists", request.getUsername()));
        } else if (accountRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateFieldException(String.format("Email: %s is exists", request.getEmail()));
        }
        account.setPassword(encoder.encode(request.getPassword()));
        account.setUsername(request.getUsername());
        account.setEmail(request.getEmail());
        accountRepository.save(account);
        return true;
    }

    @Override
    public List<AccountResponseDTO> getAllUsers() {
        return accountRepository.findAll().stream()
                .map(accountMapper::accountToAccountResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AccountResponseDTO getUserById(Long id) {
        return accountMapper.accountToAccountResponseDTO(getAccountById(id));
    }

    @Override
    public AccountResponseDTO getUserByUsername(String username) {
        return accountMapper
                .accountToAccountResponseDTO(accountRepository.findByUsername(username)
                        .orElseThrow(() -> new UserNotFoundException(String.format("User: %s Not Found", username))));
    }

    @Override
    public Boolean deleteById(Long id) {
        accountRepository.delete(getAccountById(id));
        return true;
    }

    private Account getAccountById(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User Not found!"));
    }
}
