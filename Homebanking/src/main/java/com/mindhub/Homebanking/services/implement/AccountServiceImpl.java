package com.mindhub.Homebanking.services.implement;

import com.mindhub.Homebanking.dtos.AccountDTO;
import com.mindhub.Homebanking.models.Account;
import com.mindhub.Homebanking.repositories.AccountRepository;
import com.mindhub.Homebanking.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;


    @Override
    public Set<AccountDTO> getAccountsDTO() {
        return accountRepository.findAll().stream().map(AccountDTO::new).collect(Collectors.toSet());
    }

    @Override
    public AccountDTO getAccountDTO(Long id) {
        return accountRepository.findById(id).map(AccountDTO::new).orElse(null);
    }

    @Override
    public Account findByNumber(String number) {
        return accountRepository.findByNumber(number);
    }

    @Override
    public void saveAccount(Account account) {
        accountRepository.save(account);
    }
}
