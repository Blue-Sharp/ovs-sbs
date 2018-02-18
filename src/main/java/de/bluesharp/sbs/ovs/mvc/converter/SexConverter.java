package de.bluesharp.sbs.ovs.mvc.converter;

import de.bluesharp.sbs.ovs.model.Sex;

import javax.faces.convert.EnumConverter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "SexConverter")
public class SexConverter extends EnumConverter {

    public SexConverter() {
        super(Sex.class);
    }
}
