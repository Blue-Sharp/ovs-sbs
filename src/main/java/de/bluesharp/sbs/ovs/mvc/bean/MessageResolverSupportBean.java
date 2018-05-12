package de.bluesharp.sbs.ovs.mvc.bean;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.springframework.stereotype.Component;

import javax.enterprise.context.ApplicationScoped;
import java.text.MessageFormat;
import java.util.ResourceBundle;

@ApplicationScoped
@Component
public class MessageResolverSupportBean implements Messages.Resolver {

    private static final String BASE_NAME = "de.blue-sharp.sbs.ovs.mvc.messages";

    public String getMessage(String message, Object... params) {
        ResourceBundle bundle = ResourceBundle.getBundle(BASE_NAME, Faces.getLocale());
        if (bundle.containsKey(message)) {
            message = bundle.getString(message);
        }
        return MessageFormat.format(message, params);
    }
}
