package com.springboot.crud_operation_banking_project.repository;

import com.springboot.crud_operation_banking_project.model.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AccountRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<Account> rowMapper = (rs, rowNum) -> {
        Account account = new Account();
        account.setAccountId(rs.getLong("accountId"));
        account.setAccountHolderName(rs.getString("accountHolderName"));
        account.setAccountType(rs.getString("accountType"));
        account.setBalance(rs.getDouble("balance"));
        return account;
    };

    public List<Account> findAll(){
        String sql = "SELECT * FROM ACCOUNT";
        return jdbcTemplate.query(sql, rowMapper);
    }

    public Optional<Account> findById(Long id) {
        String sql = "SELECT * FROM ACCOUNT WHERE ACCOUNT_ID = ?";
        return jdbcTemplate.query(sql, new Object[]{id}, rowMapper).stream().findFirst();
    }

    public int save(Account account) {
        String sql = "INSERT INTO ACCOUNT (ACCOUNT_HOLDER_NAME, ACCOUNT_TYPE, BALANCE) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, account.getAccountHolderName(), account.getAccountType(), account.getBalance());
    }

    public int update(Account account) {
        String sql = "UPDATE ACCOUNT SET ACCOUNT_HOLDER_NAME = ?, ACCOUNT_TYPE = ?, BALANCE = ? WHERE ACCOUNT_ID = ?";
        return jdbcTemplate.update(sql, account.getAccountHolderName(), account.getAccountType(), account.getBalance(), account.getAccountId());
    }

    public int deleteById(Long id) {
        String sql = "DELETE FROM ACCOUNT WHERE ACCOUNT_ID = ?";
        return jdbcTemplate.update(sql, id);
    }
}
