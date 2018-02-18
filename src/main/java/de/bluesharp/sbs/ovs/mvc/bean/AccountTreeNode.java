package de.bluesharp.sbs.ovs.mvc.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@AllArgsConstructor
@Data
public class AccountTreeNode implements Serializable {
    private String name;
}
