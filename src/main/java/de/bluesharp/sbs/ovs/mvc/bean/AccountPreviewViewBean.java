package de.bluesharp.sbs.ovs.mvc.bean;

import de.bluesharp.sbs.ovs.model.Account;
import de.bluesharp.sbs.ovs.model.Sex;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import javax.faces.view.ViewScoped;
import java.io.Serializable;

@SuppressWarnings("CdiManagedBeanInconsistencyInspection")
@Component
@ViewScoped
@Data
@Slf4j
public class AccountPreviewViewBean implements Serializable {
    private Account account;

    public static String getMessageKey(@Nullable Sex sex) {
        if (sex != null) {
            switch (sex) {
                case MALE: {
                    return "user.sex.male";
                }
                case FEMALE: {
                    return "user.sex.female";
                }
                default: {
                    return "user.sex.unset";
                }
            }
        } else {
            return "user.sex.unset";
        }
    }
}
