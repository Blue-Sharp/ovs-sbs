package de.bluesharp.sbs.ovs.mvc.bean;

import de.bluesharp.sbs.ovs.model.Account;
import de.bluesharp.sbs.ovs.model.Sex;
import de.bluesharp.sbs.ovs.service.AccountService;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.omnifaces.util.Exceptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.FacesException;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Locale;

import static org.omnifaces.util.Exceptions.unwrap;

@SuppressWarnings("CdiManagedBeanInconsistencyInspection")
@Component
@ViewScoped
@Data
@Slf4j
public class AccountEditViewBean implements UserSexI18nSupportBean, Serializable {
    private Account account;

    private Sex[] sexes = {Sex.MALE, Sex.FEMALE};
    private Locale[] locales = {Locale.GERMANY};

    @Getter(value = AccessLevel.NONE)
    private final AccountService accountService;

    @Autowired
    public AccountEditViewBean(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostConstruct
    private void init() {
        //account = Account.builder().build();
        account = Account.builder()
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
                .build();
    }

    public void createAccount() {
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
