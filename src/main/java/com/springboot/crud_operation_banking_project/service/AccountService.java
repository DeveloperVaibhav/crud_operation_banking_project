package com.springboot.crud_operation_banking_project.service;

import com.springboot.crud_operation_banking_project.model.Account;
import com.springboot.crud_operation_banking_project.repository.AccountRepository;
import com.springboot.crud_operation_banking_project.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    @Autowired
    private final AccountRepository accountRepository;

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account getAccountById(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found with ID: " + id));
    }

    public void createAccount(Account account) {
        accountRepository.save(account);
    }

    public void updateAccount(Long id, Account account) {
        Account existing = getAccountById(id);
        existing.setAccountHolderName(account.getAccountHolderName());
        existing.setAccountType(account.getAccountType());
        existing.setBalance(account.getBalance());
        accountRepository.update(existing);
    }

    public void deleteAccount(Long id) {
        getAccountById(id);
        accountRepository.deleteById(id);
    }
}
