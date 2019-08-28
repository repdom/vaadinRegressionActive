package com.dany.dany.config;

import com.dany.dany.main.Login;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.server.ServiceInitEvent;
import com.vaadin.flow.server.VaadinServiceInitListener;
import org.springframework.stereotype.Component;

@Component
public class ConfigureUIServiceInitListener implements VaadinServiceInitListener {

    @Override
    public void serviceInit(ServiceInitEvent event) {
        event.getSource().addUIInitListener(uiEvent -> {
            final UI ui = uiEvent.getUI();
            ui.addBeforeEnterListener(this::beforeEnter); //
        });
    }
    private void beforeEnter(BeforeEnterEvent event) {
        if (!Login.class.equals(event.getNavigationTarget()) //
                && !SecurityUtils.isUserLoggedIn()) { //
            event.rerouteTo(Login.class); //
        }
    }

}
