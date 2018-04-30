package de.bluesharp.sbs.ovs.mvc.bean;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;

@SuppressWarnings("CdiManagedBeanInconsistencyInspection")
@Component
@ViewScoped
@Data
@Slf4j
public class UserInfoViewBean implements Serializable {
    private Authentication authentication;

    @Getter(value = AccessLevel.NONE)
    private final UserDetailsService userDetailsService;

    @Autowired
    public UserInfoViewBean(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @PostConstruct
    private void init() {
        authentication = SecurityContextHolder.getContext().getAuthentication();
    }

    public static String getMessageKey(@Nullable String authority) {
        if (!StringUtils.isEmpty(authority)) {
            switch (authority) {
                case "ADMIN": {
                    return "authentication.authorities.admin";
                }
                case "CHAIRMAN": {
                    return "authentication.authorities.chairman";
                }
                case "MANAGER": {
                    return "authentication.authorities.manager";
                }
                case "STUDENT": {
                    return "authentication.authorities.student";
                }
                case "PARTNER": {
                    return "authentication.authorities.partner";
                }
                default: {
                    String msg = String.format("Authority has invalid value: %s", authority);
                    throw new IllegalArgumentException(msg);
                }
            }
        }else {
            return "authentication.authorities.none";
        }
    }
}
