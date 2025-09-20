package com.springboot.crud_operation_banking_project.service;

import com.springboot.crud_operation_banking_project.model.Account;
import com.springboot.crud_operation_banking_project.repository.AccountRepository;
import com.springboot.crud_operation_banking_project.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class AccountService {

    private AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<Account> getAllAccounts() {
        log.info("Fetching all accounts");
        return accountRepository.findAll();
    }

    public Account getAccountById(Long id) {
        log.info("Fetching account with ID: {}", id);
        return accountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found with ID: " + id));
    }

    public void createAccount(Account account) {
        log.info("Creating account: {}", account);
        accountRepository.save(account);
    }

    public void updateAccount(Long id, Account account) {
        log.info("Updating account with ID: {}", id);
        Account existingUser = getAccountById(id);
        log.info("Existing user: {}", existingUser);
        existingUser.setAccountHolderName(account.getAccountHolderName());
        existingUser.setAccountType(account.getAccountType());
        existingUser.setBalance(account.getBalance());
        accountRepository.update(existingUser);
    }

    public void deleteAccount(Long id) {
        log.info("Deleting account with ID: {}", id);
        Account existingUser = getAccountById(id);
        log.info("Existing user: {}", existingUser);
        accountRepository.deleteById(id);
    }
}
