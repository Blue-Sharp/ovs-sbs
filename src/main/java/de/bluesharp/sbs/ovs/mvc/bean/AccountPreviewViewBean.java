package de.bluesharp.sbs.ovs.mvc.bean;

import de.bluesharp.sbs.ovs.model.Account;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.faces.view.ViewScoped;
import java.io.Serializable;

@SuppressWarnings("CdiManagedBeanInconsistencyInspection")
@Component
@ViewScoped
@Data
@Slf4j
public class AccountPreviewViewBean implements UserSexI18nSupportBean, Serializable {
    private Account account;
}
