package com.mindhub.Homebanking.services;
import com.mindhub.Homebanking.dtos.AccountDTO;
import com.mindhub.Homebanking.models.Account;
import java.util.Set;

public interface AccountService {

    Set<AccountDTO> getAccountsDTO();
    AccountDTO getAccountDTO(Long id);

    Account findByNumber(String number);
    void saveAccount(Account account);

}
