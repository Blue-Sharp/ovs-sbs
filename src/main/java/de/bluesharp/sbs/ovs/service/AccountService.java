package de.bluesharp.sbs.ovs.service;

import de.bluesharp.sbs.ovs.model.Account;
import de.bluesharp.sbs.ovs.repository.AccountRepository;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AccountService {

    private AccountRepository repository;

    @Autowired
    public AccountService(AccountRepository repository) {
        this.repository = repository;
    }

    public Set<Account> getAccounts() {
        val result = new HashSet<Account>();

        repository.findAll().forEach(result::add);
        return result;
    }

    public Set<Account> getChairman() {
        return new HashSet<>(repository.findByChairmanIsNotNull());
    }

    public long countByExample(Account example) {
        return repository.count(Example.of(example));
    }

    public Account save(Account account) {
        return repository.save(account);
    }
}
