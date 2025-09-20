package com.springboot.crud_operation_banking_project.controller;

import com.springboot.crud_operation_banking_project.model.Account;
import com.springboot.crud_operation_banking_project.repository.AccountRepository;
import com.springboot.crud_operation_banking_project.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api")
public class AccountController {

    private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/accounts")
    public ResponseEntity<List<Account>> getAllAccounts() {
        return ResponseEntity.ok(accountService.getAllAccounts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccount(@PathVariable Long id) {
        return ResponseEntity.ok(accountService.getAccountById(id));
    }

    @PostMapping
    public ResponseEntity<String> createAccount(@RequestBody Account account) {
        accountService.createAccount(account);
        return ResponseEntity.ok("Account created successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateAccount(@PathVariable Long id, @RequestBody Account account) {
        accountService.updateAccount(id, account);
        return ResponseEntity.ok("Account updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return ResponseEntity.ok("Account deleted successfully");
    }
}
