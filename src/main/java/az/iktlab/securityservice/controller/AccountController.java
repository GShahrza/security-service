package az.iktlab.securityservice.controller;

import az.iktlab.securityservice.dto.request.AccountRequestDTO;
import az.iktlab.securityservice.dto.response.AccountResponseDTO;
import az.iktlab.securityservice.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/users")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET,RequestMethod.PUT,RequestMethod.DELETE})
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AccountResponseDTO> getAllUsers() {
        return accountService.getAllUsers();
    }

    @GetMapping(path = "/{id}")
    public AccountResponseDTO getUserById(@PathVariable Long id) {
        return accountService.getUserById(id);
    }

    @GetMapping(path = "/")
    public AccountResponseDTO getUserByUsername(@RequestParam String username) {
        return accountService.getUserByUsername(username);
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean update(@PathVariable Long id, @RequestBody AccountRequestDTO accountResponse) {
        return accountService.updateUser(id, accountResponse);
    }

    @DeleteMapping(path = "/{id}")
    public Boolean delete(@PathVariable Long id){
        return accountService.deleteById(id);
    }
}
