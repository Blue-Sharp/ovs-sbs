package de.bluesharp.sbs.ovs.mvc.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@SuppressWarnings("WeakerAccess")
@AllArgsConstructor
@Data
public class AccountTreeNode implements Serializable {
    enum AccountTreeNodeType {
        ALL,
        CHAIRMAN,
        MANAGER,
        STUDENT,
        PARTNER,
        NONE
    }

    private String name;
    private AccountTreeNodeType type;
}
