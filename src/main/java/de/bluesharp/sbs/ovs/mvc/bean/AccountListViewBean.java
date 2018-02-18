package de.bluesharp.sbs.ovs.mvc.bean;

import de.bluesharp.sbs.ovs.model.Sex;
import de.bluesharp.sbs.ovs.model.Account;
import de.bluesharp.sbs.ovs.service.AccountService;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("CdiManagedBeanInconsistencyInspection")
@Component
@ViewScoped
@Data
@Slf4j
public class AccountListViewBean implements Serializable {

    private List<Account> accounts;

    private List<Account> filteredAccounts;
    private Account selectedAccount;

    private List<Sex> sexes;

    @Getter(value = AccessLevel.NONE)
    private final AccountService accountService;

    @Autowired
    public AccountListViewBean(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostConstruct
    public void init() {
        accounts = new ArrayList<>(accountService.getAccounts());
        sexes = EnumUtils.getEnumList(Sex.class);
    }

    public static String getMessageKey(@NotNull Sex sex) {
        return String.format("user.%s.%s", sex.getClass().getSimpleName().toLowerCase(), sex.name().toLowerCase());
    }
}
