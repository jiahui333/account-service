package com.example.controller;

import com.example.dto.AccountCreationRequest;
import com.example.entity.Account;
import com.example.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping
    public ResponseEntity<Account> createAccount(@Valid @RequestBody AccountCreationRequest accountCreationRequest) {
        Account account = accountService.createAccount(accountCreationRequest.getName(), accountCreationRequest.getEmail(), accountCreationRequest.getOpeningAmount()) ;
        return ResponseEntity.status(HttpStatus.CREATED).body(account);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccount(@PathVariable Long id) {
        Account account=accountService.getAccountById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found"));
        return ResponseEntity.ok(account);
    }

    @PutMapping("/{id}/balance")
    public ResponseEntity<Void> updateBalance(@PathVariable Long id, @RequestBody BigDecimal newBalance) {
        Account account = accountService.getAccountById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found"));
        account.setBalance(newBalance);
        accountService.updateAccount(account);
        return ResponseEntity.noContent().build();
    }
}
