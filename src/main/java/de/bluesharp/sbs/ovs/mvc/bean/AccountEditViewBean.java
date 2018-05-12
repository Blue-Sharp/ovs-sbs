package de.bluesharp.sbs.ovs.mvc.bean;

import de.bluesharp.sbs.ovs.model.Account;
import de.bluesharp.sbs.ovs.model.Sex;
import de.bluesharp.sbs.ovs.service.AccountService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.omnifaces.util.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Locale;
import java.util.Objects;

@SuppressWarnings("CdiManagedBeanInconsistencyInspection")
@Component
@SessionScoped
@Data
@Slf4j
public class AccountEditViewBean implements UserSexI18nSupportBean, Serializable {

    private final AccountService accountService;

    private Account account;

    private Sex[] sexes = {Sex.MALE, Sex.FEMALE};
    private Locale[] locales = {Locale.GERMANY};

    @Autowired
    public AccountEditViewBean(AccountService accountService, Messages.Resolver resolver) {
        this.accountService = accountService;

        Messages.setResolver(resolver);
    }

    @PostConstruct
    private void init() {
        this.account = Account.builder().build();
    }

    public void setAccount(Account account) {
        if (Objects.isNull(account)) {
            this.account = Account.builder().build();
            init();
        } else {
            this.account = account;
        }
    }

    public String saveAccount() {
        long example = accountService.countByExample(this.account);
        if (example == 0) {
            try {
                this.account = accountService.save(this.account);
                return "accounts-browse";
            } catch (DataIntegrityViolationException e) {
                Messages.addGlobalError("action.save.error.DataIntegrityViolationException");
            }
        } else {
            Messages.addGlobalError("action.save.error.DataIntegrityViolationException");
        }
        return null;
    }

    public String getMessageKey(@Nullable Locale locale) {
        if (locale != null) {
            return locale.getDisplayCountry();
        } else {
            return "user.locale.unset";
        }
    }
}
