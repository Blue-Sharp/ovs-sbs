package de.bluesharp.sbs.ovs.mvc.bean;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.validation.constraints.NotNull;
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
    public void init() {
        authentication = SecurityContextHolder.getContext().getAuthentication();
    }

    public static String getMessageKey(@NotNull String authority) {
        return String.format("authentication.authorities.%s", authority.toLowerCase());
    }
}
