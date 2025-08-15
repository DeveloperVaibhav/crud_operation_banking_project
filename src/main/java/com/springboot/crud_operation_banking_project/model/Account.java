package com.springboot.crud_operation_banking_project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    private Long accountId;
    private String accountHolderName;
    private String accountType;
    private Double balance;
}
