package de.bluesharp.sbs.ovs.mvc.bean;

import de.bluesharp.sbs.ovs.model.Account;
import de.bluesharp.sbs.ovs.model.Sex;
import de.bluesharp.sbs.ovs.service.AccountService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    private Account account;

    public void setAccount(Account account) {
        if (Objects.isNull(account)) {
            this.account = Account.builder().build();
        } else {
            this.account = account;
        }
    }

    private Sex[] sexes = {Sex.MALE, Sex.FEMALE};
    private Locale[] locales = {Locale.GERMANY};

    private final AccountService accountService;

    @Autowired
    public AccountEditViewBean(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostConstruct
    private void init() {
        this.account = Account.builder().build();
/*        account = Account.builder()
                .userName("pirat")
                .firstName("Rafael")
                .lastName("Kansy")
                .sex(Sex.MALE)
                .birthday(LocalDate.of(1983, 06, 20))
                .birthplace("Krappitz")
                .email("rafael.kansy@xxx-xxx.de")
                .phone("+49 160 xx xx xx xx")
                .locale(Locale.GERMANY)
                .zip("80939")
                .city("München")
                .street("XXX XXX xx")
                .build();*/
    }

    public void saveAccount() {
        accountService.save(this.account);
    }

    public String getMessageKey(@Nullable Locale locale) {
        if (locale != null) {
            return locale.getDisplayCountry();
        } else {
            return "user.locale.unset";
        }
    }
}
