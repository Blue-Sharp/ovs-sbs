package de.bluesharp.sbs.ovs.repository;

import de.bluesharp.sbs.ovs.model.Account;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.Set;

public interface AccountRepository extends PagingAndSortingRepository<Account, Long>, QueryByExampleExecutor<Account> {
    Set<Account> findByChairmanIsNotNull();
}
