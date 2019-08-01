package com.dany.dany.main;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.router.RouterLink;

@Push
@Route("")
public class MainLayout extends HorizontalLayout implements RouterLayout {
    Div contenidoDentro;

    public MainLayout() {
        VerticalLayout menu = crearMenu();

        LoginForm component = new LoginForm();
        menu.setWidth("30%");
        menu.setHeight("100%");
        /*component.addLoginListener(e -> {
           try {
               // ResponseEntity<?> response =
               UI.getCurrent().getPage().executeJavaScript("window.location.replace('/dashboard');");
               UI.getCurrent().navigate(Dashboard.class);
           } catch (Exception ex) {
               component.setError(true);
           }
        });
        //String jwt = (String) VaadinSession.getCurrent().getAttribute("JSESSIONID");
        String jwt = (String) VaadinSession.getCurrent().getSession().getId();
        System.out.println(jwt);*/

        //if (jwt == null) {
            menu.add(component);
        //} else {
            menu.remove(component);
            // UI.getCurrent().getPage().executeJavaScript("window.location.replace('/dashboard');");
        //}
        contenidoDentro = new Div();
        contenidoDentro.setWidth("70%");
        contenidoDentro.setHeight("100%");

        // contenidoDentro.add(new Label("Probando Papolo"));

        setSizeFull();
        add(menu, contenidoDentro);
    }

    private VerticalLayout crearMenu() {
        VerticalLayout v = new VerticalLayout();

        v.add(new Label("Administradora de dispositivos Dany"));
        v.add(new RouterLink("Dashboard", Dashboard.class));
        v.add(new RouterLink("Dispositivos", VistaCrud.class));
        v.add(new RouterLink("Usuarios", VistaUsuario.class));
        // v.add(listBox);
        // v.add(new )

        return v;
    }
}
