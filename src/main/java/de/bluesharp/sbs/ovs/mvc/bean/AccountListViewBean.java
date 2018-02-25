package de.bluesharp.sbs.ovs.mvc.bean;

import de.bluesharp.sbs.ovs.model.Account;
import de.bluesharp.sbs.ovs.service.AccountService;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
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

    @Getter(value = AccessLevel.NONE)
    private final AccountService accountService;

    private final AccountPreviewViewBean accountPreviewViewBean;

    @Autowired
    public AccountListViewBean(AccountService accountService, AccountPreviewViewBean accountPreviewViewBean) {
        this.accountService = accountService;
        this.accountPreviewViewBean = accountPreviewViewBean;
    }

    @PostConstruct
    public void init() {
        accounts = new ArrayList<>(accountService.getAccounts());
    }

    public void onRowSelect() {
        accountPreviewViewBean.setAccount(selectedAccount);
    }
}
