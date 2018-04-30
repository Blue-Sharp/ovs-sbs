package de.bluesharp.sbs.ovs.mvc.bean;

import de.bluesharp.sbs.ovs.model.Sex;
import org.springframework.lang.Nullable;

public interface UserSexI18nSupportBean {

    default String getMessageKey(@Nullable Sex sex) {
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
