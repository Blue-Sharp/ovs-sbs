package de.bluesharp.sbs.ovs.mvc.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.util.Arrays;
import java.util.Locale;

@FacesConverter("de.bluesharp.sbs.ovs.LocaleConverter")
public class LocaleConverter implements Converter<Locale> {
    @Override
    public Locale getAsObject(FacesContext context, UIComponent component, String value) {
        return Arrays.stream(Locale.getAvailableLocales())
                .filter(locale -> value.equals(locale.getDisplayCountry()))
                .findFirst()
                .orElse(null);
    }


    @Override
    public String getAsString(FacesContext context, UIComponent component, Locale value) {
        return value.getDisplayCountry();
    }
}
